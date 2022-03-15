package me.jakobkraus.slothlang.architecture;

import me.jakobkraus.slothlang.util.SerializationContext;

import java.io.IOException;

public interface SerializationFunction {

    void serialize(SerializationContext context) throws IOException;
}
