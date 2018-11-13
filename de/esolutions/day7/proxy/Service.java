package de.esolutions.day7.proxy;

public interface Service {
    @Protected
    boolean protectedCall(String s);
    boolean unprotectedCall(String s);
}
