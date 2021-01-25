package com.ilyabuglakov.raise.model.service.servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RequestService {

    private static class InstanceHolder {
        public static RequestService INSTANCE = new RequestService();
    }

    public static RequestService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public Optional<Integer> getIntParameter(HttpServletRequest request, String parameterName) {
        Optional<Integer> optionalInteger = Optional.empty();
        String parameter = request.getParameter(parameterName);
        if (parameter != null) {
            try {
                optionalInteger = Optional.of(Integer.parseInt(parameter));
            } catch (NumberFormatException e) {
                //Exception ignored because method will return Optional.empty anyway
            }
        }
        return optionalInteger;
    }

    public void setRequestErrorAttributes(HttpServletRequest request, String messagePropertyKey, int errorCode) {
        request.setAttribute("errorMessage", messagePropertyKey);
        request.setAttribute("errorCode", errorCode);
    }


}
