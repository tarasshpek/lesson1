import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class FailsOnXXMaxPermSize12m {

    static final List<Interface> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        for (int i = 0; i < 1000000; i++) {
            if (i % 10000 == 0){
                System.out.println("Iteration #" + i);
                Thread.sleep(500L);
            }
            list.add(createProxy(createClassLoader(i)));
        }
    }

    private static Interface createProxy(ClassLoader classLoader){
        InvocationHandler handler = new MyInvocationHandler();
        Interface proxy = (Interface) Proxy.newProxyInstance(
                classLoader,
                new Class[] { Interface.class },
                handler);
        return proxy;
    }

    private static class MyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return null;
        }
    }

    private static ClassLoader createClassLoader(int i) throws MalformedURLException {
        String fictiousClassloaderJAR = "file:" + i + ".jar";

        URL[] fictiousClassloaderURL = new URL[] { new URL(fictiousClassloaderJAR) };

        // Create a new classloader instance
        URLClassLoader newClassLoader = new URLClassLoader(fictiousClassloaderURL);

        return newClassLoader;
    }

    public interface Interface {
        void method();
    }
}
