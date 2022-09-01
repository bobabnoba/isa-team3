package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Boat;
import com.ftn.fishingbooker.model.Client;
import com.ftn.fishingbooker.model.Instructor;
import com.ftn.fishingbooker.model.VacationHome;
import com.ftn.fishingbooker.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    final private ClientService clientService;
    final private HomeService homeService;
    final private BoatService boatService;
    final private InstructorService instructorService;

    @Override
    public void subscribe(String email, String entityType, Long entityId) {
        Client client = clientService.getClientByEmail(email);
        if (client != null) {
            switch (entityType) {
                case "home": {
                    VacationHome vacationHome = homeService.getById(entityId);
                    if (vacationHome != null) {
                        client.getVacationHomeSubscription().add(vacationHome);
                        clientService.save(client);
                    }
                    break;
                }
                case "boat": {
                    Boat boat = boatService.getById(entityId);
                    if (boat != null) {
                        client.getBoatSubscription().add(boat);
                        clientService.save(client);
                    }
                    break;
                }
                case "instructor": {
                    Instructor instructor = instructorService.findById(entityId);
                    if (instructor != null) {
                        client.getInstructorSubscription().add(instructor);
                        clientService.save(client);
                    }
                    break;
                }

            }
        }
    }

    @Override
    public void unsubscribe(String email, String entityType, Long entityId) {
        Client client = clientService.getClientByEmail(email);
        if (client != null) {
            switch (entityType) {
                case "home": {
                    for (VacationHome home : client.getVacationHomeSubscription()) {
                        if (home.getId() == entityId) {
                            client.getVacationHomeSubscription().remove(home);
                            clientService.save(client);
                            break;
                        }
                    }
                    break;
                }
                case "boat": {
                    for (Boat boat : client.getBoatSubscription()) {
                        if (boat.getId() == entityId) {
                            client.getBoatSubscription().remove(boat);
                            clientService.save(client);
                            break;
                        }
                    }
                    break;
                }
                case "instructor": {
                    for (Instructor instructor : client.getInstructorSubscription()) {
                        if (instructor.getId() == entityId) {
                            client.getInstructorSubscription().remove(instructor);
                            clientService.save(client);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }


}