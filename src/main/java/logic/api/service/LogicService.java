package logic.api.service;

import java.util.List;
import java.util.Map;

public interface LogicService {
    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";
    public static final String INVALID_INPUT = "The input is invalid";

    /**
     * For a given number(s), it generates the sequence of numbers starting from 1, replacing
     * any number divisible by 3 with "Fizz" and any number divisible by 5 with "Buzz" and 
     * any number divisible by 3 and 5 with "Fizz Buzz".
     */
    public Map<String, List<String>> generateFizzBuzzSequences(Map<String, String> inputData);

    /**
     * Serialize the collection to json string.
     */
    public String generateJsonFromCollection(Map<String, List<String>> fizzBuzzSequence);
}