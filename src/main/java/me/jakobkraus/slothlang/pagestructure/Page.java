package me.jakobkraus.slothlang.pagestructure;

public class Page {

    private final byte[] memory = new byte[4096];

    public byte loadByte(int index) {
        return this.memory[index & 0xFFF];
    }

    public void storeByte(int index, byte value) {
        this.memory[index & 0xFFF] = value;
    }

    public int loadWord(int index) {
        return (this.loadByte(index + 3) << 24) |
                (this.loadByte(index + 2) << 16) |
                (this.loadByte(index + 1) << 8) |
                this.loadByte(index);
    }

    public void storeWord(int index, int value) {
        this.storeByte(index + 3, (byte) ((value & 0xFF000000) >> 24));
        this.storeByte(index + 2, (byte) ((value & 0xFF0000) >> 16));
        this.storeByte(index + 1, (byte) ((value & 0xFF00) >> 8));
        this.storeByte(index, (byte) (value & 0xFF));
    }
}
