package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.InstructionPointer;
import me.jakobkraus.slothlang.util.SerializationContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Je {

    private static final byte opCode = InstructionType.JE.getOpCode();

    public static void serialize(SerializationContext context) throws IOException {
        DataOutputStream outputStream = context.getOutputStream();
        outputStream.writeByte(opCode);
        outputStream.writeInt(Integer.parseInt(context.getArgs()));
    }

    public static void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        InstructionPointer instructionPointer = context.getInstructionPointer();
        if (stack.pop() == stack.pop()) {
            instructionPointer.setInstructionPointer(context.getCodeStructure().readInt(instructionPointer.getInstructionPointerValue()));
        } else {
            instructionPointer.increment(InstructionType.JE.getInstructionLength());
        }
    }
}
