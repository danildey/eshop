package com.epam.preprod.eshop.task7.factory;

import com.epam.preprod.eshop.task7.TextBookInterface;
import com.epam.preprod.eshop.task7.proxy.TextBookMapHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MapTextBookFactory implements TextBookFactory {

    @Override
    public TextBookInterface createTextBook(TextBookInterface textBook) throws IllegalAccessException {
        InvocationHandler handler = new TextBookMapHandler(textBook);
        Object proxy = Proxy.newProxyInstance(TextBookInterface.class.getClassLoader(),
                new Class[]{TextBookInterface.class}, handler);
        return (TextBookInterface) proxy;
    }


}