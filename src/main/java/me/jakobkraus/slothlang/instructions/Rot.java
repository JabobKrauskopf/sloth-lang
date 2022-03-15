package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.InstructionPointer;
import me.jakobkraus.slothlang.util.SerializationContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Rot {

    private static final byte opCode = InstructionType.ROT.getOpCode();

    public static void serialize(SerializationContext context, String args) throws IOException {
        DataOutputStream outputStream = context.getOutputStream();
        outputStream.writeByte(opCode);
        outputStream.writeInt(Integer.parseInt(args));
    }

    public static void execute(ExecutionContext context) {
        InstructionPointer instructionPointer = context.getInstructionPointer();
        int constant = context.getCodeStructure().readInt(instructionPointer.getInstructionPointerValue());
        int[] cache = new int[constant];
        Stack stack = context.getInstructionStack();
        for (int i = 0; i < cache.length; i++) {
            cache[i] = stack.pop();
        }
        for(int j : cache) {
            stack.push(j);
        }
        instructionPointer.increment(1 + InstructionType.ROT.getArgLength());
    }
}
