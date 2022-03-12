package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;

import java.io.DataOutputStream;
import java.io.IOException;

public class Csi implements Instruction {

    private final byte opCode = InstructionType.CSI.getOpCode();
    private final int constant;

    public Csi(int constant) {
        this.constant = constant;
    }

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(opCode);
        outputStream.writeInt(constant);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getStack().push(this.constant);
        context.getInstructionPointer().increment();
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + this.constant + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(this.constant)).replace(' ', '0')
        );
    }
}
