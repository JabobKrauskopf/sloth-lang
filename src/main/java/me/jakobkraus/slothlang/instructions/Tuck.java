package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Tuck implements Instruction {

    private final byte opCode = InstructionType.TUCK.getOpCode();
    private final int constant;

    public Tuck(int constant) {
        this.constant = constant;
    }

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(opCode);
        outputStream.writeInt(constant);
    }

    @Override
    public void execute(ExecutionContext context) {
        Stack stack = context.getInstructionStack();
        Stack cacheStack = new Stack();
        for (int i = 0; i < this.constant - 1; i++) {
            cacheStack.push(stack.pop());
        }
        int cache = stack.pop();
        for (int i = 0; i < this.constant - 1; i++) {
            stack.push(cacheStack.pop());
        }
        stack.push(cache);
        context.getInstructionPointer().increment(1 + InstructionType.TUCK.getArgLength());
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + this.constant + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(this.constant)).replace(' ', '0')
        );
    }
}
