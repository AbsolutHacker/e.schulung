package de.esolutions.services;

import java.util.ServiceLoader;

public class RunTransmogrifier {
    public static void main(String[] args) {
        ServiceLoader<Transmogrifier> sl = ServiceLoader.load(Transmogrifier.class);
        sl.forEach((s) -> {});
    }
}
