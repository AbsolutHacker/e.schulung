package de.esolutions.day7;

import java.util.HashMap;
import java.util.Map;

public class FunWithMaps {

    Map<String, Object> map;

    public FunWithMaps() {
        map = new HashMap<>();
    }

    public void duh() {
        System.out.println(map.getClass());
    }

    public static void main(String[] args) {
        new FunWithMaps().duh();
    }
}
