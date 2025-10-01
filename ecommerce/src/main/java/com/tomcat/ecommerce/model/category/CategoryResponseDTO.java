package com.tomcat.ecommerce.model.category;


public class CategoryResponseDTO {
    private String responseMessage;

    public CategoryResponseDTO(String responseMessage) {
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
