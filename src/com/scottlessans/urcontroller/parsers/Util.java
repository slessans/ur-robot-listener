package com.scottlessans.urcontroller.parsers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Util {

    /**
     * Parses 4-byte integer with proper ordering for URC
     * @param byteBuffer buffer to parse from
     */
    public static int parseInteger(ByteBuffer byteBuffer) {
        return byteBuffer.order(ByteOrder.BIG_ENDIAN).getInt();
    }

}
