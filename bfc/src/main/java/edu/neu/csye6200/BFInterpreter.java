package edu.neu.csye6200;

import java.util.Iterator;

public class BFInterpreter implements InterpreterAPI{
    private StringBuilder output;
    private Iterator<String> inputIterator;
    private int cellsUsed;
    private int operationsUsed;
    private Cell currentCell;
    private Cell firstCell;
    
    private class Cell {
        /**
         * linked list of cells to represent the memory tape
         * each cell has an integer value and a pointer to the next and previous cells
         */
        private int value;
        private Cell next;
        private Cell prev;

        private void increment() {
            value = (value + 1)%256;
        }
        private void decrement() {
            value = (value - 1)%256;
        }
        private void setValue(int value) {
            this.value = value;
        }
        private int getValue() {
            return value;
        }
        private Cell getNext() {
            if (next == null) {
                next = new Cell(0);
                cellsUsed++;
                next.prev = this;
            }
            return next;
        }
        private Cell getPrev() throws Exception {
            if (prev == null) {
                throw new Exception("Script attempts to access a cell in negative memory.");
            }
            return prev;
        }

        private Cell(int value) {
            this.value = value;
        }
    }

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

    public void interpret(String code, int relativeIndex) throws Exception {
        int i = 0;
        while (i < code.length()) {
            char c = code.charAt(i);

            if (c == '>') {
                currentCell = currentCell.getNext();
            } else if (c == '<') {
                try {
                    currentCell = currentCell.getPrev();
                } catch (Exception e) {
                    throw new Exception(e.getMessage() + " (index " + (relativeIndex + i) + ")");
                }
            } else if (c == '+') {
                currentCell.increment();
            } else if (c == '-') {
                currentCell.decrement();
            } else if (c == '.') {
                output.append((char) currentCell.getValue());
            } else if (c == '[') {
                int closingBracketIndex = getClosingBracketIndex(code, i);
                if (closingBracketIndex == -1) {
                    throw new Exception("Unmatched opening bracket at index " + (relativeIndex + i));
                }
                while (currentCell.getValue() != 0) {
                    interpret(code.substring(i + 1, closingBracketIndex), relativeIndex + i + 1);
                }
                i = closingBracketIndex; // Move the index to the closing bracket
            } else if (c == ',') {
                if (inputIterator.hasNext()) {
                    String input = new String(inputIterator.next().getBytes("UTF-8"), "ISO-8859-1");
                    if (input.length() > 0) {
                        currentCell.setValue(input.charAt(0));
                    } else {
                        currentCell.setValue(0);
                    }
                } else {
                    currentCell.setValue(0);
                }
            }
            operationsUsed++;
            i++;
        }
    }

    @Override
    public String interpret(String code, Iterator<String> inputIterator) {
        currentCell = new Cell(0);
        firstCell = currentCell; // Keep track of the first cell
        this.inputIterator = inputIterator;
        output = new StringBuilder();
        cellsUsed = 1;
        operationsUsed = 0;
        try {
            interpret(code, 0);
        } catch (Exception e) {
            output.append("Error: " + e.getMessage());
        }
        try {
            return new String(output.toString().getBytes("ISO-8859-1"), "UTF-8");
        }
        catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String toString() {
        StringBuilder memory = new StringBuilder();
        for (Cell cell = firstCell; cell != null; cell = cell.next) {
            memory.append("|"); // Start with a leading pipe for each cell
            if (cell == currentCell) {

                memory.append("*"+cell.getValue()+"*"); // Start with a leading pipe for the first cell
            } else {
                memory.append(cell.getValue()); // Add a space for non-current cells
            }
        }
        memory.append("|");
        return memory.toString();
    }

    public static void demo() {
        BFInterpreter bf = new BFInterpreter();
        System.out.println("Starting BFInterpreter demo (text only)...\n");
        String code = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        String result = bf.interpret(code, null);
        System.out.println("Program output:");
        System.out.print(result); // Hello World!
        System.out.println("\nExecution Report:");
        System.out.println("- Cells used: " + bf.cellsUsed);
        System.out.println("- Operations used: " + bf.operationsUsed);
        System.out.println("- Memory state: " + bf.toString());
        System.out.println("\nBFInterpreter demo completed!");
    }

}
