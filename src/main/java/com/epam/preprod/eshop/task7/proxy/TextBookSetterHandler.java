package com.epam.preprod.eshop.task7.proxy;


import com.epam.preprod.eshop.task7.TextBookInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TextBookSetterHandler implements InvocationHandler {
    private TextBookInterface textBook;

    public TextBookSetterHandler(TextBookInterface textBookImpl) {
        this.textBook = textBookImpl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        String methodName = method.getName();
        if (methodName.startsWith("set")) {
            throw new IllegalArgumentException("SetXXXX methods does not support.");
        }
        return method.invoke(textBook, args);
    }


}
