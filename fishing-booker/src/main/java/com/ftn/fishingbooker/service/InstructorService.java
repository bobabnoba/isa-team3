package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

import javax.mail.MessagingException;

public interface InstructorService {
    User registerInstructor(Instructor instructor, String motivation) throws MessagingException;
}
