package com.mobiquity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

	private int index;
	private double weight;
	private double cost;

}
