package me.jakobkraus.slothlang.runtime;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.pagestructure.PageDirectory;
import me.jakobkraus.slothlang.util.*;

import java.io.IOException;

public class Runtime {
    private final Stack instructionStack = new Stack();
    private final Stack callStack = new Stack();
    private final PageDirectory pageDirectory = new PageDirectory();
    private CodeStructure codeStructure;

    public void loadFile(String filepath) throws IOException {
        this.codeStructure = new CodeStructure(FileHelper.readBinary(filepath));
    }

    public void printInstructionPointer() {
        System.out.println(this.codeStructure.getInstructionPointer().getInstructionPointerValue());
    }

    public void printInstructionStack() {
        this.instructionStack.print();
    }

    public void printCallStack() {
        this.callStack.print();
    }

    public void runAll() {
        ExecutionContext context = new ExecutionContext(this.instructionStack, this.callStack, this.pageDirectory, this.codeStructure);

        while (this.codeStructure.available()) {
            this.runNext(context);
        }
    }

    public void runNext(int n) {
        ExecutionContext context = new ExecutionContext(this.instructionStack, this.callStack, this.pageDirectory, this.codeStructure);

        for (int i = 0; i < n; i++) {
            this.runNext(context);
        }
    }

    public void runNext() {
        ExecutionContext context = new ExecutionContext(this.instructionStack, this.callStack, this.pageDirectory, this.codeStructure);

        this.runNext(context);
    }

    public void runNext(ExecutionContext context) {
        InstructionType instructionType = InstructionType.getInstructionTypeFromOpCode(
                this.codeStructure.readByte()
        );

        instructionType.execute(context);
    }
}
