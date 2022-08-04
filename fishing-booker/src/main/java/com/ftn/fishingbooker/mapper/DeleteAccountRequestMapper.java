package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.DeleteAccountRequestDto;
import com.ftn.fishingbooker.dto.DeleteAccountResponse;
import com.ftn.fishingbooker.model.DeleteAccountRequest;

public class DeleteAccountRequestMapper {

    public static DeleteAccountRequest mapToEntity(DeleteAccountRequestDto dto) {
        DeleteAccountRequest request = new DeleteAccountRequest();
        request.setEmail(dto.getEmail());
        request.setExplanation(dto.getMotivation());
        request.setApproved(false);
        return request;
    }

    public static DeleteAccountResponse mapToDto(DeleteAccountRequest request) {
        DeleteAccountResponse response = new DeleteAccountResponse();
        response.setId(request.getId());
        response.setEmail(request.getEmail());
        response.setExplanation(request.getExplanation());
        response.setApproved(request.isApproved());
        response.setAdminResponse(request.getAdminResponse());
        return response;
    }

    public static DeleteAccountRequest mapToEntityFromResponse(DeleteAccountResponse dto) {
        DeleteAccountRequest request = new DeleteAccountRequest();
        request.setId(dto.getId());
        request.setEmail(dto.getEmail());
        request.setExplanation(dto.getExplanation());
        request.setApproved(dto.isApproved());
        request.setAdminResponse(dto.getAdminResponse());
        return request;
    }
}
