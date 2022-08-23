package com.ftn.fishingbooker.dto;

public class AdminReportResponseDto {

    Long reportId;
    String response;
    String ownerEmail;
    String clientEmail;
    boolean penalty;

    public boolean isPenalty() {
        return penalty;
    }

    public Long getReportId() {
        return reportId;
    }

    public String getResponse() {
        return response;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getClientEmail() {
        return clientEmail;
    }
}
