package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.User;

import javax.mail.MessagingException;

public interface InstructorService {

    User register(Instructor instructor, String motivation) throws MessagingException;
}
