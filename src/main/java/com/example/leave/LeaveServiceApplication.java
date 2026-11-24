package com.example.leave;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.io.IOException;

public class LeaveServiceApplication {

    public static void main(String[] args) throws IOException {

        String environment = System.getenv("APP_ENV");

        if (environment == null) {
            environment = "local";
        }

        LeaveCalculator calculator = new LeaveCalculator();

        int remainingLeave = calculator.calculateRemainingLeave(24, 8);

        double encashment =
                calculator.calculateLeaveEncashment(remainingLeave, 1500.0);

        String response =
                "Employee Leave Service\n" +
                "Environment: " + environment + "\n" +
                "Total Leave: 24\n" +
                "Leave Taken: 8\n" +
                "Remaining Leave: " + remainingLeave + "\n" +
                "Leave Encashment: Rs. " + encashment + "\n";

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {
            byte[] bytes = response.getBytes();

            exchange.getResponseHeaders()
                    .set("Content-Type", "text/plain");

            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });

        server.start();

        System.out.println("Leave Service running on port 8080");
        System.out.println("Environment: " + environment);
    }
}
