package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.model.*;

public interface InstructorService {
    User registerInstructor(Instructor mapToInstructor, String motivation);
}
