package com.mobiquity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import lombok.Cleanup;

public class TestUtils {

	public String getResourcePath(String fileName) {
		var resourcePath = Paths.get("src", "main", "test", "resources", fileName);
		return resourcePath.toFile().getAbsolutePath();
	}

	public String getData(String absolutOutputePath) throws IOException {
		var stringBuilder = new StringBuilder();
		var is = new FileInputStream(absolutOutputePath);
		var isr = new InputStreamReader(is, StandardCharsets.UTF_8);
		@Cleanup
		var buffReader = new BufferedReader(isr);
		while (buffReader.ready()) {
			stringBuilder.append(buffReader.readLine() + "\n");
		}

		return stringBuilder.toString();
	}
}
