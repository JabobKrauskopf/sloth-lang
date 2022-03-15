package me.jakobkraus.slothlang.util;

import java.io.DataOutputStream;

public class SerializationContext {

    private final DataOutputStream outputStream;
    private String args;

    public SerializationContext(DataOutputStream outputStream, String args) {
        this.outputStream = outputStream;
        this.args = args;
    }

    public DataOutputStream getOutputStream() {
        return this.outputStream;
    }

    public String getArgs() {
        return this.args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
