package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.InstructionPointer;
import me.jakobkraus.slothlang.util.SerializationContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Srl {

    private static final byte opCode = InstructionType.SRL.getOpCode();

    public static void serialize(SerializationContext context, String args) throws IOException {
        DataOutputStream outputStream = context.getOutputStream();
        outputStream.writeByte(opCode);
        outputStream.writeInt(Integer.parseInt(args));
    }

    public static void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        InstructionPointer instructionPointer = context.getInstructionPointer();
        int constant = context.getCodeStructure().readInt(instructionPointer.getInstructionPointerValue());
        stack.push(stack.pop() >>> constant);
        context.getInstructionPointer().increment(1 + InstructionType.SRL.getArgLength());
    }
}
