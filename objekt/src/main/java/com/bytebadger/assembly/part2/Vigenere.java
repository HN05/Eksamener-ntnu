package com.bytebadger.assembly.part2;

/**
 * This class provides methods for encrypting and decrypting text messages using the Vigenere cipher.
 * 
 * The Vigenere cipher is based on the Caesar cipher, which shifts each letter of a
 * string by a specified number of positions in the English alphabet (26 letters).
 * 
 * Instead of just one fixed shift, it has a sequence of shift values. 
 */

public class Vigenere {
    private final int[] shifts;

    /**
     * Constructor for the Vigenere class.
     *
     * @param shifts the number of positions to shift each letter in the alphabet. Negative values mean shift in th e opposite direction.
     *             For example, a shift of -1 means to shift each letter backward by 1 position.
     * @throws IllegalArgumentException if the key is null or empty
     */

     public Vigenere(int[] shifts) {
        if (shifts == null || shifts.length == 0) throw new IllegalArgumentException();
        this.shifts = shifts;
    }

    
    /**
     *  The method encrypts a given text, using the Vigenere cipher, using the object's shifts.
     * 
     * The shifts are a sequence of integers that represent the shift for each letter in the text.
     * The first letter is encrypted with the first shift,
     * the second letter with the second shift, and so on. When all shifts are used,
     * the sequence starts again from the first shift.
     * 
     * This shifts each letter of a string by a specified number of positions 
     * in the english alphabet (26 letters) while keeping the case intact. 
     * Non-alphabetic characters remain unchanged. 
     * 
     * Example:
     * The text "aBcx. yZ!" encrypted with the key sequence [2,5,-1,0], returns "cfbxae":
     * - 'a' is shifted by 2 to give 'c'
     * - 'B' is shifted by 5 to give 'F', keeping upper case
     * - 'c' is shifted by -1 to give 'b', negative indicate opposite direction
     * - 'x' is not shifted since the shift is 0
     * - dot '.' and space ' ' remain unchanged
     * Now we have used all shifts, so we start again with the first shift:
     * - 'y' is shifted by 2 to give 'a', where we wrap the alphabet: y -> z -> a
     * - 'Z' is shifted by 5 to 'E', again wrapping and keeping upper case
     * - '!' remains unchanged
     * 
     * Note that we only consume shifts for letters, not for non-alphabetic characters.
     * 
     * return null if the input text is null.
     * 
     * @param text the input text to be encrypted
     * @return the encrypted text
     */

    public String encrypt(String text) {
        return shift(text, shifts);
    }

    /**
     * This method decrypts a given text using Caesar Cipher,
     * i.e. undoes the encryption, giveing the original text.
     * 
     * Returns null if the input text is null.
     * 
     * @param text the input text to be decrypted
     * @return the decrypted text
     */

    public String decrypt(String text) {
        int[] invertedShifts = new int[shifts.length];
        for (int i = 0; i < shifts.length; i++) {
            invertedShifts[i] = shifts[i] * -1;
        }
        return shift(text, invertedShifts);
    }

    private String shift(String text, int[] shifts) {
        if (text == null) return null;

        String newText = "";
        int index = 0;
        for (char letter : text.toCharArray()) {
            if (!Character.isLetter(letter)) {
                newText += letter;
                continue;
            }
            boolean isUpperCase = Character.isUpperCase(letter);
            int newLetter = Character.toLowerCase(letter);
            int start = 'a'; // get the start num
            int interval = 26; // amount of letters
            newLetter -= start;
            newLetter += shifts[index];
            newLetter %= interval;
            if (newLetter < 0) {
                newLetter += interval;
            }
            newLetter += start;
            if (isUpperCase) {
                newText += (char) Character.toUpperCase(newLetter);
            } else {
                newText += (char) newLetter;
            }
            index = (index + 1) % shifts.length;
        }
        return newText;
    }

    public static void main(String[] args) {
        int[] shifts = {25};
        var test = new Vigenere(shifts);
        System.out.println(test.decrypt("Sghr hr z sghmj xnt!"));
    }
}
