import java.time.LocalDate; // import the LocalDate class
import java.time.LocalTime; // import the LocalDate class



public class Pointing {

	public Pointing(PointingType type) {
		pointingType = type;
		date = LocalDate.now();
		hour = LocalTime.now();
	}

	protected LocalDate date;

	protected LocalTime hour;

	protected PointingType pointingType;

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getHour() {
		return hour;
	}

	public PointingType getType() {
		return pointingType;
	}
}
