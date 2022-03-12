package me.jakobkraus.slothlang.architecture;

public enum InstructionType {
    CSI((byte) 0b00000000, "csi"),
    ADD((byte) 0b00001000, "add"),
    SUB((byte) 0b00001001, "sub"),
    MOD((byte) 0b00001010, "mod"),
    SQR((byte) 0b00001011, "sqr"),
    J((byte) 0b00010000, "j"),
    JE((byte) 0b00010001, "je"),
    JNE((byte) 0b00010010, "jne"),
    CALL((byte) 0b00010011, "call"),
    RETURN((byte) 0b00010100, "return"),
    AND((byte) 0b00100000, "and"),
    OR((byte) 0b00100001, "or"),
    XOR((byte) 0b00100010, "xor"),
    SLL((byte) 0b00100011, "sll"),
    SRL((byte) 0b00100100, "srl"),
    EQ((byte) 0b01000000, "eq"),
    COPY((byte) 0b01000001, "copy"),
    DROP((byte) 0b01000010, "drop"),
    SWAP((byte) 0b01000011, "swap"),
    ROT((byte) 0b01000100, "rot");

    private final byte opCode;
    private final String instructionString;

    InstructionType(byte opCode, String instructionString) {
        this.opCode = opCode;
        this.instructionString = instructionString;
    }

    public static InstructionType getInstructionTypeFromString(String string) {
        for (InstructionType s : InstructionType.values()) {
            if (s.getInstructionString().equals(string))
                return s;
        }
        // TODO: Add default or throw Error
        throw new RuntimeException("Couldn't find instruction " + string);
    }

    public static InstructionType getInstructionTypeFromOpCode(byte opCode) {
        for (InstructionType s : InstructionType.values()) {
            if (s.getOpCode() == opCode)
                return s;
        }
        // TODO: Add default or throw Error
        throw new RuntimeException("Couldn't find instruction " + opCode);
    }

    public byte getOpCode() {
        return this.opCode;
    }

    public String getInstructionString() {
        return this.instructionString;
    }
}
