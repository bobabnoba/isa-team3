package com.ftn.fishingbooker.projection;

public interface DatabaseReport {

    Long getId();

    String getComment();

    Boolean getClientShowedUp();

    String getClientEmail();

    Long getInstructorId();

    Long getBoatOwnerId();

    Long getHomeOwnerId();

    String getType();
}
