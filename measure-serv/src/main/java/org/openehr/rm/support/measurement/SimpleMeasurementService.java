/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SimpleMeasurementService"
 * keywords:    "support"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2007 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.support.measurement;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.converter.ConversionException;
import javax.measure.unit.Unit;

/**
 * Simple implementation of measurement information service
 *
 * @author Rong Chen
 * @version 1.0
 *
 */
public class  SimpleMeasurementService implements MeasurementService {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static MeasurementService getInstance() {
	return soleInstance;
    }

    private static final SimpleMeasurementService soleInstance = 
	    new SimpleMeasurementService();

    private SimpleMeasurementService() {		
    }

    
    /** This is a list of commonly used UCUM codes in health care.
     *  Cp. e.g. http://www.hl7.de/download/documents/ucum/ucumdata.html
     *  and http://unitsofmeasure.org/ucum.html and http://unitsofmeasure.org/ucum-essence.xml
     */
    private static Set<String> commonUCUMCodes =  new HashSet<String>( Arrays.asList(
            "%", "/uL", "[iU]/L", "10*3/uL", "10*6/uL", "fL", "g/dL", "g/L", "g/mL", "kPa", "m[iU]/mL", "meq/L", "mg/dL", "mm[Hg]", "mmol/kg", "mmol/L", "mosm/kg", "ng/mL", 
            "nmol/L", "pg", "pg/mL", "pmol/L", "U/L", "u[iU]/mL", "ug/dL", "ug/L", "ug/mL", "[lg]", "10*6/{Specimen}", "/{tot}", "10*3", "10*3.{RBC}", "10*5", "10*6", "10*8", "%", 
            "{Relative}%", "%{Total}", "%{0to3Hours}", "/10*10", "/10*6", "/10*9", "/10*12", "%{Normal}", "%{SpermMotility}", "%{Positive}", "%{FetalErythrocytes}", 
            "%{OfLymphocytes}", "%{ofBacteria}", "%{OfWBCs}", "%{Abnormal}", "%{EosSeen}", "%{Hemolysis}", "%{Blockade}", "%/100{WBC}", "%{Binding}",
            "%{TotalProtein}", "%{Bound}", "%{Hemoglobin}", "%{HemoglobinSaturation}", "%{Carboxyhemoglobin}", "%{HemoglobinA1C}", "%{Excretion}", "%{Uptake}", "ug/ng",
            "ng/mg", "ng/mg{Protein}", "ug/mg", "ug/mg{Cre}", "mg/mg", "mg/mg{Cre}", "ng/g", "ng/g{Cre}", "ug/g", "ug/[100]g", "ug/g{DryWeight}", "ug/g{Cre}", "ug/g{Hgb}", 
            "mg/g", "mg/g{Cre}", "g/g", "ng/kg", "ug/kg", "mg/kg", "g/kg", "g/[100]g", "g/g{Cre}", "pmol/umol", "nmol/mmol", "nmol/mmol{Cre}", "nmol/mol", "umol/mol", "mmol/mol",
            "mmol/mol{Cre}", "umol/mol{Cre}", "eq/umol", "eq/mmol", "{BoneCollagen}eq/mmol{Cre}", "{BoneCollagen}eq/umol{Cre}", "%{vol}", "%{Oxygen}", "mL/dL", 
            "%{NormalPooledPlasma}", "%{Activity}", "%{BasalActivity}", "%{Inhibition}", "/{Entity}", "/100{WBC}", "/100", "/100{Spermatozoa}", "/10*12{rbc}", "deg", "/[arb`U]", 
            "u[iU]", "[iU]", "10*6.[iU]", "[in_i]", "[ft_i]", "[yd_i]", "[fth_i]", "[mi_i]", "[nmi_i]", "[Ch]", "fm", "pm", "nm", "um", "mm", "cm", "dm", "m", "km", "[gr]", "[oz_av]", "[oz_tr]",
            "[lb_av]", "[ston_av]", "[dr_av]", "fg", "pg", "ng", "ug", "ug/{TotalVolume}", "ug/{Specimen}", "mg", "mg/{Volume}", "mg/{TotalVolume}", "g", "g/{TotalWeight}", "dg", "cg",
            "kg", "t", "pg/mm", "K", "Cel", "[degF]", "K/W", "ps", "ns", "us", "ms", "s", "ks", "Ms", "min", "h", "d", "wk", "mo", "a", "eq", "ueq", "meq", "meq/{Specimen}", "mol",
            "mmol", "mmol/{TotalVolume}", "fmol", "pmol", "umol", "nmol", "mosm", "meq/m2", "mmol/m2", "[sin_i]", "[sft_i]", "[syd_i]", "mm2", "cm2", "m2", "[foz_us]", "[cin_i]",
            "[cup_us]", "[pt_us]", "[qt_us]", "[gal_us]", "[fdr_us]", "fL", "pL", "nL", "uL", "mL", "mL/{h`b}", "L", "dL", "cL", "kL", "hL", "L.s2/s", "/mg", "/g", "/g{creat}", "/g{HGB}", 
            "/g{tot`nit}", "/g{tot`prot}", "/g{wet`tis}", "/kg", "/kg{body`wt}", "fmol/mg", "nmol/mg", "umol/mg", "umol/mg{Cre}", "mol/kg", "fmol/g", "nmol/g", "nmol/g{Cre}", "umol/g",
            "umol/g{Cre}", "umol/g{Hgb}", "mmol/g", "mmol/kg", "osm/kg", "mosm/kg", "meq/g", "meq/g{Cre}", "meq/kg", "[iU]/g", "[iU]/g{Hgb}", "{Ehrlich_U}/100g", "[iU]/kg", 
            "umol/min/g", "mU/g", "mU/g{Hgb}", "U/g", "U/g{Hgb}", "U/g{Cre}", "mU/mg{Cre}", "mU/mg", "kU/g", "kat/kg", "mL/kg", "L/kg", "kCal/[oz_av]", "/m2", "g/m2", "kg/m2", 
            "ug/m2", "mg/m2", "ng/m2", "g.m", "g.m/{hb}", "g.m/({hb}.m2)", "kg/mol", "/uL", "{Cells}/uL", "{rbc}/uL", "10*3/uL", "10*6/uL", "10*9/uL", "/mL", "{Spermatozoa}/mL",
            "{Copies}/mL", "10*3/mL", "10*3{Copies}/mL", "10*6/mL", "10*9/mL", "{cfu}/mL", "/dL", "/L", "10*3/L", "10*6/L", "10*12/L", "10*9/L", "pg/mL", "ng/mL", "ng/mL{rbc}", 
            "ug/mL", "mg/mL", "g/mL", "pg/dL", "ng/dL", "ug/dL", "ug/dL{rbc}", "mg/dL", "mg{Phenylketones}/dL", "g/dL", "ng/L", "pg/L", "ug/L", "mg/L", "g/L", "kg/L", "mg/m3",
            "kg/m3", "fmol/mL", "pmol/mL", "nmol/mL", "umol/mL", "mol/mL", "pmol/dL", "nmol/dL", "umol/dL", "mmol/dL", "mmol/L", "pmol/L", "nmol/L", "umol/L", "mol/L", 
            "mol/m3", "ueq/mL", "meq/mL", "eq/mL", "{AHG}eq/mL", "10*6.eq/mL", "ueq/L", "meq/L", "eq/L", "meq/dL", "mosm/L", "osm/L", "u[iU]/mL", "m[iU]/mL",
            "{IgGPhospholipid}U/mL", "{IgMPhospholipid}U/mL", "{ComplementCh50}U/mL", "{IgAPhospholipid}U/mL", "{Elisa_U}/mL", "[iU]/mL", "k[iU]/mL", "[iU]/dL", 
            "{Ehrlich_U}/dL", "m[iU]/L", "[iU]/L", "[pH]",
            
            "osm", "/wk", "ml/wk", "mL/wk", "g/wk", "mg/wk", "B", "dB", "cm[H2O]", "mm[H20]",  "10*6/mm3", "U", "mU", 
            
            // both [iU] and [IU] are correct in case-sensitive UCUM variant
            "[IU]/L","m[IU]/mL", "u[IU]/mL",  "u[IU]", "[IU]", "10*6.[IU]", "[IU]/g", "[IU]/g{Hgb}", "[IU]/kg", "u[iU]/mL", "m[iU]/mL", "[iU]/mL", "k[iU]/mL", "[iU]/dL", "m[iU]/L", "[iU]/L",
           
            // We also need to add the lower-case variant for L because both l and L are valid for litre in the case-sensitive UCUM variant 
            "/ul", "[iU]/l", "10*3/ul", "10*6/ul", "fl", "g/dl", "g/l", "g/ml", "m[iU]/ml", "meq/l", "mg/dl", "mmol/l","ng/ml", 
            "nmol/l","pg/ml", "pmol/l", "U/l", "u[iU]/ml", "ug/dl", "ug/l", "ug/ml",  "ml/dl", 
            "fl", "pl", "nl", "ul", "ml", "ml/{h`b}", "l", "dl", "cl", "kl", "hl", "l.s2/s", 
            "ml/kg", "l/kg",  "/ul", "{Cells}/ul", "{rbc}/ul", "10*3/ul", "10*6/ul", "10*9/ul", "/ml", "{Spermatozoa}/ml",
            "{Copies}/ml", "10*3/ml", "10*3{Copies}/ml", "10*6/ml", "10*9/ml", "{cfu}/ml", "/dl", "/l", "10*3/l", "10*6/l", "10*12/l", "10*9/l", "pg/ml", "ng/ml", "ng/ml{rbc}", 
            "ug/ml", "mg/ml", "g/ml", "pg/dl", "ng/dl", "ug/dl", "ug/dl{rbc}", "mg/dl", "mg{Phenylketones}/dl", "g/dl", "ng/l", "pg/l", "ug/l", "mg/l", "g/l", "kg/l",
            "fmol/ml", "pmol/ml", "nmol/ml", "umol/ml", "mol/ml", "pmol/dl", "nmol/dl", "umol/dl", "mmol/dl", "mmol/l", "pmol/l", "nmol/l", "umol/l", "mol/l", 
            "ueq/ml", "meq/ml", "eq/ml", "{AHG}eq/ml", "10*6.eq/ml", "ueq/l", "meq/l", "eq/l", "meq/dl", "mosm/l", "osm/l", "u[iU]/ml", "m[iU]/ml",
            "{IgGPhospholipid}U/ml", "{IgMPhospholipid}U/ml", "{ComplementCh50}U/ml", "{IgAPhospholipid}U/ml", "{Elisa_U}/ml", "[iU]/ml", "k[iU]/ml", "[iU]/dl", 
            "{Ehrlich_U}/dl", "m[iU]/l", "[iU]/l"            
            
        ));    
    /**
     * Returns True if the units string according to
     * the HL7 UCUM specification.
     * Note that this implementation currrently assumes case-sensitive UCUM format.
     *
     * @param units
     * @return true if units valid
     * @throws IllegalArgumentException if units null
     */
    public boolean isValidUnitsString(String units) {
        if(units == null) {
            throw new IllegalArgumentException("units null");
        }
        
        // Unfortunately the UCUM support in the library javax.measure.unit uses a very outdated version of UCUM.
        // E.g. mm[Hg], osm, eq are missing in there. Therefore we first check if the unit is a UCUM unit commonly used in health care. 
        if (commonUCUMCodes.contains(units)) {
            return true;
        }

        //  Only if it is not in this list, we continue to check with the provided library if the UCUM unit can be constructed.
        Unit<?> unit = null;
        try {
            // This is equivalent to this UCUMFormat.getCaseSensitiveInstance().parseObject(units, new ParsePosition(0));
            // That means it is the case-sensitive variant of UCUM that is used here.
            unit = Unit.valueOf(units); 
        } catch(java.lang.IllegalArgumentException e) {

        }
       
        // If we want to support the case-insensitive UCUM variant as well, we can do so via this:
        // Unit unitCI = UCUMFormat.getCaseInsensitiveInstance().parseObject(units, new ParsePosition(0));

        
       /* System.out.println("===============================");
        if (unit != null) {
            System.out.println("Unit parsed CS: "+units +"->"+UCUMFormat.getCaseSensitiveInstance().format(unit));
        } else {
            System.out.println("Unit NOT parsed CS: "+ units);
        }
        if (unitCI != null){
            System.out.println("Unit parsed CI: "+units +"->"+UCUMFormat.getCaseInsensitiveInstance().format(unitCI));
        } else {
            System.out.println("Unit NOT parsed CI: "+ units);
        }
*/
        return unit!= null;
    }

    /**
     * Return True if two units strings correspond to the same
     * measured property.
     * 
     * @param units1
     * @param units2
     * @return true if two units equal
     * @throws IllegalArgumentException if units1 or units2 null
     */
    public boolean unitsEquivalent(String units1, String units2) {
	if(units1 == null) {
	    throw new IllegalArgumentException("units1 null");
	}
	if(units2 == null) {
     throw new IllegalArgumentException("units2 null");
	}
	if (units1.equals(units2)) {
        return true;
    }
    
	// SG: Not too sure that this is actually correct...
	// a) For case-sensitive UCUM, mg and Mg are quite different, but would be accepted as the same (milli vs. mega)
	//     Also, the upper-case G in the case-sensitive UCUM refers to Gauss' constant
	// b) Would a unit be equivalent if they are formally the same, e.g A[mpere] = C[oulomb]/s[econd] are formally equivalent
	   Unit<?> unit1 = Unit.valueOf(units1);
       Unit<?> unit2 = Unit.valueOf(units2);
	try {
        return (unit1.getConverterToAny(unit2).convert(1) ==1);
    } catch (UnsupportedOperationException e) {      
        return false;
    } catch (ConversionException e) {
        // Units not even compatible
        return false;
    }
//	return units1.equalsIgnoreCase(units2);    	
    }

    /**
     * Return True if two units strings are comparable.
     * 
     * @param units1
     * @param units2
     * @return true if two units comparable
     * @throws IllegalArgumentException if units1 or units2 null
     */
    public boolean unitsComparable(String units1, String units2) {
	if (unitsEquivalent(units1, units2)){
	    return true;
	}else{
	    Unit<?> u1 = Unit.valueOf(units1);
	    Unit<?> u2 = Unit.valueOf(units2);
	    return u1.isCompatible(u2);
	}
    }

    /**
     * Comparison between two measures.
     * 
     * @param units1
     * @param units2
     * @return a negative integer, zero, or a positive integer as this measure
     *         is less than, equal to, or greater than the specified measurable
     *         quantity.
     * @throws IllegalArgumentException if units1, units2 null or not comparable
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int compare(String units1, Double value1, String units2, Double value2) {
	if(value1 == null) {
	    throw new IllegalArgumentException("value1 null");
	}
	if(value2 == null) {
	    throw new IllegalArgumentException("value2 null");
	}
	if (unitsEquivalent(units1, units2)){
	    return value1.compareTo(value2);
	}else{
	    Unit<?> unit1 = Unit.valueOf(units1);
	    Unit<?> unit2 = Unit.valueOf(units2);
	    
	    if (!unit1.isCompatible(unit2)){ 
		throw new IllegalArgumentException("units '"+units1+"' is not comparable to '"+units2+"'");
	    }
	    Measure measure1 = Measure.valueOf(value1, unit1);
	    Measure measure2 = Measure.valueOf(value2, unit2);
	    return measure1.compareTo(measure2);
	}
    }
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
 *  The Original Code is SimpleMeasurementService.java
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