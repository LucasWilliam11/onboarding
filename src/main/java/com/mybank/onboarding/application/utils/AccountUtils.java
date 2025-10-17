package com.mybank.onboarding.application.utils;

import java.security.SecureRandom;
import java.util.Random;

public class AccountUtils {
    private static final Random RANDOM = new SecureRandom();

    public static String generateAccountNumber(int length) {
        if (length <= 0) throw new IllegalArgumentException("length deve ser > 0");
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}
