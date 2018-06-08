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

public class HttpParserTest {
    private final String REQUEST_ITEM = "GET /shop/item?get_info=2  HTTP/1.1";
    private final String REQUEST_WRONG_PARAMETER = "GET /shop/item?get_info=dasda  HTTP/1.1";
    private final String REQUEST_COUNT = "GET /shop/count HTTP/1.1";
    private final String SEPARATOR = "\r\n\r\n";

    private RequestParser parser;
    private BufferedReader reader;
    private Request expectedRequest;
    private Map<String, String> arguments;

    @Before
    public void setUp() {
        parser = new HttpParser();
        reader = mock(BufferedReader.class);
        arguments = new HashMap<>();
    }

    @Test
    public void shouldContainsJustRowDataAndCommand() throws IOException {
        when(reader.readLine()).thenReturn(REQUEST_COUNT).thenReturn(SEPARATOR);

        expectedRequest = new Request(REQUEST_COUNT + "\r\n", "count", arguments);
        Request actuallyRequest = parser.parseRequest(reader);

        Assert.assertEquals(expectedRequest, actuallyRequest);
        Assert.assertTrue(actuallyRequest.getArguments().isEmpty());
    }

    @Test
    public void shouldContainsRowDataCommandAndOneArgument() throws IOException {
        when(reader.readLine()).thenReturn(REQUEST_ITEM).thenReturn(SEPARATOR);

        arguments.put("item", "2");
        expectedRequest = new Request(REQUEST_ITEM + "\r\n", "item", arguments);

        Request actuallyRequest = parser.parseRequest(reader);
        Assert.assertEquals(expectedRequest, actuallyRequest);
        Assert.assertFalse(actuallyRequest.getArguments().isEmpty());
        Assert.assertEquals("2", actuallyRequest.getArgument("item"));
    }

    @Test
    public void shouldContainsJustRowData() throws IOException {
        when(reader.readLine()).thenReturn(REQUEST_WRONG_PARAMETER).thenReturn(SEPARATOR);

        expectedRequest = new Request(REQUEST_WRONG_PARAMETER + "\r\n", "item", arguments);

        Request actuallyRequest = parser.parseRequest(reader);
        Assert.assertEquals(expectedRequest, actuallyRequest);
        Assert.assertTrue(actuallyRequest.getArguments().isEmpty());
    }
}