package com.scottlessans.urcontroller.models;


public final class PackageHeader {

    /**
     * the TOTAL length of the package, this will be longer than the buffer
     * pointed to by decoder, since it includes bytes for length and type code
     * and any other header information that may have already been parsed. This is
     * the exact value parsed from the package header.
     */
    final public int totalPackageLength;

    /**
     * uchar code converted to int from package header
     */
    final public int packageTypeCode;

    /**
     * The length of the actual content, this is totalPackageLength less
     * the number of bytes used for the header. this will be the size of
     * the underlying buffer/available bytes to read via the corresponding
     * URControllerContentDecoder.
     */
    final public int totalContentLength;

    public PackageHeader(
            final int totalPackageLength,
            final int packageTypeCode,
            final int totalContentLength) {
        this.totalPackageLength = totalPackageLength;
        this.packageTypeCode = packageTypeCode;
        this.totalContentLength = totalContentLength;
    }
}
