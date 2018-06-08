package com.epam.preprod.eshop.collections;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.exceptions.IndexOutOfModifiableArrayException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SemiModifiableContainerTest {

    private Product book1m;
    private Product book2m;
    private Product book3m;
    private Product book1um;
    private Product book2um;
    private Product book3um;
    private SemiModifiableContainer<Product> products;
    private List<Product> unmod;
    private List<Product> mod;

    @Before
    public void setUp() {
        book1um = new TextBook("um1", "c1");
        book2um = new TextBook("um2", "c2");
        book3um = new TextBook("um3", "c3");
        book1m = new TextBook("m1", "c4");
        book2m = new TextBook("m2", "c5");
        book3m = new TextBook("m3", "c6");

        unmod = new ArrayList<>(Arrays.asList(book1um, book2um, book3um));
        mod = new ArrayList<>(Arrays.asList(book1m, book2m, book3m));

        products = new SemiModifiableContainer<>(unmod, mod);
    }

    @Test
    public void shouldReturnSize() throws Exception {
        assertEquals(6, products.size());
    }

    @Test
    public void shouldReturnTrueIfListIsEmpty() throws Exception {
        unmod = new ArrayList<>();
        mod = new ArrayList<>();
        products = new SemiModifiableContainer<>(unmod, mod);
        assertTrue(products.isEmpty());
    }

    @Test
    public void shouldReturnTrueIfContainsElement() throws Exception {
        assertTrue(products.contains(book1m));
        assertTrue(products.contains(book1um));
        assertTrue(products.contains(book3m));
        assertFalse(products.contains(new TextBook("s1", "c1")));
    }

    @Test
    public void shouldReturnArrayOfObjectsFromBothList() throws Exception {
        Product[] expected = {book1um, book2um, book3um, book1m, book2m, book3m};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    public void shouldReturnArrayOfObjectsFromBothListToInputArray() throws Exception {
        Product[] expected = {book1um, book2um, book3um, book1m, book2m, book3m, null, book1um};
        Object[] array = {book1um, book1um, book1um, book1um, book1um, book1um, book1um, book1um};
        Object[] actuals = products.toArray(array);
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void shouldReturnArrayOfObjectsFromBothListToInputArrayThatSmallerThenList() throws Exception {
        Product[] expected = {book1um, book2um, book3um, book1m, book2m, book3m};
        Object[] array = {book1um, book1um};
        Object[] actuals = products.toArray(array);
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void shouldAddNewElementAfterLastElement() throws Exception {
        TextBook book1t = new TextBook("s1", "c1");
        products.add(book1t);
        assertTrue(products.size() == 7);
        assertTrue(products.contains(book1t));
    }

    @Test
    public void shouldRemoveByObjectIfElementIsModifiable() throws Exception {
        assertTrue(products.size() == 6);
        assertTrue(products.remove(book3m));
        assertTrue(products.size() == 5);
        assertFalse(products.contains(book3m));
    }

    @Test(expected = IndexOutOfModifiableArrayException.class)
    public void shouldNotRemoveByObjectIfElementIsUnmodifiable() throws Exception {
        assertTrue(products.remove(book3um));
    }

    @Test
    public void shouldReturnTrueIfContainsAll() throws Exception {
        assertTrue(products.containsAll(mod));
        assertTrue(products.containsAll(unmod));
    }

    @Test
    public void shouldReturnFalseIfNotContainsAll() throws Exception {
        List list = new ArrayList(mod);
        list.add(new TextBook("s1", "c1"));
        System.out.println(products.containsAll(mod));
        assertFalse(products.containsAll(list));
    }

    @Test
    public void shouldAddAllObjectsFromCollectionToListAfterLastElement() throws Exception {
        TextBook book1t = new TextBook("s1", "c1");
        TextBook book2t = new TextBook("s1", "c1");
        TextBook book3t = new TextBook("s1", "c1");
        mod = new ArrayList<>(Arrays.asList(book1t, book2t, book3t));
        products.addAll(mod);
        assertTrue(products.size() == 9);
        assertTrue(products.containsAll(mod));
    }

    @Test
    public void shouldAddAllObjectsFromCollectionByIndex() throws Exception {
        TextBook book1t = new TextBook("s1", "c1");
        TextBook book2t = new TextBook("s1", "c1");
        List list = Arrays.asList(book1t, book2t);
        products.addAll(3, list);
        assertTrue(products.size() == 8);
        assertTrue(products.containsAll(list));
    }

    @Test
    public void shouldRemoveAllIfWeRemoveOnlyFromModifiableList() throws Exception {
        List<Product> modCopy = new ArrayList<>(mod);
        assertTrue(products.removeAll(mod));
        assertTrue(products.size() == 3);
        assertFalse(products.containsAll(modCopy));
    }

    @Test(expected = IndexOutOfModifiableArrayException.class)
    public void shouldThrowExceptionIfWeTryToRemoveFromUnmodifiableList() throws Exception {
        assertFalse(products.removeAll(unmod));
        assertTrue(products.containsAll(unmod));
    }

    @Test(expected = IndexOutOfModifiableArrayException.class)
    public void shouldThrowExceptionIfWeTryToRetainFromUnmodifiableList() throws Exception {
        assertFalse(products.retainAll(mod));
        assertTrue(products.containsAll(mod));
    }

    @Test
    public void shoulReturnTrueIfWeRemoveOnlyFromModifiableList() throws Exception {
        assertTrue(products.retainAll(unmod));
        assertTrue(products.containsAll(unmod));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionIfWeTryClear() throws Exception {
        products.clear();
    }

    @Test
    public void shouldReturnTrueIfClearEmptyList() throws Exception {
        products = new SemiModifiableContainer<>(Collections.emptyList(), mod);
        products.clear();
        assertTrue(products.isEmpty());
    }

    @Test
    public void shouldReturnElementByIndex() throws Exception {
        assertEquals(book1m, products.get(3));
        assertEquals(book1um, products.get(0));
    }

    @Test
    public void shouldSetElementByIndex() throws Exception {
        assertEquals(products.get(3), products.set(3, book2m));
        assertEquals(book2m, products.get(3));
    }

    @Test(expected = IndexOutOfModifiableArrayException.class)
    public void shouldThrowExceptionIfWeTryToSetInUnmodifiableList() throws Exception {
        products.set(2, book2um);
    }

    @Test
    public void shouldAddElementByIndex() throws Exception {
        products.add(3, book1m);
        assertEquals(book1m, products.get(3));
        assertEquals(book1m, products.get(4));
        assertTrue(products.size() == 7);
    }

    @Test
    public void shouldAddElementByIndexInTheEndOfList() throws Exception {
        products.add(6, book1m);
        assertEquals(book1m, products.get(6));
        assertTrue(products.size() == 7);
    }

    @Test(expected = IndexOutOfModifiableArrayException.class)
    public void shouldThrowExceptionIfWeTryToAddElementIntoUnmodifiable() throws Exception {
        products.add(2, book1m);
        assertTrue(products.size() == 6);
    }

    @Test
    public void shouldRemoveByIndexFromModifiableList() throws Exception {
        assertEquals(book1m, products.remove(3));
        assertTrue(products.size() == 5);
        assertFalse(products.contains(book1m));
    }

    @Test(expected = IndexOutOfModifiableArrayException.class)
    public void shouldThrowExceptionIfRemoveFomUnmodifiableList() throws Exception {
        products.remove(0);
        assertTrue(products.contains(book1um));
    }

    @Test
    public void shouldReturnIndexOfFirstFoundedElement() throws Exception {
        assertTrue(products.indexOf(book1m) == 3);
        assertTrue(products.indexOf(book1um) == 0);

    }

    @Test
    public void shouldReturnNegativeIfElementIsNotFound() throws Exception {
        assertTrue(products.indexOf(new TextBook("11", "1")) == -1);
        assertTrue(products.lastIndexOf(new TextBook("11", "1")) == -1);
    }

    @Test
    public void indexOfEmptyShouldBeNegative() throws Exception {
        mod = new ArrayList<>();
        unmod = new ArrayList<>();
        products = new SemiModifiableContainer<>(unmod, mod);
        assertTrue(products.indexOf(book1m) == -1);
        assertTrue(products.indexOf(book1um) == -1);
    }

    @Test
    public void shouldReturnIndexOfLastFoundedElement() throws Exception {
        products.add(book1m);
        assertTrue(products.lastIndexOf(book1m) == 6);
        assertTrue(products.lastIndexOf(book1um) == 0);
    }

    @Test
    public void hasNextShouldReturnFalseOnEmptyList() throws Exception {
        mod = new ArrayList<>();
        unmod = new ArrayList<>();
        products = new SemiModifiableContainer<>(unmod, mod);
        System.out.println(products.size());
        assertFalse(products.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void nextShouldThrowExceptionOnEmptyList() throws Exception {
        List unmod = new ArrayList();
        List mod = new ArrayList();
        products = new SemiModifiableContainer<>(unmod, mod);
        products.iterator().next();
    }

    @Test
    public void hasNextShouldReturnTrueSeveralTimes() throws Exception {
        assertTrue(products.iterator().hasNext());
        assertTrue(products.iterator().hasNext());
    }

    @Test
    public void hasNextShouldReturnFalseAfterNextInListWithOneElement() throws Exception {
        mod = new ArrayList<>();
        unmod = new ArrayList<>(Arrays.asList(book1um));
        products = new SemiModifiableContainer<>(unmod, mod);
        Iterator<Product> iter = products.iterator();
        assertTrue(iter.hasNext());
        assertEquals(book1um, iter.next());
        assertFalse(iter.hasNext());
    }


    @Test
    public void shouldIterateThroughAllTheElements() throws Exception {
        Iterator<Product> iter = products.iterator();
        List<Product> actal = new ArrayList<>();
        while (iter.hasNext()) {
            actal.add(iter.next());
        }
        assertTrue(products.containsAll(actal));
    }
}