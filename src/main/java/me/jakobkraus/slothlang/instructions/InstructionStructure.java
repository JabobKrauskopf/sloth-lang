package me.jakobkraus.slothlang.instructions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.runtime.ExecutionContext;
import me.jakobkraus.slothlang.runtime.InstructionPointer;

public class InstructionStructure {
    private final List<Instruction> instructions = new ArrayList<>();

    public void addInstruction(InstructionType instructionType, int args) {
        switch (instructionType) {
            case ADD -> this.instructions.add(new Add());
            case SUB -> this.instructions.add(new Sub());
            case CSI -> this.instructions.add(new Csi(args));
            case SQR -> this.instructions.add(new Sqr());
            case J -> this.instructions.add(new J(args));
            case JE -> this.instructions.add(new Je(args));
            case JNE -> this.instructions.add(new Jne(args));
            case CALL -> this.instructions.add(new Call(args));
            case RETURN -> this.instructions.add(new Return());
            case AND -> this.instructions.add(new And());
            case OR -> this.instructions.add(new Or());
            case XOR -> this.instructions.add(new Xor());
            case SLL -> this.instructions.add(new Sll(args));
            case SRL -> this.instructions.add(new Srl(args));
            case EQ -> this.instructions.add(new Eq());
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

    public void runAll(ExecutionContext context) {
        while (context.getInstructionPointer().getInstructionPointerValue() < this.instructions.size()) {
            this.runNext(context);
        }
    }

    public void runNext(ExecutionContext context) {
        InstructionPointer instructionPointer = context.getInstructionPointer();
        if (instructionPointer.getInstructionPointerValue() < this.instructions.size()) {
            this.instructions.get(instructionPointer.getInstructionPointerValue())
                    .execute(context);
        }
    }
}
