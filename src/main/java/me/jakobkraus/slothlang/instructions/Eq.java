package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.stack.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Eq implements Instruction {

    private final byte opCode = InstructionType.EQ.getOpCode();

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(opCode);
        outputStream.writeInt(0);
    }

    @Override
    public void execute(Stack stack) {
        stack.push(stack.pop() == stack.pop() ? 1 : 0);
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + 0 + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(0)).replace(' ', '0')
        );
    }
}
