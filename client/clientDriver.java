
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class clientDriver 
{
    public static void main(String argv[])
    {
        // Validate command line parameters
        if (argv.length < 1) {
	    System.out.println("Usage: java parameter name needed ");
	    System.exit(1);
	}      

        String name = argv[0];
     
        // Install security manager.  This is only necessary
        // if the remote object's client stub does not reside
        // on the client machine (it resides on the server).
        //  System.setSecurityManager(new SecurityManager());

        // Get a remote reference to the EchoImpl class
        String strName = "rmi://localhost/rmiserver";
//      String strName = "rmi://localhost:2934/EchoService";
        System.out.println("Client: Looking up " + strName + "...");
        serverIF server = null;
	
        try {
	    server = (serverIF)Naming.lookup(strName);
        }
        catch (Exception e) {
	    
	    System.out.println("Client: Exception thrown looking up " + e + " "+ strName);
	    System.exit(1);
        }

		
	try {
        //2 scanners.. oen for ints and another for strings
		Scanner scanner = new Scanner(System.in);
		Scanner tempScanner = new Scanner(System.in); 
		String info="";
		String response=""; //will be used to print out return strings from calendarmanager
		//if user already has session open, he is not allowed to open another
		//else adds user to current online user 
		String online = server.addOnlineUser(name);
		String error = "Error, Users already has session opened. Program will exit now.";
		if(online.equals(error)){
			System.out.println(error);
				return; 
			}
		int choice;         //tracks user's option choice
		boolean control=true;  
		server.getCalendar(name); //either creates or selects a calendar for new or returning user
		while(control==true){
			//menu
			System.out.println("Menu: ");
			System.out.println("0 - view current online users");
			System.out.println("1 - view list of people with calendars in the system");
			System.out.println("2 - view all my events");
			System.out.println("3 - view Someone else's events");
            System.out.println("4 - view all group events");
			System.out.println("5 - add an event");
			System.out.println("6 - delete an event");
			System.out.println("7 - exit");
			System.out.print("enter the number of one of the options: ");
            System.out.println(" ");
			choice = scanner.nextInt();
			
			//logic/code for options of menu
			
			if(choice == 0){
				 System.out.println(server.viewOnlineUsers());
			}
            // option to view list of plp with calendars
			else if(choice==1){
	    		String list = server.ViewListCalendars();
	    		System.out.println("\nlist of Calendar Owners: \n"+ list);
			}
            //view my events
			else if(choice==2){
				response = server.ViewEvents(name);
				System.out.println(response);
			}
            //view someone else's events
			else if(choice==3){
				System.out.println("Enter the name of the person whos events you want to see: ");
				info = tempScanner.nextLine();
				response = server.ViewOthersEvents(info);
				System.out.println(response);
			}
            else if(choice==4){
                System.out.println("list of all group events: ");
                //info = tempScanner.nextLine();
                response = server.ViewAllEvents();
                System.out.println(response);
            }
            
            //add event to my calendar
			else if(choice==5){
	    		String[] eventinfo = new String[5];
	    		System.out.print("Access Control of event (Private, Public, Group): ");
	    		info = tempScanner.nextLine();
				eventinfo[0]=info;
	    		System.out.println("Please enter an hour block - ex: 10-11"); 
	    		info = tempScanner.nextLine();
				eventinfo[1]=info;
	    		System.out.println("AM or PM?");
	    		info = tempScanner.nextLine();
	    		eventinfo[2]=info;
	    		System.out.println("event date? format Month/day/year ex: 04/10/2016? ");
	    		info = tempScanner.nextLine();
	    		eventinfo[3]=info;
	    		System.out.println("Decription of event: ");
	    		info = tempScanner.nextLine();
	    		eventinfo[4]=info;
	    		
				response = server.addEvent(eventinfo, name);
				System.out.println(response);
			}
            //
			else if(choice == 6){
				System.out.println("Enter Time block You want to free up. ex: 10-11 ");
				info = tempScanner.nextLine();
				response = server.deleteEvent(info);
				System.out.println(response);
			}
				
			else if(choice==7) control=false;
		}
		
		//remove user from current online users. 
		server.removeOnlineUsers(name);
		
	    System.out.println("\nyou have exited calendar program");   
	}
	catch (Exception e) {  
	    System.out.println("Client: Exception thrown calling EchoMessage().");
	    System.exit(1);
	}
    }
           
}



