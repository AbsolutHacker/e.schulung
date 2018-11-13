package de.esolutions.day7.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void testProtection() {
        Service service = ServiceFactory.create();
        assertTrue(service.unprotectedCall("everybody's allowed to do this"));
        assertThrows(SecurityException.class, () -> service.protectedCall("dreist gewinnt"));
        Security.login();
        assertTrue(service.protectedCall("mit meiner karte komm ich ueberall rein"));
        Security.logout();
    }

}