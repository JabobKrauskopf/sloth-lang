package me.jakobkraus.slothlang.runtime;

public class InstructionPointer {

    private int instructionPointer;

    public InstructionPointer(int initialValue) {
        this.instructionPointer = initialValue;
    }

    public InstructionPointer() {
        this.instructionPointer = 0;
    }

    public void increment() {
        this.instructionPointer++;
    }

    public void decrement() {
        this.instructionPointer--;
    }

    public void setInstructionPointer(int instructionPointer) {
        this.instructionPointer = instructionPointer;
    }

    public int getInstructionPointerValue() {
        return this.instructionPointer;
    }
}
