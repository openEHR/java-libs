package org.openehr.rm.datatypes.basic;

public enum ReferenceModelName {
	
	DV_BOOLEAN("DV_BOOLEAN"),
	DV_COUNT("DV_COUNT"),
	DV_QUANTITY("DV_QUANTITY"),
	DV_PROPORTION("DV_PROPORTION"),
	DV_TEXT("DV_TEXT"),
	DV_CODED_TEXT("DV_CODED_TEXT"),
	DV_ORDINAL("DV_ORDINAL"),
	CODE_PHRASE("CODE_PHRASE"),
	DV_DATE_TIME("DV_DATE_TIME"),
	DV_DURATION("DV_DURATION");
	
	
	private final String name;
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	ReferenceModelName(String name) {
		this.name = name;
	}
}
