package com.epam.preprod.eshop.tools.productcreator.reflectioncreator;

import com.epam.preprod.eshop.anotation.Init;
import com.epam.preprod.eshop.anotation.Service;
import com.epam.preprod.eshop.consoleio.FacadeDataIo;
import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.epam.preprod.eshop.message.Messages.CHOOSE_PRODUCT_REFLECTION;

public class ReflectionProductCreator {
    private InputInteraction interaction;
    private Map<Class<?>, Method> methodMap;
    private ResourceBundle resourceBundle;
    private FacadeDataIo dataIo;

    public ReflectionProductCreator(InputInteraction interaction, ResourceBundle resourceBundle,
                                    Map<Class<?>, Method> methodMap, FacadeDataIo dataIo) {
        this.resourceBundle = resourceBundle;
        this.interaction = interaction;
        this.methodMap = methodMap;
        this.dataIo = dataIo;
    }

    public Product create() {
        Object product = null;
        Class<? extends Product> clazz = interaction.readProductClass(CHOOSE_PRODUCT_REFLECTION);
        if (clazz.isAnnotationPresent(Service.class) && !Modifier.isAbstract(clazz.getModifiers())) {
            try {
                product = initClass(clazz);
            } catch (Exception e) {
                dataIo.print("System Problems.");
            }
        } else {
            dataIo.print("Class" + clazz.getSimpleName() + "is abstract or not a @Service");
        }
        return (Product) product;
    }

    private Object initClass(Class<? extends Product> clazz) throws IllegalAccessException,
            InstantiationException, InvocationTargetException {
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)) {
                method.invoke(obj, fillArguments(method));
            }
        }
        return obj;
    }

    private Object[] fillArguments(Method method) throws InvocationTargetException, IllegalAccessException {
        Class<?>[] parameters = method.getParameterTypes();
        List<Object> args = new ArrayList<>();
        for (Class paramClass : parameters) {
            String messageResource = method.getAnnotation(Init.class).resource();
            String message = resourceBundle.getString(messageResource);
            args.add(methodMap.get(paramClass).invoke(interaction, message));
        }
        return args.toArray();
    }
}
