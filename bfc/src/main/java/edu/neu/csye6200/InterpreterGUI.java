package edu.neu.csye6200;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class InterpreterGUI {
    private enum lastUsed {
        INIT, INPUT, OUTPUT
    }

    lastUsed status;

    private final JFrame frame;

    private JTextArea inputArea;
    private JTextArea outputArea;
    private final JButton interpretButton;
    private final JButton clearButton;
    private final JLabel statusLabel; // Status label

    private InterpreterAPI interpreter = (InterpreterAPI) new BFInterpreter();

    public InterpreterGUI() {
        // Initialize the frame
        frame = new JFrame("BFInterpreter");
        frame.setSize(1100, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize input area
        inputArea = new JTextArea(10, 50);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane inputScroll = new JScrollPane(inputArea);

        // Initialize output area
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane outputScroll = new JScrollPane(outputArea);

        // Initialize status label
        statusLabel = new JLabel("Status: Ready");

        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.add(inputScroll, BorderLayout.NORTH);
        innerPanel.add(outputScroll, BorderLayout.CENTER);
        innerPanel.add(statusLabel, BorderLayout.SOUTH); // Add status label to the inner panel


        // Initialize buttons
        interpretButton = new JButton("Interpret");
        clearButton = new JButton("Clear");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(interpretButton);
        buttonPanel.add(clearButton);
        // Add action listeners
        interpretButton.addActionListener((ActionEvent e) -> {
            status = lastUsed.INIT; // Reset status to INIT
            outputArea.setText(""); // Clear the output area before interpreting
            statusLabel.setText("Status: Running..."); // Update status label
            Consumer<Character> stdout = (Character c) -> {
                if (status != lastUsed.OUTPUT) {
                    if (status == lastUsed.INPUT) {
                        outputArea.append("\n"); // Clear the input area before interpreting
                    }
                    status = lastUsed.OUTPUT;
                    outputArea.append("Out: "); // Clear the output area before interpreting
                }
                outputArea.append(c.toString());
            };
            Supplier<Character> stdin = () -> {
                /**
                 * TODO: listen for input from the user and return it as a Character.
                 */  
                return '1';              
            };
            String input = inputArea.getText();
            try {
                interpreter.interpret(input, stdin, stdout);
                statusLabel.setText("Status: Finished"); // Update status label
            } catch (Exception ex) {
                outputArea.append("\nError: " + ex.getMessage());
                statusLabel.setText("Status: Error"); // Update status label
            }
        });

        clearButton.addActionListener((ActionEvent e) -> {
            inputArea.setText("");
            outputArea.setText("");
            statusLabel.setText("Status: Ready");
        });

        // Add components to frame
        frame.add(new JLabel("Enter code Below:"), BorderLayout.NORTH);
        frame.add(innerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void demo() {
        SwingUtilities.invokeLater(InterpreterGUI::new);
    }
}