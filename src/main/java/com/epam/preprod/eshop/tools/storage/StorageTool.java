package com.epam.preprod.eshop.tools.storage;

public interface StorageTool<T> {

    void saveState(T object, String path);

    T loadState(String path);
}
