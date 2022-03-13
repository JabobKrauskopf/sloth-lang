package me.jakobkraus.slothlang.pagestructure;

public class Page {

    private final int[] memory = new int[4096];

    public int loadWord(int index) {
        return this.memory[index & 0xFFF];
    }

    public void storeWord(int index, int value) {
        this.memory[index & 0xFFF] = value;
    }
}
