package me.jakobkraus.slothlang.runtime;

import me.jakobkraus.slothlang.instructions.InstructionStructure;
import me.jakobkraus.slothlang.stack.Stack;
import me.jakobkraus.slothlang.util.FileHelper;

import java.io.IOException;

public class Runtime {
    byte[] code;
    private final InstructionStructure struct = new InstructionStructure();
    private final Stack stack = new Stack();

    public void loadFile(String filepath) throws IOException {
        this.code = FileHelper.readBinary(filepath);
    }

    public void deserialize() throws IOException {
        this.struct.setSerialization(this.code);
        this.struct.deserialize();
    }

    public void printInstructions() {
        this.struct.print();
    }

    public void printStack() {
        this.stack.print();
    }

    public void runAll() {
        this.struct.runAll(this.stack);
    }

    public void runNext() {
        this.struct.runNext(this.stack);
    }
}
