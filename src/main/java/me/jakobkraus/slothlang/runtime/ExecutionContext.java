package me.jakobkraus.slothlang.runtime;

import me.jakobkraus.slothlang.stack.Stack;

public class ExecutionContext {

    private final Stack stack;
    private final InstructionPointer instructionPointer;

    public ExecutionContext(Stack stack, InstructionPointer instructionPointer) {
        this.stack = stack;
        this.instructionPointer = instructionPointer;
    }

    public Stack getStack() {
        return this.stack;
    }

    public InstructionPointer getInstructionPointer() {
        return this.instructionPointer;
    }
}
