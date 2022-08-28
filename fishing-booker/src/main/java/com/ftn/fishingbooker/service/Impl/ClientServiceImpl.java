package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.enumeration.ReservationType;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.User;
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
    public boolean cancelUpcomingReservation(Long reservationId, String userEmail) {
        Client client = (Client) userRepository.findByEmail(userEmail);
        for (Reservation reservation : client.getReservationsMade()) {
            if (reservation.getId() == reservationId) {
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

