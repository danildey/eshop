package com.epam.preprod.eshop.network.server.handler;

import com.epam.preprod.eshop.network.exchange.Request;
import com.epam.preprod.eshop.network.networkcommand.NetworkCommandContainer;
import com.epam.preprod.eshop.network.parser.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket client;
    private RequestParser parser;
    private NetworkCommandContainer commandContainer;

    public ServerHandler(Socket client, NetworkCommandContainer container, RequestParser parser) {
        this.client = client;
        this.parser = parser;
        this.commandContainer = container;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            while (!client.isClosed()) {
                Request request = parser.parseRequest(in);
                String response = commandContainer.getCommand(request.getCommand()).execute(request);
                out.println(response);
            }
        } catch (IOException e) {
            System.out.println("Connection problem");
        }
    }
}
