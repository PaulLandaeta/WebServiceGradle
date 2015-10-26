package com.mojix.resteasy;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * Created by PLandaeta on 26/10/2015.
 */

public class app extends Application {

    protected HashSet<Object> singletons = new HashSet<Object>();
    public app() {

    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> providers = new LinkedHashSet<Class<?>>();
        return providers;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
