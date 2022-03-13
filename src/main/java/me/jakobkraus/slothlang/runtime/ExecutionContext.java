package me.jakobkraus.slothlang.runtime;

import me.jakobkraus.slothlang.pagestructure.PageDirectory;
import me.jakobkraus.slothlang.util.Stack;

public class ExecutionContext {

    private final Stack instructionStack;
    private final Stack callStack;
    private final InstructionPointer instructionPointer;
    private final PageDirectory pageDirectory;

    public ExecutionContext(Stack instructionStack, Stack callStack, InstructionPointer instructionPointer, PageDirectory pageDirectory) {
        this.instructionStack = instructionStack;
        this.callStack = callStack;
        this.instructionPointer = instructionPointer;
        this.pageDirectory = pageDirectory;
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

    public PageDirectory getPageDirectory() {
        return this.pageDirectory;
    }
}
