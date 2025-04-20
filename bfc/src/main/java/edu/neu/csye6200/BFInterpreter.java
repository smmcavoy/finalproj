package edu.neu.csye6200;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BFInterpreter implements InterpreterAPI{
    private int operationsUsed;
    private MemoryTape memory;
 

    public static int getClosingBracketIndex(String code, int start) {
        int openBrackets = 1;
        for (int i = start + 1; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '[') {
                openBrackets++;
            } else if (c == ']') {
                openBrackets--;
                if (openBrackets == 0) {
                    return i;
                }
            }
        }
        return -1; // No matching closing bracket found
    }

    private void interpret(String code, int relativeIndex, Supplier<Character> stdin, Consumer<Character> stdout) throws Exception {
        int i = 0;
        while (i < code.length()) {
            char c = code.charAt(i);

            if (c == '>') {
                try {
                    memory.cursorRight();
                } catch (Exception e) {
                    throw new Exception(e.getMessage() + " (index " + (relativeIndex + i) + ")");
                }
            } else if (c == '<') {
                try {
                    memory.cursorLeft();
                } catch (Exception e) {
                    throw new Exception(e.getMessage() + " (index " + (relativeIndex + i) + ")");
                }
            } else if (c == '+') {
                memory.increment();
            } else if (c == '-') {
                memory.decrement();
            } else if (c == '.') {
                Character outputChar = (char) memory.read();
                // convert from ISO-8859-1 to utf-8
                outputChar = new String(new byte[]{(byte) outputChar.charValue()}, 0, 1, java.nio.charset.StandardCharsets.ISO_8859_1).charAt(0);
                stdout.accept(outputChar);
            } else if (c == '[') {
                int closingBracketIndex = getClosingBracketIndex(code, i);
                if (closingBracketIndex == -1) {
                    throw new Exception("Unmatched opening bracket at index " + (relativeIndex + i));
                }
                while (memory.read() != 0) {
                    interpret(code.substring(i + 1, closingBracketIndex), relativeIndex + i + 1, stdin, stdout);
                }
                i = closingBracketIndex; // Move the index to the closing bracket
            } else if (c == ',') {
                Character inputChar = stdin.get();
                if (inputChar == null) {
                    throw new Exception("Failed to read from stdin");
                }
                // convert from utf-8 to ISO-8859-1
                inputChar = new String(new byte[]{(byte) inputChar.charValue()}, 0, 1, java.nio.charset.StandardCharsets.ISO_8859_1).charAt(0);
                memory.write((int) inputChar);
            }
            operationsUsed++;
            i++;
        }
    }

    @Override
    public void interpret(String code, Supplier<Character> stdin, Consumer<Character> stdout) {
        memory = new MemoryTape();
        operationsUsed = 0;
        try {
            interpret(code, 0, stdin, stdout);
        } catch (Exception e) {
            String error_msg = "\nError: " + e.getMessage() + "\n" + "Memory state when error occurred:\n" + toString() + "\n";
            for (Character c: error_msg.toCharArray()) {
                stdout.accept(c);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Operations used: ").append(operationsUsed).append("\n");
        s.append("Memory state:\n").append(memory.toString()).append("\n");
        return s.toString();
    }

    public static void demo() {
        BFInterpreter bf = new BFInterpreter();
        System.out.println("Starting BFInterpreter demo (text only)...\n");
        String code = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        bf.interpret(code, () -> null, c -> System.out.print(c));
        System.out.println("Execution Report:");
        System.out.println(bf.toString());
        System.out.println("BFInterpreter demo completed!");
    }

}
