/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ThaiTran
 */
public class Validator {

    static Pattern patternPhone = Pattern.compile("^(0|84)(2(0[3-9]|1[0-6|8|9]|2[0-2|5-9]|3[2-9]|4[0-9]|5[1|2|4-9]|6[0-3|9]|7[0-7]|8[0-9]|9[0-4|6|7|9])|3[2-9]|5[5|6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$");
    static Pattern patternID = Pattern.compile("^([0-9]{9})|([0-9]{12})$");

    public static boolean phoneValidate(String phone) {
        Matcher m = patternPhone.matcher(phone);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    public static boolean IDValidate(String id) {
        Matcher m = patternID.matcher(id);
        if (m.matches()) {
            return true;
        }
        return false;
    }
}
