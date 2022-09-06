package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.dao.SpecialOfferCalendarInfo;
import com.ftn.fishingbooker.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {


    @Query(value = "select so.*\n" +
            "    from adventure_special_offers as aso\n" +
            "    join special_offer as so on aso.special_offers_id = so.id\n" +
            "    where adventure_id = 112 and is_used = false\n" +
            "    and current_date > active_from and current_date <active_to", nativeQuery = true)
    Collection<SpecialOffer> getAvailableOffersForAdventure(Long adventureId);

    @Query( "    select so.activeFrom as activeFrom, so.activeTo as activeTo, " +
            "    so.reservationStartDate as reservationStartDate, so.reservationEndDate as reservationEndDate, " +
            "    a.title as title " +
            "    from Adventure a " +
            "    join a.specialOffers so " +
            "    where a.instructor.id = :id and so.isUsed = false")
    Collection<SpecialOfferCalendarInfo> getAllOffersForInstructor(Long id);

    @Query( "    select so " +
            "    from Boat b " +
            "    join b.specialOffers so " +
            "    where b.boatOwner.id = :id and so.isUsed = false and so.isCaptain = true")
    Collection<SpecialOffer> getAllCaptainOffersForBoatOwner(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    SpecialOffer findLockedById(Long offerId);
}
