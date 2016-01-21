package com.scottlessans.urcontroller.models.packages;


import com.scottlessans.urcontroller.models.Message;

import java.util.Arrays;

final public class UnknownPackage extends BasePackage {

    private final byte[] _content;

    public UnknownPackage(Message message, int packageTypeCode, byte[] content) {
        super(message, packageTypeCode);
        this._content = content;
    }

    public byte[] getContent() {
        return this._content;
    }

    @Override
    public String toString() {
        return "UnknownPackage{" +
                "typeCode=" + this.getPackageTypeCode() + ", " +
                "content=" + Arrays.toString(this.getContent()) +
                '}';
    }
}
