package com.scottlessans.urcontroller.parsers;

/**
 * Created by slessans on 1/20/16.
 */
public class UnknownMessageTypeException extends Exception {

    public final int unknownValue;

    public UnknownMessageTypeException(int unknownValue) {
        this.unknownValue = unknownValue;
    }
}
