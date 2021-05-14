package com.mobiquity.packer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.mobiquity.exception.APIException;
import com.mobiquity.utils.InputPackerParser;

import lombok.Cleanup;

public class Packer {

	private Packer() {
	}

	public static String pack(String filePath) throws APIException {
		var stringBuilder = new StringBuilder();
		try {
			var inputStream = new FileInputStream(filePath);
			var streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			@Cleanup
			var bufferedReader = new BufferedReader(streamReader);
			while (bufferedReader.ready()) {
				var lineResult = InputPackerParser.processLine(bufferedReader.readLine());
				stringBuilder.append(lineResult + "\n");
			}
		} catch (IOException e) {
			throw new APIException("Input is not found!");
		}
		return stringBuilder.toString();
	}
}
