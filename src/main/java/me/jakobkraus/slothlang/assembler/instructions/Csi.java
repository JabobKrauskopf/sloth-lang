package me.jakobkraus.slothlang.assembler.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;

public class Csi implements Instruction {

    private final int opCode = InstructionType.CSI.getOpcode();
    private final int constant;

    public Csi(int constant) {
        this.constant = constant;
    }

    @Override
    public int getOpCode() {
        return this.opCode;
    }

    @Override
    public byte[] serialize() {
        return new byte[]{(byte) opCode,
                (byte) (0xFF & (this.constant >> 24)),
                (byte) (0xFF & (this.constant >> 16)),
                (byte) (0xFF & (this.constant >> 8)),
                (byte) (0xFF & this.constant)
        };
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + this.constant + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(this.constant)).replace(' ', '0')
        );
    }
}
