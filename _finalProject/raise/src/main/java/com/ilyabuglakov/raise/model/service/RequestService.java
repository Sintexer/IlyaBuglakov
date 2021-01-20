package com.ilyabuglakov.raise.model.service;

import javax.servlet.http.HttpServletRequest;

public class RequestService {

    private static class InstanceHolder{
        public static RequestService INSTANCE = new RequestService();
    }

    public static RequestService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setRequestErrorAttributes(HttpServletRequest request, String messagePropertyKey, int errorCode){
        request.setAttribute("errorMessage", messagePropertyKey);
        request.setAttribute("errorCode", errorCode);
    }
}
