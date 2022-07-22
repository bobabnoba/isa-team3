package com.ftn.fishingbooker.repository;


import com.ftn.fishingbooker.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.util.*;

@Transactional
@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {

    @Query(value = "select id, content_type, data, name, size, vacation_home_id from file_entity u where u.vacation_home_id = ?1", nativeQuery = true)
    List<FileEntity> getAllByVacationHomeId(Long homeId);
}
