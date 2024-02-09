package com.hastega.review.userlibrary;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Utils {

    public static String getHash(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static BCrypt.Result verify(String password, String hash){
        return BCrypt.verifyer().verify(password.toCharArray(), hash);
    }
}
