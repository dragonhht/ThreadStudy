package async;

import java.lang.reflect.Method;

/**
 * Description.
 * User: huang
 * Date: 18-6-7
 */
public class Main {

    public static void async(String className, Object... args) {
        Main main = new Main();
        ClassLoader classLoader = main.getClass().getClassLoader();
        try {
            Class cls = classLoader.loadClass(className);
            Object obj = cls.newInstance();
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                Async async = method.getDeclaredAnnotation(Async.class);
                if (async != null) {
                    int size = async.size();
                    for (int i = 0; i < size; i++) {
                        new Thread(new ThreadAsync(obj, method, args)).start();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("找不到类");
        }  catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        async("async.AsyncTest");
    }

}
