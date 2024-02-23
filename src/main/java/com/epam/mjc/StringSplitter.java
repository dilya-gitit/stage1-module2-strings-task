package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public static List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> substrings = new ArrayList<>();
        int startIndex = 0;
        int endIndex;
        while (startIndex < source.length()) {
            endIndex = findNextDelimiterIndex(source, startIndex, delimiters);
            if (endIndex == -1) {
                substrings.add(source.substring(startIndex).trim());
                break;
            } else {
                if (endIndex != startIndex) {
                    substrings.add(source.substring(startIndex, endIndex).trim());
                }
                startIndex = endIndex + 1;
            }
        }
        return substrings;
    }

    private static int findNextDelimiterIndex(String source, int startIndex, Collection<String> delimiters) {
        int minIndex = -1;
        for (String delimiter : delimiters) {
            int index = source.indexOf(delimiter, startIndex);
            if (index != -1 && (minIndex == -1 || index < minIndex)) {
                minIndex = index;
            }
        }
        return minIndex;
    }
}
