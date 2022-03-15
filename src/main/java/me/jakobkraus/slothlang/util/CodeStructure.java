package me.jakobkraus.slothlang.util;

public class CodeStructure {

    private byte[] code;

    public CodeStructure(byte[] code) {
        this.code = code;
    }

    public byte[] getCode() {
        return this.code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public byte readByte(int address) {
        return this.code[address];
    }

    public int readInt(int address) {
        return (code[address + 1] << 24) |
                (code[address + 2] << 16) |
                (code[address + 3] << 8) |
                code[address + 4];
    }

    public int getLength() {
        return this.code.length;
    }
}
