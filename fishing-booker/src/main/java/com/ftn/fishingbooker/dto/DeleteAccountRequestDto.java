package com.ftn.fishingbooker.dto;

public class DeleteAccountRequestDto {

    public String email;
    public String explanation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

}
