package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day18Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private AbstractSolution subject;

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        subject = new Day18();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPart1() {
        //Given
        String[] inputs = {
                "2 * 3 + (4 * 5)", "5 + (8 * 3 + 9 + 3 * 4 * 3)",
                "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))",
                "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"};

        String[] outputs = {"26", "437", "12240", "13632"};

        for(int i = 0; i < inputs.length; i++) {
            String input = inputs[i];
            //When
            subject.Part1(input);

            //Then
            assertEquals(makeTargetString(outputs, i) + System.lineSeparator(), outContent.toString());
        }
    }

    @Test
    public void testPart2() {
        //Given
        String[] inputs = {
                "1 + (2 * 3) + (4 * (5 + 6))",
                "2 * 3 + (4 * 5)",
                "5 + (8 * 3 + 9 + 3 * 4 * 3)",
                "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))",
                "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"};

        String[] outputs = {"51", "46", "1445", "669060", "23340"};

        for(int i = 0; i < inputs.length; i++) {
            String input = inputs[i];
            //When
            subject.Part2(input);

            //Then
            assertEquals(makeTargetString(outputs, i) + System.lineSeparator(), outContent.toString());
        }
    }

    private String makeTargetString(String[] arr, int curIndex) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < curIndex; i++) {
            sb.append(arr[i] + System.lineSeparator());
        }

        sb.append(arr[curIndex]);

        return sb.toString();
    }
}
