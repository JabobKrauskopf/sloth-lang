package me.jakobkraus.slothlang.assembler;

import me.jakobkraus.slothlang.util.FileHelper;

public class Assembler {

    public String code = "";
    private final InstructionStructure struct = new InstructionStructure();

    public void loadFile(String filepath) {
        this.code = FileHelper.readFile(filepath);
    }

    public void loadString(String code) {
        this.code = code;
    }

    public void parse() {
        for (String line : this.code.split("\n")) {
            this.struct.parseInstruction(line);
        }
    }

    public void saveSerialization(String filepath) {
        this.struct.serialize();
        FileHelper.saveBinary(filepath, this.struct.getSerialization());
    }

    public void printInstructions() {
        struct.print();
    }
}
