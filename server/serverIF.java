import java.rmi.*;
import java.util.*;
public interface serverIF extends Remote {
	public void getCalendar(String name) throws RemoteException;
	
	public String ViewListCalendars() throws RemoteException;
	public String ViewEvents(String name) throws RemoteException;
    public String ViewOthersEvents(String name) throws RemoteException;
    public String ViewAllEvents() throws RemoteException;
	public String addEvent(String[] eventinfo, String name) throws RemoteException;
	public String deleteEvent(String eventinfo) throws RemoteException;
	
	public String addOnlineUser(String Name) throws RemoteException;
	public void removeOnlineUsers(String Name) throws RemoteException;
	public String viewOnlineUsers() throws RemoteException;
	
}
