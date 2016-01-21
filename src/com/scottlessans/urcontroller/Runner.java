package com.scottlessans.urcontroller;


import com.scottlessans.urcontroller.decoders.DecodeException;
import com.scottlessans.urcontroller.models.Message;
import com.scottlessans.urcontroller.models.packages.Package;
import com.scottlessans.urcontroller.parsers.MessageParser;
import com.scottlessans.urcontroller.parsers.UnknownMessageTypeException;

import java.io.IOException;
import java.io.InputStream;

public class Runner {

    final private InputStream _inputStream;

    public Runner(InputStream inputStream) {
        this._inputStream = inputStream;
    }

    public void run() throws IOException, DecodeException {
        final MessageParser parser = new MessageParser(this._inputStream);

        while (true) {
            try {
                final Message message = parser.parse();

                System.out.println("----------------------------------");
                System.out.println("Received message: " + message.type);
                System.out.println("Total packages: " + message.packages.size());

                int i = 0;
                for(final Package p : message.packages) {
                    System.out.println("");
                    System.out.println("------ Package " + i + " ------");
                    System.out.println(p);
                    i++;
                }
                System.out.println("----------------------------------");
                System.out.println("");

            } catch (UnknownMessageTypeException e) {
                System.err.println("Unknown message type: " + e.unknownValue);
                System.err.println("Skipping message and waiting for new...");
            }
        }
    }

}
