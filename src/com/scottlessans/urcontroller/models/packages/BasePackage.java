package com.scottlessans.urcontroller.models.packages;

import com.scottlessans.urcontroller.models.Message;

abstract public class BasePackage implements Package {

    final protected int packageTypeCode;
    final protected Message message;

    public BasePackage(final Message message, final int packageTypeCode) {
        this.packageTypeCode = packageTypeCode;
        this.message = message;
    }

    @Override
    public Message getMessage() {
        return this.message;
    }

    @Override
    public int getPackageTypeCode() {
        return this.packageTypeCode;
    }

}
