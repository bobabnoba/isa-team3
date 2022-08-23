package com.ftn.fishingbooker.dao;

public interface DatabaseReport {

    Long getId();

    String getComment();

    Boolean getClientShowedUp();

    String getClientEmail();

    Long getInstructorId();

    Long getBoatOwnerId();

    Long getHomeOwnerId();

    Boolean getPenaltySuggested();

    String getType();
}
