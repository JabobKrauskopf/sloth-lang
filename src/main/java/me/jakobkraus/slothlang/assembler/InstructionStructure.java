package me.jakobkraus.slothlang.assembler;

import me.jakobkraus.slothlang.assembler.instructions.Add;
import me.jakobkraus.slothlang.assembler.instructions.Csi;
import me.jakobkraus.slothlang.assembler.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

public class InstructionStructure {
    private final List<Instruction> instructions = new ArrayList<>();
    private final List<Byte> serialization = new ArrayList<>();

    public void parseInstruction(String instruction) {
        String[] instructionSplit = instruction.split(" ");
        switch (instructionSplit[0]) {
            case "add":
                this.instructions.add(new Add());
                break;
            case "csi":
                this.instructions.add(new Csi(Integer.parseInt(instructionSplit[1])));
                break;
            default:
                break;
        }
    }

    public void serialize() {
        for (Instruction s : this.instructions) {
            for (byte b : s.serialize()) {
                this.serialization.add(b);
            }
        }
    }

    public void print() {
        for(Instruction s : this.instructions) {
            s.print();
        }
    }

    public byte[] getSerialization() {
        byte[] arr = new byte[this.serialization.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.serialization.get(i);
        }
        return arr;
    }
}
