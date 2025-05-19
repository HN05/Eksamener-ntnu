package com.bytebadger.assembly.part2;

import java.util.HashMap;
import java.util.Map;


public class CharacterFrequency {

    /**
     * This method counts the frequency of each character in a given string.
     * 
     * @return a Map with characters as keys and their frequencies as values
     */

    public static Map<Character, Integer> countCharacterFrequency(String input) {

        Map<Character, Integer> map = new HashMap<>();
        if (input == null) return map;
        for (Character chr : input.toCharArray()) {
            map.compute(chr, (c, n) -> n==null ? 1 : n+1);
        }

        return map;

    }
}
