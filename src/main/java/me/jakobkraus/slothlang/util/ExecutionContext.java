package me.jakobkraus.slothlang.util;

import me.jakobkraus.slothlang.pagestructure.PageDirectory;

public class ExecutionContext {

    private final Stack instructionStack;
    private final Stack callStack;
    private final PageDirectory pageDirectory;
    private final CodeStructure codeStructure;

    public ExecutionContext(Stack instructionStack, Stack callStack, PageDirectory pageDirectory, CodeStructure codeStructure) {
        this.instructionStack = instructionStack;
        this.callStack = callStack;
        this.pageDirectory = pageDirectory;
        this.codeStructure = codeStructure;
    }

    public Stack getInstructionStack() {
        return this.instructionStack;
    }

    public Stack getCallStack() {
        return this.callStack;
    }

    public InstructionPointer getInstructionPointer() {
        return this.codeStructure.getInstructionPointer();
    }

    public PageDirectory getPageDirectory() {
        return this.pageDirectory;
    }

    public CodeStructure getCodeStructure() {
        return this.codeStructure;
    }
}
