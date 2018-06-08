package com.epam.preprod.eshop.network.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

public class HttpUtil {
    private static final String SEPARATOR = "\r\n\r\n";

    public static String readHeaders(BufferedReader in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String currentLine;
        while (!(currentLine = in.readLine()).equals(SEPARATOR)) {
            if (currentLine.isEmpty()) {
                break;
            }
            sb.append(currentLine).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String getURIFromRequest(String request) {
        int from = request.indexOf(" ") + 1;
        int to = request.indexOf(" ", from);

        String uri = null;
        if (to > from) {
            uri = request.substring(from, to);
        }
        return uri;
    }

    public static String wrapResponse(String text) {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Date: " + new Date() + "\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + text.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        return response + text;
    }
}
