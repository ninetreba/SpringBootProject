package com.accenture.russiaatc.irentservice10.SNAPSHOT.configuration;

import com.accenture.russiaatc.irentservice10.SNAPSHOT.model.CallContext;


public class SecurityContext {
    private static final ThreadLocal<CallContext> context = new ThreadLocal<>();

    public static void set(CallContext callContext){
        context.set(callContext);
    }

    public static CallContext get(){
        return context.get();
    }

    public static void clear(){
        context.remove();
    }

}
