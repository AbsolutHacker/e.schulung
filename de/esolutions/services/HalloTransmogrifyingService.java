package de.esolutions.services;

public class HalloTransmogrifyingService implements Transmogrifier {
    public HalloTransmogrifyingService() {
        System.out.println("Instantiating HalloTransmogrifyingService");
    }

    @Override
    public void transmogrify() {
        System.out.println("Hallo!");
    }
}
