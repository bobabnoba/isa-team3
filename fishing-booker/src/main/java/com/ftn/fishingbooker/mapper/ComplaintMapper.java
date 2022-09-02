package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.ComplaintDto;
import com.ftn.fishingbooker.model.Complaint;

public class ComplaintMapper {


    public static Complaint map(ComplaintDto complaintDto) {

        Complaint complaint = new Complaint();
        complaint.setComplaint(complaintDto.getComplaint());
        complaint.setReservationId(complaintDto.getReservationId());
        complaint.setReservationType(complaintDto.getReservationType());
        return complaint;
    }
}
