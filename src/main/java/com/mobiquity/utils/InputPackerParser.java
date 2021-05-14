
package com.mobiquity.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mobiquity.domain.InputPackage;
import com.mobiquity.domain.Item;
import com.mobiquity.exception.APIException;
import com.mobiquity.exception.APIExceptionType;

public class InputPackerParser {

	private static final PackageProcessor PROCESSOR = new PackageProcessor();

	private InputPackerParser() {
	}

	public static String processLine(String line) throws APIException {

		var inputPackage = parseLine(line);
		var resultItems = PROCESSOR.process(inputPackage.getWeightLimit(), inputPackage.getItems());
		if (resultItems == null || resultItems.isEmpty()) {
			return "-";
		}
		return resultItems.stream().map(i -> Integer.toString(i.getIndex())).collect(Collectors.joining(","));
	}

	private static InputPackage parseLine(String line) throws APIException {
		String[] configurationParts = line.split(" : ");
		try {
			if (configurationParts.length != 2) {
				throw new APIException(APIExceptionType.INVALID_INPUT);
			}

			var weightLimit = Integer.parseInt(configurationParts[0]);
			if (weightLimit > PackerConstants.MAX_PACKAGE_WEIGHT) {
				throw new APIException(APIExceptionType.MAX_PACKAGE_WEIGHT);
			}
			String[] itemsParts = configurationParts[1].split("\\s+");

			if (itemsParts.length == 0 || itemsParts.length > PackerConstants.MAX_ITEMS_NUMBER) {
				throw new APIException(APIExceptionType.INVALID_ITEMS_NUMBER);
			}

			List<Item> itemsList = new ArrayList<>();
			for (String itemString : itemsParts) {
				var item = parseItemString(itemString);
				itemsList.add(item);
			}
			return new InputPackage(weightLimit, itemsList);

		} catch (NumberFormatException e) {
			throw new APIException(APIExceptionType.INVALID_INPUT);
		}

	}

	private static Item parseItemString(String itemString) throws APIException {
		String[] itemParts = itemString.replace("(", "").replace(")", "").split(",");
		if (itemParts.length != 3) {
			throw new APIException("Invalid Input!");
		}
		var index = Integer.parseInt(itemParts[0]);
		var weight = Float.parseFloat(itemParts[1]);
		if (weight > PackerConstants.MAX_ITEM_WEIGHT) {
			throw new APIException(APIExceptionType.MAX_ITEM_WEIGHT);
		}
		var cost = Float.parseFloat(itemParts[2].replace("€", ""));
		if (cost > PackerConstants.MAX_ITEM_COST) {
			throw new APIException(APIExceptionType.MAX_ITEM_COST);
		}
		return new Item(index, weight, cost);
	}

}