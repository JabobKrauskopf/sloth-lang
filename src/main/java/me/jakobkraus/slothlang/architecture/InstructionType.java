package me.jakobkraus.slothlang.architecture;

public enum InstructionType {
    ADD((byte) 0b00000000, "add"),
    SUB((byte) 0b00000001, "sub"),
    CSI((byte) 0b00000010, "csi"),
    SQR((byte) 0b00000011, "sqr"),
    J((byte) 0b00000100, "j"),
    Je((byte) 0b00000101, "je"),
    Jne((byte) 0b00000110, "jne"),
    EQ((byte) 0b10000000, "eq");

    private final byte opCode;
    private final String instructionString;

    InstructionType(byte opCode, String instructionString) {
        this.opCode = opCode;
        this.instructionString = instructionString;
    }

    public byte getOpCode() {
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
