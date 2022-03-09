package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.stack.Stack;

public class Csi implements Instruction {

    private final int opCode = InstructionType.CSI.getOpcode();
    private final int constant;

    public Csi(int constant) {
        this.constant = constant;
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
    public void execute(Stack stack) {
        stack.push(this.constant);
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + this.constant + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(this.constant)).replace(' ', '0')
        );
    }
}
