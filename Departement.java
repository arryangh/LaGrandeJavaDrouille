import java.util.UUID;
import java.util.TreeMap;

public class Departement {

	public Departement(int iddepartement,String departementname){
		idDepartement=iddepartement;
		departementName=departementname;
		employeeMap=new TreeMap<UUID, Employee>();
	}

	private int idDepartement;

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

	public int getId() {
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

	public void deleteEmployee(UUID idDelete) {
		if (employeeMap == null || idDelete == null) {
			return;
		}
		Employee temp = employeeMap.get(idDelete);
		employeeMap.remove(idDelete);
		delete (temp);
	}

}
