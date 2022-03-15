package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.SerializationContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.IOException;

public class Eq {

    private static final byte opCode = InstructionType.EQ.getOpCode();

    public static void serialize(SerializationContext context) throws IOException {
        context.getOutputStream().writeByte(opCode);
    }

    public static void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        stack.push(stack.pop() == stack.pop() ? 1 : 0);
        context.getInstructionPointer().increment();
    }
}
