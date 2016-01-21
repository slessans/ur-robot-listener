package com.scottlessans.urcontroller.parsers;


import com.scottlessans.urcontroller.decoders.DecodeException;
import com.scottlessans.urcontroller.decoders.URControllerContentDecoder;
import com.scottlessans.urcontroller.models.Message;
import com.scottlessans.urcontroller.models.PackageHeader;
import com.scottlessans.urcontroller.models.packages.Package;

public interface PackageParser {
    /**
     *
     * @param message the message being parsed. this should only be used to provide a reference
     *                to the message for the Package object, do not modify the message or make
     *                any assumptions that it is fully initialized or in a given state.
     * @param decoder decoder pointing to buffer with content of package, ready to be parsed
     * @param packageHeader the header
     * @return the parsed package
     * @throws DecodeException
     */
    Package parse(
            final Message message,
            final URControllerContentDecoder decoder,
            final PackageHeader packageHeader) throws DecodeException;
}
