package me.jakobkraus.slothlang.runtime;

import me.jakobkraus.slothlang.stack.Stack;

public class ExecutionContext {

    private final Stack instructionStack;
    private final Stack callStack;
    private final InstructionPointer instructionPointer;

    public ExecutionContext(Stack instructionStack, Stack callStack, InstructionPointer instructionPointer) {
        this.instructionStack = instructionStack;
        this.callStack = callStack;
        this.instructionPointer = instructionPointer;
    }

    public Stack getInstructionStack() {
        return this.instructionStack;
    }

    public Stack getCallStack() {
        return this.callStack;
    }

    public InstructionPointer getInstructionPointer() {
        return this.instructionPointer;
    }
}
