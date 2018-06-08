package com.epam.preprod.eshop.collections;

import com.epam.preprod.eshop.exceptions.IndexOutOfModifiableArrayException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SemiModifiableContainer<T> implements List<T> {

    private List<T> unmodifiable;

    private List<T> modifiable;

    public SemiModifiableContainer(List<T> unmodifiable, List<T> modifiable) {
        if (unmodifiable == null || modifiable == null) {
            throw new IllegalArgumentException();
        }
        this.unmodifiable = unmodifiable;
        this.modifiable = modifiable;
    }

    @Override
    public int size() {
        return unmodifiable.size() + modifiable.size();
    }

    @Override
    public boolean isEmpty() {
        return unmodifiable.isEmpty() && modifiable.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return new SemiModifiableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] totalArray = new Object[size()];
        System.arraycopy(unmodifiable.toArray(), 0, totalArray, 0, unmodifiable.size());
        System.arraycopy(modifiable.toArray(), 0, totalArray, unmodifiable.size(), modifiable.size());
        return totalArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size())
            return (T[]) Arrays.copyOf(toArray(), size(), a.getClass());
        System.arraycopy(toArray(), 0, a, 0, size());
        if (a.length > size())
            a[size()] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        return modifiable.add(t);
    }

    @Override
    public boolean remove(Object o) {
        if (unmodifiable.contains(o)) {
            throw new IndexOutOfModifiableArrayException("Object is unmodifiable.");
        }
        return modifiable.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return unmodifiable.contains(o) || modifiable.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean contains = false;
        for (Object o : c) {
            contains = contains(o);
            if (!contains) {
                break;
            }
        }
        return contains;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return modifiable.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < unmodifiable.size()) {
            throw new IndexOutOfModifiableArrayException();
        }
        return modifiable.addAll(index - unmodifiable.size(), c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            if (unmodifiable.contains(o)) {
                throw new IndexOutOfModifiableArrayException();
            }
        }
        return modifiable.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (!c.containsAll(unmodifiable)) {
            throw new IndexOutOfModifiableArrayException();
        }
        return modifiable.retainAll(c);
    }

    @Override
    public void clear() {
        if (unmodifiable.isEmpty()) {
            modifiable.clear();
        } else {
            throw new UnsupportedOperationException("List contains unmodifiable elements.");
        }
    }

    @Override
    public T get(int index) {
        if (index < unmodifiable.size()) {
            return unmodifiable.get(index);
        } else {
            return modifiable.get(index - unmodifiable.size());
        }
    }

    @Override
    public T set(int index, T element) {
        if (index < unmodifiable.size()) {
            throw new IndexOutOfModifiableArrayException();
        } else {
            return modifiable.set(index - unmodifiable.size(), element);
        }
    }

    @Override
    public void add(int index, T element) {
        if (index < unmodifiable.size()) {
            throw new IndexOutOfModifiableArrayException();
        } else {
            modifiable.add(index - unmodifiable.size(), element);
        }
    }

    @Override
    public T remove(int index) {
        if (index < unmodifiable.size()) {
            throw new IndexOutOfModifiableArrayException();
        } else {
            return modifiable.remove(index - unmodifiable.size());
        }
    }

    @Override
    public int indexOf(Object o) {
        int index;
        if ((index = unmodifiable.indexOf(o)) >= 0) {
            return index;
        } else if ((index = modifiable.indexOf(o)) >= 0) {
            return index += unmodifiable.size();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index;
        if ((index = modifiable.lastIndexOf(o)) >= 0) {
            return index += unmodifiable.size();
        } else if ((index = unmodifiable.indexOf(o)) >= 0) {
            return index = unmodifiable.lastIndexOf(o);
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private class SemiModifiableIterator implements Iterator<T> {
        Iterator<T> unmodIter;
        Iterator<T> modIter;
        Iterator<T> currentItr;

        boolean isRemovable = false;

        public SemiModifiableIterator() {
            unmodIter = unmodifiable.iterator();
            modIter = modifiable.iterator();
        }

        public boolean hasNext() {
            if (unmodIter.hasNext()) {
                currentItr = unmodIter;
                return true;
            } else if (modIter.hasNext()) {
                currentItr = modIter;
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return (T) currentItr.next();
            } else {
                throw new NoSuchElementException();
            }
        }


    }
}
