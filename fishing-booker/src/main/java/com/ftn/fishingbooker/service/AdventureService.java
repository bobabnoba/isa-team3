package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Adventure;
import java.util.*;

public interface AdventureService {

    Collection<Adventure> getAll();

    void addImage(Long adventureId, String fileName);

    Adventure addAdventure(Adventure adventure, String instructorEmail);

    Adventure getById(Long id);

    Adventure updateAdventureInfo(Long id, Adventure adventure);
}
