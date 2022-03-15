package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.SerializationContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.IOException;

public class Copy {

    private static final byte opCode = InstructionType.COPY.getOpCode();

    public static void serialize(SerializationContext context) throws IOException {
        context.getOutputStream().writeByte(opCode);
    }

    public static void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        int cache = stack.pop();
        stack.push(cache);
        stack.push(cache);
        context.getInstructionPointer().increment();
    }
}
