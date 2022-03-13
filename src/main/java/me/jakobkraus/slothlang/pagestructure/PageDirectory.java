package me.jakobkraus.slothlang.pagestructure;

public class PageDirectory {

    private final PageTable[] pages = new PageTable[1024];

    public int loadWord(int index) {
        return this.pages[(index & 0xFFC00000) >> 22].loadWord(index);
    }

    public void storeWord(int index, int value) {
        int parsedIndex = (index & 0xFFC00000) >> 22;

        if (this.pages[parsedIndex] == null)
            this.pages[parsedIndex] = new PageTable();

        this.pages[parsedIndex].storeWord(index, value);
    }
}
