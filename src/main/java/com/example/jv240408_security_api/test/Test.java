package com.example.jv240408_security_api.test;

import org.mindrot.jbcrypt.BCrypt;

public class Test {
    public static void main(String[] args) {
        System.out.println(BCrypt.checkpw("123456","$2a$10$kQAfmjYqfxX2PJ7MnA4Ii.8WRWCG.Y2ZRuDF2ncpAK183SJjUf1ay"));
    }
}
