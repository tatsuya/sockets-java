package com.tatsuyaoiw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.String.format;

public class EchoClient {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (Socket echoSocket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String userInput;
            // The loop reads a line at a time from the standard input stream and immediately sends it to the server by
            // writing it to the PrintWriter connected to the socket.
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println(format("echo: %s", in.readLine()));
            }
        } catch (UnknownHostException e) {
            System.err.println(format("Don't know about host %s", hostName));
            System.exit(1);
        } catch (IOException e) {
            System.err.println(format("Couldn't get I/O for the connection to %s", hostName));
            System.exit(1);
        }
    }
}
