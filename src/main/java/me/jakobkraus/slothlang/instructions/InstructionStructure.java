package me.jakobkraus.slothlang.instructions;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.stack.Stack;

public class InstructionStructure {
    private final List<Instruction> instructions = new ArrayList<>();

    public void parseInstruction(String instruction) {
        String[] instructionSplit = instruction.split(" ");
        switch (InstructionType.getInstructionTypeFromString(instructionSplit[0])) {
            case ADD:
                this.instructions.add(new Add());
                break;
            case CSI:
                this.instructions.add(new Csi(Integer.parseInt(instructionSplit[1])));
                break;
            case SUB:
                this.instructions.add(new Sub());
                break;
            case SQR:
                this.instructions.add(new Sqr());
                break;
        }
    }

    public void parseInstruction(byte opCode, int args) {
        switch (InstructionType.getInstructionTypeFromOpCode(opCode)) {
            case ADD:
                this.instructions.add(new Add());
                break;
            case CSI:
                this.instructions.add(new Csi(args));
                break;
            case SUB:
                this.instructions.add(new Sub());
                break;
            case SQR:
                this.instructions.add(new Sqr());
                break;
        }
    }

    public void parse(String instructions) {
        for (String line : instructions.split("\n")) {
            this.parseInstruction(line);
        }
    }

    public void serialize(DataOutputStream outputStream) throws IOException {
        for (Instruction s : this.instructions) {
            s.serialize(outputStream);
        }
    }

    public void deserialize(DataInputStream inputStream) throws IOException {
        this.instructions.clear();
        while (inputStream.available() != 0) {
            byte opCode = inputStream.readByte();
            int args = inputStream.readInt();
            this.parseInstruction(opCode, args);
        }
    }

    public void print() {
        for (Instruction s : this.instructions) {
            s.print();
        }
    }

    public void runAll(Stack stack) {
        while (this.instructions.size() > 0) {
            this.runNext(stack);
        }
    }

    public void runNext(Stack stack) {
        if (this.instructions.size() > 0) {
            this.instructions.remove(0).execute(stack);
        }
    }
}
