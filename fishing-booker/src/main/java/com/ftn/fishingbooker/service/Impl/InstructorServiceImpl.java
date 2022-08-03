package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    @Override
    public ArrayList<Instructor> getAll() {
        return new ArrayList<Instructor>();
    }
}
