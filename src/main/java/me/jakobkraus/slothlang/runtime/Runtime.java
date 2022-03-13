package me.jakobkraus.slothlang.runtime;

import me.jakobkraus.slothlang.instructions.InstructionStructure;
import me.jakobkraus.slothlang.pagestructure.PageDirectory;
import me.jakobkraus.slothlang.util.FileHelper;
import me.jakobkraus.slothlang.util.Stack;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Runtime {
    private final InstructionStructure struct = new InstructionStructure();
    private final Stack instructionStack = new Stack();
    private final Stack callStack = new Stack();
    private final InstructionPointer instructionPointer = new InstructionPointer();
    private final PageDirectory pageDirectory = new PageDirectory();
    private byte[] code;

    public void loadFile(String filepath) throws IOException {
        this.code = FileHelper.readBinary(filepath);
    }

    public void deserialize() throws IOException {
        ByteArrayInputStream serialization = new ByteArrayInputStream(this.code);
        DataInputStream inputStream = new DataInputStream(serialization);
        this.struct.deserialize(inputStream);
    }

    public void printInstructions() {
        this.struct.print();
    }

    public void printInstructionStack() {
        this.instructionStack.print();
    }

    public void printCallStack() {
        this.callStack.print();
    }

    public void printInstructionPointer() {
        System.out.println(this.instructionPointer.getInstructionPointerValue());
    }

    public void runAll() {
        this.struct.runAll(
                new ExecutionContext(this.instructionStack, this.callStack, this.instructionPointer, this.pageDirectory)
        );
    }

    public void runNext(int n) {
        for (int i = 0; i < n; i++) {
            this.runNext();
        }
    }

    public void runNext() {
        this.struct.runNext(
                new ExecutionContext(this.instructionStack, this.callStack, this.instructionPointer, this.pageDirectory)
        );
    }
}
