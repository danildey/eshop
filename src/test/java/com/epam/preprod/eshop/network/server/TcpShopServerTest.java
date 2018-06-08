package com.epam.preprod.eshop.network.server;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.entity.TextBook;
import com.epam.preprod.eshop.network.networkcommand.NetworkCommandContainer;
import com.epam.preprod.eshop.network.networkcommand.TcpCommand;
import com.epam.preprod.eshop.network.networkcommand.tcp.ErrorTcpCommand;
import com.epam.preprod.eshop.network.networkcommand.tcp.GetProductByIdTcpCommand;
import com.epam.preprod.eshop.network.networkcommand.tcp.GetProductCountTcpCommand;
import com.epam.preprod.eshop.network.parser.RequestParser;
import com.epam.preprod.eshop.network.parser.implementation.TcpParser;
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

public class TcpShopServerTest {
    private final int PORT = 5050;
    private FacadeService facadeService;
    private HashMap<Product, Integer> map;
    private TextBook product;

    private NetworkCommandContainer<TcpCommand> container;
    private RequestParser parser;
    private ShopServer server;
    private Map<String, TcpCommand> commands;

    private final String HOST = "localhost";
    private final String REQUEST_ITEM = "get item=1";
    private final String REQUEST_WRONG_PARAMETER = "get item = 5";
    private final String REQUEST_COUNT = "get count";
    private final String REQUEST_ERROR = "get lalala";

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
        commands.put("item", new GetProductByIdTcpCommand(facadeService));
        commands.put("count", new GetProductCountTcpCommand(facadeService));
        commands.put("Error", new ErrorTcpCommand(facadeService));

        parser = new TcpParser();
        container = new NetworkCommandContainer<>(commands);
        server = new ShopServer(PORT, parser, container);
        server.setDaemon(true);
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
        String response = fromServer.readLine();
        Assert.assertEquals("1", response);
    }

    @Test
    public void shouldReturnProductNameAndPrice() throws IOException {
        toServer.println(REQUEST_ITEM);
        String response = fromServer.readLine();
        Assert.assertEquals("name | 1", response);
    }

    @Test
    public void shouldReturnWrongCommandMessage() throws IOException {
        toServer.println(REQUEST_ERROR);
        String response = fromServer.readLine();
        Assert.assertEquals("Wrong Command", response);
    }

    @Test
    public void shouldReturnWrongParameterMessage() throws IOException {
        toServer.println(REQUEST_WRONG_PARAMETER);
        String response = fromServer.readLine();
        Assert.assertEquals("Wrong parameter", response);
    }
}