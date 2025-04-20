package edu.neu.csye6200;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface InterpreterAPI {
    public void interpret(String input, Supplier<Character> stdin, Consumer<Character> stdout) throws Exception;
}