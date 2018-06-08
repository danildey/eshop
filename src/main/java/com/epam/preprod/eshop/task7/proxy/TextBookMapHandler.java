package com.epam.preprod.eshop.task7.proxy;

import com.epam.preprod.eshop.task7.TextBookInterface;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TextBookMapHandler implements InvocationHandler {

    private Map<String, Object> textbookMap;
    private TextBookInterface textBook;

    public TextBookMapHandler(TextBookInterface textBook) throws IllegalAccessException {
        this.textBook = textBook;
        this.textbookMap = new HashMap<>();
        initMap(textBook);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        String methodName = method.getName();
        if (methodName.startsWith("set")) {
            String newName = methodName.replaceFirst("set", "");
            return textbookMap.put(newName, args[0]);
        }
        if (methodName.startsWith("is")) {
            String newName = methodName.replaceFirst("is", "");
            return textbookMap.get(newName);
        }
        if (methodName.startsWith("get")) {
            String newName = methodName.replaceFirst("get", "");
            return textbookMap.get(newName);
        }
        return method.invoke(textBook, args);
    }

    private void initMap(TextBookInterface textBook) throws IllegalAccessException {
        for (Class<?> current = textBook.getClass(); current != Object.class; current = current.getSuperclass()) {
            for (Field field : current.getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();
                String newName = name.substring(0, 1).toUpperCase() + name.substring(1);
                textbookMap.put(newName, field.get(textBook));
            }
        }
    }
}
