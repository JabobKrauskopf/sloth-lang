package me.jakobkraus.slothlang.pagestructure;

public class PageTable {

    private final Page[] pages = new Page[1024];

    public byte loadByte(int index) {
        return this.pages[(index & 0x3FF000) >> 12].loadByte(index);
    }

    public void storeByte(int index, byte value) {
        int parsedIndex = (index & 0x3FF000) >> 12;

        if (this.pages[parsedIndex] == null)
            this.pages[parsedIndex] = new Page();

        this.pages[parsedIndex].storeByte(index, value);
    }
}
