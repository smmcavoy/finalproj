package edu.neu.csye6200;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class InterpreterGUI {

   
    private final JFrame frame;

    
    private JTextArea inputArea;
    private JTextArea outputArea;
    private final JButton interpretButton;
    private final JButton clearButton;

    private InterpreterAPI interpreter = (InterpreterAPI) new BFInterpreter();

    public InterpreterGUI() {
        // Initialize the frame
        frame = new JFrame("Brainfuck Interpreter");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize input area
        inputArea = new JTextArea(5, 50);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane inputScroll = new JScrollPane(inputArea);

        // Initialize output area
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        JScrollPane outputScroll = new JScrollPane(outputArea);

        // Initialize buttons
        interpretButton = new JButton("Interpret");
        clearButton = new JButton("Clear");

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(interpretButton);
        buttonPanel.add(clearButton);

        
        interpretButton.addActionListener((ActionEvent e) -> {
            String code = inputArea.getText();
            Iterator<String> dummyIterator = Arrays.asList("").iterator(); // no external input
            String result = interpreter.interpret(code, dummyIterator);
            outputArea.setText(result);
        });

        
        clearButton.addActionListener(_ -> {
            inputArea.setText("");
            outputArea.setText("");
        });

        
        frame.add(new JLabel("Enter Brainfuck Code Below:"), BorderLayout.NORTH);
        frame.add(inputScroll, BorderLayout.WEST);
        frame.add(outputScroll, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

    
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterpreterGUI::new);
    }
}