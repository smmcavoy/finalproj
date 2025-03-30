package edu.neu.csye6200;

import java.util.Iterator;
import javax.swing.*;

public class InterpreterGUI {
    /**
     * TODO: Implement the GUI for the interpreter. Should have:
     * 1. A text area for input.
     * 2. A text area for output.
     * 3. A button to run the interpreter.
     * 4. A menu bar to save/load .bf files.
     */
    private Iterator<String> inputIterator;
    private InterpreterAPI interpreter;

    private String getInput() {
        /**
         * TODO: Implement the method to get additional input from the user while program is running. Input should always be a single character.
         */
        return ""; // Placeholder for actual input retrieval
    }

    private void run() {
        /**
         * TODO: When run button is clicked, do the following:
         * 1. Disable buttons and input textarea.
         * 2. Get the input from the GUI.
         * 3. Call the interpreter with the input and the input iterator. (interpreter.run([input text area contents], inputIterator))
         * 4. Display the output (returned by interpreter.run) in the GUI.
         * 5. Enable buttons and input textarea again.
         */
    }

    public InterpreterGUI(InterpreterAPI interpreter) {
        inputIterator = new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return true; // Always true for GUI input
            }

            @Override
            public String next() {
                return getInput(); // Get input from the GUI
            }
        };
        this.interpreter = interpreter;
        /**
         * TODO: Implement the GUI layout and components.
         */
    }

    public static void demo() {
        // update later with actual class instance
        InterpreterAPI interpreter = new InterpreterAPI() {
            @Override
            public String interpret(String input, Iterator<String> inputStream) {
                // Placeholder for actual interpretation logic
                return "Interpreted output"; // Placeholder for actual output
            }
        };
        InterpreterGUI gui = new InterpreterGUI(interpreter);
    }
}
