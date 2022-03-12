package me.jakobkraus.slothlang.assembler;

import me.jakobkraus.slothlang.instructions.InstructionStructure;
import me.jakobkraus.slothlang.util.FileHelper;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembler {

    public String code = "";
    private final InstructionStructure struct = new InstructionStructure();

    public String cleanCode(String code) {
        String[] lines = code.replaceAll("\n\n+", "\n").split("\n");
        StringBuilder cleanCode = new StringBuilder();

        Pattern labelPattern = Pattern.compile("(^[^:\s]+):");
        Pattern funcPattern = Pattern.compile("^func ([^:]+):");
        Map<String, Integer> labelMatches = new HashMap<>();
        Map<String, Integer> funcMatches = new HashMap<>();

        int lineNumber = 0;
        for (String line : lines) {
            Matcher labelMatch = labelPattern.matcher(line);
            if (labelMatch.matches()) {
                labelMatches.put(labelMatch.group(1), lineNumber);
                continue;
            }

            Matcher funcMatch = funcPattern.matcher(line);
            if (funcMatch.matches()) {
                funcMatches.put(funcMatch.group(1), lineNumber);
                continue;
            }

            lineNumber++;
            cleanCode.append(line).append("\n");
        }

        String parsedNewCode = cleanCode.toString();

        for (Map.Entry<String, Integer> entry : labelMatches.entrySet()) {
            parsedNewCode = parsedNewCode.replaceFirst(entry.getKey(), entry.getValue().toString());
        }

        for (Map.Entry<String, Integer> entry : funcMatches.entrySet()) {
            parsedNewCode = parsedNewCode.replaceFirst(entry.getKey(), entry.getValue().toString());
        }

        return parsedNewCode;
    }

    public void loadFile(String filepath) throws IOException {
        this.code = cleanCode(FileHelper.readFile(filepath));
    }

    public void loadString(String code) {
        this.code = code;
    }

    public void parse() {
        this.struct.parse(this.code);
    }

    public void saveSerialization(String filepath) throws IOException {
        ByteArrayOutputStream serialization = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(serialization);

        this.struct.serialize(outputStream);
        FileHelper.saveBinary(filepath, serialization);
    }

    public void printInstructions() {
        struct.print();
    }

    public void print() {
        System.out.println(this.code);
    }
}
