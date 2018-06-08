package com.epam.preprod.eshop.wrappers;

public class StringSumHashWrapper {

    private String str;

    final int NUMBER_OF_USED_SYMBOLS = 4;

    public StringSumHashWrapper(String str) {
        if (str == null || str.length() < NUMBER_OF_USED_SYMBOLS) {
            throw new IllegalArgumentException("Input argument can't be null or smaller then "
                    + NUMBER_OF_USED_SYMBOLS + " symbols");
        }
        this.str = str;
    }

    @Override
    public int hashCode() {
        char[] chars = str.toCharArray();
        int hash = 0;
        for (int i = 0; i < NUMBER_OF_USED_SYMBOLS; i++) {
            hash += chars[i];
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringSumHashWrapper that = (StringSumHashWrapper) o;

        return str.equals(that.str);
    }

    @Override
    public String toString() {
        return str;
    }
}
