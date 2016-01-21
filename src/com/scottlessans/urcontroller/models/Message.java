package com.scottlessans.urcontroller.models;

import com.scottlessans.urcontroller.models.packages.Package;

import java.util.List;

final public class Message {

    final public MessageType type;
    final public List<Package> packages;

    public Message(MessageType type, List<Package> packages) {
        this.type = type;
        this.packages = packages;
    }

}
