package com.tuto.commonlibrary.util;

import lombok.experimental.UtilityClass;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class TestUtil {
    public static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static String readJsonFrom(String dirName, String fileName) throws URISyntaxException, IOException {
        return Files.readString(Paths.get(ClassLoader.getSystemResource(dirName + "/" + fileName).toURI()));
    }
}
