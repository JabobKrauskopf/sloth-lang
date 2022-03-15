package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.SerializationContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Je {

    private static final byte opCode = InstructionType.JE.getOpCode();

    public static void serialize(SerializationContext context, String args) throws IOException {
        DataOutputStream outputStream = context.getOutputStream();
        outputStream.writeByte(opCode);
        outputStream.writeInt(Integer.parseInt(args));
    }

    public static void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        if (stack.pop() == stack.pop()) {
            // context.getInstructionPointer().setInstructionPointer(this.address);
        } else {
            context.getInstructionPointer().increment(1 + InstructionType.JE.getArgLength());
        }
    }
}
