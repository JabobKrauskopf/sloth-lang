package me.jakobkraus.slothlang.architecture;

import java.util.Objects;

public enum InstructionType {
    ADD((byte) 0b00000000, "add"),
    CSI((byte) 0b00000001, "csi"),
    SQR((byte) 0b00000011, "sqr"),
    SUB((byte) 0b00000010, "sub");

    private final byte opCode;
    private final String instructionString;

    InstructionType(byte opCode, String instructionString) {
        this.opCode = opCode;
        this.instructionString = instructionString;
    }

    public int getOpCode() {
        return this.opCode;
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
            if (s.getOpCode() == opCode)
                return s;
        }
        // TODO: Add default or throw Error
        return ADD;
    }
}
