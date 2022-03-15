package me.jakobkraus.slothlang.assembler;

import me.jakobkraus.slothlang.architecture.InstructionType;
import me.jakobkraus.slothlang.instructions.*;
import me.jakobkraus.slothlang.util.FileHelper;
import me.jakobkraus.slothlang.util.SerializationContext;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembler {

    private String code = "";

    public String cleanCode(String code) {
        String[] lines = code
                .replaceAll("\n\n+", "\n")
                .replaceAll("(^|(?<=\n))\s+", "")
                .split("\n");
        StringBuilder cleanCode = new StringBuilder();

        Pattern labelPattern = Pattern.compile("(^[^:\s]+):");
        Pattern funcPattern = Pattern.compile("^func ([^:]+):");
        Map<String, Integer> labelMatches = new HashMap<>();
        Map<String, Integer> funcMatches = new HashMap<>();

        int address = 0;
        for (String line : lines) {
            if (line.startsWith("//"))
                continue;
            Matcher labelMatch = labelPattern.matcher(line);
            if (labelMatch.matches()) {
                labelMatches.put(labelMatch.group(1), address);
                continue;
            }

            Matcher funcMatch = funcPattern.matcher(line);
            if (funcMatch.matches()) {
                funcMatches.put(funcMatch.group(1), address);
                continue;
            }

            String[] instructions = line.split(" ", 2);
            InstructionType instructionType = InstructionType.getInstructionTypeFromString(instructions[0]);
            address = address + instructionType.getInstructionLength();
            cleanCode.append(line).append("\n");
        }

        String parsedNewCode = cleanCode.toString();

        for (Map.Entry<String, Integer> entry : labelMatches.entrySet()) {
            parsedNewCode = parsedNewCode.replaceAll(
                    "(?<!call\s)" + entry.getKey(),
                    entry.getValue().toString()
            );
        }

        for (Map.Entry<String, Integer> entry : funcMatches.entrySet()) {
            parsedNewCode = parsedNewCode.replaceAll(
                    "call " + entry.getKey(),
                    "call " + entry.getValue().toString()
            );
        }

        return parsedNewCode;
    }

    public void loadFile(String filepath) throws IOException {
        this.code = cleanCode(FileHelper.readFile(filepath));
    }

    public void loadString(String code) {
        this.code = code;
    }

    public void saveSerialization(String filepath) throws IOException {
        ByteArrayOutputStream serialization = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(serialization);

        SerializationContext context = new SerializationContext(outputStream);

        for (String line : this.code.split("\n")) {
            String[] instructions = line.split(" ", 2);
            InstructionType instructionType = InstructionType.getInstructionTypeFromString(instructions[0]);

            switch (instructionType) {
                case CSI -> Csi.serialize(context, instructions[1]);
                case ADD -> Add.serialize(context);
                case SUB -> Sub.serialize(context);
                case SQR -> Sqr.serialize(context);
                case J -> J.serialize(context, instructions[1]);
                case JE -> Je.serialize(context, instructions[1]);
                case JNE -> Jne.serialize(context, instructions[1]);
                case CALL -> Call.serialize(context, instructions[1]);
                case RETURN -> Return.serialize(context);
                case AND -> And.serialize(context);
                case OR -> Or.serialize(context);
                case XOR -> Xor.serialize(context);
                case SLL -> Sll.serialize(context, instructions[1]);
                case SRL -> Srl.serialize(context, instructions[1]);
                case EQ -> Eq.serialize(context);
                case EQI -> Eqi.serialize(context, instructions[1]);
                case COPY -> Copy.serialize(context);
                case DROP -> Drop.serialize(context);
                case SWAP -> Swap.serialize(context);
                case ROT -> Rot.serialize(context, instructions[1]);
                case TUCK -> Tuck.serialize(context, instructions[1]);
                case SW -> Sw.serialize(context, instructions[1]);
                case LW -> Lw.serialize(context, instructions[1]);
            }
        }

        FileHelper.saveBinary(filepath, serialization);
    }

    public void print() {
        System.out.println(this.code);
    }
}
