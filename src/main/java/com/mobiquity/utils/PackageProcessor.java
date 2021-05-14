package com.mobiquity.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mobiquity.domain.Item;

public class PackageProcessor {

	public List<Item> process(int weightLimit, List<Item> items) {
		// Applying the knapsack algorithm ..
		// filter items that exceeds the max weight
		items = items.stream().filter(item -> item.getWeight() <= weightLimit).collect(Collectors.toList());
		// sort the items list..
		items.sort(Comparator.comparing(Item::getWeight));

		int itemsSize = items.size();
		Double[] weights = items.stream().map(Item::getWeight).toArray(Double[]::new);
		Double[] costs = items.stream().map(Item::getCost).toArray(Double[]::new);

		// generate base cases
		double[][] board = new double[itemsSize + 1][weightLimit + 1];

		// Main logic
		for (var item = 1; item <= itemsSize; item++) {
			for (var weight = 1; weight <= weightLimit; weight++) {
				double maxCostWithoutCurrent = board[item - 1][weight];
				double currentMaxCost = 0;
				double currentWeight = weights[item - 1]; // We use item-1 to account for the extra row at the top
				if (weight >= currentWeight) { // We check if the package can fit the current item
					currentMaxCost = costs[item - 1]; // If so, currentMaxCost is at least the value of the current
														// item
					int remainingWeight = (int) Math.ceil(weight - currentWeight); // remainingWeight must be at
					currentMaxCost += board[item - 1][remainingWeight]; // Add the maximum cost obtainable with the
				}
				board[item][weight] = Math.max(maxCostWithoutCurrent, currentMaxCost); // Pick the largest
			}
		}
		double maxCost = board[itemsSize][weightLimit];
		return getItems(board, maxCost, weightLimit, items);

	}

	private List<Item> getItems(double[][] board, double maxCost, double weightLimit, List<Item> items) {
		// backtracking the board to get the items
		double remainingCost = maxCost;
		double remainingWeight = weightLimit;
		List<Item> finalItems = new ArrayList<>();
		for (var i = items.size(); i > 0 && maxCost > 0; i--) {
			if (remainingCost != board[i - 1][(int) Math.ceil(remainingWeight)]) {
				var item = items.get(i - 1);
				finalItems.add(item);
				remainingCost -= item.getCost();
				remainingWeight = remainingWeight - item.getWeight();
			}
			if (remainingWeight < 0) {
				break;
			}
		}
		// return them sorted by index..
		finalItems.sort(Comparator.comparing(Item::getIndex));
		return finalItems;
	}

}