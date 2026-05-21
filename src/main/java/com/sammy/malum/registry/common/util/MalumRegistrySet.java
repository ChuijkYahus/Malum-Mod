package com.sammy.malum.registry.common.util;

public class MalumRegistrySet {

    public final String id;

    public MalumRegistrySet(String id) {
        this.id = id;
    }

    public String name(String name) {
        return name.replace("%s", id);
    }

}
