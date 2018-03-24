package com.glen.missingletters;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class MissingLetterTest {


    private MissingLetters missingLetters = new MissingLetters();

    @Test
    public void allLetters() {

        String input = "A quick brown fox jumps over the lazy dog";
        String expectedOutput = "";
        assertEquals(expectedOutput, missingLetters.findMissing(input));
    }

    @Test
    public void missingAllButOneLetter() {

        String input = "A";
        String expectedOutput = "bcdefghijklmnopqrstuvwxyz";
        assertEquals(expectedOutput, missingLetters.findMissing(input));
    }

    @Test
    public void emptyString() {

        String input = "";
        String expectedOutput = "abcdefghijklmnopqrstuvwxyz";
        assertEquals(expectedOutput, missingLetters.findMissing(input));
    }

    @Test
    public void nullString() {

        String input = null;
        String expectedOutput = "abcdefghijklmnopqrstuvwxyz";
        assertEquals(expectedOutput, missingLetters.findMissing(input));
    }

    @Test
    public void missingSingleLetter() {

        String input = "A quick brown fox jumps over the lazy do";
        String expectedOutput = "g";
        assertEquals(expectedOutput, missingLetters.findMissing(input));
    }


    @Test
    public void slowYellowCat() {

        String input = "A slow yellow fox crawls under the proactive dog";
        String expectedOutput = "bjkmqz";
        assertEquals(expectedOutput, missingLetters.findMissing(input));
    }

    @Test
    public void testTheLions() {
        String input = "Lions, and tigers, and bears, oh my!";
        String expectedOutput = "cfjkpquvwxz";
        assertEquals(expectedOutput, missingLetters.findMissing(input));
    }

    @Test
    public void clearDuplicateLetters() {
        String input = "abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz abcdefghijklmnopqrstuvwxyz";
        String expectedOutput = "";
        assertEquals(expectedOutput, missingLetters.findMissing(input));
    }
}
