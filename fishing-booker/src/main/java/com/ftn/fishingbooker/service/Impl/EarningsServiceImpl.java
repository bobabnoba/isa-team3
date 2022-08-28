package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Earnings;
import com.ftn.fishingbooker.model.Reservation;
import com.ftn.fishingbooker.model.UserRank;
import com.ftn.fishingbooker.repository.EarningsRepository;
import com.ftn.fishingbooker.service.EarningsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class EarningsServiceImpl implements EarningsService {

    private final EarningsRepository earningsRepository;

    public EarningsServiceImpl(EarningsRepository earningsRepository) {
        this.earningsRepository = earningsRepository;
    }

    @Override
    public Collection<Earnings> getAllInDateRange(Date from, Date to) {
        return earningsRepository.getAllInDateRange(from, to);
    }

    @Override
    public Collection<Earnings> getAllForAdvertiserInDateRange(Date from, Date to, String email) {
        return earningsRepository.getAdvertisersInDateRange(from, to, email);
    }

    @Override
    public void saveApplicationEarnings(Reservation reservation, String email, UserRank advertiserRank) {
        double amount = reservation.getPrice() - (reservation.getPrice() * advertiserRank.getReservationPercentage()/100);
        Earnings earnings = new Earnings(reservation, amount, email);
        earningsRepository.save(earnings);
    }

    @Override
    public void deleteApplicationEarnings(Long reservationId) {
        Earnings earnings = earningsRepository.getByReservationId(reservationId);
        if(earnings != null){
            earningsRepository.delete(earnings);
        }
    }
}
