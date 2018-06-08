package com.epam.preprod.eshop.task7.factory;

import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.task7.TextBookInterface;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UnmodifiableTextBookFactoryTest {
    private TextBookFactory factory;
    private TextBookInterface textBook;
    private TextBookInterface unmodifiableTextBook;

    @Before
    public void setUp() throws IllegalAccessException {
        factory = new UnmodifiableTextBookFactory();
        textBook = mock(TextBook.class);
        unmodifiableTextBook = factory.createTextBook(textBook);
    }

    @Test
    public void shouldCallAllGetters() {
        unmodifiableTextBook.getAuthor();
        unmodifiableTextBook.getId();
        unmodifiableTextBook.getPublicationDate();
        unmodifiableTextBook.getClassification();
        unmodifiableTextBook.getSubjectArea();
        unmodifiableTextBook.getGenre();
        unmodifiableTextBook.getPublisher();
        unmodifiableTextBook.getDescription();
        unmodifiableTextBook.getName();
        unmodifiableTextBook.getPages();
        unmodifiableTextBook.getIsbn();
        unmodifiableTextBook.getPrice();
        unmodifiableTextBook.isHardBack();

        verify(textBook, times(1)).getAuthor();
        verify(textBook, times(1)).getId();
        verify(textBook, times(1)).getPublicationDate();
        verify(textBook, times(1)).getClassification();
        verify(textBook, times(1)).getSubjectArea();
        verify(textBook, times(1)).getPublisher();
        verify(textBook, times(1)).getDescription();
        verify(textBook, times(1)).getName();
        verify(textBook, times(1)).getPages();
        verify(textBook, times(1)).getIsbn();
        verify(textBook, times(1)).getPrice();
        verify(textBook, times(1)).isHardBack();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenWeCallSetMethods() {
        unmodifiableTextBook.setPublisher("test");
        verify(textBook, times(0)).setPublisher(anyString());
    }
}