package com.example.zadanie_jstl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dogovor {

    public static boolean validate(String number) {
        String regex = "\\d{4,6}/21-[A-Z]{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
