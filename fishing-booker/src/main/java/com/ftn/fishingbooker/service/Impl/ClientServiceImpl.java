package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.User;
import com.ftn.fishingbooker.service.DateService;
import com.ftn.fishingbooker.service.RegistrationService;
import com.ftn.fishingbooker.repository.UserRepository;
import com.ftn.fishingbooker.service.ClientService;
import com.ftn.fishingbooker.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private DateService dateService;

    @Override
    public void registerClient(Client client) throws MessagingException {
        userRepository.save(client);
        String registrationToken = registrationService.generateRegistrationToken(client.getEmail(), client.getRole().getName());
        registrationService.sendRegistrationEmail(registrationToken, client.getEmail());
    }

    @Override
    public boolean hasOverlappingReservation(Long clientId, Date startDate, Date endDate) {
        User client = userRepository.getById(clientId);
        if (client != null) {
            for (Reservation reservation : reservationService.findAllForClient(clientId)) {
                if (dateService.doPeriodsOverlap(reservation.getStartDate(), reservation.getEndDate(), startDate, endDate))
                    return true;
            }
        }

        return false;
    }

}

