package com.epam.preprod.eshop.wrappers;

public class StringLengthHashWrapper {

    private String str;

    public StringLengthHashWrapper(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Input argument can't be null");
        }
        this.str = str;
    }

    @Override
    public int hashCode() {
        return str.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringLengthHashWrapper that = (StringLengthHashWrapper) o;

        return str.equals(that.str);
    }

    @Override
    public String toString() {
        return str;
    }
}
