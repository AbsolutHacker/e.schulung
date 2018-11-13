package de.esolutions.day7.proxy;

import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
public class ServiceFactory {

    public static Service create() {
        Service service = Shim.create(new ServiceImpl(), ServiceFactory::SecurityShimFunction);
        service = Shim.create(service, ServiceFactory::LoggingShimFunction);
        return Shim.create(service, ServiceFactory::LoggingShimFunction);
    }

    private static void LoggingShimFunction(Object o, Method method, Object[] args) throws Throwable {
        StringBuilder sb = new StringBuilder("[log] Invocation of ");
        sb.append(method.getName()).append(" on ").append(o);
        System.err.println(sb);
    }

    private static void SecurityShimFunction(Object o, Method method, Object[] args) throws Throwable {
        if (method.getAnnotation(Protected.class) != null
            && (!Security.isLoggedIn())) {
            throw new SecurityException("No way, unprivileged access");
        }
    }

}
