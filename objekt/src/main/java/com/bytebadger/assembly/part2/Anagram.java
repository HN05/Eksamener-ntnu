package com.bytebadger.assembly.part2;

import java.util.ArrayList;
import java.util.List;


public class Anagram {

    /**
     * This method checks if two strings are anagrams.
     * 
     * Two strings are anagrams if they contain the same characters
     * in the same frequency, but possibly in a different order.
     * The method ignores case and spaces.
     * 
     * Empty strings cannot be anagrams.
     * Strings of different length cannot be anagrams.
     * 
     * Example:
     * The words "listen" and "silent" are anagrams.
     * 
     * @param str1 the first input string
     * @param str2 the second input string
     * @return a boolean value indicating whether the two strings are anagrams
     */
    public boolean isAnagram(String str1, String str2) {

        if (str1 == null || str2 == null) return false;
        if (str1.equals("") || str2.equals("")) return false;
        if (str1.length() != str2.length()) return false;

        List<Character> left = new ArrayList<>();
        for (char chr : str2.toCharArray()) {
            left.add(Character.toLowerCase(chr));
        }
        for (Character chr : str1.toCharArray()) {
            if (chr == ' ') continue;
            chr = Character.toLowerCase(chr);
            if (!left.contains(chr)) return false;
            left.remove(chr);
        }
        return true;
    }
}

