package me.jakobkraus.slothlang.instructions;

import me.jakobkraus.slothlang.stack.Stack;

public interface Instruction {

    byte[] serialize();

    void execute(Stack stack);

    void print();
}
