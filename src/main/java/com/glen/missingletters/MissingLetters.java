package com.glen.missingletters;

/**
 * Find missing letters.
 * <p>
 * Basic rules:
 * - ignore spaces, non-alphabetical, non-US-ASCII
 * - ignore case (that is, case-insensitive)
 * - return in lower-case, alphabetical order
 * - input strings from 0 to 50 characters;  but also, very large input strings
 */
@SuppressWarnings("ALL")
public class
MissingLetters {

    /*
        Ascii notes
        LOWER_CASE_BYTE_RUN
            a : 97
            z : 122

        UPPER_CASE_BYTE_RUN
            A : 65
            Z : 90
    */

    private static final int TOTAL_POSSIBLE_LETTERS = 26;
    private static final int LOWER_BOUND_OF_LOWERCASE_BYTE_RUN = 97;
    private static final int UPPER_BOUND_OF_LOWERCASE_BYTE_RUN = 122;
    private static final int LOWER_BOUND_OF_UPPERCASE_BYTE_RUN = 65;
    private static final int UPPER_BOUND_OF_UPPERCAS_BYTE_RUN = 90;
    private static final int IS_LETTER_FOUND_INDICATOR = 1;
    private static final int NULL_INT_INDICATOR = -1;
    private static final String ALL_LETTERS = "abcdefghijklmnopqrstuvwxyz";



    /**
     * Required single argument: String to check
     *
     * @param args
     */
    public static void main(String args[]) {
        if (args == null && args.length < 1) {
            throw new IllegalArgumentException("args must include a string");
        }
        MissingLetters ml = new MissingLetters();
        String result = ml.findMissing(args[0]);

    }

    public MissingLetters() {
    }


    /**
     * Given an input String, return another String (in lowercase) of all english letters not found in the input String.
     *
     * Will check case-insensitively.
     *
     * @param inputString if null, will treat as empty string
     * @return the letters of the English alphabet that do not appear in the given string.
     */
    public String findMissing(final String inputString) {

        final String result;
        if (inputString == null || inputString.length() == 0) {
            result = ALL_LETTERS;
        } else {

            // the 26 spaces in this foundByteArray correspond to letters a to z
            final byte[] foundByteArray = new byte[TOTAL_POSSIBLE_LETTERS];


            /*
            Note regarding the chunking loop below:

            The loop below that chunks the search into 1,000 line sets does not, as currently implemented,
            improve performance over a

            However, should we wish to improve the current performance for Extremly Large Input Strings (say,
            tens of Megabytes), this chunking would be a first step down that road.  A straightforward approach
            would be to have parallel threads take N characters each (e.g., N = 1,000), process their various chunks,
            and then return the results.

            Optimal size of chunk and optimal number of threads would need to be optimzed through
            testing on a production-comparable system.

            */

            final int chunkSize = 1000;
            final int entireStringLength = inputString.length();
            int foundCount = 0;
            for (int i = 0; (i < entireStringLength) && foundCount < TOTAL_POSSIBLE_LETTERS; i = i + chunkSize) {
                foundCount = fillInFoundByteArray(inputString, foundByteArray, i, chunkSize, foundCount);
                renderMessage("after filling in. start=" + i + "; foundCount=" + foundCount);
            }

            result = getResultString(foundByteArray, foundCount);
        }
        renderMessage("findMissing: input=" + inputString + "; result=" + result);
        return result;
    }


    /**
     * Work on a chunk of the input String
     * @param entireString
     * @param foundByteArray
     * @param startIndex
     * @param chunkSize
     * @param foundCount
     * @return the new foundCount, after processing the specified chunk
     */
    private int fillInFoundByteArray(final String entireString, final byte[] foundByteArray, final int startIndex,
                                     final int chunkSize, int foundCount) {
        final int entireStringLength = entireString.length();

        // Loop through the designated chunk of the entireString
        for (int i = startIndex; (i < startIndex + chunkSize) && i < entireStringLength; i++) {
            final byte foundByte = (byte) entireString.charAt(i);
            int foundByteSlotToCheck = NULL_INT_INDICATOR;
            final boolean isLowerCase = foundByte >= LOWER_BOUND_OF_LOWERCASE_BYTE_RUN && foundByte <= UPPER_BOUND_OF_LOWERCASE_BYTE_RUN;
            if (isLowerCase) {
                foundByteSlotToCheck = foundByte - LOWER_BOUND_OF_LOWERCASE_BYTE_RUN;
            } else {
                final boolean isUpperCase = foundByte >= LOWER_BOUND_OF_UPPERCASE_BYTE_RUN && foundByte <= UPPER_BOUND_OF_UPPERCAS_BYTE_RUN;
                if (isUpperCase) {
                    foundByteSlotToCheck = foundByte - LOWER_BOUND_OF_UPPERCASE_BYTE_RUN;
                }
            }

            if (foundByteSlotToCheck != NULL_INT_INDICATOR) {

                // At this point, we know it's a letter
                // If we don't already have it marked as found, then it's a new find, so:
                //     1. Increase the foundCount counter
                //     2. Fill the slot in the byte array indicating "found!"
                final boolean isAlreadyFound = foundByteArray[foundByteSlotToCheck] == IS_LETTER_FOUND_INDICATOR;
                if (!isAlreadyFound) {
                    foundCount++;
                    foundByteArray[foundByteSlotToCheck] = IS_LETTER_FOUND_INDICATOR;
                }

                // Once we've found all the letters, quit the loop
                if (foundCount == TOTAL_POSSIBLE_LETTERS) {
                    break;
                }
            }
        }
        return foundCount;
    }

    /**
     * Get the final result
     * @param foundByteArray
     * @param foundCount
     * @return a String corresponding to the items in the given foundByteArray that are not found
     */
    private String getResultString(final byte[] foundByteArray, final int foundCount) {
        final String result;
        if (foundCount == TOTAL_POSSIBLE_LETTERS) {
            result = "";
        } else if (foundCount == 0) {
            result = ALL_LETTERS;
        } else {
            final byte finalNotFoundByteArray[] = new byte[TOTAL_POSSIBLE_LETTERS - foundCount];
            int notFoundCount = 0;
            for (int i = 0; i < TOTAL_POSSIBLE_LETTERS; i++) {
                if (foundByteArray[i] != IS_LETTER_FOUND_INDICATOR) {
                    final int intValue = i + LOWER_BOUND_OF_LOWERCASE_BYTE_RUN;
                    finalNotFoundByteArray[notFoundCount++] = (byte) (i + LOWER_BOUND_OF_LOWERCASE_BYTE_RUN);
                }
            }
            result = new String(finalNotFoundByteArray);
        }
        return result;
    }


    private static void renderMessage(final String msg) {
        // swap out for log4j, or some such logging mechanism....
        System.out.println(msg);
    }


}
