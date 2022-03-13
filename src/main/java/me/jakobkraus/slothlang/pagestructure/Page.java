package me.jakobkraus.slothlang.pagestructure;

public class Page {

    private final byte[] memory = new byte[4096];

    public byte loadByte(int index) {
        return this.memory[index & 0xFFF];
    }

    public void storeByte(int index, byte value) {
        this.memory[index & 0xFFF] = value;
    }
}
