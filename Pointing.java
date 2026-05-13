import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Pointing implements Serializable {

	//Pour la sérialization
	private static final long serialVersionUID = 1L;

	private UUID idPointing;
	private UUID employeeId;

	private LocalDate date;
	private LocalTime hour;
	private PointingType pointingType;

	public Pointing(UUID employeeId, PointingType type) {
		this.idPointing = UUID.randomUUID();
		this.employeeId = employeeId;
		this.date = LocalDate.now();
		this.hour = roundToQuarter(LocalTime.now());
		this.pointingType = type;
	}

	private LocalTime roundToQuarter(LocalTime time) {
		int minutes = time.getMinute();
		int rounded = (int)(Math.round(minutes / 15.0)) * 15;
		return time.withMinute(0).withSecond(0).withNano(0)
				.plusMinutes(rounded % 60)
				.plusHours(rounded / 60);
	}

	// Getters
	public UUID getIdPointing() { return idPointing; }
	public UUID getEmployeeId() { return employeeId; }
	public LocalDate getDate() { return date; }
	public LocalTime getHour() { return hour; }
	public PointingType getType() { return pointingType; }

	@Override
	public String toString() {
		return "Pointing{" +
				"employee=" + employeeId +
				", date=" + date +
				", hour=" + hour +
				", type=" + pointingType +
				'}';
	}
}