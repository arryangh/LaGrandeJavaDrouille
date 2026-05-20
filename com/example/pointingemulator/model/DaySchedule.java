package com.example.pointingemulator.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.io.Serializable;

public class DaySchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	public DaySchedule(DayOfWeek dayofweek,LocalTime theoricalstart,LocalTime theoricalend){
		 dayOfWeek=dayofweek;
		 theoricalStart=theoricalstart;
		 theoricalEnd=theoricalend;
	}
	protected DayOfWeek dayOfWeek;

	protected LocalTime theoricalStart;

	protected LocalTime theoricalEnd;



	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public LocalTime getTheoricalStart() {
		return theoricalStart;
	}

	public LocalTime getTheoricalEnd() {
		return theoricalEnd;
	}

	public void setDayOfWeek(DayOfWeek dayofweek) {
		this.dayOfWeek = dayofweek;
	}

	public void setTheoricalStart(LocalTime newstart) {
		this.theoricalStart = newstart;
	}

	public void setTheoricalEnd(LocalTime newend) {
		this.theoricalEnd = newend;
	}
}
