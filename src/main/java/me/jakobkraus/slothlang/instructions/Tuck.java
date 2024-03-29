package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.InstructionPointer;
import me.jakobkraus.slothlang.util.SerializationContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Tuck {

    private static final byte opCode = InstructionType.TUCK.getOpCode();

    public static void serialize(SerializationContext context) throws IOException {
        DataOutputStream outputStream = context.getOutputStream();
        outputStream.writeByte(opCode);
        outputStream.writeInt(Integer.parseInt(context.getArgs()));
    }

    public static void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        Stack cacheStack = new Stack();
        InstructionPointer instructionPointer = context.getInstructionPointer();
        int constant = context.getCodeStructure().readInt(instructionPointer.getInstructionPointerValue());
        for (int i = 0; i < constant - 1; i++) {
             cacheStack.push(stack.pop());
         }
         int cache = stack.pop();
         for (int i = 0; i < constant - 1; i++) {
             stack.push(cacheStack.pop());
         }
         stack.push(cache);
        context.getInstructionPointer().increment(InstructionType.TUCK.getInstructionLength());
    }
}
