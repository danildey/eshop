package com.epam.preprod.eshop.network.server;

import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.network.networkcommand.HttpCommand;
import com.epam.preprod.eshop.network.networkcommand.NetworkCommandContainer;
import com.epam.preprod.eshop.network.networkcommand.http.ErrorHttpCommand;
import com.epam.preprod.eshop.network.networkcommand.http.GetProductByIdHttpCommand;
import com.epam.preprod.eshop.network.networkcommand.http.GetProductCountHttpCommand;
import com.epam.preprod.eshop.network.parser.RequestParser;
import com.epam.preprod.eshop.network.parser.implementation.HttpParser;
import com.epam.preprod.eshop.network.util.HttpUtil;
import com.epam.preprod.eshop.service.FacadeService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HttpShopServerTest {
    private final String SEPARATOR = "\r\n\r\n";
    private final String HOST = "localhost";
    private final String REQUEST_ITEM = "GET /shop/item?get_info=1  HTTP/1.1 " + SEPARATOR;
    private final String REQUEST_WRONG_PARAMETER = "GET /shop/item?get_info=5  HTTP/1.1 " + SEPARATOR;
    private final String REQUEST_COUNT = "GET /shop/count HTTP/1.1 " + SEPARATOR;
    private final String REQUEST_ERROR = "GET /shop/ HTTP/1.1 " + SEPARATOR;
    private final int PORT = 9090;

    private NetworkCommandContainer<HttpCommand> container;
    private RequestParser parser;
    private ShopServer server;
    private Map<String, HttpCommand> commands;

    private FacadeService facadeService;
    private HashMap map;
    private TextBook product;

    private BufferedReader fromServer;
    private PrintWriter toServer;
    private Socket socket;

    @Before
    public void startServer() throws IOException {
        facadeService = mock(FacadeService.class);
        product = mock(TextBook.class);
        map = mock(HashMap.class);

        when(map.size()).thenReturn(1);
        when(product.getName()).thenReturn("name");
        when(product.getPrice()).thenReturn(1L);
        when(facadeService.getProduct(1L)).thenReturn(product);
        when(facadeService.getAllProducts()).thenReturn(map);

        commands = new HashMap<>();
        commands.put("item", new GetProductByIdHttpCommand(facadeService));
        commands.put("count", new GetProductCountHttpCommand(facadeService));
        commands.put("Error", new ErrorHttpCommand(facadeService));

        parser = new HttpParser();
        container = new NetworkCommandContainer<>(commands);
        server = new ShopServer(PORT, parser, container);
        server.start();

        socket = new Socket(HOST, PORT);
        fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer = new PrintWriter(socket.getOutputStream(), true);
    }

    @After
    public void closeConnection() throws IOException {
        socket.close();
        fromServer.close();
        toServer.close();
    }

    @Test
    public void shouldReturnCountOfProducts() throws IOException {
        toServer.println(REQUEST_COUNT);
        HttpUtil.readHeaders(fromServer);
        String response = fromServer.readLine();
        Assert.assertEquals("{count: 1}", response);
    }

    @Test
    public void shouldReturnProductInJson() throws IOException {
        toServer.println(REQUEST_ITEM);
        HttpUtil.readHeaders(fromServer);
        String response = fromServer.readLine();
        Assert.assertEquals("{name: name, price: 1}", response);
    }

    @Test
    public void shouldReturnWrongCommandMessage() throws IOException {
        toServer.println(REQUEST_ERROR);
        HttpUtil.readHeaders(fromServer);
        String response = fromServer.readLine();
        Assert.assertEquals("Wrong Command", response);
    }

    @Test
    public void shouldReturnWrongParameterMessage() throws IOException {
        toServer.println(REQUEST_WRONG_PARAMETER);
        HttpUtil.readHeaders(fromServer);
        String response = fromServer.readLine();
        Assert.assertEquals("Wrong parameter", response);
    }
}