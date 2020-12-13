package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private AbstractSolution subject;

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        subject = new Day13();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPart1() {
        //Given
        String input = "939\n" +
                "7,13,x,x,59,x,31,19";

        //When
        subject.Part1(input);

        //Then
        assertEquals("295" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPart2() {
        //Given
        String input = "939\n" +
                "7,13,x,x,59,x,31,19";

        //When
        subject.Part2(input);

        //Then
        assertEquals("1068781" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPart2Others() {
        //Given
        String[] inputs = {"939\n17,x,13,19", "939\n67,7,59,61", "939\n67,x,7,59,61", "939\n67,7,x,59,61", "939\n1789,37,47,1889"};
        String[] outputs = {"3417", "754018", "779210", "1261476", "1202161486"};

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
