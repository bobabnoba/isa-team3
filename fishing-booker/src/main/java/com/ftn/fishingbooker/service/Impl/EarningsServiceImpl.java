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
    public void saveEarnings(Reservation reservation, String email, UserRank advertiserRank) {
        double appAmount = reservation.getPrice() - (reservation.getPrice() * advertiserRank.getReservationPercentage()/100);
        Earnings appEarnings = new Earnings(reservation, appAmount, email, true);
        earningsRepository.save(appEarnings);

        double advertiserAmount = reservation.getPrice() * advertiserRank.getReservationPercentage()/100;
        Earnings advertiserEarnings = new Earnings(reservation, advertiserAmount, email, false);
        earningsRepository.save(advertiserEarnings);
    }

    @Override
    public void deleteEarnings(Reservation reservation) {
        Collection<Earnings> earnings = earningsRepository.getByReservationId(reservation.getId());
        if(earnings != null){
            if(reservation.getCancelingPercentage() > 0) {
                double cancelingAmount = reservation.getPrice() * reservation.getCancelingPercentage()/100;
                Earnings cancelingAdvEarnings = new Earnings(reservation, cancelingAmount, earnings.stream().findFirst().get().getAdvertiserEmail(), false);
                earningsRepository.save(cancelingAdvEarnings);
            }
            earningsRepository.deleteAll(earnings);
        }
    }
}
