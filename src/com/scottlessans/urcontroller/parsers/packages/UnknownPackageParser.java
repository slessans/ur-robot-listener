package com.scottlessans.urcontroller.parsers.packages;

import com.scottlessans.urcontroller.decoders.DecodeException;
import com.scottlessans.urcontroller.decoders.URControllerContentDecoder;
import com.scottlessans.urcontroller.models.Message;
import com.scottlessans.urcontroller.models.PackageHeader;
import com.scottlessans.urcontroller.models.packages.Package;
import com.scottlessans.urcontroller.models.packages.UnknownPackage;
import com.scottlessans.urcontroller.parsers.PackageParser;

public class UnknownPackageParser implements PackageParser {

    @Override
    public Package parse(
            final Message message,
            final URControllerContentDecoder decoder,
            final PackageHeader packageHeader) throws DecodeException {
        final byte[] content = new byte[packageHeader.totalContentLength];
        decoder.decodeBytes(content);
        return new UnknownPackage(message, packageHeader.packageTypeCode, content);
    }
}
