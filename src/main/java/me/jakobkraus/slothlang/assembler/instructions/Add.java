package me.jakobkraus.slothlang.assembler.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;

public class Add implements Instruction {

    private final int opCode = InstructionType.ADD.getOpcode();

    @Override
    public int getOpCode() {
        return this.opCode;
    }

    @Override
    public byte[] serialize() {
        return new byte[]{(byte) opCode, 0b0, 0b0, 0b0, 0b0};
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + 0 + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(0)).replace(' ', '0')
        );
    }
}
