package com.example.SpringMVC.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation,Integer> {
    /**
     * @param integer
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return isPrime(integer);
    }

    public boolean isPrime(Integer number){
        int c=0;
        for(int i=1;i<=number;i++){
            if(number%i==0) c++;
        }
        return c == 2;

    }

}
