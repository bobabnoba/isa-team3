package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.model.*;
import com.ftn.fishingbooker.repository.ClientRepository;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private DateService dateService;
    @Autowired
    private UserRankService userRankService;
    @Autowired
    private EarningsService earningsService;
    @Autowired
    private EmailService emailService;


    @Override
    public void registerClient(Client client) throws MessagingException {
        userRepository.save(client);
        String registrationToken = registrationService.generateRegistrationToken(client.getEmail(), client.getRole().getName());
        registrationService.sendRegistrationEmail(registrationToken, client.getEmail());
    }

    @Override
    public boolean hasOverlappingReservation(String email, Date startDate, Date endDate) {
        User client = userRepository.findByEmail(email);
        if (client != null) {
            Collection<Reservation> clientReservations = reservationService.findAllForClient(client.getId());
            if (clientReservations == null) {
                return false;
            }

            for (Reservation reservation : clientReservations) {
                if (dateService.doPeriodsOverlap(reservation.getStartDate(), reservation.getEndDate(), startDate, endDate))
                    return true;
            }
        }

        return false;
    }

    @Override
    public Client getClientByEmail(String userEmail) {
        return clientRepository.findByEmail(userEmail);
    }

    @Override
    public void updatePoints(Client client, double reservationPrice) {
        client.setPoints(client.getPoints() + reservationPrice * client.getRank().getReservationPercentage() / 100);

        userRankService.getLoyaltyProgram().forEach(rank -> {
            if (rank.getName().contains("CLIENT") && rank.getMinPoints() < client.getPoints()) {
                client.setRank(rank);
            }
        });
        userRepository.save(client);
    }

    @Override
    public void addPenalty(String email) {
        Client client = getClientByEmail(email);
        client.setNoOfPenalties(client.getNoOfPenalties() + 1);
        userRepository.save(client);
    }

    @Override
    public List<Reservation> getUpcomingReservations(String email) {
        List<Reservation> reservationList = new ArrayList<>();
        Client client = getClientByEmail(email);
        Date now = new Date();
        Collection<Reservation> reservations = client.getReservationsMade();

        for (Reservation reservation : reservations) {
            if (reservation.getIsCancelled() == false && (reservation.getStartDate().after(now)) || reservation.getStartDate().equals(now)) {
                reservationList.add(reservation);
            }
        }
        return reservationList;
    }

    @Override
    public List<Reservation> getPastReservations(String email, ReservationType reservationType) {
        List<Reservation> reservationList = new ArrayList<>();
        Client client = getClientByEmail(email);
        Date now = new Date();
        Collection<Reservation> reservations = client.getReservationsMade();

        for (Reservation reservation : reservations) {
            if (reservation.getIsCancelled() == false && (reservation.getStartDate().before(now)) &&
                    reservation.getType() == reservationType) {
                reservationList.add(reservation);
            }
        }
        return reservationList;
    }

    @Override
    public Collection<Boat> getBoatSubscription(String clientEmail) {
        Client client = getClientByEmail(clientEmail);
        return client.getBoatSubscription();
    }

    @Override
    public Collection<VacationHome> getVacationHomeSubscription(String clientEmail) {
        Client client = getClientByEmail(clientEmail);
        return client.getVacationHomeSubscription();
    }

    @Override
    public Collection<Instructor> getInstructorSubscription(String clientEmail) {
        Client client = getClientByEmail(clientEmail);
        return client.getInstructorSubscription();
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public boolean isClientSubscribed(String clientEmail, String entityType, Long entityId) {
        Client client = clientRepository.findByEmail(clientEmail);
        switch (entityType) {
            case "home": {
                return clientRepository.vacationHomeSubscriptionExists(client.getId(), entityId);
            }
            case "instructor": {
                return clientRepository.instructorHomeSubscriptionExists(client.getId(), entityId);
            }
            case "boat": {
                return clientRepository.boatSubscriptionExists(client.getId(), entityId);
            }

        }
        return false;
    }

    @Override
    public Collection<Client> getAll() {
        return clientRepository.findAllByDeleted(false);
    }

    @Override
    public void emailSubscribers(User owner, String entityType){
        Collection<Client> clients = getAll();
        clients.forEach(client -> {
            if(isClientSubscribed(client.getEmail(), entityType, owner.getId())){
                String content = emailService.createSubscriptionEmail(owner.getFirstName(), owner.getLastName());
                try {
                    emailService.sendEmail(client.getEmail(), "New Special Offer At Easy&Peasy Booker", content);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean cancelUpcomingReservation(Long reservationId, String userEmail) {
        Client client = (Client) userRepository.findByEmail(userEmail);
        for (Reservation reservation : client.getReservationsMade()) {
           var id = reservation.getId();
            if (id == reservationId) {
                if (canBeCanceled(reservation)) {
                    reservation.setIsCancelled(true);
                    earningsService.deleteEarnings(reservation);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean canBeCanceled(Reservation reservation) {
        Date inThreeDays = dateService.addDaysToJavaUtilDate(new Date(), 3);

        if (inThreeDays.before(reservation.getStartDate())) {
            return true;
        }
        return false;
    }

}

