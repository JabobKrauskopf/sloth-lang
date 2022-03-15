package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.InstructionPointer;
import me.jakobkraus.slothlang.util.SerializationContext;

import java.io.DataOutputStream;
import java.io.IOException;

public class Sw {

    private static final byte opCode = InstructionType.SW.getOpCode();

    public static void serialize(SerializationContext context) throws IOException {
        DataOutputStream outputStream = context.getOutputStream();
        outputStream.writeByte(opCode);
        outputStream.writeInt(Integer.parseInt(context.getArgs()));
    }

    public static void execute(ExecutionContext context) {
        InstructionPointer instructionPointer = context.getInstructionPointer();
        int address = context.getCodeStructure().readInt(instructionPointer.getInstructionPointerValue());
        context.getPageDirectory().storeWord(address, context.getInstructionStack().pop());
        context.getInstructionPointer().increment(InstructionType.SW.getInstructionLength());
    }
}
