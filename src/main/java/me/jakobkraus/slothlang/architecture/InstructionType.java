package me.jakobkraus.slothlang.architecture;

public enum InstructionType {
    ADD(0b00000000, 0),
    CSI(0b00000001, 1);

    private final int opcode;
    private final int argnum;

    InstructionType(int opcode, int argnum) {
        this.opcode = opcode;
        this.argnum = argnum;
    }

    public int getArgnum() {
        return argnum;
    }

    public int getOpcode() {
        return opcode;
    }
}
