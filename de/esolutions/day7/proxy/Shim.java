package de.esolutions.day7.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * A class of @link{InvocationHandler}s used to transparently shim
 * an underlying object. Shim performs unwrapping of exceptions,
 * well, transparently.
 */
@SuppressWarnings("unchecked")
public class Shim implements InvocationHandler {

    private Object wrappedObject;
    private ShimFunction shimFunction;

    public Shim(Object wrappedObject, ShimFunction shimFunction) {
        this.wrappedObject = wrappedObject;
        this.shimFunction = shimFunction;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        shimFunction.run(wrappedObject, method, args);
        try {
            return method.invoke(wrappedObject, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    public static <T> T create(Object o, ShimFunction shim) {
        return (T) Proxy.newProxyInstance(
          o.getClass().getClassLoader(),
          o.getClass().getInterfaces(),
          new Shim(o, shim)
        );
    }

    public interface ShimFunction {
        void run(Object o, Method m, Object[] a) throws Throwable;
    }

}