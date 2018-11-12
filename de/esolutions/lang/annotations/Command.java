package de.esolutions.lang.annotations;

import de.esolutions.lang.annotations.Transmogrifier;

public class Command {

    private boolean debug;

    private Transmogrifier t;

    @Inject("blau")
    public Command(Transmogrifier t) {
        this.t = t;
    }

    public void doIt() {
        t.transmogrify();
    }

}
