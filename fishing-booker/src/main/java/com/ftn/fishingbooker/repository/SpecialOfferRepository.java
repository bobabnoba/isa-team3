package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {


    @Query(value = "select so.*\n" +
            "    from adventure_special_offers as aso\n" +
            "    join special_offer as so on aso.special_offers_id = so.id\n" +
            "    where adventure_id = 112 and is_used = false\n" +
            "    and current_date > active_from and current_date <active_to", nativeQuery = true)
    Collection<SpecialOffer> getAvailableOffersForAdventure(Long adventureId);
}
