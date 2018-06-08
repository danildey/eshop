package com.epam.preprod.eshop.task7.factory;

import com.epam.preprod.eshop.task7.TextBookInterface;
import com.epam.preprod.eshop.task7.proxy.TextBookSetterHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class UnmodifiableTextBookFactory implements TextBookFactory {

    @Override
    public TextBookInterface createTextBook(TextBookInterface textBookInterface) {
        InvocationHandler handler = new TextBookSetterHandler(textBookInterface);
        Object proxy = Proxy.newProxyInstance(TextBookInterface.class.getClassLoader(),
                new Class[]{TextBookInterface.class}, handler);
        return (TextBookInterface) proxy;
    }
}
