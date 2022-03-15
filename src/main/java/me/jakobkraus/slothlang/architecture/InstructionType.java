package me.jakobkraus.slothlang.architecture;

public enum InstructionType {
    CSI((byte) 0b00000000, "csi", 5),
    ADD((byte) 0b00001000, "add", 1),
    SUB((byte) 0b00001001, "sub", 1),
    MOD((byte) 0b00001010, "mod", 1),
    SQR((byte) 0b00001011, "sqr", 1),
    J((byte) 0b00010000, "j", 5),
    JE((byte) 0b00010001, "je", 5),
    JNE((byte) 0b00010010, "jne", 5),
    CALL((byte) 0b00010011, "call", 5),
    RETURN((byte) 0b00010100, "return", 1),
    AND((byte) 0b00100000, "and", 1),
    OR((byte) 0b00100001, "or", 1),
    XOR((byte) 0b00100010, "xor", 1),
    SLL((byte) 0b00100011, "sll", 2),
    SRL((byte) 0b00100100, "srl", 2),
    EQ((byte) 0b01000000, "eq", 1),
    EQI((byte) 0b01000001, "eqi", 5),
    COPY((byte) 0b01001000, "copy", 1),
    DROP((byte) 0b01001001, "drop", 1),
    SWAP((byte) 0b01001010, "swap", 1),
    ROT((byte) 0b01001011, "rot", 5),
    TUCK((byte) 0b01001100, "tuck", 5),
    SW((byte) 0b01001101, "sw", 5),
    LW((byte) 0b01001110, "lw", 5);

    private final byte opCode;
    private final String instructionString;
    private final int instructionLength;

    InstructionType(byte opCode, String instructionString, int instructionLength) {
        this.opCode = opCode;
        this.instructionString = instructionString;
        this.instructionLength = instructionLength;
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

    public int getInstructionLength() {
        return this.instructionLength;
    }
}
