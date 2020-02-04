package gui;
import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private String name;
	private String occupation;
	private int ageCategory;
	private String empCat;
	private String taxId;
	private boolean itCitzen;
	private String gender;

	public FormEvent(Object arg0) {
		super(arg0);
		
	}
	
	public FormEvent(Object arg0, String name, String occupation, int ageCat, 
			String empCat, String taxId, boolean itCitzen, String gender) {
		super(arg0);
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCat;
		this.empCat = empCat;
		this.taxId = taxId;
		this.itCitzen = itCitzen;
		this.gender = gender;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public int getAgeCategory() {
		return this.ageCategory;
	}
	
	public String getEmploymentCategory() {
		return this.empCat;
	}

	public String getTaxId() {
		return taxId;
	}

	public boolean isItCitzen() {
		return itCitzen;
	}

	public String getGender() {
		return gender;
	}
	
	
	
	

}
