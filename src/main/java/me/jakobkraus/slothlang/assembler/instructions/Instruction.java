package me.jakobkraus.slothlang.assembler.instructions;

public interface Instruction {
    int getOpCode();
    byte[] serialize();
    void print();
}
