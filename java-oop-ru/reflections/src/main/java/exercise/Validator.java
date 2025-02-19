package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {

    public static List<String> validate(Object obj) {
        List<String> notValidFields = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(obj) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {
        Map<String, List<String>> validationErrors = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            List<String> errors = new ArrayList<>();

            try {
                if (field.isAnnotationPresent(NotNull.class)) {
                    if (field.get(obj) == null) {
                        errors.add("can not be null");
                    }
                }

                if (field.isAnnotationPresent(MinLength.class)) {
                    MinLength minLength = field.getAnnotation(MinLength.class);
                    if (field.get(obj) instanceof String) {
                        String value = (String) field.get(obj);
                        if (value.length() < minLength.minLength()) {
                            errors.add("length less than " + minLength.minLength());
                        }
                    }
                }

                if (!errors.isEmpty()) {
                    validationErrors.put(field.getName(), errors);
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return validationErrors;
    }
}
