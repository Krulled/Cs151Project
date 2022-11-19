package simpleCalendar;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Fall 2022: CS 151 Programming Assignment 5 (Simple GUI Calendar) Solution
 * @author Jasmine Lao
 * @version 1.0 11/7/2022
 */

public class CalendarModel {
	
	ArrayList<EventsInDates> allEventsPerDate; //Data structure that holds events per date
	ArrayList<ChangeListener> listeners; //Data structure that holds views
	static boolean savingEventStatus;
	
	public CalendarModel() {
		this.allEventsPerDate = new ArrayList<EventsInDates>();
		this.listeners = new ArrayList<ChangeListener>();
	}
	
	/**
	 * Adds an event to the events array list.
	 * Serves as MUTATOR.
	 * @param event, event to be added
	 */
	public void addEventsInDates(EventsInDates eventList) {
		allEventsPerDate.add(eventList);
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener listener : listeners) {
			listener.stateChanged(e);
		}
	}
	
	/**
	 * Returns an ArrayList consisting of all events.
	 * Serves as ACCESSOR.
	 * @return ArrayList<Events>, an ArrayList of Events
	 */
	public ArrayList<EventsInDates> getEvents() {
		return allEventsPerDate;
	}
	
	/**
	 * Adds change listener to listeners array list.
	 * Serves as ATTACHER.
	 * @param listener, listener to be added to ArrayList
	 */
	public void attach(ChangeListener listener) {
		listeners.add(listener);
	}
	
	public boolean saveEvent(String title, String date, String start, String end) {
		savingEventStatus = true;
		
		try {	
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

			LocalDate dateInLocalDate = LocalDate.parse(date, dateFormatter);
			LocalTime startInLocalTime = LocalTime.parse(start, timeFormatter);
			LocalTime endInLocalTime = LocalTime.parse(end, timeFormatter);
		
			Event newEvent = new Event();
			newEvent.setName(title);
			newEvent.setDate(dateInLocalDate);
			newEvent.setStartDate(startInLocalTime);
			newEvent.setEndDate(endInLocalTime);
			
			//check for conflicts within events created
			for(EventsInDates list : allEventsPerDate) {
				for(Event e : list.getDatesEvents()) {
					if(e.checkForConflict(newEvent) == true) {
						return false;
					}
					if(e.equals(newEvent)) {
						return false;
					}
				}
			}
		
			//check if date with list of events already exists in allEventsPerDate
			boolean contains = false;
			for(EventsInDates list: allEventsPerDate) {
				if(list.getDate().equals(dateInLocalDate)) {
					contains = true;
				}
			}
						
			if(contains == false) { //if not, create new eventList for this date
				EventsInDates eventList = new EventsInDates(dateInLocalDate); //create new list to hold all events for date
				eventList.addEventsInDates(newEvent); //add newEvent to list
				this.addEventsInDates(eventList); //add newEvent to allEventsPerDate
			}
			else { //if eventList already exists, directly add newEvent to the list
				for(EventsInDates list: allEventsPerDate) {
					if(list.getDate().equals(dateInLocalDate)) {
						list.addEventsInDates(newEvent);
					}
				}
			}
		}
		catch(DateTimeParseException x) {
			savingEventStatus = false;
		}
		return true;
	}
	
	public String getDateAndEvents(Calendar calendar) {
		String result = "";
		String dayOfWeek = getDayOfWeekName(calendar.get(Calendar.DAY_OF_WEEK));
		int month = calendar.get(Calendar.MONTH)+1;
		int date = calendar.get(Calendar.DATE);
		int year = calendar.get(Calendar.YEAR);
		result += dayOfWeek + " " + month + "/" + date + "/" + year + "\n	";
		
	
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
		String dateInString = month + "/" + date + "/" + year;
		LocalDate dateInLocalDate = LocalDate.parse(dateInString, dateFormatter);
		EventsInDates listOfEventsForDate = new EventsInDates(dateInLocalDate);
		for(EventsInDates a : allEventsPerDate) {
			if(a.getDate().equals(dateInLocalDate)) {
				listOfEventsForDate = a;
			}
		}
		for(Event e : listOfEventsForDate.getDatesEvents()) {
			if(!result.contains(e.toString())) {
				result += e.toString() + "	";
			}
		}
		return result;
	}
	
	public String getDayOfWeekName(int value) {
		String name = "";
		 if (value == 1) {
			name = "SUNDAY";
		}
		 else if (value == 2) {
			name = "MONDAY";
		} 
		else if (value == 3) {
			name = "TUESDAY";
		} 
		else if (value == 4) {
			name = "WEDNESDAY";
		} 
		else if (value == 5) {
			name = "THURSDAY";
		} 
		else if (value == 6) {
			name = "FRIDAY";
		} 
		else if (value == 7) {
			name = "SATURDAY";
		} 
		return name;
	}
	
	public void loadEvents() {
		//should use deserialization
	}
}

