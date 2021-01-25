package com.ilyabuglakov.raise.model.service.auth;

public class AuthServiceFactory {

    private static class InstanceHolder{
        public static final AuthServiceFactory INSTANCE =  new AuthServiceFactory();
    }

    public static AuthServiceFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public static AuthService getAuthService(){
        return getInstance().getDefaultAuthService();
    }

    private AuthService getDefaultAuthService(){
        return new ShiroAuthService();
    }

}
