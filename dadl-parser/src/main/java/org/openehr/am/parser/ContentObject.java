/*
 * Copyright (C) 2008 Cambio Healthcare Systems, Sweden.
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

/**
 * The result of parsing DADL input, the value is either
 * a list of attribute values or a complex object block
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ContentObject extends Parsed {
	
	public ContentObject(List<AttributeValue> attributeValues,
			ComplexObjectBlock complexObjectBlock) {
		this.attributeValues = attributeValues;
		this.complexObjectBlock = complexObjectBlock;
	}
	
	public ComplexObjectBlock getComplexObjectBlock() {
		return this.complexObjectBlock;
	}
	
	public List<AttributeValue> getAttributeValues() {
		return this.attributeValues;
	}

    /* fields */
    private List<AttributeValue> attributeValues;
    private ComplexObjectBlock complexObjectBlock;
}
