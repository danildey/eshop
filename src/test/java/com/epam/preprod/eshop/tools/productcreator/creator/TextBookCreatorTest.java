package com.epam.preprod.eshop.tools.productcreator.creator;

import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.tools.inputinteraction.InputInteraction;
import com.epam.preprod.eshop.tools.productcreator.Creator;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextBookCreatorTest {
    private final String STRING_FIELD = "field";
    private final int INTEGER_FIELD = 1;
    private final long LONG_FIELD = 1L;
    private final LocalDateTime DATE_FIELD = LocalDateTime.now();
    private final boolean BOOLEAN_FIELD = true;

    private Creator<TextBook> creator;
    private InputInteraction interaction;

    @Before
    public void startUp() {
        interaction = mock(InputInteraction.class);
        when(interaction.readString(any(String.class))).thenReturn(STRING_FIELD);
        when(interaction.readInteger(any(String.class))).thenReturn(INTEGER_FIELD);
        when(interaction.readLocaleDate(any(String.class))).thenReturn(DATE_FIELD);
        when(interaction.readLong(any(String.class))).thenReturn(LONG_FIELD);
        when(interaction.readBoolean(any(String.class))).thenReturn(BOOLEAN_FIELD);
        creator = new TextBookCreator(interaction);
    }

    @Test
    public void create() {
        TextBook created = creator.create();
        assertEquals(LONG_FIELD, created.getId());
        assertEquals(STRING_FIELD, created.getName());
        assertEquals(STRING_FIELD, created.getDescription(), STRING_FIELD);
        assertEquals(LONG_FIELD, created.getPrice());
        assertEquals(INTEGER_FIELD, created.getIsbn());
        assertEquals(INTEGER_FIELD, created.getPages());
        assertEquals(STRING_FIELD, created.getAuthor());
        assertEquals(STRING_FIELD, created.getGenre());
        assertEquals(BOOLEAN_FIELD, created.isHardBack());
        assertEquals(STRING_FIELD, created.getSubjectArea());
        assertEquals(STRING_FIELD, created.getClassification());
    }


}