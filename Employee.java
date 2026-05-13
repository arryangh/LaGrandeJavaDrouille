import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class Employee {

	private UUID idEmployee;

	private String employeeName;

	private float stockHours;

	private List<DaySchedule> schedule;

	private boolean working;

	private List<Pointing> pointingList;


	public Employee(String name){
		idEmployee = UUID.randomUUID();
		stockHours = 0;
		employeeName = name;
		working = false;
		schedule = new ArrayList<>();
		pointingList = new ArrayList<>();
	}





	public void pointing() {
		if (working==true){
			Pointing new_pointing = new Pointing(idEmployee,PointingType.CHECK_OUT);
			pointingList.add(new_pointing);
			List<LocalTime> schedule = getTodaySchedule(LocalDate.now().getDayOfWeek());
			Duration duration = Duration.between(new_pointing.getHour(), schedule.get(1));
			stockHours+=duration.toHours();

		}
		else {
			Pointing new_pointing = new Pointing(idEmployee,PointingType.CHECK_IN);
			pointingList.add(new_pointing);
			List<LocalTime> schedule = getTodaySchedule(LocalDate.now().getDayOfWeek());
			Duration duration = Duration.between(new_pointing.getHour(), schedule.get(0));
			stockHours+=duration.toHours();

		}
		setWorking();

	}

	@Override
	public String toString() {
		return "Employee{" +
				"employeeID=" + idEmployee +
				", Name=" + employeeName +
				", stock hour=" + stockHours +
				", is working=" + working +
				'}';
	}

	public UUID getId() {
		return idEmployee;
	}

	public String getName() {
		return employeeName;
	}

	public void setName(String Name) {
		employeeName = Name;
	}

	public float getStock() {
		return stockHours;
	}

	public void addStock(float nbMin) {
		stockHours+=nbMin;
	}

	public List<LocalTime> getTodaySchedule(DayOfWeek day) {
		List<LocalTime> todaySchedule = new ArrayList<>();
		todaySchedule.add(0,schedule.get(day.getValue()).getTheoricalStart());
		todaySchedule.add(1,schedule.get(day.getValue()).getTheoricalEnd());
		return todaySchedule;
	}

	public void setSchedule(TreeMap<DayOfWeek,List<LocalTime>> newSchedule) {
		for (DayOfWeek i : newSchedule.keySet()) {
			schedule.add(new DaySchedule(i, newSchedule.get(i).get(0), newSchedule.get(i).get(1)));
		}
	}

	public boolean isWorking() {
		return working;
	}

	private void setWorking() {
		working = (working == false);
	}

}
