package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Complaint;

import java.util.Collection;

public interface ComplaintService {

    Boolean checkForComplaint(Long reservationId);

    Complaint makeAdventureComplaint(Complaint complaint);

    Complaint makeBoatComplaint(Complaint complaint);

    Complaint makeVacationHomeComplaint(Complaint complaint);

    Collection<Complaint> getAllPendingComplaints();

    void addAdminResponse(Long id, String response);
}
