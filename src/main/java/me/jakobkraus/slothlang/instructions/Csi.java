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
        InstructionPointer instructionPointer = context.getInstructionPointer();
        int instructionPointerValue = instructionPointer.getInstructionPointerValue();

        context.getInstructionStack().push(context.getCodeStructure().readInt(instructionPointerValue));
        instructionPointer.increment(InstructionType.CSI.getInstructionLength());
    }
}
