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

public class Day16Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private AbstractSolution subject;

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        subject = new Day16();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testPart1() {
        //Given
        String input = "class: 1-3 or 5-7\n" +
                "row: 6-11 or 33-44\n" +
                "seat: 13-40 or 45-50\n" +
                "\n" +
                "your ticket:\n" +
                "7,1,14\n" +
                "\n" +
                "nearby tickets:\n" +
                "7,3,47\n" +
                "40,4,50\n" +
                "55,2,20\n" +
                "38,6,12";

        //When
        subject.Part1(input);

        //Then
        assertEquals("71" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testPart2() throws IOException {
        //Note: this is an exception case where no example provided target output
        //So doing this out based on my input
        //Given
        String input = Main.parseDayInput(16);

        //When
        subject.Part2(input);

        //Then
        assertEquals("2766491048287" + System.lineSeparator(), outContent.toString());
    }
}
