package me.jakobkraus.slothlang.pagestructure;

public class PageTable {

    private final Page[] pages = new Page[1024];

    public int loadWord(int index) {
        return this.pages[(index & 0x3FF000) >> 12].loadWord(index);
    }

    public void storeWord(int index, int value) {
        int parsedIndex = (index & 0x3FF000) >> 12;

        if (this.pages[parsedIndex] == null)
            this.pages[parsedIndex] = new Page();

        this.pages[parsedIndex].storeWord(index, value);
    }
}
