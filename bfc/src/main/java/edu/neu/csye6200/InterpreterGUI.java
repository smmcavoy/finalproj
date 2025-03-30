package edu.neu.csye6200;

public class InterpreterGUI {
    public String getInput() {
        /**
         * TODO: Implement a method to ask the user for input.
         * Input needs to be a SINGLE CHARACTER.
         */
        return "";
    }

    public void showOutput() {
        /**
         * TODO: Implement a method for the interpreter to call to show output.
         */
    }

    public void disable() {
        /**
         * TODO: Implement a method to disable buttons/input text area.
         * Should also clear the contents of the output text area.
         */
    }

    public void enable() {
        /**
         * TODO: Implement a method to enable buttons/input text area.
         */
    }

    public String getTextFieldContents() {
        /**
         * TODO: Implement a method to return the contents of the text field.
         */
        return "";
    }

    public InterpreterGUI() {
        /**
         * TODO: Implement the GUI for the interpreter. Needs:
         * - text area for input
         * - a button to run the interpreter
         * - text area for output
         * Note: to get output of interpreter, call interpreter.run(input) and display the result in the output text area. 
         * Ideally, buttons should be disabled until interpreter provides output.
         * 
         * Stretch goal:
         * - menu bar with options to save and load .bf files.
         * 
         */
    }

    public void demo() {
        InterpreterGUI gui = new InterpreterGUI();
    }
}
