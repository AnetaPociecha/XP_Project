package com.agh.technology.xp.project.webscraper.validate;

import org.apache.commons.validator.routines.UrlValidator;

public class UrlValidatorFacade {
    private static UrlValidator validator;

    static {
        String[] schemes = {"http", "https"};
        validator = new UrlValidator(schemes);
    }

    public static boolean isValid(String url) {
        return validator.isValid(url);
    }

}
