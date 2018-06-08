package com.epam.preprod.eshop.exceptions;

public class DuplicateElementException extends IllegalArgumentException {

    public DuplicateElementException() {
        super();
    }

    public DuplicateElementException(String s) {
        super(s);
    }
}
