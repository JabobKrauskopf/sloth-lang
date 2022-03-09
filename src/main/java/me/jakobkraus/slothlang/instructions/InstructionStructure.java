package me.jakobkraus.slothlang.instructions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.stack.Stack;

public class InstructionStructure {
    private final List<Instruction> instructions = new ArrayList<>();
    private final ByteArrayOutputStream serialization = new ByteArrayOutputStream();

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

    public void parseInstruction(byte[] byteInstruction) {
        switch (InstructionType.getInstructionTypeFromOpCode(byteInstruction[0])) {
            case ADD:
                this.instructions.add(new Add());
                break;
            case CSI:
                long arg = ((byteInstruction[4] & 0xFF)) |
                        ((byteInstruction[3] & 0xFF) << 8) |
                        ((byteInstruction[2] & 0xFF) << 16) |
                        ((long) (byteInstruction[1] & 0xFF) << 24);
                this.instructions.add(new Csi((int) arg));
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

    public void serialize() {
        for (Instruction s : this.instructions) {
            for (byte b : s.serialize()) {
                this.serialization.write(b);
            }
        }
    }

    public void deserialize() {
        this.instructions.clear();
        byte[] byteInstructions = this.serialization.toByteArray();
        for (int i = 0; i < (byteInstructions.length / 5); i++) {
            this.parseInstruction(Arrays.copyOfRange(byteInstructions, (i * 5), (i * 5) + 5));
        }
    }

    public void print() {
        for (Instruction s : this.instructions) {
            s.print();
        }
    }

    public ByteArrayOutputStream getSerialization() {
        return this.serialization;
    }

    public void setSerialization(byte[] serialization) throws IOException {
        this.serialization.reset();
        this.serialization.write(serialization);
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
