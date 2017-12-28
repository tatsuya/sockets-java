package com.tatsuyaoiw.sockets;

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
             // If automatic flushing is enabled it will be done only when one of the println, printf, or format methods
             // is invoked, rather than whenever a newline character happens to be output.
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            // The while loop reads a line at a time from the standard input stream and immediately sends it to the
            // server by writing it to the PrintWriter connected to the socket.
            //
            // The while loop continues until the user types an end-of-input character. The while loop then terminates,
            // and the Java runtime automatically closes the readers and writers connected to the socket and to the
            // standard input stream, and it closes the socket connection to the server. The Java runtime closes these
            // resources automatically because they were created in the try-with-resources statement. The Java runtime
            // closes these resources in reverse order that they were created. (This is good because streams connected
            // to a socket should be closed before the socket itself is closed.)
            String userInput;
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
