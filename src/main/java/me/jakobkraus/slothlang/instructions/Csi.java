package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.InstructionPointer;
import me.jakobkraus.slothlang.util.SerializationContext;

import java.io.DataOutputStream;
import java.io.IOException;

public class Csi {

    private static final byte opCode = InstructionType.CSI.getOpCode();

    public static void serialize(SerializationContext context, String args) throws IOException {
        DataOutputStream outputStream = context.getOutputStream();
        outputStream.writeByte(opCode);
        outputStream.writeInt(Integer.parseInt(args));
    }

    public static void execute(ExecutionContext context) {
        byte[] code = context.getCode();
        InstructionPointer instructionPointer = context.getInstructionPointer();
        int instructionPointerValue = instructionPointer.getInstructionPointerValue();
        int constant = (code[instructionPointerValue + 1] << 24) |
                (code[instructionPointerValue + 2] << 16) |
                (code[instructionPointerValue + 3] << 8) |
                code[instructionPointerValue + 4];

        context.getInstructionStack().push(constant);
        instructionPointer.increment(1 + InstructionType.CSI.getArgLength());
    }
}
