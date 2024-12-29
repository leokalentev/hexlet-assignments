package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Object obj) {
        List<String> notValidFields = new ArrayList<>();

        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);

                try {
                    if (field.get(obj) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Ошибка доступа к полю: " + field.getName(), e);
                }
            }
        }

        return notValidFields;
    }
    public static Map<String, List<String>> advancedValidate(Object obj) {
        Map<String, List<String>> errors = new HashMap<>();

        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            List<String> fieldErrors = new ArrayList<>();

            try {
                Object value = field.get(obj);

                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    fieldErrors.add("can not be null");
                }

                if (field.isAnnotationPresent(MinLength.class) && value instanceof String strValue) {
                    MinLength annotation = field.getAnnotation(MinLength.class);
                    if (strValue.length() < annotation.minLength()) {
                        fieldErrors.add("length less than " + annotation.minLength());
                    }
                }

                if (!fieldErrors.isEmpty()) {
                    errors.put(field.getName(), fieldErrors);
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException("Ошибка доступа к полю: " + field.getName(), e);
            }
        }
        return errors;
    }
}
// END
