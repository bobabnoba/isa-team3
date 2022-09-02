package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Complaint;

public interface ComplaintService {

    Boolean checkForComplaint(Long reservationId);

    Complaint makeAdventureComplaint(Complaint complaint);

    Complaint makeBoatComplaint(Complaint complaint);

    Complaint makeVacationHomeComplaint(Complaint complaint);
}
