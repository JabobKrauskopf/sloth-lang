package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.stack.Stack;

public class Sub implements Instruction {

    private final int opCode = InstructionType.SUB.getOpcode();

    @Override
    public byte[] serialize() {
        return new byte[]{(byte) opCode, 0b0, 0b0, 0b0, 0b0};
    }

    @Override
    public void execute(Stack stack) {
        int[] cache = stack.pop(2);
        stack.push(cache[0] - cache[1]);
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + 0 + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(0)).replace(' ', '0')
        );
    }
}
