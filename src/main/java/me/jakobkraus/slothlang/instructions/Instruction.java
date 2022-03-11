package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.runtime.InstructionPointer;
import me.jakobkraus.slothlang.stack.Stack;

import java.io.DataOutputStream;
import java.io.IOException;

public interface Instruction {

    void serialize(DataOutputStream outputStream) throws IOException;

    void execute(Stack stack, InstructionPointer instructionPointer);

    void print();
}
