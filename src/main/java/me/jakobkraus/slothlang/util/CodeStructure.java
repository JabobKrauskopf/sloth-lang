package me.jakobkraus.slothlang.util;

import me.jakobkraus.slothlang.assembler.Assembler;

public class CodeStructure {

    private byte[] code;
    private final InstructionPointer instructionPointer;

    public CodeStructure(byte[] code) {
        this.code = code;
        this.instructionPointer = new InstructionPointer(Assembler.HEADER_LENGTH + this.readInt(2));
    }

    public byte[] getCode() {
        return this.code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public byte readByte() {
        return this.readByte(instructionPointer.getInstructionPointerValue());
    }

    public byte readByte(int address) {
        return this.code[address];
    }

    public int readInt() {
        return this.readInt(this.instructionPointer.getInstructionPointerValue());
    }

    public int readInt(int address) {
        return (this.code[address + 1] << 24) |
                (this.code[address + 2] << 16) |
                (this.code[address + 3] << 8) |
                this.code[address + 4];
    }

    public int getLength() {
        return this.code.length;
    }

    public InstructionPointer getInstructionPointer() {
        return this.instructionPointer;
    }

    public boolean available() {
        return this.instructionPointer.getInstructionPointerValue() < this.code.length;
    }
}
