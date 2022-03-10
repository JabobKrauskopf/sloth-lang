package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.stack.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Sub implements Instruction {

    private final int opCode = InstructionType.SUB.getOpCode();

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.write(opCode);
        outputStream.write(new byte[] {0b0, 0b0, 0b0, 0b0});
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
