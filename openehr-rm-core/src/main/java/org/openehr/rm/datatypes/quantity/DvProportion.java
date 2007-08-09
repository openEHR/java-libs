/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvProportion"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2007 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.datatypes.quantity;

import java.util.List;

import org.openehr.rm.Attribute;
import org.openehr.rm.datatypes.text.CodePhrase;

/**
 * Models a ratio of values, i.e. where the numerator and denominator 
 * are both pure numbers
 * 
 * @author Rong Chen
 */
public class DvProportion extends DvAmount<DvProportion> {

	/**
     * Constructs a DvAmount with referenceRanges and accuracy
     *
     * @param referenceRanges   null if not specified
     * @param normalRange null if not specified
     * @param normalStatus null if not specified
     * @param accuracy          0 means not recorded      
     * @param accuracyPercent
     * @param magnitudeStatus null if not specified
     * @param numerator		not null
     * @param denominator   not null
     * @param type			not null
     * @param precision		null if not specified
     */
    public DvProportion(
    		@Attribute (name = "referenceRanges") List<ReferenceRange<DvProportion>> referenceRanges,
    		@Attribute (name = "normalRange") DvInterval<DvProportion> normalRange,
    		@Attribute (name= "normalStatus") CodePhrase normalStatus,
    		@Attribute (name = "accuracy") double accuracy, 
    		@Attribute (name = "accuracyPercent") boolean accuracyPercent,
    		@Attribute (name= "magnitudeStatus") String magnitudeStatus,
    		@Attribute (name = "numerator") double numerator,
    		@Attribute (name = "denominator") double denominator,
    		@Attribute (name = "type") ProportionKind type,
    		@Attribute (name = "precision") Integer precision) {
    	
        super(referenceRanges, normalRange, normalStatus , accuracy,
        		accuracyPercent, magnitudeStatus);
        
        if(type == null) {
        	throw new IllegalArgumentException("null type");
        } else if(type == ProportionKind.UNITARY) {
        	if(denominator != 1) {
        		throw new IllegalArgumentException(
        				"denominator for unitary proportion must be 1");
        	}
        } else if(type == ProportionKind.PERCENT) {
        	if(denominator != 100) {
        		throw new IllegalArgumentException(
        				"denominator for unitary proportion must be 100");
        	}
        } else if(type == ProportionKind.FRACTION ||
        		type == ProportionKind.INTEGER_FRACTION) {
        	
        	if(! bothIntegral(numerator, denominator)) {
        		throw new IllegalArgumentException(
        			"both numberator and denominator must be integral for " +
        			"fraction or integer fraction proportion");        		
        	}
        }
        
        if(bothIntegral(numerator, denominator) && precision != 0) {
        	throw new IllegalArgumentException("precision must be 0 if both " +
        			"numerator and denominator are integral");
        }
        if( !bothIntegral(numerator, denominator) && precision == 0) {
        	throw new IllegalArgumentException("zero precision for " +
        			"non-integral numerator or denominator");
        }
        
        this.numerator = numerator;
        this.denominator = denominator;
        this.type = type;
        this.precision = precision;
    }
    
    /**
     * Creates a simple DvProportion
     */
    public DvProportion(double numerator, double denominator, 
    		ProportionKind type, Integer precision) {
    	this(null, null, null, 0.0, false, null, numerator, denominator, type, 
    			precision);
    }
    
    /**
	 * @return Returns the denominator.
	 */
	public double getDenominator() {
		return denominator;
	}

	/**
	 * @return Returns the numerator.
	 */
	public double getNumerator() {
		return numerator;
	}

	/**
	 * @return Returns the precision, null if unspecified
	 */
	public Integer getPrecision() {
		return precision;
	}

	/**
	 * @return Returns the type.
	 */
	public ProportionKind getType() {
		return type;
	}

	/**
	 * True if the numerator and denominator values are integers
	 * 
	 * @return true if integral
	 */
	public boolean isIntegral() {
		return bothIntegral(numerator, denominator);
	}	
	
	private boolean bothIntegral(double num1, double num2) {
		return (Math.floor(num1) == num1) && (Math.floor(num2) == num2);
	}
	
	@Override
	public DvQuantified<DvProportion> add(DvQuantified<DvProportion> q) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DvQuantified<DvProportion> subtract(DvQuantified<DvProportion> q) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Class getDiffType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Number getMagnitude() {
		return new Double(numerator / denominator);
	}
	@Override
	public boolean isStrictlyComparableTo(DvOrdered ordered) {
		// TODO Auto-generated method stub
		return false;
	}
	public int compareTo(DvOrdered arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* fields */
	private final double numerator;
	private final double denominator;
	private final ProportionKind type;
	private final Integer precision;
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is DvProportion.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */