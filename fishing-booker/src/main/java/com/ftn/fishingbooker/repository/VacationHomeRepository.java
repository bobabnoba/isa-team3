package com.ftn.fishingbooker.repository;

import com.ftn.fishingbooker.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.transaction.*;

@Transactional
@Repository
public interface VacationHomeRepository extends JpaRepository<VacationHome,Long> {

    VacationHome getById(Long id);
}
