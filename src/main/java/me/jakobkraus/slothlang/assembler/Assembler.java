package me.jakobkraus.slothlang.assembler;

import me.jakobkraus.slothlang.instructions.InstructionStructure;
import me.jakobkraus.slothlang.util.FileHelper;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembler {

    public String code = "";
    private final InstructionStructure struct = new InstructionStructure();

    public String cleanCode(String code) {
        Pattern pattern = Pattern.compile("(^[^:]+):");
        StringBuilder newCode = new StringBuilder();
        int lineNumber = 0;
        String[] lines = code.split("\n");
        List<String> matches = new ArrayList<>();
        List<Integer> matchesLines = new ArrayList<>();

        for (String line : lines) {
            Matcher match = pattern.matcher(line);
            if (match.matches()) {
                matches.add(match.group(1));
                matchesLines.add(lineNumber);
                continue;
            }
            lineNumber++;
            newCode.append(line).append("\n");
        }
        String parsedNewCode = newCode.toString();
        for (int i = 0; i < matches.size(); i++) {
            parsedNewCode = parsedNewCode.replaceFirst(matches.get(i), matchesLines.get(i).toString());
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
