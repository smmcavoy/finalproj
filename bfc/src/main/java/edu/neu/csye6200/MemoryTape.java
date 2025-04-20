package edu.neu.csye6200;

public class MemoryTape {
    private int maxLength;
    private Cell head;
    private Cell currentCell;

    private class Cell {
        /**
         * linked list of cells to represent the memory tape
         * each cell has an integer value and a pointer to the next and previous cells
         */
        private int value;
        private int index;
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
        private Cell getNext() throws Exception {
            if (index + 1 >= maxLength) {
                throw new Exception("Memory tape is full.");
            }
            if (next == null) {
                next = new Cell(0, index + 1);
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

        private Cell() {
            this(0);
        }

        private Cell(int value) {
            this(0, 0);
        }

        private Cell (int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    void cursorLeft() throws Exception {
        currentCell = currentCell.getPrev();
    }
    void cursorRight() throws Exception {
        currentCell = currentCell.getNext();
    }
    void increment() {
        currentCell.increment();
    }
    void decrement() {
        currentCell.decrement();
    }
    int read() {
        return currentCell.getValue();
    }
    void write(int value) {
        currentCell.setValue(value);
    }

    @Override
    public String toString() {
        StringBuilder memoryState = new StringBuilder();
        for (Cell cell = head; cell != null; cell = cell.next) {
            memoryState.append("|"); // Start with a leading pipe for each cell
            if (cell == currentCell) {

                memoryState.append("*"+cell.getValue()+"*"); // Start with a leading pipe for the first cell
            } else {
                memoryState.append(cell.getValue()); // Add a space for non-current cells
            }
        }
        memoryState.append("|");
        return memoryState.toString();
    }

    public MemoryTape() {
        this(30000); // Default size of 30,000 cells
    }

    public MemoryTape(int maxLength) {
        this.maxLength = maxLength;
        head = new Cell();
        currentCell = head;
    }
}
