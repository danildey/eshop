package com.epam.preprod.eshop.collections;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.entity.TextBook;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CopyOnWriteProductContainerTest {
    private Product product1;
    private Product product2;
    private Product product3;
    private CopyOnWriteProductContainer<Product> products;

    @Before
    public void setUp() {
        products = new CopyOnWriteProductContainer<>();
        product1 = new TextBook("tb1", "c1");
        product2 = new TextBook("tb2", "c2");
        product3 = new TextBook("tb3", "c3");
    }

    @Test
    public void shouldReturnSize() throws Exception {
        products.add(product1);
        products.add(product2);
        assertEquals(2, products.size());
    }

    @Test
    public void shouldBeEmpty() throws Exception {
        assertTrue(products.isEmpty());
    }

    @Test
    public void shouldNotBeEmpty() throws Exception {
        products.add(product1);
        assertFalse(products.isEmpty());
    }

    @Test
    public void shouldReturnTrueIfContainsProduct() throws Exception {
        products.add(product1);
        assertTrue(products.contains(product1));
    }

    @Test
    public void shouldReturnFalseIfContainsProduct() throws Exception {
        products.add(product1);
        assertFalse(products.contains(product2));
    }

    @Test
    public void shouldReturnArrayOfProducts() throws Exception {
        products.addAll(Arrays.asList(product1, product2));
        Object[] expected = new Product[2];
        expected[0] = product1;
        expected[1] = product2;
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    public void shouldCopyListToExternalArray() throws Exception {
        products.addAll(Arrays.asList(product1, product2));
        Object[] expected = {product1, product2, null, product2};
        Object[] array = {product1, product1, product1, product2};
        Object[] actuals = products.toArray(array);
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void shouldRemoveFromListByProduct() throws Exception {
        products.add(product1);
        products.remove(product1);
        assertTrue(products.size() == 0);
    }

    @Test
    public void shouldRemoveProductFromListByIndex() throws Exception {
        products.add(product1);
        products.remove(0);
        System.out.println(products.size());
        assertTrue(products.isEmpty());
    }

    @Test
    public void shouldCheckIfContainsAllElements() throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.addAll(Arrays.asList(product1, product2));
        products.addAll(Arrays.asList(product1, product2, product3));
        assertTrue(products.containsAll(list));
    }

    @Test
    public void shouldAddAllProductsInTheEndOfList() throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.addAll(Arrays.asList(product1, product2));
        products.addAll(list);
        assertTrue(products.containsAll(list));
    }


    @Test
    public void shouldAddAllProductsByIndex() throws Exception {
        List<Product> list = new ArrayList<Product>(Arrays.asList(product1, product3));
        products.addAll(Arrays.asList(product2, product2, product2));
        products.addAll(1, list);
        assertTrue(products.containsAll(list));
    }

    @Test
    public void shouldRemoveAllProducts() throws Exception {
        products.addAll(Arrays.asList(product1, product1, product2, product3));
        List<Product> list = new ArrayList<Product>(Arrays.asList(product1, product3));
        products.removeAll(list);
        assertFalse(products.contains(product1) || products.contains(product3));
    }

    @Test
    public void shouldRetainOnlyGeneralValues() throws Exception {
        products.addAll(Arrays.asList(product1, product1, product2, product3));
        List<Product> list = new ArrayList<Product>(Arrays.asList(product1, product3));
        products.retainAll(list);
        List<Product> expected = new ArrayList<Product>(Arrays.asList(product1, product1, product3));
        assertArrayEquals(expected.toArray(), products.toArray());
    }

    @Test
    public void shouldReturnProductByIndex() throws Exception {
        products.addAll(Arrays.asList(product1, product2));
        assertEquals(product1, products.get(0));
    }

    @Test
    public void set() throws Exception {
        products.addAll(Arrays.asList(product1, product2));
        boolean result = product1.equals(products.set(0, product3));
        result &= product3.equals(products.get(0));
        assertTrue(result);
    }

    @Test
    public void shouldSetNullAndReturnAldValue() throws Exception {
        products.addAll(Arrays.asList(product1, product2));
        assertTrue(product1.equals(products.set(0, null)));
        assertTrue(null == products.get(0));
    }

    @Test
    public void shouldAddByIndex() throws Exception {
        products.addAll(Arrays.asList(product1, product3));
        products.add(1, product2);
        List<Product> expected = new ArrayList<>();
        expected.addAll(Arrays.asList(product1, product2, product3));
        assertArrayEquals(expected.toArray(), products.toArray());
    }

    @Test
    public void shouldReturnNegativeValueForNotContained() throws Exception {
        products.add(product1);
        products.add(null);
        assertTrue(products.indexOf(product3) == (-1));
        assertTrue(products.lastIndexOf(product3) == (-1));
    }

    @Test
    public void shouldReturnIndexOfFirstNull() throws Exception {
        products.addAll(Arrays.asList(product1, null, product3, null));
        assertTrue(products.indexOf(null) == 1);
    }

    @Test
    public void shouldReturnIndexOfFirstFoundProduct() throws Exception {
        products.addAll(Arrays.asList(product1, null, product3, product3));
        assertTrue(products.indexOf(product3) == 2);
    }

    @Test
    public void shouldReturnIndexOfLastFoundProduct() throws Exception {
        products.addAll(Arrays.asList(product1, null, product3, product3));
        assertTrue(products.lastIndexOf(product3) == 3);
    }

    @Test
    public void shouldReturnIndexOfLastNull() throws Exception {
        products.addAll(Arrays.asList(product1, null, product3, null));
        assertTrue(products.lastIndexOf(null) == 3);
    }

    @Test
    public void hasNextShouldReturnFalseOnEmptyList() throws Exception {
        assertFalse(products.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextShouldThrowExceptionOnEmptyList() throws Exception {
        products.iterator().next();
    }

    @Test
    public void hasNextShouldReturnTrueSeveralTimes() throws Exception {
        products.add(product1);
        assertTrue(products.iterator().hasNext());
        assertTrue(products.iterator().hasNext());
    }

    @Test
    public void hasNextShouldReturnFalseAfterNextInListWithOneElement() throws Exception {
        products.add(product1);
        Iterator<Product> iter = products.iterator();
        assertTrue(iter.hasNext());
        assertEquals(product1, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void removeThrowsExceptionIfCallBeforeNext() throws Exception {
        products.add(product1);
        Iterator<Product> iter = products.iterator();
        iter.next();
        iter.remove();
        iter.remove();
    }

    @Test
    public void shouldIterateThroughAllTheElements() throws Exception {
        products.addAll(Arrays.asList(null, product1, product3, product3));
        Iterator<Product> iter = products.iterator();
        List<Product> actual = new ArrayList<>();
        while (iter.hasNext()) {
            actual.add(iter.next());
        }
        assertTrue(products.containsAll(actual));
    }

    @Test
    public void hasNextShouldReturnFalseOnEmptyListCOWIter() throws Exception {
        assertFalse(products.cowIterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextShouldThrowExceptionOnEmptyListCOWIter() throws Exception {
        products.cowIterator().next();
    }

    @Test
    public void hasNextShouldReturnTrueSeveralTimesCOWIter() throws Exception {
        products.add(product1);
        Iterator<Product> iter = products.cowIterator();
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
    }

    @Test
    public void hasNextShouldReturnFalseAfterNextInListWithOneElementCOWIter() throws Exception {
        products.add(product1);
        Iterator<Product> iter = products.cowIterator();
        assertTrue(iter.hasNext());
        assertEquals(product1, iter.next());
        assertFalse(iter.hasNext());
    }


    @Test(expected = UnsupportedOperationException.class)
    public void removeThrowsExceptionIfCallBeforeNextCOWIter() throws Exception {
        products.add(product1);
        Iterator<Product> iter = products.cowIterator();
        iter.next();
        iter.remove();
    }

    @Test
    public void shouldIterateThroughAllTheElementsCOWIter() throws Exception {
        products.addAll(Arrays.asList(null, product1, product3, product3));
        Iterator<Product> iter = products.cowIterator();
        List<Product> actal = new ArrayList<>();
        while (iter.hasNext()) {
            actal.add(iter.next());
        }
        assertTrue(products.containsAll(actal));
    }

}