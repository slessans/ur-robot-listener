package com.scottlessans.urcontroller;

import com.scottlessans.urcontroller.decoders.DecodeException;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws DecodeException {
        final String hostName = args[0];
        final int portNumber = Integer.parseInt(args[1]);

        try (
                Socket socket = new Socket(hostName, portNumber);
                InputStream inputStream = socket.getInputStream()
        ) {
            new Runner(inputStream).run();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }

}
