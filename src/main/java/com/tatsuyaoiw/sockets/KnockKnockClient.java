package com.tatsuyaoiw.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.String.format;

public class KnockKnockClient {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java KnockKnockClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (Socket kkSocket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()))) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println(format("Client: %s", fromUser));
                    out.println(fromUser);
                }
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
