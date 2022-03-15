package me.jakobkraus.slothlang.util;

import me.jakobkraus.slothlang.pagestructure.PageDirectory;

public class ExecutionContext {

    private final Stack instructionStack;
    private final Stack callStack;
    private final InstructionPointer instructionPointer;
    private final PageDirectory pageDirectory;
    private byte[] code;

    public ExecutionContext(Stack instructionStack, Stack callStack, InstructionPointer instructionPointer, PageDirectory pageDirectory, byte[] code) {
        this.instructionStack = instructionStack;
        this.callStack = callStack;
        this.instructionPointer = instructionPointer;
        this.pageDirectory = pageDirectory;
        this.code = code;
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

    public byte[] getCode() {
        return this.code;
    }
}
