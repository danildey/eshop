package com.epam.preprod.eshop.entity;

import com.epam.preprod.eshop.anotation.Init;
import com.epam.preprod.eshop.task7.TextBookInterface;

public class TextBook extends PrintedBook implements TextBookInterface {

    private String subjectArea;

    private String classification;

    public TextBook() {
    }

    public TextBook(String subjectArea, String classification) {
        this.subjectArea = subjectArea;
        this.classification = classification;
    }

    public TextBook(long id, long price, String name, String description, String subjectArea, String classification) {
        super(id, price, name, description);
        this.subjectArea = subjectArea;
        this.classification = classification;
    }

    @Init(resource = "textBookClassification")
    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Init(resource = "textBookSubjectArea")
    public void setSubjectArea(String subjectArea) {
        this.subjectArea = subjectArea;
    }

    public String getSubjectArea() {
        return subjectArea;
    }

    public String getClassification() {
        return classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextBook textBook = (TextBook) o;

        if (!subjectArea.equals(textBook.subjectArea)) return false;
        return classification.equals(textBook.classification);
    }

    @Override
    public int hashCode() {
        int result = subjectArea.hashCode();
        result = 31 * result + classification.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + getId() + "]" + " TextBook{" +
                "subjectArea='" + subjectArea + '\'' +
                '}';
    }
}
