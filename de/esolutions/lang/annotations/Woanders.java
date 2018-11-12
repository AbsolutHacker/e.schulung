package de.esolutions.lang.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import de.esolutions.lang.annotations.Color;
import de.esolutions.lang.annotations.Transmogrifier;
import de.esolutions.lang.annotations.Command;

public class Woanders {

    public static void main(String[] args) throws SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Command c = createCommand();
        c.doIt();
    }

    @SuppressWarnings("unchecked")
    private static Command createCommand() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?>[] cc = Command.class.getDeclaredConstructors();
        List<Constructor<Command>> constructors = Arrays.stream(cc)
          .filter(c -> c.getAnnotation(Inject.class) != null)
          .map(c -> (Constructor<Command>) c)
          .collect(Collectors.toList())
          ;
        System.out.println(constructors);
        if (constructors.size() != 1) {
            throw new IllegalStateException("constructor not unique");
        }

        Constructor<Command> c = constructors.get(0);
        Inject annon = c.getAnnotation(Inject.class);
        Class<?>[] types  =c.getParameterTypes();
        if (types.length != 1) {
            throw new IllegalStateException("too many arguments");
        }

        Class<?> type = types[0];

        if (type != Transmogrifier.class) {
            throw new IllegalStateException("unsupported type " + type.getName());
        }

        Object arg;
        switch (annon.name()) {
            case "rot":
                arg = Color.RED;
                break;
            case "blau":
                arg = Color.BLUE;
                break;
            default:
                arg = Color.GREEN;
                break;
        }

        return c.newInstance(arg);
    }

}
