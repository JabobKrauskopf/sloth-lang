package me.jakobkraus.slothlang;

import me.jakobkraus.slothlang.assembler.Assembler;

public class Main {

    public static void main(String[] args) {
        Assembler assembler = new Assembler();
        assembler.loadFile("example.sat");
        assembler.parse();
        assembler.saveSerialization("exanoke.satbin");
        assembler.printInstructions();
    }
}
