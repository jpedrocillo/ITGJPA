package com.itg.exam.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public Boolean checkEmailFormat(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
