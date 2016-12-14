// Rodrigo Sejas Jaldin
// Handles events. creates and deletes events for a calendar.

import java.util.*;
public class Calendar {
	
	ArrayList<Event> events;
	String owner;
	int ownerID;
	
	public Calendar(String name){	
		owner = name;
		events = new ArrayList<Event>();
	}
    //checks to see if there are any conflicts with another event.
	boolean canEventBeAdded(Event newEvent){
		for(int i=0;i<events.size();i++){
			Event temp = events.get(i);
            if(temp.year==newEvent.year && temp.month==newEvent.month&& temp.day==newEvent.day){
            	if(temp.startTime==newEvent.startTime&&temp.endTime==newEvent.endTime){
            		return false; //exact same time block 
            	}
            	//if event is within another event - not implemented
            }
		}
		return true;
	}
	//add an event into th calendar
	String addEvent(String[] eventinfo){
		String access=eventinfo[0];
		String timeBlock = eventinfo[1];
		String timeday = eventinfo[2];
		String date = eventinfo[3];
		String description = eventinfo[4];
		
		String[] split = timeBlock.split("-");
		int starttime = Integer.parseInt(split[0]);
        int endtime = Integer.parseInt(split[1]);
        
        split = date.split("/");
        int day = Integer.parseInt(split[1]);
        int month = Integer.parseInt(split[0]);
        int year = Integer.parseInt(split[2]);
		
        //grabs all the info from user user and creates an event object and adds it to events array
		Event newEvent = new Event(access,starttime,endtime,month,
				day,year,description);
		
		boolean canBeAdded = canEventBeAdded(newEvent);
		if(canBeAdded == false)
			return "Event already exits at given time block, delete first to add this new event";
		
		events.add(newEvent);
		return "event has been added";
	}
	//function that handles deleting an event
	String deleteEvent(String timeblock){
		String[] split = timeblock.split("-");
		int starttime = Integer.parseInt(split[0]);
        int endtime = Integer.parseInt(split[1]);
        int indexToBeDel=-1;
		for(int i=0;i<events.size();i++){
			Event temp = events.get(i);
			if (temp.startTime==starttime && temp.endTime==endtime)
				indexToBeDel=i;
				break;
		}
		if(indexToBeDel==-1)
				return "error, could not be deleted. Event at time block may not exist";
		events.remove(indexToBeDel);
		return "Event has been deleted";
	}
	// function that handles view all events in calendar
	String viewEvents(String control){
		StringBuilder sb = new StringBuilder();
        
        //if this is true, user is accessing another persons events. That means user can only
        //see other users public events
        if(control.equals("public")){
            for(int i=0;i<this.events.size();i++){
                Event temp = this.events.get(i);
                if(temp.access.equals("public")||temp.access.equals("Public")){
                    sb.append("----EVENT NUMBER: "+i+"----\n");
                    sb.append("access: "+temp.access+"\n");
                    sb.append("Start Time: "+temp.startTime+" End Time: "+temp.endTime+"\n");
                    sb.append("Month: "+temp.month+" Day: "+temp.day+ "Year: "+temp.year+"\n");
                    sb.append("access: "+temp.access+"\n");
                    sb.append("Description: "+temp.description+"\n");
                }
            }
        }
        //this means, user is accessing its own calendar so he can see his own public and private events.
        else{
            for(int i=0;i<this.events.size();i++){
                Event temp = this.events.get(i);
                sb.append("----EVENT NUMBER: "+i+"----\n");
                sb.append("access: "+temp.access+"\n");
                sb.append("Start Time: "+temp.startTime+" End Time: "+temp.endTime+"\n");
                sb.append("Month: "+temp.month+" Day: "+temp.day+ "Year: "+temp.year+"\n");
                sb.append("access: "+temp.access+"\n");
                sb.append("Description: "+temp.description+"\n");
               }

        }
		String result = sb.toString();
		return result;
	}
    // function that handles view all events in calendar
    // function can be combined with above function.
    String viewGroupEvents(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<this.events.size();i++){
            Event temp = this.events.get(i);
            if(temp.access.equals("group")||temp.access.equals("Group")){   //makes sure to only include group events
                sb.append("----EVENT NUMBER: "+i+"----\n");
                sb.append("access: "+temp.access+"\n");
                sb.append("Start Time: "+temp.startTime+" End Time: "+temp.endTime+"\n");
                sb.append("Month: "+temp.month+" Day: "+temp.day+ "Year: "+temp.year+"\n");
                sb.append("access: "+temp.access+"\n");
                sb.append("Description: "+temp.description+"\n");
            }
        }
        String result = sb.toString();
        return result;
    }
    
	
	
	public static void main(String[] args) {
		Calendar temp = new Calendar("rob");
		//temp.addEvent();

	}
	
//------- class that encapsulates all info. of an event for that calendar.
	class Event{
		String access;
		int startTime;
		int endTime;
		int month;
		int day;
		int year;
		String description;
		public Event(){
		}
		public Event(String access, int startTime, int endTime, int month,
				int day, int year, String description){
			this.access=access;
			this.startTime=startTime;
			this.endTime=endTime;
			this.month=month;
			this.day=day;
			this.year=year;
			this.description=description;
		}
		
	}

}
