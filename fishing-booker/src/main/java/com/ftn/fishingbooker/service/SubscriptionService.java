package com.ftn.fishingbooker.service;

public interface SubscriptionService {

    void subscribe(String email, String entityType, Long entityId);

    void unsubscribe(String email, String entityType, Long entityId);
}
