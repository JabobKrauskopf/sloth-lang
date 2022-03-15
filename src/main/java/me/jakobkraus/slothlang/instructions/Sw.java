package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;

import java.io.DataOutputStream;
import java.io.IOException;

public class Sw implements Instruction {

    private final byte opCode = InstructionType.SW.getOpCode();
    private final int address;

    public Sw(int address) {
        this.address = address;
    }

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(opCode);
        outputStream.writeInt(this.address);
    }

    @Override
    public void execute(ExecutionContext context) {
        context.getPageDirectory().storeWord(this.address, context.getInstructionStack().pop());
        context.getInstructionPointer().increment(1 + InstructionType.SW.getArgLength());
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + this.address + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(this.address)).replace(' ', '0')
        );
    }
}
