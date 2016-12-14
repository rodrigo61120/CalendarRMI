// Rodrigo Sejas Jaldin
// java file that handles all requests from the users

import java.rmi.*;
import java.util.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class CalendarManager extends UnicastRemoteObject implements serverIF {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Calendar> calendarList; //keeps track off all calendars 
	private ArrayList<String> onlineUsers; //keeps track of whos currently online
	Calendar current;
	
	public CalendarManager() throws RemoteException {
		calendarList = new ArrayList<Calendar>();
		onlineUsers = new ArrayList<String>();
	}

	 //names of all calendars
	public String ViewListCalendars() throws RemoteException {
			String listNames="";
			for(int i=0;i<calendarList.size();i++){
				Calendar temp = calendarList.get(i);
				listNames = listNames.concat(temp.owner + "\n");
			}
			return listNames; //long string with all names
	}	

	//gets calendar for specific person. if persons doesn't have a calendar 
	//it creates one for him. if calendar exists for person, then selects correct calendar 
	public void getCalendar(String name) throws RemoteException {
		//check to see if calendar for this user already exists
		boolean exists = false;
		for(int i=0;i<calendarList.size();i++){
			Calendar temp = calendarList.get(i);
			String tempname=temp.owner;
			if(tempname.equals(name)==true){
				current = temp;
				exists=true;
				break;
			}
		}
        //person does not exist, create calendar for him
		if(exists==false){
			current = new Calendar(name);
			calendarList.add(current);
		}	
	}
	//all events in persons calendar
	public String ViewEvents(String name) throws RemoteException {
		Calendar temp;
		for(int i=0;i<calendarList.size();i++){
			temp=calendarList.get(i);
			if(name.equals(temp.owner)){
				return temp.viewEvents("both"); //will display both public and private events for this eperson
            }
        }
        return "There are no events to display";
    }
    //events in another persons calendar
    public String ViewOthersEvents(String name) throws RemoteException{
        Calendar temp;
        for(int i=0;i<calendarList.size();i++){
            temp=calendarList.get(i);
            if(name.equals(temp.owner)){
                return temp.viewEvents("public");
            }
        }
        return "name of person does not have a calendar";
    }
    //all group events
    public String ViewAllEvents() throws RemoteException{
        Calendar temp;
        StringBuilder sb = new StringBuilder();
        sb.append("~~~List of Group Events~~~\n");
        for(int i=0;i<calendarList.size();i++){
            temp=calendarList.get(i);
            sb.append(temp.viewGroupEvents());
        }
        String result = sb.toString();
        return result;
    }

    //add events
    public String addEvent(String[] eventinfo, String name) throws RemoteException {
        Calendar temp;
        String result="";
        for(int i=0;i<calendarList.size();i++){
            temp=calendarList.get(i);
            if(name.equals(temp.owner)){
                result = temp.addEvent(eventinfo);
            }
        }
        return result;
    }
    //delete events
	public String deleteEvent(String eventinfo) throws RemoteException{
		String result = current.deleteEvent(eventinfo);
		return result;
	}

	//online users add, remove, view
	public String addOnlineUser(String Name){
		for(int i=0;i<onlineUsers.size();i++){
			String temp = onlineUsers.get(i);
			if(temp.equals(Name))
				return "Error, Users already has session opened. Program will exit now.";
		}
		onlineUsers.add(Name);
		return "New user, session has started";
	}
	public void removeOnlineUsers(String Name){
		onlineUsers.remove(Name);
	}
	public String viewOnlineUsers(){
		StringBuilder sb = new StringBuilder();
		sb.append("---LIST OF ONLINE USERS--- \n");
		for(int i=0;i<onlineUsers.size();i++){
			String temp = onlineUsers.get(i);
			sb.append(temp+"\n");
		}
		String result=sb.toString();
		return result;
	}
	
}
