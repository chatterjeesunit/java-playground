package com.algorithms.recursions;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given a string of length n, find all permutations of the string
 * e.g a -> a
 * ab -> ab , ba
 * abc -> abc, acb, bac, bca, cab, cba
 */
public class StringPermutations {


    public Set<String> permute(String s) {
        if(s.length() == 1) {
            return Set.of(s);
        }

        Set<String> allPermutations = new HashSet<>();

        for (int i = 0; i < s.length() ; i++) {
            String firstCharForPermutation = s.substring(i,i+1);
            Set<String> childPermutations = permute(s.substring(0,i) + s.substring(i+1));
            Set<String> permutations = childPermutations
                    .stream()
                    .map(str -> firstCharForPermutation + str)
                    .collect(Collectors.toSet());
            allPermutations.addAll(permutations);
        }
        return allPermutations;
    }
}
