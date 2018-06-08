package com.epam.preprod.eshop.network.parser.implementation;

import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.parser.RequestParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TcpParserTest {
    private final String REQUEST_ITEM = "get item=1";
    private final String REQUEST_COUNT = "get count";
    private RequestParser parser;
    private BufferedReader reader;
    private Request expectedRequest;
    private Map<String, String> arguments;

    @Before
    public void setUp() {
        parser = new TcpParser();
        reader = mock(BufferedReader.class);
        arguments = new HashMap<>();
    }

    @Test
    public void shouldContainsJustRowDataAndCommand() throws IOException {
        when(reader.readLine()).thenReturn(REQUEST_COUNT);

        expectedRequest = new Request(REQUEST_COUNT, "count", arguments);

        Request actuallyRequest = parser.parseRequest(reader);
        Assert.assertEquals(expectedRequest, actuallyRequest);
        Assert.assertTrue(actuallyRequest.getArguments().isEmpty());
    }

    @Test
    public void shouldContainsRowDataCommandAndOneArgument() throws IOException {
        when(reader.readLine()).thenReturn(REQUEST_ITEM);

        arguments.put("item", "1");
        expectedRequest = new Request(REQUEST_ITEM, "item", arguments);

        Request actuallyRequest = parser.parseRequest(reader);
        Assert.assertEquals(expectedRequest, actuallyRequest);
        Assert.assertFalse(actuallyRequest.getArguments().isEmpty());
        Assert.assertEquals("1", actuallyRequest.getArgument("item"));
    }
}
