package edu.neu.csye6200;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Driver class starting...\n");
        InterpreterGUI.demo();
        BFInterpreter.demo();
        System.out.println("\nDriver class finished!");
    }
}