package com.epam.preprod.eshop.collections;


import com.epam.preprod.eshop.entity.Product;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;


public class ProductContainer<T extends Product> implements List<T> {

    private final int DEFAULT_CAPACITY = 10;

    private int size = 0;

    private Object products[];

    public ProductContainer() {
        products = new Object[DEFAULT_CAPACITY];
    }

    public ProductContainer(int initialCapacity) {
        if (initialCapacity > 0) {
            products = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public Iterator<T> iterator() {
        return new Iter();
    }

    public Iterator<T> filterIterator(Predicate predicate) {
        return new FilterIterator(new Iter(), predicate);
    }

    public Object[] toArray() {
        return Arrays.copyOf(products, size);
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(products, size, a.getClass());
        System.arraycopy(products, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;

    }

    public boolean add(T product) {
        ensureCapacity(size + 1);
        products[size++] = product;
        return false;
    }

    private void ensureCapacity(int checkSize) {
        if (checkSize > products.length) {
            grow(checkSize);
        }
    }

    private void grow(int minSize) {
        int oldCapacity = products.length;
        int newCapacity = oldCapacity * 3 / 2 + 1;
        if (newCapacity - minSize < 0)
            newCapacity = minSize;
        products = Arrays.copyOf(products, newCapacity);
    }

    public boolean remove(Object o) {
        int index = 0;
        if ((index = indexOf(o)) >= 0) {
            System.arraycopy(products, index + 1, products, index,
                    size - index);
            size--;
            return true;
        } else {
            return false;
        }
    }


    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    public boolean addAll(Collection<? extends T> c) {
        if (c.size() > 0) {
            int newSize = size + c.size();
            ensureCapacity(newSize);
            Object[] inputArray = c.toArray();
            System.arraycopy(inputArray, 0, products, size, inputArray.length);
            size = newSize;
            return true;
        } else {
            return false;
        }
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        int newSize = size + c.size();
        int toPos = index + c.size();
        int numMoved = size - index;

        checkRangeAdd(index);
        ensureCapacity(newSize);

        Object[] inputArray = c.toArray();
        if (numMoved > 0)
            System.arraycopy(products, index, products, toPos, numMoved);
        System.arraycopy(inputArray, 0, products, index, inputArray.length);
        size = newSize;
        return true;
    }


    public boolean removeAll(Collection<?> c) {
        boolean isModified = false;
        for (Object o : c) {
            int index = 0;
            while ((index = indexOf(o, index)) >= 0) {
                System.arraycopy(products, index + 1, products, index, size - index);
                size--;
                isModified = true;
            }
        }
        return isModified;
    }


    public boolean retainAll(Collection<?> c) {
        Object[] result = new Object[size];
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            for (Object o : c) {
                if (Objects.equals(o, products[i])) {
                    result[newSize++] = o;
                }
            }
        }
        size = newSize;
        products = result;
        return false;
    }


    public void clear() {
        for (int i = 0; i < size; i++)
            products[i] = null;
        size = 0;
    }

    public T get(int index) {
        checkRange(index);
        return (T) products[index];
    }

    public T set(int index, T element) {
        checkRange(index);
        T oldValue = (T) products[index];
        products[index] = element;
        return oldValue;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public void add(int index, Product element) {
        checkRangeAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(products, index, products, index + 1, size - index);
        products[index] = element;
        size += 1;
    }

    private void checkRangeAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public T remove(int index) {
        checkRange(index);
        T removed = (T) products[index];
        System.arraycopy(products, index + 1, products, index, size - index);
        size--;
        return removed;
    }

    public int indexOf(Object o) {
        return indexOf(o, 0);
    }

    public int indexOf(Object o, int startIndex) {
        if (o == null) {
            for (int i = startIndex; i < size; i++)
                if (Objects.isNull(products[i]))
                    return i;
        } else {
            for (int i = startIndex; i < size; i++)
                if (Objects.equals(o, products[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i > 0; i--)
                if (products[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i > 0; i--)
                if (o.equals(products[i]))
                    return i;
        }
        return -1;
    }

    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private class FilterIterator<T> implements Iterator<T> {
        private Iterator<? extends T> iterator;
        private Predicate<T> predicate;
        private T nextElement;
        private boolean hasNext;

        private FilterIterator(Iterator<? extends T> iterator, Predicate predicate) {
            this.iterator = iterator;
            this.predicate = predicate;
            nextMatch();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public T next() {
            if (!hasNext) {
                throw new NoSuchElementException();
            }
            return nextMatch();
        }

        private T nextMatch() {
            T oldMatch = nextElement;
            while (iterator.hasNext()) {
                T o = iterator.next();
                if (predicate.test(o)) {
                    hasNext = true;
                    nextElement = o;
                    return oldMatch;
                }
            }
            hasNext = false;
            return oldMatch;
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }

    private class Iter implements Iterator<T> {
        private int cursor;
        private int lastReturned = -1;
        private boolean isRemovable = false;

        public boolean hasNext() {
            return cursor != size;
        }

        public T next() {
            if (cursor >= size)
                throw new NoSuchElementException();
            Object[] products = ProductContainer.this.products;
            isRemovable = true;
            return (T) products[lastReturned = cursor++];
        }

        public void remove() {
            if (lastReturned < 0)
                throw new IllegalStateException();
            ProductContainer.this.remove(lastReturned);
            cursor = lastReturned;
            lastReturned = -1;
            isRemovable = false;

        }

    }
}
