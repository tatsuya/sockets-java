package com.tatsuyaoiw.sockets.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class EchoServer {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }

        int portNumber = parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(parseInt(args[0]));
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println(format("Exception caught when trying to listen on port %d or listening for a connection", portNumber));
            System.out.println(e.getMessage());
        }
    }
}
