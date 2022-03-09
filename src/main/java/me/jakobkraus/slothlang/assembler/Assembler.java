package me.jakobkraus.slothlang.assembler;

import me.jakobkraus.slothlang.instructions.InstructionStructure;
import me.jakobkraus.slothlang.util.FileHelper;

import java.io.IOException;

public class Assembler {

    public String code = "";
    private final InstructionStructure struct = new InstructionStructure();

    public void loadFile(String filepath) throws IOException {
        this.code = FileHelper.readFile(filepath);
    }

    public void loadString(String code) {
        this.code = code;
    }

    public void parse() {
       this.struct.parse(this.code);
    }

    public void saveSerialization(String filepath) throws IOException {
        this.struct.serialize();
        FileHelper.saveBinary(filepath, this.struct.getSerialization());
    }

    public void printInstructions() {
        struct.print();
    }
}
