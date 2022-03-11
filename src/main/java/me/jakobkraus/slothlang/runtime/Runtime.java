package me.jakobkraus.slothlang.runtime;

import me.jakobkraus.slothlang.instructions.InstructionStructure;
import me.jakobkraus.slothlang.stack.Stack;
import me.jakobkraus.slothlang.util.FileHelper;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Runtime {
    byte[] code;
    private final InstructionStructure struct = new InstructionStructure();
    private final InstructionPointer instructionPointer = new InstructionPointer();
    private final Stack stack = new Stack();

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

    public void printStack() {
        this.stack.print();
    }

    public void runAll() {
        this.struct.runAll(this.stack, this.instructionPointer);
    }

    public void runNext() {
        this.struct.runNext(this.stack, this.instructionPointer);
    }

    public InstructionPointer getInstructionPointer() {
        return this.instructionPointer;
    }
}
