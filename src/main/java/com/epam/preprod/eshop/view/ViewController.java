package com.epam.preprod.eshop.view;

import java.util.Map;

public class ViewController {
    private Map<String, View> views;

    public ViewController(Map<String, View> views) {
        this.views = views;
    }

    public void showView(String command, Object obj) {
        views.get(command).show(obj);
    }
}
