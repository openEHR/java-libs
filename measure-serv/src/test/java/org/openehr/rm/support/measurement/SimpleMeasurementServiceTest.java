package org.openehr.rm.support.measurement;

import junit.framework.TestCase;

public class SimpleMeasurementServiceTest extends TestCase {

    @Override
    public void setUp() {
        service = SimpleMeasurementService.getInstance();
    }

    public void testunitsEquivalent() throws Exception {
        //assertTrue(service.unitsEquivalent("mg", "MG"));
        assertFalse(service.unitsEquivalent("mg", "MG"));
        assertFalse(service.unitsEquivalent("mg", "mG"));
        assertTrue(service.unitsEquivalent("A", "C/s"));

        assertFalse(service.unitsEquivalent("mg", "kg"));
    }

    public void testunitsComparable() throws Exception {
        assertTrue(service.unitsComparable("mg", "kg"));
        assertFalse(service.unitsComparable("mg", "ml"));
        assertTrue(service.unitsComparable("cm", "[in_i]"));
    }

    public void testunitsComparison() throws Exception {
        assertTrue(service.compare("kg", 1.0, "g", 1000.0)==0);
        assertTrue(service.compare("l", 1.0, "ml", 100.0)>0);
        assertTrue(service.compare("[in_i]", 2.0, "cm", 5.0)>0);
        assertFalse(service.compare("[in_i]", 1000.0, "m", 26.0)>0);
    }

    public void testunitsValid() throws Exception {
        assertTrue(service.isValidUnitsString("mg"));
        assertTrue(service.isValidUnitsString("osm"));
        assertTrue(service.isValidUnitsString("mm[Hg]"));
        assertTrue(service.isValidUnitsString("ug"));
        assertTrue(service.isValidUnitsString("ug/L"));
        assertTrue(service.isValidUnitsString("ug/l"));
        assertTrue(service.isValidUnitsString("km/h"));
        assertTrue(service.isValidUnitsString("[iU]"));
        assertTrue(service.isValidUnitsString("[IU]"));
        assertTrue(service.isValidUnitsString("a")); // year        
        
        assertTrue(service.isValidUnitsString("wk"));
        assertTrue(service.isValidUnitsString("1/wk"));        
        assertTrue(service.isValidUnitsString("/wk"));        
        assertTrue(service.isValidUnitsString("[oz_av]/wk"));
        
        assertTrue(service.isValidUnitsString("eq"));
        assertFalse(service.isValidUnitsString("Eq"));

        assertFalse(service.isValidUnitsString("osmole"));
        assertFalse(service.isValidUnitsString("iU"));
        assertFalse(service.isValidUnitsString("yr")); // year is "a" 
        assertFalse(service.isValidUnitsString("milligm"));
        assertFalse(service.isValidUnitsString("mgm"));
       
        // White space is not recognized in a a unit term and should generally not occur. 
        // UCUM implementations may flag whitespace as an error rather than ignore it. 
        // Whitespace is not used as a separator of otherwise ambiguous parts of a unit term. 
        //assertFalse(service.isValidUnitsString(" ")); exactly one space is tolerated at the moment, because the openEHR specs prohibit it to be completely empty.  
        assertFalse(service.isValidUnitsString(" m"));
        assertFalse(service.isValidUnitsString("m "));
        assertFalse(service.isValidUnitsString("10*3/ ul"));
        assertFalse(service.isValidUnitsString("10* 3/ul"));
        assertFalse(service.isValidUnitsString("10 *3/ul"));
        assertFalse(service.isValidUnitsString("rad2 {a}"));
        assertFalse(service.isValidUnitsString("rad 2{a}"));
     
        // Official UCUM tests:

        assertTrue(service.isValidUnitsString("m"));
        assertFalse(service.isValidUnitsString("m/")); // / is not followed by a term
        assertTrue(service.isValidUnitsString(""));
        assertTrue(service.isValidUnitsString("/m"));
        assertTrue(service.isValidUnitsString("10*3/ul"));
        assertTrue(service.isValidUnitsString("10*-3/ul"));
        assertTrue(service.isValidUnitsString("10*+3/ul"));        
        assertFalse(service.isValidUnitsString("10+3/ul")); // 10 is not a valid unit
        assertTrue(service.isValidUnitsString("m"));
        assertTrue(service.isValidUnitsString("m[H2O]"));
        assertTrue(service.isValidUnitsString("10*23"));
        assertTrue(service.isValidUnitsString("rad2"));
        assertTrue(service.isValidUnitsString("m3.kg-1.s-2"));
        assertTrue(service.isValidUnitsString("4.[pi].10*-7.N/A2"));
        /** test that the parser supports both {} inserts, but not unicode characters too, while we're at it */
        assertTrue(service.isValidUnitsString("rad2{a}"));
        assertFalse(service.isValidUnitsString("rad2{錠}")); // no unicode chars allowed.
        assertTrue(service.isValidUnitsString("{a}.rad2{b}"));
        assertFalse(service.isValidUnitsString("{a}rad2{b}"));
        assertTrue(service.isValidUnitsString("1{c}"));
        assertFalse(service.isValidUnitsString("{|}1"));
        assertTrue(service.isValidUnitsString("{e}"));
        assertTrue(service.isValidUnitsString("%"));

        /** 
         These codes are taken from the first draft of a proposed Canadian UCUM subset. 
         (the subset was subsequently corrected after being tested) */

        assertTrue(service.isValidUnitsString("[cup_us]"));
        assertTrue(service.isValidUnitsString("[foz_br]"));
        assertTrue(service.isValidUnitsString("[ft_i]"));
        assertTrue(service.isValidUnitsString("[in_i]"));
        assertTrue(service.isValidUnitsString("[yd_i]"));
        assertTrue(service.isValidUnitsString("[gal_br]"));
        assertTrue(service.isValidUnitsString("[lb_av]"));
        assertTrue(service.isValidUnitsString("[oz_av]"));
        assertTrue(service.isValidUnitsString("[pt_br]"));
        assertTrue(service.isValidUnitsString("[qt_br]"));
        assertTrue(service.isValidUnitsString("[sft_i]"));
        assertTrue(service.isValidUnitsString("[sin_i]"));
        assertTrue(service.isValidUnitsString("[syd_i]"));
        assertTrue(service.isValidUnitsString("[tbs_us]"));
        assertTrue(service.isValidUnitsString("[tsp_us]"));
        assertTrue(service.isValidUnitsString("1/d"));
        assertTrue(service.isValidUnitsString("1/min"));
        assertTrue(service.isValidUnitsString("a"));
        assertTrue(service.isValidUnitsString("cm"));
        assertTrue(service.isValidUnitsString("cm2"));
        assertTrue(service.isValidUnitsString("cm3"));
        assertTrue(service.isValidUnitsString("d"));
        assertTrue(service.isValidUnitsString("dg"));
        assertTrue(service.isValidUnitsString("dl"));
        assertTrue(service.isValidUnitsString("g"));
        assertTrue(service.isValidUnitsString("g/d"));
        assertTrue(service.isValidUnitsString("g/l"));
        assertTrue(service.isValidUnitsString("h"));
        assertFalse(service.isValidUnitsString("iU")); // iU needs [] around it
        assertTrue(service.isValidUnitsString("kg"));
        assertTrue(service.isValidUnitsString("l"));
        assertTrue(service.isValidUnitsString("m"));
        assertTrue(service.isValidUnitsString("mm"));
        assertTrue(service.isValidUnitsString("m2"));
        assertTrue(service.isValidUnitsString("meq"));
        assertTrue(service.isValidUnitsString("mg"));
        assertTrue(service.isValidUnitsString("mg"));
        assertTrue(service.isValidUnitsString("mg/d"));
        assertTrue(service.isValidUnitsString("min"));
        assertTrue(service.isValidUnitsString("ml"));
        assertTrue(service.isValidUnitsString("ml/s"));
        assertTrue(service.isValidUnitsString("mm[Hg]"));
        assertTrue(service.isValidUnitsString("mm2"));
        assertTrue(service.isValidUnitsString("mm3"));
        assertTrue(service.isValidUnitsString("mmol"));
        assertTrue(service.isValidUnitsString("mmol/l"));
        assertTrue(service.isValidUnitsString("mo"));
        assertTrue(service.isValidUnitsString("mol"));
        assertTrue(service.isValidUnitsString("ms"));
        assertTrue(service.isValidUnitsString("mU"));
        assertTrue(service.isValidUnitsString("ng"));
        assertTrue(service.isValidUnitsString("ng"));
        assertTrue(service.isValidUnitsString("nl"));
        assertTrue(service.isValidUnitsString("nl"));
        assertTrue(service.isValidUnitsString("pg/ml"));
        assertTrue(service.isValidUnitsString("s"));
        assertTrue(service.isValidUnitsString("U"));
        assertTrue(service.isValidUnitsString("U/l"));
        assertTrue(service.isValidUnitsString("ug"));
        assertTrue(service.isValidUnitsString("ug/min"));
        assertTrue(service.isValidUnitsString("ul"));
        assertTrue(service.isValidUnitsString("umol"));
        assertTrue(service.isValidUnitsString("umol/l"));
        assertTrue(service.isValidUnitsString("wk"));
        assertTrue(service.isValidUnitsString("%"));
        assertTrue(service.isValidUnitsString("[cup_us]"));
        assertTrue(service.isValidUnitsString("[foz_br]"));
        assertTrue(service.isValidUnitsString("[gal_br]"));
        assertTrue(service.isValidUnitsString("[sft_i]"));
        assertTrue(service.isValidUnitsString("[sin_i]"));
        assertTrue(service.isValidUnitsString("[lb_av]"));
        assertTrue(service.isValidUnitsString("[oz_av]"));
        assertTrue(service.isValidUnitsString("[pt_br]"));
        assertTrue(service.isValidUnitsString("[qt_br]"));
        assertTrue(service.isValidUnitsString("[tbs_us]"));
        assertTrue(service.isValidUnitsString("[tsp_us]"));
        assertTrue(service.isValidUnitsString("[syd_i]"));
        assertTrue(service.isValidUnitsString("cm2"));
        assertTrue(service.isValidUnitsString("cm3"));
        assertTrue(service.isValidUnitsString("g"));
        assertTrue(service.isValidUnitsString("kg"));
        assertTrue(service.isValidUnitsString("l"));
        assertTrue(service.isValidUnitsString("m2"));
        assertTrue(service.isValidUnitsString("meq"));
        assertTrue(service.isValidUnitsString("mg"));
        assertTrue(service.isValidUnitsString("ml"));
        assertTrue(service.isValidUnitsString("mm2"));
        assertTrue(service.isValidUnitsString("mm3"));
        assertTrue(service.isValidUnitsString("mmol"));
        assertFalse(service.isValidUnitsString("molv")); // molv is not a valid unit
        assertTrue(service.isValidUnitsString("mU"));
        assertTrue(service.isValidUnitsString("ng"));
        assertTrue(service.isValidUnitsString("nl"));
        assertTrue(service.isValidUnitsString("U"));
        assertTrue(service.isValidUnitsString("ug"));
        assertTrue(service.isValidUnitsString("ul"));
        assertTrue(service.isValidUnitsString("umol"));
        assertTrue(service.isValidUnitsString("a"));
        assertTrue(service.isValidUnitsString("d"));
        assertTrue(service.isValidUnitsString("h"));
        assertTrue(service.isValidUnitsString("min"));
        assertTrue(service.isValidUnitsString("mo"));
        assertTrue(service.isValidUnitsString("s"));
        assertTrue(service.isValidUnitsString("wk"));
        assertTrue(service.isValidUnitsString("[ft_i]"));
        assertTrue(service.isValidUnitsString("[in_i]"));
        assertTrue(service.isValidUnitsString("[lb_av]"));
        assertTrue(service.isValidUnitsString("[oz_av]"));
        assertTrue(service.isValidUnitsString("[yd_i]"));
        assertTrue(service.isValidUnitsString("cm"));
        assertTrue(service.isValidUnitsString("g"));
        assertTrue(service.isValidUnitsString("kg"));
        assertTrue(service.isValidUnitsString("m"));
        assertTrue(service.isValidUnitsString("mm"));
        assertTrue(service.isValidUnitsString("[mi_us]"));
        assertTrue(service.isValidUnitsString("[yd_i]"));
        assertTrue(service.isValidUnitsString("deg"));
        assertTrue(service.isValidUnitsString("km"));
        assertTrue(service.isValidUnitsString("m"));
        assertTrue(service.isValidUnitsString("%"));
        assertTrue(service.isValidUnitsString("/[HPF]"));
        assertTrue(service.isValidUnitsString("/[LPF]"));
        assertTrue(service.isValidUnitsString("/L"));
        assertTrue(service.isValidUnitsString("/mL"));
        assertTrue(service.isValidUnitsString("/mmol"));
        assertTrue(service.isValidUnitsString("[APL'U]"));
        assertFalse(service.isValidUnitsString("[BETH'U]")); // not a valid unit
        assertTrue(service.isValidUnitsString("[GPL'U]"));
        assertTrue(service.isValidUnitsString("[IU]"));
        assertTrue(service.isValidUnitsString("[IU]/d"));
        assertTrue(service.isValidUnitsString("[IU]/L"));
        assertTrue(service.isValidUnitsString("[IU]/mL"));
        assertFalse(service.isValidUnitsString("[iIU]")); // not a valid unit (iIU)
        assertFalse(service.isValidUnitsString("[iIU]/d")); // not a valid unit (iIU)
        assertFalse(service.isValidUnitsString("[iIU]/L")); // not a valid unit (iIU)
        assertFalse(service.isValidUnitsString("[iIU]/mL")); // not a valid unit (iIU)
        assertTrue(service.isValidUnitsString("[MPL'U]"));
        assertTrue(service.isValidUnitsString("10*12/L"));
        assertTrue(service.isValidUnitsString("10*6/L"));
        assertTrue(service.isValidUnitsString("10*9/L"));
        assertTrue(service.isValidUnitsString("Cel"));
        assertTrue(service.isValidUnitsString("cm"));
        assertTrue(service.isValidUnitsString("cm/s"));
        assertTrue(service.isValidUnitsString("fL"));
        assertTrue(service.isValidUnitsString("fmol/L"));
        assertTrue(service.isValidUnitsString("g"));
        assertFalse(service.isValidUnitsString("g/12h")); // not a valid unit (12h - should be 12.h)
        assertFalse(service.isValidUnitsString("g/48h")); // not a valid unit (48h)
        assertFalse(service.isValidUnitsString("g/4h")); // not a valid unit (4h)
        assertFalse(service.isValidUnitsString("g/6h")); // not a valid unit (6h)
        assertFalse(service.isValidUnitsString("g/72h")); // not a valid unit (72h)
        assertTrue(service.isValidUnitsString("g/d"));
        assertTrue(service.isValidUnitsString("g/g"));
        assertTrue(service.isValidUnitsString("g/L"));
        assertTrue(service.isValidUnitsString("h"));
        assertTrue(service.isValidUnitsString("km"));
        assertTrue(service.isValidUnitsString("kU/L"));
        assertTrue(service.isValidUnitsString("L/L"));
        assertTrue(service.isValidUnitsString("m[IU]/L"));
        assertFalse(service.isValidUnitsString("m[iIU]/L")); // not a valid unit (iIU)
        assertTrue(service.isValidUnitsString("mg"));
        assertFalse(service.isValidUnitsString("mg/12h")); // not a valid unit (12h)
        assertTrue(service.isValidUnitsString("mg/d"));
        assertTrue(service.isValidUnitsString("mg/g"));
        assertTrue(service.isValidUnitsString("mg/L"));
        assertTrue(service.isValidUnitsString("mg/mg"));
        assertTrue(service.isValidUnitsString("mg/mL"));
        assertTrue(service.isValidUnitsString("min"));
        assertTrue(service.isValidUnitsString("mL"));
        assertFalse(service.isValidUnitsString("mL/10h")); // not a valid unit (10h)
        assertFalse(service.isValidUnitsString("mL/12h")); // not a valid unit (12h)
        assertFalse(service.isValidUnitsString("mL/2h")); // not a valid unit (2h)
        assertFalse(service.isValidUnitsString("mL/4h")); // not a valid unit (4h)
        assertFalse(service.isValidUnitsString("mL/5h")); // not a valid unit (5h)
        assertFalse(service.isValidUnitsString("mL/6h")); // not a valid unit (6h)
        assertFalse(service.isValidUnitsString("mL/72h")); // not a valid unit (72h)
        assertFalse(service.isValidUnitsString("mL/8h")); // not a valid unit (8h)
        assertTrue(service.isValidUnitsString("mL/d"));
        assertTrue(service.isValidUnitsString("mL/min"));
        assertTrue(service.isValidUnitsString("mm"));
        assertTrue(service.isValidUnitsString("mm/h"));
        assertTrue(service.isValidUnitsString("mm[Hg]"));
        assertTrue(service.isValidUnitsString("mmol"));
        assertFalse(service.isValidUnitsString("mmol/12h")); // not a valid unit (12h)
        assertFalse(service.isValidUnitsString("mmol/5h")); // not a valid unit (5h)
        assertFalse(service.isValidUnitsString("mmol/6h")); // not a valid unit (6h)
        assertTrue(service.isValidUnitsString("mmol/d"));
        assertTrue(service.isValidUnitsString("mmol/g"));
        assertTrue(service.isValidUnitsString("mmol/kg"));
        assertFalse(service.isValidUnitsString("mmol/kg[H20]")); // not a valid unit (kg[H20])
        assertTrue(service.isValidUnitsString("mmol/L"));
        assertTrue(service.isValidUnitsString("mmol/mmol"));
        assertTrue(service.isValidUnitsString("mU/L"));
        assertTrue(service.isValidUnitsString("ng/d"));
        assertTrue(service.isValidUnitsString("ng/g"));
        assertTrue(service.isValidUnitsString("ng/L"));
        assertTrue(service.isValidUnitsString("ng/mL"));
        assertTrue(service.isValidUnitsString("nmol/d"));
        assertTrue(service.isValidUnitsString("nmol/g"));
        assertTrue(service.isValidUnitsString("nmol/h/mL"));
        assertTrue(service.isValidUnitsString("nmol/L"));
        assertTrue(service.isValidUnitsString("nmol/mmol"));
        assertTrue(service.isValidUnitsString("nmol/nmol"));
        assertTrue(service.isValidUnitsString("pg"));
        assertTrue(service.isValidUnitsString("pg/mL"));
        assertTrue(service.isValidUnitsString("pmol/d"));
        assertTrue(service.isValidUnitsString("pmol/g"));
        assertTrue(service.isValidUnitsString("pmol/h/mg"));
        assertTrue(service.isValidUnitsString("pmol/h/mL"));
        assertTrue(service.isValidUnitsString("pmol/L"));
        assertTrue(service.isValidUnitsString("pmol/mmol"));
        assertTrue(service.isValidUnitsString("s"));
        assertTrue(service.isValidUnitsString("U"));
        assertFalse(service.isValidUnitsString("U/12h")); // not a valid unit (12h)
        assertFalse(service.isValidUnitsString("U/1h")); // not a valid unit (1h)
        assertFalse(service.isValidUnitsString("U/2h")); // not a valid unit (2h)
        assertTrue(service.isValidUnitsString("U/d"));
        assertTrue(service.isValidUnitsString("U/g"));
        assertTrue(service.isValidUnitsString("U/kg"));
        assertTrue(service.isValidUnitsString("U/L"));
        assertTrue(service.isValidUnitsString("U/mL"));
        assertTrue(service.isValidUnitsString("u[IU]/mL"));
        assertFalse(service.isValidUnitsString("u[iIU]/mL")); // not a valid unit (iIU)
        assertTrue(service.isValidUnitsString("ug"));
        assertTrue(service.isValidUnitsString("ug/d"));
        assertTrue(service.isValidUnitsString("ug/g"));
        assertTrue(service.isValidUnitsString("ug/L"));
        assertTrue(service.isValidUnitsString("ug/mL"));
        assertTrue(service.isValidUnitsString("um/s"));
        assertTrue(service.isValidUnitsString("umol"));
        assertTrue(service.isValidUnitsString("umol/2.h"));
        assertTrue(service.isValidUnitsString("umol/d"));
        assertTrue(service.isValidUnitsString("umol/g"));
        assertTrue(service.isValidUnitsString("umol/L"));
        assertTrue(service.isValidUnitsString("umol/mmol"));
        assertTrue(service.isValidUnitsString("umol/umol"));
        assertTrue(service.isValidUnitsString("wk"));

        /** 
         These codes are taken from Keith's draft list of ICO/UCUM codes,
         at http://motorcycleguy.blogspot.com/2009/11/iso-to-ucum-mapping-table.html
         */
        assertTrue(service.isValidUnitsString("[arb'U]"));
        assertTrue(service.isValidUnitsString("dyn.s/(cm5.m2)"));
        assertTrue(service.isValidUnitsString("[iU]/mL"));
        assertTrue(service.isValidUnitsString("mL/h"));
        assertTrue(service.isValidUnitsString("[bdsk'U]"));
        assertTrue(service.isValidUnitsString("dyn.s/cm5"));
        assertTrue(service.isValidUnitsString("K/W"));
        assertTrue(service.isValidUnitsString("mm[Hg]"));
        assertTrue(service.isValidUnitsString("{bsa}"));
        assertTrue(service.isValidUnitsString("cm[H2O]"));
        assertFalse(service.isValidUnitsString("cm[H20]"));
        assertTrue(service.isValidUnitsString("kg{body_wt}"));
        assertTrue(service.isValidUnitsString("mm/h"));
        assertTrue(service.isValidUnitsString("cal"));
        assertTrue(service.isValidUnitsString("cm[H2O].s/L"));
        assertTrue(service.isValidUnitsString("kg/m2")); 
        assertTrue(service.isValidUnitsString("mmol/(8.h.kg)"));
        assertTrue(service.isValidUnitsString("{cfu}"));
        assertTrue(service.isValidUnitsString("cm[H2O]/(s.m)"));
        assertTrue(service.isValidUnitsString("kg/h"));
        assertTrue(service.isValidUnitsString("mmol/(8.h)"));
        assertTrue(service.isValidUnitsString("[drp]"));
        assertTrue(service.isValidUnitsString("dB[SPL]"));
        assertTrue(service.isValidUnitsString("L/(8.h)"));
        assertTrue(service.isValidUnitsString("mmol/(kg.h)"));
        assertTrue(service.isValidUnitsString("[ka'U]"));
        assertTrue(service.isValidUnitsString("REM"));
        assertTrue(service.isValidUnitsString("L/h"));
        assertTrue(service.isValidUnitsString("mmol/h"));
        assertTrue(service.isValidUnitsString("kcal"));
        assertTrue(service.isValidUnitsString("g{creat}"));
        assertTrue(service.isValidUnitsString("[lb_av]"));
        assertTrue(service.isValidUnitsString("ng/(8.h)"));
        assertTrue(service.isValidUnitsString("kcal/(8.h)"));
        assertTrue(service.isValidUnitsString("g{hgb}"));
        assertTrue(service.isValidUnitsString("ng/(8.h.kg)"));
        assertTrue(service.isValidUnitsString("kcal/d"));
        assertTrue(service.isValidUnitsString("g{tit_nit}"));
        assertTrue(service.isValidUnitsString("ms/s"));
        assertTrue(service.isValidUnitsString("ng/(kg.h)"));
        assertTrue(service.isValidUnitsString("kcal/h"));
        assertTrue(service.isValidUnitsString("g{tot_prot}"));
        assertTrue(service.isValidUnitsString("Ms"));
        assertTrue(service.isValidUnitsString("ng/h"));
        assertTrue(service.isValidUnitsString("[knk'U]"));
        assertTrue(service.isValidUnitsString("g{wet_tis}"));
        assertTrue(service.isValidUnitsString("meq/(8.h)"));
        assertTrue(service.isValidUnitsString("osm"));
        assertTrue(service.isValidUnitsString("[mclg'U]"));
 
        // According to the UCUM test cases, this is valid, but not sure why. "g.m/{hb}.m2" could be valid, but "g.m/{hb}m2" ?        
        // The Bacus-Naur at http://unitsofmeasure.org/ucum.html seems to suggest it is not valid 
        //assertTrue(service.isValidUnitsString("g.m/{hb}m2"));
        
        assertTrue(service.isValidUnitsString("meq/(8.h.kg)"));
        assertTrue(service.isValidUnitsString("osm/kg"));
        assertTrue(service.isValidUnitsString("{od}"));
        assertTrue(service.isValidUnitsString("g.m/{hb}"));
        assertTrue(service.isValidUnitsString("meq/(kg.h)"));
        assertTrue(service.isValidUnitsString("osm/L"));
        assertTrue(service.isValidUnitsString("pH"));
        assertTrue(service.isValidUnitsString("g/(8.h)"));
        assertTrue(service.isValidUnitsString("meq/h"));
        assertTrue(service.isValidUnitsString("pA"));
        assertTrue(service.isValidUnitsString("[ppb]"));
        assertTrue(service.isValidUnitsString("g/(8.kg.h)"));
        assertTrue(service.isValidUnitsString("mg/(8.h)"));
        assertTrue(service.isValidUnitsString("Pa"));
        assertTrue(service.isValidUnitsString("[ppm]"));
        assertTrue(service.isValidUnitsString("g/(kg.h)"));
        assertTrue(service.isValidUnitsString("mg/(8.h.kg)"));
        assertTrue(service.isValidUnitsString("[pptr]"));
        assertTrue(service.isValidUnitsString("g/h"));
        assertTrue(service.isValidUnitsString("mg/(kg.h)"));
        assertTrue(service.isValidUnitsString("S"));
        assertTrue(service.isValidUnitsString("[ppth]"));
        assertTrue(service.isValidUnitsString("[in_us]"));
        assertTrue(service.isValidUnitsString("mg/h"));
        assertFalse(service.isValidUnitsString("ug(8.h)"));
        assertFalse(service.isValidUnitsString("ug(8hr)"));
        assertTrue(service.isValidUnitsString("[todd'U]"));
        assertTrue(service.isValidUnitsString("[in_i'Hg]"));
        assertTrue(service.isValidUnitsString("m[iU]/mL"));
        assertTrue(service.isValidUnitsString("ug/(8.h.kg)"));
        assertTrue(service.isValidUnitsString("/[arb'U]"));
        assertTrue(service.isValidUnitsString("[iU]"));
        assertTrue(service.isValidUnitsString("mL/{hb}.m2"));
        assertTrue(service.isValidUnitsString("ug/(kg.h)"));
        assertTrue(service.isValidUnitsString("[HPF]"));
        assertTrue(service.isValidUnitsString("[iU]/d"));
        assertTrue(service.isValidUnitsString("mL/(8.h)"));
        assertTrue(service.isValidUnitsString("ug/h"));
        assertTrue(service.isValidUnitsString("/{tot}"));
        assertTrue(service.isValidUnitsString("[iU]/h"));
        assertTrue(service.isValidUnitsString("mL/(8.h.kg)"));

        assertTrue(service.isValidUnitsString("u[iU]"));
        assertTrue(service.isValidUnitsString("/[iU]"));
        assertTrue(service.isValidUnitsString("[iU]/kg"));
        assertTrue(service.isValidUnitsString("mL/{hb}"));
        assertTrue(service.isValidUnitsString("10*3{rbc}"));
        assertTrue(service.isValidUnitsString("[iU]/L"));
        assertTrue(service.isValidUnitsString("mL/(kg.h)"));
        assertTrue(service.isValidUnitsString("10.L/(min.m2)"));
        assertTrue(service.isValidUnitsString("[iU]/min"));
        assertTrue(service.isValidUnitsString("mL/cm[H2O]"));
        assertTrue(service.isValidUnitsString("%"));
        assertTrue(service.isValidUnitsString("bar"));
        assertTrue(service.isValidUnitsString("g/L"));
        assertTrue(service.isValidUnitsString("L.s"));
        assertTrue(service.isValidUnitsString("mg"));
        assertTrue(service.isValidUnitsString("mmol/(kg.d)"));
        assertTrue(service.isValidUnitsString("ng/L"));
        assertTrue(service.isValidUnitsString("ueq"));
        assertTrue(service.isValidUnitsString("/kg"));
        assertTrue(service.isValidUnitsString("Bq"));
        assertTrue(service.isValidUnitsString("g/m2"));
        assertTrue(service.isValidUnitsString("L/(min.m2)"));
        assertTrue(service.isValidUnitsString("mg/(kg.d)"));
        assertTrue(service.isValidUnitsString("mmol/(kg.min)"));
        assertTrue(service.isValidUnitsString("ng/m2"));
        assertTrue(service.isValidUnitsString("ug"));
        assertTrue(service.isValidUnitsString("/L"));
        assertTrue(service.isValidUnitsString("Cel"));
        assertTrue(service.isValidUnitsString("g/min"));
        assertTrue(service.isValidUnitsString("L/d"));
        assertTrue(service.isValidUnitsString("mg/(kg.min)"));
        assertTrue(service.isValidUnitsString("mmol/kg"));
        assertTrue(service.isValidUnitsString("ng/min"));
        assertTrue(service.isValidUnitsString("ug/(kg.d)"));
        assertTrue(service.isValidUnitsString("/m3"));
        assertTrue(service.isValidUnitsString("cm"));
        assertTrue(service.isValidUnitsString("Gy"));
        assertTrue(service.isValidUnitsString("L/kg"));
        assertTrue(service.isValidUnitsString("mg/d"));
        assertTrue(service.isValidUnitsString("mmol/L"));
        assertTrue(service.isValidUnitsString("ng/mL"));
        assertTrue(service.isValidUnitsString("ug/(kg.min)"));
        assertTrue(service.isValidUnitsString("/min"));
        assertTrue(service.isValidUnitsString("cm2/s"));
        assertTrue(service.isValidUnitsString("h"));
        assertTrue(service.isValidUnitsString("L/min"));
        assertTrue(service.isValidUnitsString("mg/dL"));
        assertTrue(service.isValidUnitsString("mmol/m2"));
        assertTrue(service.isValidUnitsString("ng/s"));
        assertTrue(service.isValidUnitsString("ug/d"));
        assertTrue(service.isValidUnitsString("/m3"));
        assertTrue(service.isValidUnitsString("d"));
        assertTrue(service.isValidUnitsString("hL"));
        assertTrue(service.isValidUnitsString("L/s"));
        assertTrue(service.isValidUnitsString("mg/kg"));
        assertTrue(service.isValidUnitsString("mmol/min"));
        assertTrue(service.isValidUnitsString("nkat"));
        assertTrue(service.isValidUnitsString("ug/dL"));
        assertTrue(service.isValidUnitsString("/min"));
        assertTrue(service.isValidUnitsString("dB"));
        assertTrue(service.isValidUnitsString("J/L"));
        assertTrue(service.isValidUnitsString("lm"));
        assertTrue(service.isValidUnitsString("mg/L"));
        assertTrue(service.isValidUnitsString("mol/(kg.s)"));
        assertTrue(service.isValidUnitsString("nm"));
        assertTrue(service.isValidUnitsString("ug/g"));
        assertTrue(service.isValidUnitsString("/mL"));
        assertTrue(service.isValidUnitsString("deg"));
        assertTrue(service.isValidUnitsString("kat"));
        assertTrue(service.isValidUnitsString("m"));
        assertTrue(service.isValidUnitsString("mg/m2"));
        assertTrue(service.isValidUnitsString("mol/kg"));
        assertTrue(service.isValidUnitsString("nmol/s"));
        assertTrue(service.isValidUnitsString("ug/kg"));
        assertTrue(service.isValidUnitsString("1/mL"));
        assertTrue(service.isValidUnitsString("eq"));
        assertTrue(service.isValidUnitsString("kat/kg"));
        assertTrue(service.isValidUnitsString("m/s2"));
        assertTrue(service.isValidUnitsString("mg/m3"));
        assertTrue(service.isValidUnitsString("mol/L"));
        assertTrue(service.isValidUnitsString("ns"));
        assertTrue(service.isValidUnitsString("ug/L"));
        assertTrue(service.isValidUnitsString("10*12/L"));
        assertTrue(service.isValidUnitsString("eV"));
        assertTrue(service.isValidUnitsString("kat/L"));
        assertTrue(service.isValidUnitsString("m2"));
        assertTrue(service.isValidUnitsString("mg/min"));
        assertTrue(service.isValidUnitsString("mol/m3"));
        assertTrue(service.isValidUnitsString("Ohm"));
        assertTrue(service.isValidUnitsString("ug/m2"));
        assertTrue(service.isValidUnitsString("10*3/L"));
        assertTrue(service.isValidUnitsString("kg"));
        assertTrue(service.isValidUnitsString("m2/s"));
        assertTrue(service.isValidUnitsString("mL"));
        assertTrue(service.isValidUnitsString("mol/s"));
        assertTrue(service.isValidUnitsString("Ohm.m"));
        assertTrue(service.isValidUnitsString("ug/min"));
        assertTrue(service.isValidUnitsString("10*3/mL"));
        assertTrue(service.isValidUnitsString("fg"));
        assertTrue(service.isValidUnitsString("kg.m/s"));
        assertTrue(service.isValidUnitsString("m3/s"));
        assertTrue(service.isValidUnitsString("mL/(kg.d)"));
        assertTrue(service.isValidUnitsString("mosm/L"));
        assertTrue(service.isValidUnitsString("pg"));
        assertTrue(service.isValidUnitsString("ukat"));
        assertTrue(service.isValidUnitsString("10*3/mm3"));
        assertTrue(service.isValidUnitsString("fL"));
        assertTrue(service.isValidUnitsString("kg/(s.m2)"));
        assertTrue(service.isValidUnitsString("mbar"));
        assertTrue(service.isValidUnitsString("mL/(kg.min)"));
        assertTrue(service.isValidUnitsString("ms"));
        assertTrue(service.isValidUnitsString("pg/L"));
        assertTrue(service.isValidUnitsString("um"));
        assertTrue(service.isValidUnitsString("10*6/L"));
        assertTrue(service.isValidUnitsString("fmol"));
        assertTrue(service.isValidUnitsString("kg/L"));
        assertTrue(service.isValidUnitsString("mbar.s/L"));
        assertTrue(service.isValidUnitsString("mL/(min.m2)"));
        assertTrue(service.isValidUnitsString("mV"));
        assertTrue(service.isValidUnitsString("pg/mL"));
        assertTrue(service.isValidUnitsString("umol"));
        assertTrue(service.isValidUnitsString("10*6/mL"));
        assertTrue(service.isValidUnitsString("g"));
        assertTrue(service.isValidUnitsString("kg/m3"));
        assertTrue(service.isValidUnitsString("meq"));
        assertTrue(service.isValidUnitsString("mL/d"));
        assertTrue(service.isValidUnitsString("pkat"));
        assertTrue(service.isValidUnitsString("umol/d"));
        assertTrue(service.isValidUnitsString("10*6/mm3"));
        assertTrue(service.isValidUnitsString("g.m"));
        assertTrue(service.isValidUnitsString("kg/min"));
        assertTrue(service.isValidUnitsString("meq/(kg.d)"));
        assertTrue(service.isValidUnitsString("mL/kg"));
        assertTrue(service.isValidUnitsString("pm"));
        assertTrue(service.isValidUnitsString("umol/L"));
        assertTrue(service.isValidUnitsString("10*9/L"));
        assertTrue(service.isValidUnitsString("g/(kg.d)"));
        assertTrue(service.isValidUnitsString("kg/mol"));
        assertTrue(service.isValidUnitsString("meq/(kg.min)"));
        assertTrue(service.isValidUnitsString("mL/m2"));
        assertTrue(service.isValidUnitsString("ng"));
        assertTrue(service.isValidUnitsString("pmol"));
        assertTrue(service.isValidUnitsString("umol/min"));
        assertTrue(service.isValidUnitsString("10*9/mL"));
        assertTrue(service.isValidUnitsString("g/(kg.min)"));
        assertTrue(service.isValidUnitsString("kg/s"));
        assertTrue(service.isValidUnitsString("meq/d"));
        assertTrue(service.isValidUnitsString("mL/mbar"));
        assertTrue(service.isValidUnitsString("ng/(kg.d)"));
        assertTrue(service.isValidUnitsString("ps"));
        assertTrue(service.isValidUnitsString("us"));
        assertTrue(service.isValidUnitsString("10*9/mm3"));
        assertTrue(service.isValidUnitsString("g/d"));
        assertTrue(service.isValidUnitsString("kPa"));
        assertTrue(service.isValidUnitsString("meq/kg"));
        assertTrue(service.isValidUnitsString("mL/min"));
        assertTrue(service.isValidUnitsString("ng/(kg.min)"));
        assertTrue(service.isValidUnitsString("pt"));
        assertTrue(service.isValidUnitsString("uV"));
        assertTrue(service.isValidUnitsString("10.L/min"));
        assertTrue(service.isValidUnitsString("g/dL"));
        assertTrue(service.isValidUnitsString("ks"));
        assertTrue(service.isValidUnitsString("meq/L"));
        assertTrue(service.isValidUnitsString("mL/s"));
        assertTrue(service.isValidUnitsString("ng/d"));
        assertTrue(service.isValidUnitsString("Sv"));
        assertTrue(service.isValidUnitsString("V"));
        assertTrue(service.isValidUnitsString("a/m"));
        assertTrue(service.isValidUnitsString("g/kg"));
        assertTrue(service.isValidUnitsString("L"));
        assertTrue(service.isValidUnitsString("meq/min"));
        assertTrue(service.isValidUnitsString("mm"));
        assertTrue(service.isValidUnitsString("ng/kg"));
        assertTrue(service.isValidUnitsString("t"));
        assertTrue(service.isValidUnitsString("Wb"));
    }

    private MeasurementService service;	
}
