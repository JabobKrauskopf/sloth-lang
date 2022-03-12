package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.runtime.ExecutionContext;

import java.io.DataOutputStream;
import java.io.IOException;

public interface Instruction {

    void serialize(DataOutputStream outputStream) throws IOException;

    void execute(ExecutionContext context);

    void print();
}
