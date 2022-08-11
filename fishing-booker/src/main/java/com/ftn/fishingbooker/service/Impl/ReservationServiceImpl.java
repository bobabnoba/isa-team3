package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.repository.ReservationRepository;
import com.ftn.fishingbooker.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Collection<Reservation> findAllForClient(Long clientId) {
        return reservationRepository.findAllForClient(clientId);
    }

    @Override
    public Collection<Reservation> getReservationForVacationHome(Long homeId) {
        return reservationRepository.getReservationForVacationHome(homeId);
    }

    @Override
    public Collection<Reservation> getReservationsForClient(Long clientId) {
        return reservationRepository.findAllForClient(clientId);
    }
}
