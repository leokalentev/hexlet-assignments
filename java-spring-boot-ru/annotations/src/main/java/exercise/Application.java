package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        Class<?> addressClass = address.getClass();
        Method[] methods = addressClass.getDeclaredMethods();

        for (var method: methods) {
            if (method.isAnnotationPresent(Inspect.class)) {
                String methodName = method.getName();
                String returnType = method.getReturnType().getSimpleName();

                System.out.println("Method " + methodName + " returns a value of type " + returnType);
            }
        }
        // END
    }
}
