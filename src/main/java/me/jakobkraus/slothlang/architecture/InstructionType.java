package me.jakobkraus.slothlang.architecture;

import me.jakobkraus.slothlang.instructions.*;
import me.jakobkraus.slothlang.util.ExecutionContext;
import me.jakobkraus.slothlang.util.SerializationContext;

import java.io.IOException;

public enum InstructionType {
    CSI((byte) 0b00000000, "csi", 5, Csi::serialize, Csi::execute),
    ADD((byte) 0b00001000, "add", 1, Add::serialize, Add::execute),
    SUB((byte) 0b00001001, "sub", 1, Sub::serialize, Sub::execute),
    SQR((byte) 0b00001010, "sqr", 1, Sqr::serialize, Sqr::execute),
    J((byte) 0b00010000, "j", 5, me.jakobkraus.slothlang.instructions.J::serialize, me.jakobkraus.slothlang.instructions.J::execute),
    JE((byte) 0b00010001, "je", 5, Je::serialize, Je::execute),
    JNE((byte) 0b00010010, "jne", 5, Jne::serialize, Jne::execute),
    CALL((byte) 0b00010011, "call", 5, Call::serialize, Call::execute),
    RETURN((byte) 0b00010100, "return", 1, Return::serialize, Return::execute),
    AND((byte) 0b00100000, "and", 1, And::serialize, And::execute),
    OR((byte) 0b00100001, "or", 1, Or::serialize, Or::execute),
    XOR((byte) 0b00100010, "xor", 1, Xor::serialize, Xor::execute),
    SLL((byte) 0b00100011, "sll", 2, Sll::serialize, Sll::execute),
    SRL((byte) 0b00100100, "srl", 2, Srl::serialize, Srl::execute),
    EQ((byte) 0b01000000, "eq", 1, Eq::serialize, Eq::execute),
    EQI((byte) 0b01000001, "eqi", 5, Eqi::serialize, Eqi::execute),
    COPY((byte) 0b01001000, "copy", 1, Copy::serialize, Copy::execute),
    DROP((byte) 0b01001001, "drop", 1, Drop::serialize, Drop::execute),
    SWAP((byte) 0b01001010, "swap", 1, Swap::serialize, Swap::execute),
    ROT((byte) 0b01001011, "rot", 5, Rot::serialize, Rot::execute),
    TUCK((byte) 0b01001100, "tuck", 5, Tuck::serialize, Tuck::execute),
    SW((byte) 0b01001101, "sw", 5, Sw::serialize, Sw::execute),
    LW((byte) 0b01001110, "lw", 5, Lw::serialize, Lw::execute);

    private final byte opCode;
    private final String instructionString;
    private final int instructionLength;
    private final SerializationFunction serializationFunction;
    private final ExecutionFunction executionFunction;

    InstructionType(byte opCode, String instructionString, int instructionLength, SerializationFunction serializationFunction, ExecutionFunction executionFunction) {
        this.opCode = opCode;
        this.instructionString = instructionString;
        this.instructionLength = instructionLength;
        this.serializationFunction = serializationFunction;
        this.executionFunction = executionFunction;
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

    public void serialize(SerializationContext context) throws IOException {
        this.serializationFunction.serialize(context);
    }

    public void execute(ExecutionContext context) {
        this.executionFunction.execute(context);
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
