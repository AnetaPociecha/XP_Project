package com.agh.technology.xp.project.webscraper.validate;

import com.agh.technology.xp.project.webscraper.exception.UserInputException;

public class UserInputValidator {
    public void validate(int userChoice, int max, String errorMessage) throws UserInputException {
        if(userChoice > max || userChoice < 1){
            throw new UserInputException(errorMessage);
        }
    }
}
