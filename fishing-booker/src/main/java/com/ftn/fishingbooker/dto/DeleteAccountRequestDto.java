package com.ftn.fishingbooker.dto;

public class DeleteAccountRequestDto {

    public String email;
    public String motivation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

}
