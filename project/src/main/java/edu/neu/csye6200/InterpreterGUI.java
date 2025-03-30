package main.java.edu.neu.csye6200;

public class InterpreterGUI {
    public InterpreterGUI(InterpreterAPI interpreter) {
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
        this.interpreter = interpreter;
        
    }
    public void demo() {
        InterpreterGUI gui = new InterpreterGUI(new InterpreterAPI() {
            @Override
            public String run(String input) {
                return input + "!";
            }
        });
    }
}
