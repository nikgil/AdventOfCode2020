package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private AbstractSolution subject;

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        subject = new Day15();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPart1() {
        //Given
        String[] inputs = {"0,3,6", "1,3,2", "2,1,3", "1,2,3", "2,3,1", "3,2,1", "3,1,2"};
        String[] outputs = {"436", "1", "10", "27", "78", "438", "1836"};

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
        String[] inputs = {"0,3,6", "1,3,2", "2,1,3", "1,2,3", "2,3,1", "3,2,1", "3,1,2"};
        String[] outputs = {"175594", "2578", "3544142", "261214", "6895259", "18", "362"};

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
