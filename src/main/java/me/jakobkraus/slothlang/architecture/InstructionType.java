package me.jakobkraus.slothlang.architecture;

public enum InstructionType {
    CSI((byte) 0b00000000, "csi", 4),
    ADD((byte) 0b00001000, "add", 0),
    SUB((byte) 0b00001001, "sub", 0),
    MOD((byte) 0b00001010, "mod", 0),
    SQR((byte) 0b00001011, "sqr", 0),
    J((byte) 0b00010000, "j", 4),
    JE((byte) 0b00010001, "je", 4),
    JNE((byte) 0b00010010, "jne", 4),
    CALL((byte) 0b00010011, "call", 4),
    RETURN((byte) 0b00010100, "return", 0),
    AND((byte) 0b00100000, "and", 0),
    OR((byte) 0b00100001, "or", 0),
    XOR((byte) 0b00100010, "xor", 0),
    SLL((byte) 0b00100011, "sll", 1),
    SRL((byte) 0b00100100, "srl", 1),
    EQ((byte) 0b01000000, "eq", 0),
    EQI((byte) 0b01000001, "eqi", 4),
    COPY((byte) 0b01001000, "copy", 0),
    DROP((byte) 0b01001001, "drop", 0),
    SWAP((byte) 0b01001010, "swap", 0),
    ROT((byte) 0b01001011, "rot", 4),
    TUCK((byte) 0b01001100, "tuck", 4),
    SW((byte) 0b01001101, "sw", 4),
    LW((byte) 0b01001110, "lw", 4);

    private final byte opCode;
    private final String instructionString;
    private final int argLength;

    InstructionType(byte opCode, String instructionString, int argLength) {
        this.opCode = opCode;
        this.instructionString = instructionString;
        this.argLength = argLength;
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

    public int getArgLength() {
        return this.argLength;
    }
}
