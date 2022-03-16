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
    public static int HEADER_LENGTH = 14;

    public String cleanText(String code) {
        String[] lines = code
                .replaceAll("^\n", "")
                .replaceAll("\n\n+", "\n")
                .replaceAll("(^|(?<=\n))\s+", "")
                .split("\n");
        StringBuilder cleanCode = new StringBuilder();

        Pattern labelPattern = Pattern.compile("(^[^:\s]+):");
        Pattern funcPattern = Pattern.compile("^func ([^:]+):");
        Map<String, Integer> labelMatches = new HashMap<>();
        Map<String, Integer> funcMatches = new HashMap<>();

        int address = Assembler.HEADER_LENGTH;
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
        this.code = FileHelper.readFile(filepath);
    }

    public void loadString(String code) {
        this.code = code;
    }

    public void addHeader(int dataSize, int textSize, DataOutputStream finalOutputStream) throws IOException {
        finalOutputStream.writeByte(117);
        finalOutputStream.writeByte(76);
        finalOutputStream.writeInt(dataSize);
        finalOutputStream.writeInt(textSize);
        finalOutputStream.writeInt(Assembler.HEADER_LENGTH + dataSize + textSize);
    }

    public void saveSerialization(String filepath) throws IOException {
        ByteArrayOutputStream textSerialization = new ByteArrayOutputStream();
        DataOutputStream textOutputStream = new DataOutputStream(textSerialization);

        ByteArrayOutputStream dataSerialization = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(dataSerialization);

        ByteArrayOutputStream finalSerialization = new ByteArrayOutputStream();
        DataOutputStream finalOutputStream = new DataOutputStream(finalSerialization);

        SerializationContext context = new SerializationContext(textOutputStream, "");

        String[] dataText = this.code.split("\\.text", 2);
        String data = dataText[0];
        String text = cleanText(dataText[1]);

        for (String line : text.split("\n")) {
            String[] instructions = line.split(" ", 2);
            InstructionType instructionType = InstructionType.getInstructionTypeFromString(instructions[0]);

            context.setArgs(instructionType.getInstructionLength() > 1 ? instructions[1] : "");
            instructionType.serialize(context);
        }

        this.addHeader(dataOutputStream.size(), textOutputStream.size(), finalOutputStream);
        dataSerialization.writeTo(finalSerialization);
        textSerialization.writeTo(finalSerialization);

        FileHelper.saveBinary(filepath, finalSerialization);
    }

    public void print() {
        System.out.println(this.code);
    }
}
