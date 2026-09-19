package com.BookingApp.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {

    public static void main(String[] args)
            throws IOException {

        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8080),
                        0
                );

        // Test API
        server.createContext(
                "/api/test",
                exchange -> {

                    String response =
                            "Bus Booking API is working!";

                    sendResponse(
                            exchange,
                            200,
                            response
                    );
                }
        );

        // Bus API
        server.createContext(
                "/api/buses",
                new BusApi()
        );

        // Booking API
        server.createContext(
                "/api/bookings",
                new BookingApi()
        );

        server.start();

        System.out.println(
                "================================="
        );
        System.out.println(
                "BUS BOOKING API STARTED"
        );
        System.out.println(
                "Port: 8080"
        );
        System.out.println(
                "================================="
        );
        System.out.println(
                "Test:"
        );
        System.out.println(
                "http://localhost:8080/api/test"
        );
        System.out.println(
                "Buses:"
        );
        System.out.println(
                "http://localhost:8080/api/buses"
        );
    }

    public static void sendResponse(
            HttpExchange exchange,
            int statusCode,
            String response
    ) throws IOException {

        exchange.getResponseHeaders()
                .set(
                        "Content-Type",
                        "application/json; charset=UTF-8"
                );

        byte[] responseBytes =
                response.getBytes(
                        java.nio.charset.StandardCharsets.UTF_8
                );

        exchange.sendResponseHeaders(
                statusCode,
                responseBytes.length
        );

        exchange.getResponseBody()
                .write(responseBytes);

        exchange.getResponseBody()
                .close();
    }
}