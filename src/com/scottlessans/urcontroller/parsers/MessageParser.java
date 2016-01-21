package com.scottlessans.urcontroller.parsers;

import com.scottlessans.urcontroller.decoders.DecodeException;
import com.scottlessans.urcontroller.decoders.URControllerContentDecoder;
import com.scottlessans.urcontroller.models.Message;
import com.scottlessans.urcontroller.models.MessageType;
import com.scottlessans.urcontroller.models.PackageHeader;
import com.scottlessans.urcontroller.models.packages.Package;
import com.scottlessans.urcontroller.parsers.packages.RobotModePackageParser;
import com.scottlessans.urcontroller.parsers.packages.UnknownPackageParser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MessageParser {

    private final PackageParser unknownPackageParser;
    private final Map<Integer, PackageParser> parserMap;

    private final InputStream inputStream;

    public MessageParser(InputStream inputStream) {
        this.inputStream = inputStream;
        this.parserMap = new HashMap<>();
        this.parserMap.put(0, new RobotModePackageParser());
        this.unknownPackageParser = new UnknownPackageParser();
    }

    // parse the next four bytes as an int
    protected int nextFourBytesAsInt() throws IOException {
        // convert to number
        byte [] bytes = new byte[4];
        final int numRead = this.inputStream.read(bytes);
        assert numRead == 4;
        return Util.parseInteger(ByteBuffer.wrap(bytes));
    }

    public Message parse() throws IOException, DecodeException,
            UnknownMessageTypeException {
        // how long is the message in bytes? (subtract 4 so as to include this integer)
        final int messageLength = this.nextFourBytesAsInt() - 4;
        if (messageLength < 1) {
            throw new DecodeException("Message content length is less than 1: " + messageLength);
        }

        final byte[] messageContent = new byte[messageLength];
        final int messageContentByesRead = this.inputStream.read(messageContent);
        if (messageContentByesRead != messageLength) {
            throw new DecodeException(
                    "Could not read " + messageLength + " bytes, only got " + messageContentByesRead
            );
        }

        final ByteBuffer messageBuffer = ByteBuffer.wrap(messageContent);

        // next read the message type, for now the only one we understand is 16
        final int messageTypeValue = Byte.toUnsignedInt(messageBuffer.get());
        if (messageTypeValue != 16) {
            throw new UnknownMessageTypeException(messageTypeValue);
        }

        final MessageType messageType = MessageType.RobotState;
        int bytesRead = 1; // we read 1 for message type
        final List<Package> packages = new LinkedList<>();
        final Message message = new Message(messageType, packages);

        while (bytesRead < messageLength) {
            // length minus 4 bytes for length integer
            final int packageLength = Util.parseInteger(messageBuffer);
            final int packageType = Byte.toUnsignedInt(messageBuffer.get());
            final PackageParser parser = this.parserMap.getOrDefault(packageType, this.unknownPackageParser);
            assert parser != null;

            final int contentLength = packageLength - 5;
            final byte[] content = new byte[contentLength];
            messageBuffer.get(content);
            final ByteBuffer buffer = ByteBuffer.wrap(content);
            final URControllerContentDecoder decoder = new URControllerContentDecoder(buffer);
            final Package p =
                    parser.parse(message, decoder, new PackageHeader(packageLength, packageType, contentLength));
            packages.add(p);
            bytesRead += packageLength;
        }

        return message;
    }

}
