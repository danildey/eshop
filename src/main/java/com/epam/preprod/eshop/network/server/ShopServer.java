package com.epam.preprod.eshop.network.server;

import com.epam.preprod.eshop.network.networkcommand.NetworkCommandContainer;
import com.epam.preprod.eshop.network.parser.RequestParser;
import com.epam.preprod.eshop.network.server.handler.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ShopServer extends Thread {
    private NetworkCommandContainer container;
    private ExecutorService threadPool;
    private RequestParser parser;
    private int port;

    public ShopServer(int port, RequestParser parser, NetworkCommandContainer container) {
        this.port = port;
        this.parser = parser;
        this.container = container;
    }

    @Override
    public void run() {
        try {
            this.threadPool = new ThreadPoolExecutor(
                    4,
                    64,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(256));
            ServerSocket socketServer = new ServerSocket(port, 256);
            while (true) {
                final Socket socket = socketServer.accept();
                threadPool.submit(new ServerHandler(socket, container, parser));
            }
        } catch (IOException e) {
            System.out.println("Server stopped or port already used.");
        }
    }
}
