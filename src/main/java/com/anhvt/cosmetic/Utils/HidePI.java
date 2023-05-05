package com.anhvt.cosmetic.Utils;

public class HidePI {
    public static String replacePhoneNumber(String phoneNumber) {
        return phoneNumber.replaceAll("\\d{4}$", "****");
    }

    public static String replaceEmail(String email){
        return email.replaceAll("(?<=.{3}).(?=[^@]*?.@)", "*");
    }

}
