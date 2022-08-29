package com.ftn.fishingbooker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.fishingbooker.enumeration.ReservationType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Collection;
import java.util.Date;

@Data
public class SpecialOfferDto {

    public Long id;

    public double discount;

    public double price;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Europe/Belgrade")
    public Date activeFrom;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Europe/Belgrade")
    public Date activeTo;

    @Enumerated(EnumType.STRING)
    public ReservationType type;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Europe/Belgrade")
    public Date reservationStartDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone="Europe/Belgrade")
    public Date reservationEndDate;

    public int guests;

    public Collection<UtilityDto> utilities;

    public double cancelingPercentage;

    public boolean isCaptain;
}
