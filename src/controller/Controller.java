package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import gui.FormEvent;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	
	Database db = new Database();
	
	public List<Person> getPeople() {
		
		return db.getPeople();
		
	}
	
	public void addPerson(FormEvent ev) {
		
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCatId = ev.getAgeCategory();
		String empCat = ev.getEmploymentCategory();
		boolean isIt = ev.isItCitzen();
		String taxId = ev.getTaxId();
		String gender = ev.getGender();
		
		AgeCategory ageCategory;	
		switch(ageCatId) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		default:
			ageCategory = AgeCategory.adult;
		}
		
		EmploymentCategory empCategory;
		if(empCat.equals("Occupato")) {
			empCategory = EmploymentCategory.employed;
		} else if(empCat.equals("Lavoratore autonomo")) {
			empCategory = EmploymentCategory.selfEmploed;
		} else if(empCat.equals("Disoccupato")) {
			empCategory = EmploymentCategory.unemployed;
		} else {
			empCategory = EmploymentCategory.other;
		}
		
		Gender genderCat;
		if(gender.equals("maschio")) {
			genderCat = Gender.maschio;
		} else  {
			genderCat = Gender.femmina;
		}
		
		Person person = new Person(name, occupation, ageCategory, empCategory, taxId, isIt, genderCat);
		db.addPerson(person);
		
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFronFile(file);
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}

}
