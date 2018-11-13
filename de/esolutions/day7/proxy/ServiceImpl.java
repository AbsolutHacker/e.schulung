package de.esolutions.day7.proxy;

public class ServiceImpl implements Service {

    @Override
    public boolean protectedCall(String s) {
        System.out.println("* [priv] " + s);
        return true;
    }

    @Override
    public boolean unprotectedCall(String s) {
        System.out.println("[unpriv] " + s);
        return true;
    }
}
