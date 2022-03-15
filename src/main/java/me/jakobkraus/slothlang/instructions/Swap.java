package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Swap implements Instruction {

    private final byte opCode = InstructionType.SWAP.getOpCode();

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(opCode);
    }

    @Override
    public void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        int a = stack.pop();
        int b = stack.pop();
        stack.push(a);
        stack.push(b);
        context.getInstructionPointer().increment();
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + 0 + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(0)).replace(' ', '0')
        );
    }
}
