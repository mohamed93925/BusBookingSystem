package com.BookingApp.api;

import com.BookingApp.Booking;
import com.BookingApp.BookingDAO;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;

public class BookingApi implements HttpHandler {

    private BookingDAO bookingDAO =
            new BookingDAO();

    @Override
    public void handle(HttpExchange exchange)
            throws IOException {

        String method =
                exchange.getRequestMethod();

        String path =
                exchange.getRequestURI()
                        .getPath();

        try {

            if (method.equalsIgnoreCase("GET")) {

                handleGet(exchange, path);

            } else if (
                    method.equalsIgnoreCase("POST")
            ) {

                handlePost(exchange);

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

            e.printStackTrace();

            ApiServer.sendResponse(
                    exchange,
                    500,
                    "{\"message\":\"Internal server error\"}"
            );
        }
    }

    private void handleGet(
            HttpExchange exchange,
            String path
    ) throws IOException {

        String prefix =
                "/api/bookings";

        if (path.equals(prefix)) {

            List<Booking> bookings =
                    bookingDAO.getAllBookings();

            StringBuilder json =
                    new StringBuilder();

            json.append("[");

            for (
                    int i = 0;
                    i < bookings.size();
                    i++
            ) {

                json.append(
                        bookingToJson(
                                bookings.get(i)
                        )
                );

                if (
                        i <
                                bookings.size() - 1
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

        } else {

            String bookingId =
                    path.substring(
                            prefix.length() + 1
                    );

            Booking booking =
                    bookingDAO.getBookingById(
                            bookingId
                    );

            if (booking == null) {

                ApiServer.sendResponse(
                        exchange,
                        404,
                        "{\"message\":\"Booking not found\"}"
                );

                return;
            }

            ApiServer.sendResponse(
                    exchange,
                    200,
                    bookingToJson(booking)
            );
        }
    }

    private void handlePost(
            HttpExchange exchange
    ) throws IOException {

        String body =
                new String(
                        exchange.getRequestBody()
                                .readAllBytes()
                );

        String bookingId =
                getJsonValue(
                        body,
                        "bookingId"
                );

        String passengerId =
                getJsonValue(
                        body,
                        "passengerId"
                );

        String busId =
                getJsonValue(
                        body,
                        "busId"
                );

        String seatNumber =
                getJsonValue(
                        body,
                        "seatNumber"
                );

        String bookingDate =
                getJsonValue(
                        body,
                        "bookingDate"
                );

        String bookingStatus =
                getJsonValue(
                        body,
                        "bookingStatus"
                );

        if (bookingId == null
                || passengerId == null
                || busId == null
                || seatNumber == null) {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Missing required fields\"}"
            );

            return;
        }

        if (bookingDate == null) {

            bookingDate =
                    java.time.LocalDate
                            .now()
                            .toString();
        }

        if (bookingStatus == null) {

            bookingStatus = "CONFIRMED";
        }

        Booking booking =
                new Booking(
                        bookingId,
                        passengerId,
                        busId,
                        seatNumber,
                        bookingDate,
                        bookingStatus
                );

        boolean result =
                bookingDAO.addBooking(booking);

        if (result) {

            ApiServer.sendResponse(
                    exchange,
                    201,
                    bookingToJson(booking)
            );

        } else {

            ApiServer.sendResponse(
                    exchange,
                    400,
                    "{\"message\":\"Failed to create booking\"}"
            );
        }
    }

    private void handleDelete(
            HttpExchange exchange,
            String path
    ) throws IOException {

        String prefix =
                "/api/bookings/";

        String bookingId =
                path.substring(
                        prefix.length()
                );

        boolean result =
                bookingDAO.deleteBooking(
                        bookingId
                );

        if (result) {

            ApiServer.sendResponse(
                    exchange,
                    200,
                    "{\"message\":\"Booking deleted successfully\"}"
            );

        } else {

            ApiServer.sendResponse(
                    exchange,
                    404,
                    "{\"message\":\"Booking not found\"}"
            );
        }
    }

    private String bookingToJson(
            Booking booking
    ) {

        return "{"
                + "\"bookingId\":\""
                + escape(
                booking.getBookingId()
        )
                + "\","

                + "\"passengerId\":\""
                + escape(
                booking.getPassengerId()
        )
                + "\","

                + "\"busId\":\""
                + escape(
                booking.getBusId()
        )
                + "\","

                + "\"seatNumber\":\""
                + escape(
                booking.getSeatNumber()
        )
                + "\","

                + "\"bookingDate\":\""
                + escape(
                booking.getBookingDate()
        )
                + "\","

                + "\"bookingStatus\":\""
                + escape(
                booking.getBookingStatus()
        )
                + "\""

                + "}";
    }

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

    private String escape(String value) {

        if (value == null) {

            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}