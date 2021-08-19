package com.example.blubox;
import java.util.regex.*;
import java.util.*;
public  class myutilities {

    public   boolean validateEmail(String email){
        if( email.length() <= 0 )
            return false ;
        String regex = "^(.+)@(.+)$" ;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();
    }

    public boolean validatePassword(String password){
        if( password.length() <= 0 )
            return false ;
        if(password.matches("(?=.*[~!@#$%^&*()_-]).*"))
            return false;
      return true;
    }

    public boolean validatePhone(String phone){
        String regex = "(0/91)?[7-9][0-9]{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return  matcher.matches();
    }



}
