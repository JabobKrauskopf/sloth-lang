package me.jakobkraus.slothlang.util;

import java.io.DataOutputStream;

public class SerializationContext {

    private final DataOutputStream outputStream;

    public SerializationContext(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public DataOutputStream getOutputStream() {
        return this.outputStream;
    }
}
