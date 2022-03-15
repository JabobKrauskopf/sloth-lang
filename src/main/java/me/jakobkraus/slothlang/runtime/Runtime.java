package me.jakobkraus.slothlang.runtime;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.instructions.*;
import me.jakobkraus.slothlang.pagestructure.PageDirectory;
import me.jakobkraus.slothlang.util.*;

import java.io.IOException;

public class Runtime {
    private final Stack instructionStack = new Stack();
    private final Stack callStack = new Stack();
    private final InstructionPointer instructionPointer = new InstructionPointer();
    private final PageDirectory pageDirectory = new PageDirectory();
    private CodeStructure codeStructure;

    public void loadFile(String filepath) throws IOException {
        this.codeStructure = new CodeStructure(FileHelper.readBinary(filepath));
    }

    public void printInstructionStack() {
        this.instructionStack.print();
    }

    public void printCallStack() {
        this.callStack.print();
    }

    public void printInstructionPointer() {
        System.out.println(this.instructionPointer.getInstructionPointerValue());
    }

    public void runAll() {
        ExecutionContext context = new ExecutionContext(this.instructionStack, this.callStack, this.instructionPointer, this.pageDirectory, this.codeStructure);

        while (this.instructionPointer.getInstructionPointerValue() < this.codeStructure.getLength()) {
            this.runNext(context);
        }
    }

    public void runNext(int n) {
        ExecutionContext context = new ExecutionContext(this.instructionStack, this.callStack, this.instructionPointer, this.pageDirectory, this.codeStructure);

        for (int i = 0; i < n; i++) {
            this.runNext(context);
        }
    }

    public void runNext() {
        ExecutionContext context = new ExecutionContext(this.instructionStack, this.callStack, this.instructionPointer, this.pageDirectory, this.codeStructure);

        this.runNext(context);
    }

    public void runNext(ExecutionContext context) {
        InstructionType instructionType = InstructionType.getInstructionTypeFromOpCode(
                this.codeStructure.readByte(this.instructionPointer.getInstructionPointerValue())
        );

        switch (instructionType) {
            case CSI -> Csi.execute(context);
            case ADD -> Add.execute(context);
            case SUB -> Sub.execute(context);
            case SQR -> Sqr.execute(context);
            case J -> J.execute(context);
            case JE -> Je.execute(context);
            case JNE -> Jne.execute(context);
            case CALL -> Call.execute(context);
            case RETURN -> Return.execute(context);
            case AND -> And.execute(context);
            case OR -> Or.execute(context);
            case XOR -> Xor.execute(context);
            case SLL -> Sll.execute(context);
            case SRL -> Srl.execute(context);
            case EQ -> Eq.execute(context);
            case EQI -> Eqi.execute(context);
            case COPY -> Copy.execute(context);
            case DROP -> Drop.execute(context);
            case SWAP -> Swap.execute(context);
            case ROT -> Rot.execute(context);
            case TUCK -> Tuck.execute(context);
            case SW -> Sw.execute(context);
            case LW -> Lw.execute(context);
        }
    }
}
