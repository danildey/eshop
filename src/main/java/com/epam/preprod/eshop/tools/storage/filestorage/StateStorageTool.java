package com.epam.preprod.eshop.tools.storage.filestorage;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.tools.storage.StorageTool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class StateStorageTool<T extends Map<Product, Integer>> implements StorageTool<T> {

    public StateStorageTool() {
    }

    @Override
    public void saveState(T products, String path) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path, false))) {
            objectOutputStream.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T loadState(String path) {
        Map<Product, Integer> map;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            map = (Map<Product, Integer>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            map = new HashMap<>();
        }
        return (T) map;
    }
}
