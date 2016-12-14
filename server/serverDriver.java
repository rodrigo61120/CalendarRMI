
import java.rmi.*;

   
public class serverDriver{            
    public static void main(String argv[])
    {
        try {
        
	    System.setSecurityManager(new SecurityManager());
	    
	
	   System.out.println("Server: Registering rmiserver Service");
	   CalendarManager remote = new CalendarManager();
	   Naming.rebind("rmiserver", remote);

//	   Replace with line below if rmiregistry is using port 2934	
//	   Naming.rebind("rmi://localhost:2934/EchoService", remote);

	   System.out.println("Server: Ready...");
        }
        catch (Exception e)
	    {
		System.out.println("Server: Failed to register rmiserver Service: " + e);
	    }
    }
}