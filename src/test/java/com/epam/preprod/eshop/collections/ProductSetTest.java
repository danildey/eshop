package com.epam.preprod.eshop.collections;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.exceptions.DuplicateElementException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductSetTest {
    private Product book1;
    private Product book2;
    private Product book3;
    private List<Product> products;

    @Before
    public void setUp() {
        products = new ProductSet<>();
        book1 = new TextBook("tb1", "c1");
        book2 = new TextBook("tb2", "c2");
        book3 = new TextBook("tb3", "c3");
    }

    @Test
    public void shouldAddNewElementIfNotContains() throws Exception {
        products.add(book1);
        products.add(book2);
        assertEquals(2, products.size());
    }

    @Test
    public void shouldThrowExceptionIfElementAlreadyContains() throws Exception {
        products.add(book1);
        assertFalse(products.add(book1));
    }

    @Test(expected = DuplicateElementException.class)
    public void shouldAddNewElementByIndexIfNotContains() throws Exception {
        products.add(0, book1);
        products.add(0, book1);
    }

    @Test(expected = DuplicateElementException.class)
    public void shouldThrowExceptionThenElementAlreadyContains() throws Exception {
        products.add(book1);
        products.add(0, book1);
    }

    @Test
    public void shouldAddAllNewElementIfNotContains() throws Exception {
        products.add(book1);
        products.add(book2);
        TextBook book4 = new TextBook("tb4", "c4");
        List list = new ArrayList(Arrays.asList(book3, book4));
        products.addAll(list);
        assertEquals(4, products.size());
    }

    @Test
    public void shouldThrowExceptionThenOneOrMoreElementAlreadyContains() throws Exception {
        products.add(book1);
        products.add(book2);
        TextBook book4 = new TextBook("tb4", "c4");
        List list = new ArrayList(Arrays.asList(book2, book4));
        assertFalse(products.addAll(list));

    }

    @Test
    public void shouldAddAllNewElementByIndexIfNotContains() throws Exception {
        products.add(book1);
        products.add(book2);
        TextBook book4 = new TextBook("tb4", "c4");
        List list = new ArrayList(Arrays.asList(book3, book4));
        assertTrue(products.addAll(1, list));
        assertEquals(4, products.size());
    }

    @Test
    public void shouldThrowExceptionIfOneOrMoreElementAlreadyContains() throws Exception {
        products.add(book1);
        products.add(book2);
        TextBook book4 = new TextBook("tb4", "c4");
        List list = new ArrayList(Arrays.asList(book2, book4));
        assertFalse(products.addAll(1, list));
    }

    @Test
    public void shouldSetElementByIndexAndReturnReplacedElementIfNotContains() throws Exception {
        products.add(book1);
        products.add(book2);
        assertEquals(book2, products.set(1, book3));
        assertTrue(products.contains(book3));
    }

    @Test
    public void shouldSetElementByIndexIfInputElementheSameAsAVariable() throws Exception {
        products.add(book1);
        products.add(book2);
        assertEquals(book2, products.set(1, book2));
        assertTrue(products.contains(book2));
    }

    @Test(expected = DuplicateElementException.class)
    public void shouldThrowExceptionIfInputElementAlreadyContains() throws Exception {
        products.add(book1);
        products.add(book2);
        products.set(1, book1);
    }


}