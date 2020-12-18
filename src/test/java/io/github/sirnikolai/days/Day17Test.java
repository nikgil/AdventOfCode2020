package io.github.sirnikolai.days;

import io.github.sirnikolai.AbstractSolution;
import io.github.sirnikolai.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private AbstractSolution subject;

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        subject = new Day17();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPart1() {
        //Given
        String input = ".#.\n" +
                "..#\n" +
                "###";

        //When
        subject.Part1(input);

        //Then
        assertEquals("112" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPart2() throws IOException {
        //Given
        String input = ".#.\n" +
                "..#\n" +
                "###";

        //When
        subject.Part2(input);

        //Then
        assertEquals("848" + System.lineSeparator(), outContent.toString());
    }
}
