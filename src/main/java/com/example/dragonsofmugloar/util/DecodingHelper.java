package com.example.dragonsofmugloar.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

@Slf4j
public class DecodingHelper {

    public static String decodeText(String input, Integer encryptType) {

        if (encryptType == 1) {
            return decodeBase64(input);
        }
        if (encryptType == 2) {
            return decodeRot13(input);
        }
        return input;
    }

    private static String decodeBase64(String input) {
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        return new String(decodedBytes);
    }

    private static String decodeRot13(String input) {
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                charArray[i] = (char) (((c - base + 13) % 26) + base);
            }
        }
        return new String(charArray);
    }
}
