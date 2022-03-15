package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;
import me.jakobkraus.slothlang.util.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Eqi implements Instruction {

    private final byte opCode = InstructionType.EQI.getOpCode();
    private final int constant;

    public Eqi(int constant) {
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
        stack.push(stack.pop() == this.constant ? 1 : 0);
        context.getInstructionPointer().increment(1 + InstructionType.EQI.getArgLength());
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + this.constant + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(this.constant)).replace(' ', '0')
        );
    }
}
