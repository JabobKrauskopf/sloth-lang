package me.jakobkraus.slothlang.assembler;

import me.jakobkraus.slothlang.instructions.InstructionStructure;
import me.jakobkraus.slothlang.util.FileHelper;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
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
        ByteArrayOutputStream serialization = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(serialization);

        this.struct.serialize(outputStream);
        FileHelper.saveBinary(filepath, serialization);
    }

    public void printInstructions() {
        struct.print();
    }
}
