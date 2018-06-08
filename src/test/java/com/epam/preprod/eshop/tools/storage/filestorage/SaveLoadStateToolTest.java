package com.epam.preprod.eshop.tools.storage.filestorage;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.entity.TextBook;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveLoadStateToolTest {

    private File testFile;
    private File testGzip;
    private TextBook textBook;
    private Map<Product, Integer> map;
    private StateStorageTool storageTool;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void init() throws IOException {
        storageTool = new StateStorageTool();
        textBook = new TextBook(1, 100, "name1", "desc1", "Mathematica1", "c1");
        map = new HashMap<>();
        map.put(textBook, 0);
        testFile = folder.newFile("testSave.bin");
        testGzip = folder.newFile("testGzip.bin");
    }

    @Test
    public void shouldSaveMapToFile() {
        storageTool.saveState(map, testFile.getPath());
        assertTrue(testFile.exists());
    }

    @Test
    public void shouldReturnDeSerializedMap() {
        storageTool.saveState(map, testFile.getPath());
        Map<Product, Integer> loaded = storageTool.loadState(testFile.getPath());
        assertEquals(map, loaded);
    }

    @Test
    public void shouldReturnEmptyMapIfFileNotExist() {
        Map<Product, Integer> loaded2 = storageTool.loadState(testFile.getPath());
        assertTrue(loaded2.isEmpty());
    }

    @Test
    public void archivedFileShouldBeSmallerThenNonArchived() throws Exception {
        storageTool.saveState(map, testFile.getPath());
        try (FileOutputStream fos = new FileOutputStream(testGzip)) {
            GZIPOutputStream gz = new GZIPOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(gz);
            oos.writeObject(map);
            oos.flush();
            oos.close();
        }
        assertTrue(testFile.length() > testGzip.length());
    }
}
