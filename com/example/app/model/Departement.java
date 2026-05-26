package com.example.app.model;

import com.example.pointingemulator.model.Employee;

import java.util.UUID;
import java.util.TreeMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Departement implements Serializable {

	private static final long serialVersionUID = 1L;

	public Departement(String departementname){
		idDepartement = UUID.randomUUID();;
		departementName=departementname;
		employeeMap=new TreeMap<UUID, Employee>();
	}

	private UUID idDepartement;

	private String departementName;

	private TreeMap<UUID, Employee> employeeMap;

	public boolean inDepartement(UUID id) {
		if (employeeMap != null) {
			return employeeMap.containsKey(id);
		}
		return false;
	}

	public void showAllEmployee() {
		employeeMap.forEach((id, employee) -> {
			System.out.println(id);
			System.out.println(employee);
		});
	}

	public UUID getId() {
		return idDepartement;
	}

	public String getName() {
		return departementName;
	}

	public void setName(String newname) {
		departementName=newname;
	}

	public void addEmployee(Employee param) {
		UUID id = param.getId();
		employeeMap.put(id, param);
	}

	public void deleteAllEmployee(){
		employeeMap.forEach((id, employee) -> {
			deleteEmployee(id);
		});
	}

	public void deleteEmployee(UUID idDelete) {
		if (employeeMap == null || idDelete == null) {
			return;
		}
		Employee temp = employeeMap.get(idDelete);
		employeeMap.remove(idDelete);
		temp = null;
	}

	public boolean EmployeeExist(String Name){
		for (Employee emp : employeeMap.values()) {
			if (emp.getName().equalsIgnoreCase(Name)) {
				return true;
			}
		}
		return false;
	}

	public Employee getEmployee(UUID id) {
		return employeeMap.get(id);
	}

	public List<Employee> getAllEmployees() {
		return new ArrayList<>(employeeMap.values());
	}

}