package me.jakobkraus.slothlang;

import me.jakobkraus.slothlang.assembler.Assembler;
import me.jakobkraus.slothlang.runtime.Runtime;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Assembler");

        Assembler assembler = new Assembler();
        try {
            assembler.loadFile("example.sat");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assembler.parse();
        try {
            assembler.saveSerialization("example.sab");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assembler.printInstructions();


        System.out.println("\nStarting Runtime");

        Runtime runtime = new Runtime();
        try {
            runtime.loadFile("example.sab");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            runtime.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        runtime.printInstructions();
        runtime.runAll();
        runtime.printInstructionStack();
//        runtime.printInstructionPointer();
//        runtime.struct.instructions.get(0).print();
//        System.out.println();
//        runtime.runNext();
//        runtime.printInstructionStack();
//        runtime.printInstructionPointer();
//        runtime.struct.instructions.get(21).print();
//        System.out.println();
//        runtime.runNext();
//        runtime.printInstructionStack();
//        runtime.printInstructionPointer();
    }
}
