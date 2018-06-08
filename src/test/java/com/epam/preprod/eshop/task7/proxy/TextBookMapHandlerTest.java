package com.epam.preprod.eshop.task7.proxy;

import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.task7.TextBookInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TextBookMapHandlerTest {
    private final String STRING_FIELD = "field";
    private final int INTEGER_FIELD = 1;
    private final long LONG_FIELD = 1L;
    private final LocalDateTime DATE_FIELD = LocalDateTime.now();
    private final boolean BOOLEAN_FIELD = true;

    public TextBookMapHandlerTest() throws IllegalAccessException {
    }

    @Spy
    private Map<String, Object> textBookMap = new HashMap<>();

    @Mock
    private TextBookInterface textBook = new TextBook();

    @InjectMocks
    private TextBookMapHandler handler = new TextBookMapHandler(textBook);

    private TextBookInterface proxy;

    @Before
    public void setUp() {
        proxy = (TextBookInterface) Proxy.newProxyInstance(TextBookInterface.class.getClassLoader(),
                new Class[]{TextBookInterface.class}, handler);
        proxy.setId(LONG_FIELD);
        proxy.setAuthor(STRING_FIELD);
        proxy.setPublisher(STRING_FIELD);
        proxy.setName(STRING_FIELD);
        proxy.setPages(INTEGER_FIELD);
        proxy.setDescription(STRING_FIELD);
        proxy.setPrice(LONG_FIELD);
        proxy.setHardBack(BOOLEAN_FIELD);
        proxy.setPublicationDate(DATE_FIELD);
        proxy.setClassification(STRING_FIELD);
        proxy.setSubjectArea(STRING_FIELD);
        proxy.setGenre(STRING_FIELD);
        proxy.setIsbn(INTEGER_FIELD);
    }

    @Test
    public void shouldNotCallSettersFromTextBook() {
        verify(textBook, times(0)).setId(anyInt());
        verify(textBook, times(0)).setAuthor(anyString());
        verify(textBook, times(0)).setPublisher(anyString());
        verify(textBook, times(0)).setName(anyString());
        verify(textBook, times(0)).setPages(anyInt());
        verify(textBook, times(0)).setIsbn(anyInt());
        verify(textBook, times(0)).setDescription(anyString());
        verify(textBook, times(0)).setPrice(anyInt());
        verify(textBook, times(0)).setHardBack(anyBoolean());
        verify(textBook, times(0)).setPublicationDate(any());
        verify(textBook, times(0)).setClassification(anyString());
        verify(textBook, times(0)).setGenre(anyString());
        verify(textBook, times(0)).setSubjectArea(anyString());
    }

    @Test
    public void shouldCallSettersFromTextBookMap() {
        verify(textBookMap, times(13)).put(anyString(), any());
    }

    @Test
    public void shouldNotCallGettersFromTextBook() {
        assertEquals(LONG_FIELD, proxy.getId());
        assertEquals(STRING_FIELD, proxy.getName());
        assertEquals(STRING_FIELD, proxy.getDescription(), STRING_FIELD);
        assertEquals(LONG_FIELD, proxy.getPrice());
        assertEquals(INTEGER_FIELD, proxy.getIsbn());
        assertEquals(INTEGER_FIELD, proxy.getPages());
        assertEquals(STRING_FIELD, proxy.getAuthor());
        assertEquals(STRING_FIELD, proxy.getGenre());
        assertEquals(BOOLEAN_FIELD, proxy.isHardBack());
        assertEquals(STRING_FIELD, proxy.getSubjectArea());
        assertEquals(STRING_FIELD, proxy.getClassification());
        assertEquals(DATE_FIELD, proxy.getPublicationDate());
        assertEquals(STRING_FIELD, proxy.getPublisher());

        verify(textBook, times(0)).getId();
        verify(textBook, times(0)).getName();
        verify(textBook, times(0)).getDescription();
        verify(textBook, times(0)).getPrice();
        verify(textBook, times(0)).getIsbn();
        verify(textBook, times(0)).getPages();
        verify(textBook, times(0)).getAuthor();
        verify(textBook, times(0)).getGenre();
        verify(textBook, times(0)).isHardBack();
        verify(textBook, times(0)).getClassification();
        verify(textBook, times(0)).getSubjectArea();
        verify(textBook, times(0)).getPublicationDate();
        verify(textBook, times(0)).getPublisher();

        verify(textBookMap, times(13)).get(anyString());

    }
}