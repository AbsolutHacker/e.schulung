package de.esolutions.day7.proxy;

public class Security {
    private static boolean loggedIn = false;
    static void login() {
        loggedIn = true;
    }
    static void logout() {
        loggedIn = false;
    }
    static boolean isLoggedIn() {
        return loggedIn;
    }
}
