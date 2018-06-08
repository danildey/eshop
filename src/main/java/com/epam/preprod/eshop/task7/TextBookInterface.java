package com.epam.preprod.eshop.task7;

import java.time.LocalDateTime;

public interface TextBookInterface {
    void setName(String name);

    void setId(long id);

    void setPrice(long price);

    void setDescription(String description);

    void setPublisher(String publisher);

    void setIsbn(int isbn);

    void setGenre(String genre);

    void setAuthor(String author);

    void setPublicationDate(LocalDateTime publicationDate);

    void setClassification(String classification);

    void setSubjectArea(String subjectArea);

    void setPages(int pages);

    void setHardBack(boolean hardBack);

    long getId();

    long getPrice();

    int getIsbn();

    int getPages();

    String getName();

    String getDescription();

    String getPublisher();

    String getAuthor();

    String getGenre();

    String getSubjectArea();

    String getClassification();

    LocalDateTime getPublicationDate();

    boolean isHardBack();

}
