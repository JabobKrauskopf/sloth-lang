package me.jakobkraus.slothlang.architecture;

import java.util.Objects;

public enum InstructionType {
    ADD(0b00000000, "add"),
    CSI(0b00000001, "csi"),
    SQR(0b00000011, "sqr"),
    SUB(0b00000010, "sub");

    private final int opcode;
    private final String instructionString;

    InstructionType(int opcode, String instructionString) {
        this.opcode = opcode;
        this.instructionString = instructionString;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public String getInstructionString() {
        return this.instructionString;
    }

    public static InstructionType getInstructionTypeFromString(String string) {
        for (InstructionType s : InstructionType.values()) {
            if (s.getInstructionString().equals(string))
                return s;
        }
        // TODO: Add default or throw Error
        return ADD;
    }

    public static InstructionType getInstructionTypeFromOpCode(byte opCode) {
        for (InstructionType s : InstructionType.values()) {
            if (s.getOpcode() == opCode)
                return s;
        }
        // TODO: Add default or throw Error
        return ADD;
    }
}
