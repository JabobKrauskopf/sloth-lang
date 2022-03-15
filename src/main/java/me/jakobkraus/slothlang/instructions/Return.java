package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.SerializationContext;

import java.io.IOException;

public class Return {

    private static final byte opCode = InstructionType.RETURN.getOpCode();

    public static void serialize(SerializationContext context) throws IOException {
        context.getOutputStream().writeByte(opCode);
    }

    public static void execute(ExecutionContext context) {
        context.getInstructionPointer().setInstructionPointer(
                context.getCallStack().pop()
        );
    }
}
