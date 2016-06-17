package org.openehr.rm.support.measurement;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** This is a very simple UCUM validator to validate if a given units string is a valid UCUM unit. 
 * It may not consider all edge cases, although it passes many test cases, including those provided by UCUM.
 * See http://unitsofmeasure.org/ucum.html
 * @author Sebastian Garde
 *
 */
public class SimpleUCUMValidator {

    // These can be easily extracted from the ucum-esence.xml file provided by UCUM (or directly read from the xml file)
    public static final String[] ATOMSYMBOL_VALUES = new String[] { 
            "m", "s", "g", "rad", "K", "C", "cd", "10*", "10^", "[pi]", "%", "[ppth]", "[ppm]", "[ppb]", "[pptr]", "mol", "sr", "Hz", "N", "Pa", 
            "J", "W", "A", "V", "F", "Ohm", "S", "Wb", "Cel", "T", "H", "lm", "lx", "Bq", "Gy", "Sv", "gon", "deg", "'", "''", "l", "L", "ar", 
            "min", "h", "d", "a_t", "a_j", "a_g", "a", "wk", "mo_s", "mo_j", "mo_g", "mo", "t", "bar", "u", "eV", "AU", "pc", "[c]", "[h]", 
            "[k]", "[eps_0]", "[mu_0]", "[e]", "[m_e]", "[m_p]", "[G]", "[g]", "atm", "[ly]", "gf", "[lbf_av]", "Ky", "Gal", "dyn", "erg", "P", 
            "Bi", "St", "Mx", "G", "Oe", "Gb", "sb", "Lmb", "ph", "Ci", "R", "RAD", "REM", "[in_i]", "[ft_i]", "[yd_i]", "[mi_i]", "[fth_i]", 
            "[nmi_i]", "[kn_i]", "[sin_i]", "[sft_i]", "[syd_i]", "[cin_i]", "[cft_i]", "[cyd_i]", "[bf_i]", "[cr_i]", "[mil_i]", "[cml_i]", "[hd_i]", 
            "[ft_us]", "[yd_us]", "[in_us]", "[rd_us]", "[ch_us]", "[lk_us]", "[rch_us]", "[rlk_us]", "[fth_us]", "[fur_us]", "[mi_us]", 
            "[acr_us]", "[srd_us]", "[smi_us]", "[sct]", "[twp]", "[mil_us]", "[in_br]", "[ft_br]", "[rd_br]", "[ch_br]", "[lk_br]", "[fth_br]", 
            "[pc_br]", "[yd_br]", "[mi_br]", "[nmi_br]", "[kn_br]", "[acr_br]", "[gal_us]", "[bbl_us]", "[qt_us]", "[pt_us]", "[gil_us]", 
            "[foz_us]", "[fdr_us]", "[min_us]", "[crd_us]", "[bu_us]", "[gal_wi]", "[pk_us]", "[dqt_us]", "[dpt_us]", "[tbs_us]", "[tsp_us]", 
            "[cup_us]", "[foz_m]", "[cup_m]", "[tsp_m]", "[tbs_m]", "[gal_br]", "[pk_br]", "[bu_br]", "[qt_br]", "[pt_br]", "[gil_br]", 
            "[foz_br]", "[fdr_br]", "[min_br]", "[gr]", "[lb_av]", "[oz_av]", "[dr_av]", "[scwt_av]", "[lcwt_av]", "[ston_av]", "[lton_av]", 
            "[stone_av]", "[pwt_tr]", "[oz_tr]", "[lb_tr]", "[sc_ap]", "[dr_ap]", "[oz_ap]", "[lb_ap]", "[oz_m]", "[lne]", "[pnt]", "[pca]", 
            "[pnt_pr]", "[pca_pr]", "[pied]", "[pouce]", "[ligne]", "[didot]", "[cicero]", "[degF]", "[degR]", "[degRe]", "cal_[15]", 
            "cal_[20]", "cal_m", "cal_IT", "cal_th", "cal", "[Cal]", "[Btu_39]", "[Btu_59]", "[Btu_60]", "[Btu_m]", "[Btu_IT]", "[Btu_th]", 
            "[Btu]", "[HP]", "tex", "[den]", "m[H2O]", "m[Hg]", "[in_i'H2O]", "[in_i'Hg]", "[PRU]", "[wood'U]", "[diop]", "[p'diop]", 
            "%[slope]", "[mesh_i]", "[Ch]", "[drp]", "[hnsf'U]", "[MET]", "[hp'_X]", "[hp'_C]", "[hp'_M]", "[hp'_Q]", "[hp_X]", "[hp_C]", 
            "[hp_M]", "[hp_Q]", "[kp_X]", "[kp_C]", "[kp_M]", "[kp_Q]", "eq", "osm", "[pH]", "g%", "[S]", "[HPF]", "[LPF]", "kat", "U", 
            "[iU]", "[IU]", "[arb'U]", "[USP'U]", "[GPL'U]", "[MPL'U]", "[APL'U]", "[beth'U]", "[anti'Xa'U]", "[todd'U]", "[dye'U]", 
            "[smgy'U]", "[bdsk'U]", "[ka'U]", "[knk'U]", "[mclg'U]", "[tb'U]", "[CCID_50]", "[TCID_50]", "[EID_50]", "[PFU]", "[FFU]", 
            "[CFU]", "[IR]", "[BAU]", "[AU]", "[Amb'a'1'U]", "[PNU]", "[Lf]", "[D'ag'U]", "[FEU]", "[ELU]", "[EU]", "Np", "B", "B[SPL]", 
            "B[V]", "B[mV]", "B[uV]", "B[10.nV]", "B[W]", "B[kW]", "st", "Ao", "b", "att", "mho", "[psi]", "circ", "sph", "[car_m]", 
            "[car_Au]", "[smoot]", "[m/s2/Hz^(1/2)]", "bit_s", "bit", "By", "Bd" };

    public static final Set<String> ATOMSYMBOL_SET = new HashSet<String>(Arrays.asList(ATOMSYMBOL_VALUES));    

    public static final String[] PREFIX_VALUES = new String[] { "Y", "Z", "E", "P", "T", "G", "M", "k", "h", "da", "d", "c", "m", "u", "n", "p", "f", "a", "z", "y", "Gi", "Mi", "Gi", "Ti"};
    public static final Set<String> PREFIX_SET = new HashSet<String>(Arrays.asList(PREFIX_VALUES));    


    public SimpleUCUMValidator() {

    }

    public boolean isValidUnitsString(String units) {
        // System.out.print("Unit to test: "+ units + " .... ");
        boolean isValid = isMainTerm(units);
        // System.out.println(isValid ? "Valid" : " NOT VALID");
        return isValid;
    }

    private boolean isMainTerm(String units) {
        return units.length()==0 || // empty units is allowed according to the official UCUM test cases (however currently prevented in the specs)
                  units.equals(" ") || // This is one case where we currently tolerate a unit that only consists of exactly one space (to circumvent the specs constraints that units must not be empty)
                  isTerm(units) || 
                  (units.startsWith("/") && isTerm(units.substring(1)));
    }

    private boolean isTerm(String units) {

        boolean roundBracketsOk = checkRoundBrackets(units); // check for basic consistency, then remove.
        if (!roundBracketsOk) {
            return false;
        }

        // After the basic checks above we cannot properly handle the (  ) constructs in the following. 
        units = units.replace("(", "").replace(")", "");

        if (units.startsWith(("/"))) {
            units= units.substring(1); // e.g. "/min" is allowed
        }
        if (units.endsWith("/") || units.endsWith(".") || units.endsWith("/)") || units.endsWith(".)")) {
            return false; // min/ or min. are not valid
        } 
        String[] components = units.split("/|\\."); // either / or .

        for (String component : components) {
            if (!isComponent(component)) {
                return false;
            }
        }
        return true; 
    }

    private boolean checkRoundBrackets(String units) {
        String unitsT = units;
        int openingBrackets = unitsT.startsWith("(") ? 1:0; // first letter not considered in forst loop below
        int closingBrackets = unitsT.endsWith(")") ? 1:0; // last letter not considered in second loop below
        for (int i=1; i<unitsT.length(); i++) {
            if (unitsT.charAt(i) == '(' ) {
                openingBrackets++;
                if (unitsT.charAt(i-1) != '.'  && unitsT.charAt(i-1) != '/') {
                    return false;
                } // e.g. ug(8.h) is not allowed, must enclose a full term 
            }
        }


        for (int i=0; i<unitsT.length()-1; i++) {
            if (unitsT.charAt(i) == ')' ) {
                closingBrackets ++;
                if (unitsT.charAt(i+1) != '.'  && unitsT.charAt(i+1) != '/') {
                    return false;
                }  
            }
        }

        return openingBrackets == closingBrackets;
    }

    private boolean isComponent(String component) {

        boolean isComponent = isAnnotatable(component) ||
                isAnnotation(component) ||
                isFactor(component);

        if (!isComponent) {
            // WE must still consider: must still consider <annotatable><annotation>
            if (component.endsWith("}") && component.indexOf("{") >0) { // = ends with annotation
                // check if beginning is an "annotatable"
                isComponent = isAnnotatable(component.substring(0, component.indexOf("{"))) &&
                        isAnnotation(component.substring(component.indexOf("{"))); // Required because of ASCII-only check
            }
        }

        return isComponent;
    }

    private boolean isFactor(String component) {
        try {
            Integer.parseInt(component);
        } catch (NumberFormatException e) {
            return false;
        } 
        return true;
    }

    private boolean isAnnotation(String component) {
        //System.out.print("Annotation: "+component);
        boolean isAnnotation =  component.startsWith("{") && component.endsWith("}") && isPureAscii(component.substring(1, component.length()-1));

        if (isAnnotation) {
            String compExclCurly = component.substring(1, component.length()-1);
            isAnnotation = !compExclCurly.contains("{") && !compExclCurly.contains("}"); // cannot have curtly braces in the annotation - this prevents e.g. {a}rad2{b} to be seen as valid 
        }
        return isAnnotation;
    }

    private boolean isAnnotatable(String component) {
        if (component.equals("1")) {
            return true; // It is not quite clear why e.g. 1{c} is supported but the 1 seems to be somewhat treated as a unit. 
        }

        // Other than that: simple-unit + exponent or simple-unit

        //must first check if it ends with an exponent = (optional sign + digits)
        if (component.length() >0) {
            boolean hadDigit=false;
            while (component.length() >0 && isDigit(component.substring(component.length()-1))) {
                component = component.substring(0, component.length()-1);
                hadDigit=true;
            }
            if (hadDigit && component.length() >0 &&  isSign(component.substring(component.length()-1))) {
                component = component.substring(0, component.length()-1);
            }
        }
        // We removed the exponent including the sign, if present, now it must be a simple unit if valid.
        return isSimpleUnit(component);
    }

    boolean isSign(String str) {
        return str.equals("+") || str.equals("-");
    }

    boolean isDigit(String str) {
        return isFactor(str);
    }

    private boolean isSimpleUnit(String component) {
        //System.out.println("isSimpleUnit: " +"___"+ component+"___");
        return isAtomSymbol(component) ||
                (component.length() >=1 && isPrefixSymbol(component.substring(0,1)) && isAtomSymbol(component.substring(1))) || // prefix with one character 
                (component.length()>=2 && isPrefixSymbol(component.substring(0,2)) && isAtomSymbol(component.substring(2))); // prefix with two characters
    }

    private boolean isAtomSymbol(String component) {
        return ATOMSYMBOL_SET.contains(component);
    }

    private boolean isPrefixSymbol(String ch) {
        return PREFIX_SET.contains(ch);
    }

    static CharsetEncoder asciiEncoder = Charset.forName("US-ASCII").newEncoder(); // or "ISO-8859-1" for ISO Latin 1
    private boolean isPureAscii(String v) {
        return asciiEncoder.canEncode(v);
    }

}
