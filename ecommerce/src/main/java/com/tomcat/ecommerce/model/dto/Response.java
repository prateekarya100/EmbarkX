package com.tomcat.ecommerce.model.dto;


public class Response {
    private String responseMessage;

    public Response(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return "CategoryResponseDTO{" +
                "responseMessage='" + responseMessage + '\'' +
                '}';
    }
}
