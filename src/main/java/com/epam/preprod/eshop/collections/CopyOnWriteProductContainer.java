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

public class CopyOnWriteProductContainer<T extends Product> implements List<T> {

    private Object products[];

    private int size = 0;

    private final int DEFAULT_CAPACITY = 10;

    private boolean isChangeSafe = false;

    public CopyOnWriteProductContainer() {
        products = new Object[DEFAULT_CAPACITY];
    }

    public CopyOnWriteProductContainer(int initialCapacity) {
        if (initialCapacity > 0) {
            products = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    private void doSnapshot() {
        if (isChangeSafe) {
            products = Arrays.copyOf(products, products.length);
            isChangeSafe = false;
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

    public Iterator<T> cowIterator() {
        return new COWIterator();
    }

    public Iterator<T> filterIterator(Predicate predicate) {
        return new FilterIterator(new Iter(), predicate);
    }

    public Object[] toArray() {
        return Arrays.copyOf(products, size);
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(products, size, a.getClass());
        System.arraycopy(products, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;

    }

    public boolean add(T product) {
        doSnapshot();
        ensureCapacity(size + 1);
        products[size++] = product;
        return false;
    }

    public void add(int index, Product element) {
        doSnapshot();
        checkRangeAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(products, index, products, index + 1, size - index);
        products[index] = element;
        size++;
    }

    public boolean addAll(Collection<? extends T> c) {
        doSnapshot();
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
        doSnapshot();
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

    private void checkRangeAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void ensureCapacity(int minSize) {
        if (minSize > products.length) {
            grow(minSize);
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
        doSnapshot();
        int index = 0;
        if ((index = indexOf(o)) >= 0) {
            System.arraycopy(products, index + 1, products, index,
                    size - index);
            size--;
            return true;
        }
        return false;
    }

    public T remove(int index) {
        doSnapshot();
        checkRange(index);
        T removed = (T) products[index];
        System.arraycopy(products, index + 1, products, index, size - index);
        size--;
        return removed;
    }

    public boolean removeAll(Collection<?> c) {
        doSnapshot();
        boolean isModified = false;
        for (Object o : c) {
            int index = 0;
            for (int i = 0; i < size; i++) {
                if (Objects.equals(o, products[index])) {
                    System.arraycopy(products, index + 1, products, index, size - index);
                    products[--size] = null;
                    isModified = true;
                    continue;
                }
                index++;
            }
        }
        return isModified;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        doSnapshot();
        Object[] result = new Object[size];
        int newSize = 0;
        boolean isModified = false;
        for (int i = 0; i < size; i++) {
            for (Object o : c) {
                if (Objects.equals(o, products[i])) {
                    result[newSize++] = o;
                    isModified = true;
                }
            }
        }
        size = newSize;
        products = result;
        return isModified;
    }

    public void clear() {
        doSnapshot();
        for (int i = 0; i < size; i++)
            products[i] = null;
        size = 0;
    }

    public T get(int index) {
        checkRange(index);
        return (T) products[index];
    }

    public T set(int index, T element) {
        doSnapshot();
        checkRange(index);
        T oldValue = (T) products[index];
        products[index] = element;
        return oldValue;
    }

    private void checkRange(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public int indexOf(Object o) {
        for (int i = 0; i < products.length; i++) {
            if (Objects.equals(o, products[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i > 0; i--)
            if (Objects.equals(o, products[i]))
                return i;
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
        private boolean isRemovable = false;

        public boolean hasNext() {
            return cursor != size;
        }

        public T next() {
            if (cursor >= size)
                throw new NoSuchElementException();
            isRemovable = true;
            return (T) products[cursor++];
        }

        public void remove() {
            if (cursor - 1 < 0)
                throw new IllegalStateException();
            CopyOnWriteProductContainer.this.remove(cursor - 1);
            cursor = cursor - 1;
            isRemovable = false;
        }

    }

    private class COWIterator<T> implements Iterator<T> {
        private Object[] snapshot;
        private int cursor;
        private int lastReturned = -1;

        public COWIterator() {
            this.snapshot = products;
            isChangeSafe = true;
        }

        public boolean hasNext() {
            return cursor != size;
        }

        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            return (T) snapshot[cursor++];
        }
    }
}
