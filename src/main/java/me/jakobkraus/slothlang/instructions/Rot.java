package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Rot implements Instruction {

    private final byte opCode = InstructionType.ROT.getOpCode();
    private final int constant;

    public Rot(int constant) {
        this.constant = constant;
    }

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(opCode);
        outputStream.writeInt(constant);
    }

    @Override
    public void execute(ExecutionContext context) {
        int[] cache = new int[this.constant];
        Stack stack = context.getInstructionStack();
        for (int i = 0; i < cache.length; i++) {
            cache[i] = stack.pop();
        }
        for (int j : cache) {
            stack.push(j);
        }
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
