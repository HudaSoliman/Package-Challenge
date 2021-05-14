package com.mobiquity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mobiquity.exception.APIException;
import com.mobiquity.exception.APIExceptionType;
import com.mobiquity.packer.Packer;

public class PackerTest {

	TestUtils testUtils;

	@BeforeEach
	private void setUp() {
		testUtils = new TestUtils();
	}

	@Test
	public void packerTestInput() throws APIException, IOException {

		var testFileInputPath = testUtils.getResourcePath("example_input");
		var result = Packer.pack(testFileInputPath);

		var absolutOutputePath = testUtils.getResourcePath("example_output");
		String outputData = testUtils.getData(absolutOutputePath);
		assertEquals(outputData, result, "smoke test didn't pass");
	}

	@Test
	public void packagewithMaxItemCostTest() throws APIException {
		var testFileInputPath = testUtils.getResourcePath("example_input_max_item_cost");
		try {
			Packer.pack(testFileInputPath);
		} catch (APIException e) {
			assertEquals(e.getMessage(), APIExceptionType.MAX_ITEM_COST.getMessage(),
					"Item with invalid cost passed the restriction");
		}

	}

	@Test
	public void packagewithMaxItemWeightTest() throws APIException {
		var testFileInputPath = testUtils.getResourcePath("example_input_max_item_weight");
		try {
			Packer.pack(testFileInputPath);
		} catch (APIException e) {
			assertEquals(e.getMessage(), APIExceptionType.MAX_ITEM_WEIGHT.getMessage(),
					"Item with invalid weight passed the restriction");
		}

	}
	
	@Test
	public void packagewithMaxWeightTest() throws APIException {
		var testFileInputPath = testUtils.getResourcePath("example_input_max_weight");
		try {
			Packer.pack(testFileInputPath);
		} catch (APIException e) {
			assertEquals(e.getMessage(), APIExceptionType.MAX_PACKAGE_WEIGHT.getMessage(),
					"package with invalid weight passed the restriction");
		}

	}
	
	@Test
	public void packagewithMaxInputNumberTest() throws APIException {
		var testFileInputPath = testUtils.getResourcePath("example_item_max_input_number");
		try {
			Packer.pack(testFileInputPath);
		} catch (APIException e) {
			assertEquals(e.getMessage(), APIExceptionType.INVALID_ITEMS_NUMBER.getMessage(),
					"package with invalid input number");
		}

	}

}
