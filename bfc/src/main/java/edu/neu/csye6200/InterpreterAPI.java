package edu.neu.csye6200;

import java.util.Iterator;

public interface InterpreterAPI {
    public String interpret(String input, Iterator<String> inputIterator);
}