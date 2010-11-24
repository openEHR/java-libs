/*
 * Copyright (C) 2007,2008 Cambio Healthcare Systems AB, Sweden
 * All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/gpl.txt
 *
 */

package org.openehr.am.parser;

import java.util.List;

import org.openehr.rm.support.basic.Interval;

public class PrimitiveObjectBlock extends ObjectBlock {
	
	public PrimitiveObjectBlock(String type, SimpleValue simpleValue,
			List<SimpleValue> simpleListValue, 
			Interval<Comparable> simpleIntervalValue, String termCode,
			List<String> termCodeListValue) {
		super(type);
		this.simpleValue = simpleValue;
		this.simpleIntervalValue = simpleIntervalValue;
		this.simpleListValue = simpleListValue;
		this.termCode = termCode;
		this.termCodeListValue = termCodeListValue;
	}
	
	public SimpleValue getSimpleValue() {
		return this.simpleValue;
	}	
	
	public List<SimpleValue> getSimpleListValue() {
		return simpleListValue;
	}

	public Interval<Comparable> getSimpleIntervalValue() {
		return simpleIntervalValue;
	}

	public String getTermCode() {
		return termCode;
	}

	public List<String> getTermCodeListValue() {
		return termCodeListValue;
	}
	
	private SimpleValue simpleValue;
	private List<SimpleValue> simpleListValue;
	private Interval<Comparable> simpleIntervalValue;
	private String termCode;
	private List<String> termCodeListValue;	
}
