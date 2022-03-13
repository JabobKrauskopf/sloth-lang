package me.jakobkraus.slothlang.pagestructure;

public class PageDirectory {

    private final PageTable[] pages = new PageTable[1024];

    public int loadByte(int index) {
        return this.pages[(index & 0xFFC00000) >> 22].loadByte(index);
    }

    public void storeByte(int index, byte value) {
        int parsedIndex = (index & 0xFFC00000) >> 22;

        if (this.pages[parsedIndex] == null)
            this.pages[parsedIndex] = new PageTable();

        this.pages[parsedIndex].storeByte(index, value);
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
