package com.example.SpringMVC.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password,String> {
    /**
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isPassword(s);
    }

    public boolean isPassword(String pwd){

        char ch;
        boolean uppercaseFlag=false;
        boolean loweCaseFlag=false;
        boolean specialCharacterFlag=false;
        boolean isCorrectLength=false;
        String specialCharacters="$#@!&^%*()";
        String[] spc =specialCharacters.split("");

        for(int i=0;i<pwd.length();i++){
            ch=pwd.charAt(i);
            if(Character.isUpperCase(ch)) {
                uppercaseFlag=true;
            }
            else if (Character.isLowerCase(ch)) {
                loweCaseFlag=true;
            }

        }
        for(int i=0;i<pwd.length();i++){
            if(pwd.contains(spc[i])){
                specialCharacterFlag=true;
                break;
            }
        }
        if(pwd.length()>10) isCorrectLength=true;

        if(uppercaseFlag && loweCaseFlag && specialCharacterFlag && isCorrectLength) return true;
        return false;

    }
}
