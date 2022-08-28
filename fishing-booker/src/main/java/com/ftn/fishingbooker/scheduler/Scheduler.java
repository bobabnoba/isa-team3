package com.ftn.fishingbooker.scheduler;

import com.ftn.fishingbooker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final UserService userService;

    @Scheduled(cron = "0 0 0 1 1/1 *")
    public void resetAllPenalties() {
        userService.resetAllPenalties();
    }

}
