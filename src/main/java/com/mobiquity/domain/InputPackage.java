package com.mobiquity.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputPackage {
	private int weightLimit;
	private List<Item> items;

}
