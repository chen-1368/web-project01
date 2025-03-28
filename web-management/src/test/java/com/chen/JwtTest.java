package com.chen;

import com.chen.utils.JwtUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class JwtTest {

    @Test
    public void JwtParseTest(){
        String token = JwtUtils.generateToken(new HashMap<String, Object>() {{
            put("id", 1);
            put("username", "admin");
        }});
        System.out.println(token);
        System.out.println(JwtUtils.parseToken(token));
    }
}
