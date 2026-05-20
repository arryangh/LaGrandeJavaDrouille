package com.example.pointingemulator.model;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Entreprise {

	private UUID idEntreprise;

	private String entrepriseName;

	private TreeMap<UUID, Departement> departementMap;


	public Entreprise(String Name) {
		idEntreprise = UUID.randomUUID();
		entrepriseName = Name;
		departementMap = new TreeMap<UUID, Departement>();
	}

	public UUID getDepartementID(String nom) {
		for (Map.Entry<UUID, Departement> entry : departementMap.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(nom)) {
				return entry.getKey();
			}
		}
		return null;
	}

	public void showAllEmployee() {
		departementMap.forEach((id, departement) -> {
			departement.showAllEmployee();
		});
	}

	public UUID getId() {
		return idEntreprise;
	}

	public String getName() {
		return entrepriseName;
	}

	public void setName(String Name) {
		entrepriseName = Name;
	}

	public void addDepartement(String Name) {
		Departement newdepartement = new Departement(Name);
		departementMap.put(newdepartement.getId(), newdepartement);
	}

	public void deleteDepartement(UUID id) {
		if (departementMap == null || id == null) {
			return;
		}

		Departement dept = departementMap.get(id);
		if (dept != null) {
			dept.deleteAllEmployee(); // remove all employees first
			departementMap.remove(id); // then remove the department
		}
	}

	public void addEmployee(String Name, String DepartName) {
		Employee newEmployee = new Employee(Name);
		UUID departID = getDepartementID(DepartName);
		departementMap.get(departID).addEmployee(newEmployee);
	}

	public void deleteEmployee(UUID id_delete) {
		if (id_delete == null) return;

		for (Departement dept : departementMap.values()) {
			if (dept.inDepartement(id_delete)) {
				dept.deleteEmployee(id_delete);
				return; // stop once found
			}
		}
	}

	public boolean employeeExist(String Name) {
		for (Departement dept : departementMap.values()) {
			if (dept.EmployeeExist(Name)) {
				return true;
			}
		}
		return false;
	}

	public boolean departementExist(String Name) {
		if (Name == null) return false;

		for (Departement dept : departementMap.values()) {
			if (dept.getName().equalsIgnoreCase(Name)) {
				return true;
			}
		}
		return false;
	}
}
