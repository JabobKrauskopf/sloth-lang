package me.jakobkraus.slothlang.instructions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.InstructionPointer;
import me.jakobkraus.slothlang.stack.Stack;

public class InstructionStructure {
    private final List<Instruction> instructions = new ArrayList<>();

    public void addInstruction(InstructionType instructionType, int args) {
        switch (instructionType) {
            case ADD:
                this.instructions.add(new Add());
                break;
            case SUB:
                this.instructions.add(new Sub());
                break;
            case CSI:
                this.instructions.add(new Csi(args));
                break;
            case SQR:
                this.instructions.add(new Sqr());
                break;
            case J:
                this.instructions.add(new J(args));
                break;
            case Je:
                this.instructions.add(new Je(args));
                break;
            case Jne:
                this.instructions.add(new Jne(args));
                break;
            case EQ:
                this.instructions.add(new Eq());
                break;
        }
    }

    public void parseInstruction(String instruction) {
        String[] instructionSplit = instruction.split(" ");
        addInstruction(
                InstructionType.getInstructionTypeFromString(instructionSplit[0]),
                instructionSplit.length > 1 ? Integer.parseInt(instructionSplit[1]) : 0
        );
    }

    public void parseInstruction(byte opCode, int args) {
        addInstruction(
                InstructionType.getInstructionTypeFromOpCode(opCode),
                args
        );
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

    public void runAll(Stack stack, InstructionPointer instructionPointer) {
        while (instructionPointer.getInstructionPointer() < this.instructions.size()) {
            this.runNext(stack, instructionPointer);
        }
    }

    public void runNext(Stack stack, InstructionPointer instructionPointer) {
        if (instructionPointer.getInstructionPointer() < this.instructions.size()) {
            this.instructions.get(instructionPointer.getInstructionPointer())
                    .execute(stack, instructionPointer);
        }
    }
}
