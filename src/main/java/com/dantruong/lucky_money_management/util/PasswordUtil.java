package com.dantruong.lucky_money_management.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // ham dung hash password

    public  static  String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // ham dung de kiem tra mat khau

    public  static  boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
