package edu.neu.csye6200;

import java.util.Iterator;

public class BFInterpreter implements InterpreterAPI{
    private StringBuilder output;
    private Cell currentCell;
    
    private class Cell {
        /**
         * linked list of cells to represent the memory tape
         * each cell has an integer value and a pointer to the next and previous cells
         */
        private int value;
        private Cell next;
        private Cell prev;

        private void increment() {
            value = (value + 1);
        }
        private void decrement() {
            value = (value - 1);
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

    @Override
    public String interpret(String code, Iterator<String> inputIterator) {
        output = new StringBuilder();
        currentCell = new Cell(0);

        return output.toString();
    }

    public BFInterpreter() {
        /**
         * TODO: Implement the constructor.
         */

    }

    public static void demo() {

    }

}
