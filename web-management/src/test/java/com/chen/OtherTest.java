package com.chen;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class OtherTest {
    public static void main(String[] args) {
        byte[] keyBytes = "chen".getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(keyBytes));
    }
}
