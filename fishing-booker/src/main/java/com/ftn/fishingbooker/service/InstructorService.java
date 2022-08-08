package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.InstructorAvailability;
import com.ftn.fishingbooker.model.User;

import javax.mail.MessagingException;
import java.util.Collection;

public interface InstructorService {

    User register(Instructor instructor, String motivation) throws MessagingException;

    InstructorAvailability addAvailabilityPeriod(InstructorAvailability availability, String email);

    Instructor getWithAvailability(String email);
}
