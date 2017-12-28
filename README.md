# sockets-java

Personal note and code examples from [Lesson: All About Sockets](https://docs.oracle.com/javase/tutorial/networking/sockets/index.html).

## What is Socket?

A socket is one end-point of a two-way communication link between two programs running on the network. A socket is bound to a port number so that the TCP layer can identify the application that data is destined to be sent to.

Normally, a server runs on a specific computer and has a socket that is bound to a specific port number. The server just waits, listening to the socket for a client to make a connection request.

On the client-side: The client knows the hostname of the machine on which the server is running and the port number on which the server is listening. To make a connection request, the client tries to rendezvous with the server on the server's machine and port. The client also needs to identify itself to the server so it binds to a local port number that it will use during this connection. This is usually assigned by the system.

If everything goes well, the server accepts the connection. Upon acceptance, the server gets a new socket bound to the same local port and also has its remote endpoint set to the address and port of the client. It needs a new socket so that it can continue to listen to the original socket for connection requests while tending to the needs of the connected client.

On the client side, if the connection is accepted, a socket is successfully created and the client can use the socket to communicate with the server.

The client and server can now communicate by writing to or reading from their sockets.

An endpoint is a combination of an IP address and a port number. Every TCP connection can be uniquely identified by its two endpoints. That way you can have multiple connections between your host and the server.

## Socket classes

Socket classes are used to represent the connection between a client program and a server program. The `java.net` package provides two classes `Socket` and `ServerSocket` that implement the client side of the connection and the server side of the connection, respectively.

## Reading from and Writing to a Socket

The example program implements a client, `EchoClient`, that connects to an echo server. The echo server receives data from its client and echoes it back. The example `EchoServer` implements an echo server.

The `EchoClient` example creates a socket, thereby getting a connection to the echo server. It reads input from the user on the standard input stream, and then forwards that text to the echo server by writing the text to the socket. The server echoes the input back through the socket to the client. The client program reads and displays the data passed back to it from the server.

Note that the `EchoClient` example both writes to and reads from its socket, thereby sending data to and receiving data from the echo server.

The basics are:

1. Open a socket.
2. Open an input stream and output stream to the socket.
3. Read from and write to the stream according to the server's protocol.
4. Close the streams.
5. Close the socket.
