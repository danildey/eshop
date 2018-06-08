package com.epam.preprod.eshop.collections;

import com.epam.preprod.eshop.exceptions.DuplicateElementException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

public class ProductSet<T> extends ArrayList<T> {

    public ProductSet(Collection<? extends T> c) {
        super(c);
        checkDuplicates(c);
    }

    public ProductSet() {
    }

    @Override
    public boolean add(T element) {
        if (indexOf(element) >= 0) {
            return false;
        }
        return super.add(element);
    }

    @Override
    public void add(int index, T element) {
        if (indexOf(element) >= 0) {
            throw new DuplicateElementException();
        }
        super.add(index, element);
    }

    private void checkDuplicates(Collection<? extends T> c) {
        for (Object o : c) {
            if (Collections.frequency(c, o) > 1) {
                throw new DuplicateElementException();
            }
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        checkDuplicates(c);
        for (T o : c) {
            if (indexOf(o) >= 0) {
                return false;
            }
        }
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkDuplicates(c);
        for (T o : c) {
            if (indexOf(o) >= 0) {
                return false;
            }
        }
        return super.addAll(index, c);
    }

    @Override
    public T set(int index, T element) {
        int indexOf = indexOf(element);
        if ((indexOf != index) && (indexOf >= 0)) {
            throw new DuplicateElementException();
        }
        return super.set(index, element);
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        List<T> snapshot = new ArrayList<>(this);
        for (int i = 0; i < size(); i++) {
            T returned = operator.apply((T) snapshot.get(i));
            int index = snapshot.indexOf(returned);
            if (index == i) {
                continue;
            }
            if (index >= 0) {
                throw new DuplicateElementException();
            }
            snapshot.set(i, returned);
        }
        super.replaceAll(operator);
    }
}
