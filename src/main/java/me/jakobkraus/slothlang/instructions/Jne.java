package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;
import me.jakobkraus.slothlang.stack.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public class Jne implements Instruction {

    private final byte opCode = InstructionType.Jne.getOpCode();
    private final int address;

    public Jne(int address) {
        this.address = address;
    }

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(opCode);
        outputStream.writeInt(this.address);
    }

    @Override
    public void execute(ExecutionContext context) {
        Stack stack = context.getStack();
        if (stack.pop() != stack.pop()) {
            context.getInstructionPointer().setInstructionPointer(this.address);
        } else {
            context.getInstructionPointer().increment();
        }
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + this.address + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(this.address)).replace(' ', '0')
        );
    }
}
