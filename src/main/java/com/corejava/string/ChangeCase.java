package com.corejava.string;

import org.apache.commons.lang.WordUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by sunitc on 5/11/17.
 */
public class ChangeCase {

    private static String[] countries = {"united-states","american-samoa",
            "bonaire-st-eustatius-saba","british-virgin-islands","czech-republic","denmark",
            "heard-island-mcdonald-islands","papua-new-guinea"};

    public static void main(String[] args) {

        System.out.println(
                Arrays
                        .stream(countries)
                        .map(c ->
                                WordUtils.capitalize(c.replaceAll("-", " "))
                        )
                        .collect(Collectors.toList()));

    }
}
