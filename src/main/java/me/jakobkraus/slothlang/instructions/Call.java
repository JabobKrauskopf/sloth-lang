package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;
import me.jakobkraus.slothlang.runtime.InstructionPointer;

import java.io.DataOutputStream;
import java.io.IOException;

public class Call implements Instruction {

    private final byte opCode = InstructionType.CALL.getOpCode();
    private final int address;

    public Call(int address) {
        this.address = address;
    }

    @Override
    public void serialize(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(opCode);
        outputStream.writeInt(this.address);
    }

    @Override
    public void execute(ExecutionContext context) {
        InstructionPointer instructionPointer = context.getInstructionPointer();
        context.getCallStack().push(instructionPointer.getInstructionPointerValue() + 1);
        instructionPointer.setInstructionPointer(this.address);
    }

    @Override
    public void print() {
        System.out.println(this.opCode + " " + this.address + " | "
                + String.format("%8s", Integer.toBinaryString(this.opCode)).replace(' ', '0') + " "
                + String.format("%32s", Integer.toBinaryString(this.address)).replace(' ', '0')
        );
    }
}
