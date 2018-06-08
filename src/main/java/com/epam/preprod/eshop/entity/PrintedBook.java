package com.epam.preprod.eshop.entity;

import com.epam.preprod.eshop.anotation.Init;

public abstract class PrintedBook extends Book {

    private int pages;

    private boolean isHardBack;

    public PrintedBook(int pages, boolean isHardBack) {
        this.pages = pages;
        this.isHardBack = isHardBack;
    }

    public PrintedBook(long id, long price, String name, String description) {
        super(id, price, name, description);
    }

    public PrintedBook() {
    }

    @Init(resource = "printedBookPages")
    public void setPages(int pages) {
        this.pages = pages;
    }

    @Init(resource = "printedBookHardBack")
    public void setHardBack(boolean hardBack) {
        isHardBack = hardBack;
    }

    public int getPages() {
        return pages;
    }

    public boolean isHardBack() {
        return isHardBack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PrintedBook that = (PrintedBook) o;

        if (pages != that.pages) return false;
        return isHardBack == that.isHardBack;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + pages;
        result = 31 * result + (isHardBack ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PrintedBook{" +
                "pages=" + pages +
                ", isHardBack=" + isHardBack +
                '}';
    }
}
