package com.BookingApp.api;

import com.BookingApp.Bus;
import com.BookingApp.BusDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BusApi implements HttpHandler {

    private BusDAO busDAO = new BusDAO();

    @Override
    public void handle(HttpExchange exchange)
            throws IOException {

        System.out.println(
                "BUS API REQUEST RECEIVED"
        );

        String method =
                exchange.getRequestMethod();

        URI uri =
                exchange.getRequestURI();

        String path =
                uri.getPath();

        try {

            if (method.equalsIgnoreCase("GET")) {

                System.out.println(
                        "GET /api/buses request received"
                );

                handleGet(exchange, path);

            } else if (
                    method.equalsIgnoreCase("POST")
            ) {

                handlePost(exchange);

            } else if (
                    method.equalsIgnoreCase("PUT")
            ) {

                handlePut(exchange, path);

            } else if (
                    method.equalsIgnoreCase("DELETE")
            ) {

                handleDelete(exchange, path);

            } else {

                ApiServer.sendResponse(
                        exchange,
                        405,
                        "{\"message\":\"Method not allowed\"}"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR IN BUS API:"
            );

            e.printStackTrace();

            ApiServer.sendResponse(
                    exchange,
                    500,
                    "{\"message\":\"Internal server error\"}"
            );
        }
    }

    // ==============================
    // GET
    // ==============================

    private void handleGet(
            HttpExchange exchange,
            String path
    ) throws IOException {

        String prefix =
                "/api/buses";

        // GET /api/buses
        if (path.equals(prefix)) {

            List<Bus> buses =
                    busDAO.getAllBuses();

            StringBuilder json =
                    new StringBuilder();

            json.append("[");

            for (
                    int i = 0;
                    i < buses.size();
                    i++
            ) {

                Bus bus =
                        buses.get(i);

                json.append(
                        busToJson(bus)
                );

                if (
                        i < buses.size() - 1
                ) {
                    json.append(",");
                }
            }

            json.append("]");

            ApiServer.sendResponse(
                    exchange,
                    200,
                    json.toString()
            );

            return;
        }

        // GET /api/buses/BUS01
        if (
                path.startsWith(
                        prefix + "/"
                )
        ) {

            String busId =
                    path.substring(
                            prefix.length() + 1
                    );

            Bus bus =
                    busDAO.getBusById(busId);

            if (bus == null) {

                ApiServer.sendResponse(
                        exchange,
                        404,
                        "{\"message\":\"Bus not found\"}"
                );

                return;
            }

            ApiServer.sendResponse(
                    exchange,
                    200,
                    busToJson(bus)
            );

            return;
        }

        ApiServer.sendResponse(
                exchange,
                404,
                "{\"message\":\"Invalid bus URL\"}"
        );
    }

    // ==============================
    // POST
    // ==============================

    private void handlePost(
            HttpExchange exchange
    ) throws IOException {

        String body =
                new String(
                        exchange.getRequestBody()
                                .readAllBytes(),
                        StandardCharsets.UTF_8
                );

        System.out.println(
                "POST DATA: " + body
        );

        String busId =
                getJsonValue(
                        body,
                        "busId"
                );

        String busNumber =
                getJsonValue(
                        body,
                        "busNumber"
                );

        String busName =
                getJsonValue(
                        body,
                        "busName"
                );

        String source =
                getJsonValue(
                        body,
                        "source"
                );

        String destination =
                getJsonValue(
                        body,
                        "destination"
                );

        String journeyDate =
                getJsonValue(
                        body,
                        "journeyDate"
                );

        String departureTime =
                getJsonValue(
                        body,
                        "departureTime"
                );

        String arrivalTime =
                getJsonValue(
                        body,
                        "arrivalTime"
                );

        String priceText =
                getJsonValue(
                        body,
                        "price"
                );

        String totalSeatsText =
                getJsonValue(
                        body,
                        "totalSeats"
                );

        String availableSeatsText =
                getJsonValue(
                        body,
                        "availableSeats"
                );

        if (
                busId == null
                        ||
                        busNumber == null
                        ||
                        busName == null
                        ||
                        source == null
                        ||
                        destination == null
        ) {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Missing required fields\"}"
            );

            return;
        }

        if (
                priceText == null
                        ||
                        totalSeatsText == null
                        ||
                        availableSeatsText == null
        ) {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Missing seat or price fields\"}"
            );

            return;
        }

        double price;

        int totalSeats;

        int availableSeats;

        try {

            price =
                    Double.parseDouble(
                            priceText
                    );

            totalSeats =
                    Integer.parseInt(
                            totalSeatsText
                    );

            availableSeats =
                    Integer.parseInt(
                            availableSeatsText
                    );

        } catch (NumberFormatException e) {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Invalid number format\"}"
            );

            return;
        }

        Bus bus =
                new Bus(
                        busId,
                        busNumber,
                        busName,
                        source,
                        destination,
                        journeyDate,
                        departureTime,
                        arrivalTime,
                        price,
                        totalSeats,
                        availableSeats
                );

        boolean result =
                busDAO.addBus(bus);

        if (result) {

            ApiServer.sendResponse(
                    exchange,
                    201,
                    "{\"message\":\"Bus created successfully\"}"
            );

        } else {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Failed to create bus\"}"
            );
        }
    }

    // ==============================
    // PUT
    // ==============================

    private void handlePut(
            HttpExchange exchange,
            String path
    ) throws IOException {

        String prefix =
                "/api/buses/";

        if (!path.startsWith(prefix)) {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Bus ID is required\"}"
            );

            return;
        }

        String busId =
                path.substring(
                        prefix.length()
                );

        Bus existingBus =
                busDAO.getBusById(busId);

        if (existingBus == null) {

            ApiServer.sendResponse(
                    exchange,
                    404,
                    "{\"message\":\"Bus not found\"}"
            );

            return;
        }

        String body =
                new String(
                        exchange.getRequestBody()
                                .readAllBytes(),
                        StandardCharsets.UTF_8
                );

        String busNumber =
                getJsonValue(
                        body,
                        "busNumber"
                );

        String busName =
                getJsonValue(
                        body,
                        "busName"
                );

        String source =
                getJsonValue(
                        body,
                        "source"
                );

        String destination =
                getJsonValue(
                        body,
                        "destination"
                );

        String journeyDate =
                getJsonValue(
                        body,
                        "journeyDate"
                );

        String departureTime =
                getJsonValue(
                        body,
                        "departureTime"
                );

        String arrivalTime =
                getJsonValue(
                        body,
                        "arrivalTime"
                );

        String priceText =
                getJsonValue(
                        body,
                        "price"
                );

        String totalSeatsText =
                getJsonValue(
                        body,
                        "totalSeats"
                );

        String availableSeatsText =
                getJsonValue(
                        body,
                        "availableSeats"
                );

        if (busNumber != null) {

            existingBus.setBusNumber(
                    busNumber
            );
        }

        if (busName != null) {

            existingBus.setBusName(
                    busName
            );
        }

        if (source != null) {

            existingBus.setSource(
                    source
            );
        }

        if (destination != null) {

            existingBus.setDestination(
                    destination
            );
        }

        if (journeyDate != null) {

            existingBus.setJourneyDate(
                    journeyDate
            );
        }

        if (departureTime != null) {

            existingBus.setDepartureTime(
                    departureTime
            );
        }

        if (arrivalTime != null) {

            existingBus.setArrivalTime(
                    arrivalTime
            );
        }

        if (priceText != null) {

            try {

                existingBus.setPrice(
                        Double.parseDouble(
                                priceText
                        )
                );

            } catch (
                    NumberFormatException e
            ) {

                ApiServer.sendResponse(
                        exchange,
                        400,
                        "{\"message\":\"Invalid price\"}"
                );

                return;
            }
        }

        if (totalSeatsText != null) {

            try {

                existingBus.setTotalSeats(
                        Integer.parseInt(
                                totalSeatsText
                        )
                );

            } catch (
                    NumberFormatException e
            ) {

                ApiServer.sendResponse(
                        exchange,
                        400,
                        "{\"message\":\"Invalid total seats\"}"
                );

                return;
            }
        }

        if (availableSeatsText != null) {

            try {

                existingBus.setAvailableSeats(
                        Integer.parseInt(
                                availableSeatsText
                        )
                );

            } catch (
                    NumberFormatException e
            ) {

                ApiServer.sendResponse(
                        exchange,
                        400,
                        "{\"message\":\"Invalid available seats\"}"
                );

                return;
            }
        }

        boolean result =
                busDAO.updateBus(
                        existingBus
                );

        if (result) {

            ApiServer.sendResponse(
                    exchange,
                    200,
                    "{\"message\":\"Bus updated successfully\"}"
            );

        } else {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Failed to update bus\"}"
            );
        }
    }

    // ==============================
    // DELETE
    // ==============================

    private void handleDelete(
            HttpExchange exchange,
            String path
    ) throws IOException {

        String prefix =
                "/api/buses/";

        if (!path.startsWith(prefix)) {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Bus ID is required\"}"
            );

            return;
        }

        String busId =
                path.substring(
                        prefix.length()
                );

        boolean result =
                busDAO.deleteBus(busId);

        if (result) {

            ApiServer.sendResponse(
                    exchange,
                    200,
                    "{\"message\":\"Bus deleted successfully\"}"
            );

        } else {

            ApiServer.sendResponse(
                    exchange,
                    404,
                    "{\"message\":\"Bus not found\"}"
            );
        }
    }

    // ==============================
    // Convert Bus to JSON
    // ==============================

    private String busToJson(
            Bus bus
    ) {

        return "{"

                + "\"busId\":\""
                + escape(
                bus.getBusId()
        )
                + "\","

                + "\"busNumber\":\""
                + escape(
                bus.getBusNumber()
        )
                + "\","

                + "\"busName\":\""
                + escape(
                bus.getBusName()
        )
                + "\","

                + "\"source\":\""
                + escape(
                bus.getSource()
        )
                + "\","

                + "\"destination\":\""
                + escape(
                bus.getDestination()
        )
                + "\","

                + "\"journeyDate\":\""
                + escape(
                bus.getJourneyDate()
        )
                + "\","

                + "\"departureTime\":\""
                + escape(
                bus.getDepartureTime()
        )
                + "\","

                + "\"arrivalTime\":\""
                + escape(
                bus.getArrivalTime()
        )
                + "\","

                + "\"price\":"
                + bus.getPrice()
                + ","

                + "\"totalSeats\":"
                + bus.getTotalSeats()
                + ","

                + "\"availableSeats\":"
                + bus.getAvailableSeats()

                + "}";
    }

    // ==============================
    // Read JSON value
    // ==============================

    private String getJsonValue(
            String json,
            String key
    ) {

        String search =
                "\"" + key + "\"";

        int keyIndex =
                json.indexOf(search);

        if (keyIndex == -1) {

            return null;
        }

        int colonIndex =
                json.indexOf(
                        ":",
                        keyIndex
                );

        if (colonIndex == -1) {

            return null;
        }

        int start =
                colonIndex + 1;

        while (
                start < json.length()
                        &&
                        Character.isWhitespace(
                                json.charAt(start)
                        )
        ) {

            start++;
        }

        if (
                start < json.length()
                        &&
                        json.charAt(start) == '"'
        ) {

            start++;

            int end =
                    json.indexOf(
                            "\"",
                            start
                    );

            if (end == -1) {

                return null;
            }

            return json.substring(
                    start,
                    end
            );
        }

        int end =
                json.indexOf(
                        ",",
                        start
                );

        if (end == -1) {

            end =
                    json.indexOf(
                            "}",
                            start
                    );
        }

        if (end == -1) {

            return null;
        }

        return json.substring(
                start,
                end
        ).trim();
    }

    // ==============================
    // Escape JSON characters
    // ==============================

    private String escape(
            String value
    ) {

        if (value == null) {

            return "";
        }

        return value
                .replace(
                        "\\",
                        "\\\\"
                )
                .replace(
                        "\"",
                        "\\\""
                );
    }
}