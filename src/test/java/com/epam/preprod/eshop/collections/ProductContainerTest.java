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
import java.util.function.Predicate;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductContainerTest {

    private Product product1;
    private Product product2;
    private Product product3;
    private ProductContainer<Product> products;
    private Predicate<Product> predicate = new Predicate<Product>() {
        @Override
        public boolean test(Product product) {
            if (product instanceof TextBook) {
                return ((TextBook) product).getSubjectArea().equals("java");
            } else return false;
        }


    };

    @Before
    public void setUp() {
        products = new ProductContainer<>();
        product1 = new TextBook("java", "c1");
        product2 = new TextBook("C++", "c2");
        product3 = new TextBook("SQL", "c3");
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
    public void shouldReturnTrueOrFalseIfContainsProduct() throws Exception {
        products.add(product1);
        assertTrue(products.contains(product1));
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
        Object[] expected = new Product[2];
        expected[0] = product1;
        expected[1] = product2;
        Object[] array = new Product[2];
        Object[] actuals = products.toArray(array);
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void shouldRemoveFromListByProduct() throws Exception {
        products.add(product1);
        products.remove(product1);
        assertTrue(products.isEmpty());
    }

    @Test
    public void shouldRemoveProductFromListByIndex() throws Exception {
        products.add(product1);
        products.remove(0);
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
    public void shouldGrowAfterTenAdds() throws Exception {
        for (int i = 0; i < 15; i++) {
            products.add(product1);
        }
        assertEquals(15, products.size());
    }

    @Test
    public void shouldAddAllProductsByIndex() throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.addAll(Arrays.asList(product1, product3));
        products.addAll(Arrays.asList(product2, product2, product2));
        products.addAll(1, list);
        assertTrue(products.containsAll(list));
    }

    @Test
    public void shouldRemoveAllProducts() throws Exception {
        products.addAll(Arrays.asList(product1, product1, product2, product3));
        List<Product> list = new ArrayList<Product>();
        list.addAll(Arrays.asList(product1, product3));
        products.removeAll(list);
        System.out.println(Arrays.asList(products.toArray()));
        assertFalse(products.contains(product1) || products.contains(product3));

    }

    @Test
    public void shouldRetainOnlyGeneralValues() throws Exception {
        products.addAll(Arrays.asList(product1, product1, product2, product3));
        List<Product> list = new ArrayList<Product>();
        list.addAll(Arrays.asList(product1, product3));
        products.retainAll(list);
        List<Product> expected = new ArrayList<Product>();
        expected.addAll(Arrays.asList(product1, product1, product3));
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
        List<Product> expacted = new ArrayList<>();
        expacted.addAll(Arrays.asList(product1, product2, product3));
        assertArrayEquals(expacted.toArray(), products.toArray());
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

    @Test
    public void listMustBeEmptyAfterRemove() throws Exception {
        products.add(product1);
        Iterator<Product> iter = products.iterator();
        iter.next();
        iter.remove();
        assertTrue(products.size() == 0);
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
        List<Product> actal = new ArrayList<>();
        while (iter.hasNext()) {
            actal.add(iter.next());
        }
        assertTrue(products.containsAll(actal));
    }

    @Test
    public void listShouldBeEmptyAfterRemoveAll() throws Exception {
        products.addAll(Arrays.asList(null, product1, product3, product3));
        Iterator<Product> iter = products.iterator();

        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        assertTrue(products.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void filterIteratorNextShouldThrowExceptionOnEmptyList() throws Exception {
        products.filterIterator(predicate).next();
    }

    @Test
    public void ShouldReturnTrueIfFitsTheCondition() throws Exception {
        products.add(product1);
        products.add(product2);
        assertTrue(products.filterIterator(predicate).hasNext());
        assertTrue(products.filterIterator(predicate).hasNext());
    }

    @Test
    public void shouldIterateThroughAllTheElementsThatFitsTheCondition() throws Exception {
        products.addAll(Arrays.asList(null, product1, product3, product1, product3));
        Iterator<Product> iter = products.filterIterator(predicate);
        List<Product> actal = new ArrayList<>();
        while (iter.hasNext()) {
            actal.add(iter.next());
        }
        List<Product> expected = new ArrayList<>();
        expected.add(product1);
        expected.add(product1);
        assertTrue(actal.containsAll(expected));
    }
}
