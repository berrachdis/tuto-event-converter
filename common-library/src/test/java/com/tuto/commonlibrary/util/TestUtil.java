package com.tuto.commonlibrary.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class TestUtil {
    public static String readJsonFrom(String dirName, String fileName) throws URISyntaxException, IOException {
        return Files.readString(Paths.get(ClassLoader.getSystemResource(dirName + "/" + fileName).toURI()));
    }
}
