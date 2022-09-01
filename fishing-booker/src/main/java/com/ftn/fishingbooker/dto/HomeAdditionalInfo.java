package com.ftn.fishingbooker.dto;

import com.ftn.fishingbooker.model.*;
import lombok.*;

import java.util.*;

@Data
public class HomeAdditionalInfo {
    public Collection<Utility> utilities;
    public Collection<RoomDto> rooms;
}
