package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.SerializationContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.IOException;

public class Swap {

    private static final byte opCode = InstructionType.SWAP.getOpCode();

    public static void serialize(SerializationContext context) throws IOException {
        context.getOutputStream().writeByte(opCode);
    }

    public static void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        int a = stack.pop();
        int b = stack.pop();
        stack.push(a);
        stack.push(b);
        context.getInstructionPointer().increment();
    }
}
