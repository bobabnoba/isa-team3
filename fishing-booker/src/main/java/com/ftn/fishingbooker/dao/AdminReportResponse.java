package com.ftn.fishingbooker.dao;

public class AdminReportResponse {

    Long reportId;
    String response;
    String ownerEmail;
    String clientEmail;
    boolean addPenalty;

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setAddPenalty(boolean addPenalty) {
        this.addPenalty = addPenalty;
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

    public boolean addPenalty() {
        return addPenalty;
    }
}
