
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class WentworthAirDatabase {
	
	public static Connection WentworthAir;
	public static HashMap<String,String> UsersAndPassword = new HashMap<String,String>();
	public static HashMap<String,Plane> Planes = new HashMap<String,Plane>();
	
	
	
		    public static void main(String[] args) throws SQLException{
		    	
		    	WentworthAirDatabase WIT = new WentworthAirDatabase();
		    	MainWindow W = new MainWindow();
		    	W.setVisible(true);
		    	}
		    	
		    public WentworthAirDatabase(){
		    	LoadDriver();
		    	WentworthAir = GetConnection("houstonj1","W00209948");
		    	UsersAndPassword = UserInfo();
		    	Planes = FlightsInfo();
		    	System.out.println("Planes: " + Planes);

		    }
		    
		    
		    public static HashMap<String,Plane> FlightsInfo(){
		    	try {
					ResultSet QueryResults;
					QueryResults = WentworthAir.createStatement().executeQuery("select * from flight");
					Plane tempPlane;
					while(QueryResults.next()){
						tempPlane = new Plane();
						tempPlane.setOrigin(QueryResults.getObject("Origin"));
						tempPlane.setTimeOfDeparture(QueryResults.getObject("Flight_Date"));
						tempPlane.setDestination(QueryResults.getObject("Destination"));
						tempPlane.setTimeOfArrival(QueryResults.getObject("Arrival_Date"));
						tempPlane.setFlightNumber(QueryResults.getObject("Flight_No"));
			    		Planes.put((String) QueryResults.getObject("Flight_No"),tempPlane);
			    	}
					
			    	return Planes;
			    	
				} catch (SQLException e) {
					e.printStackTrace();					
					return null;

				}
		    }
		    
		    public static HashMap<String,String> UserInfo(){
		    	
				try {
					ResultSet QueryResults;
					QueryResults = WentworthAir.createStatement().executeQuery("select * from customer");
					while(QueryResults.next()){
			    		//System.out.println(QueryResults.getObject("Email"));
			    		//System.out.println(QueryResults.getObject("Password"));
			    		UsersAndPassword.put((String)QueryResults.getObject("Email"), (String)QueryResults.getObject("Password"));
			    	}
					
			    	return UsersAndPassword;
			    	
				} catch (SQLException e) {
					e.printStackTrace();					
					return null;

				}
		    }

		    public static void LoadDriver(){
		    	try
		    	{
		    		Class.forName("oracle.jdbc.driver.OracleDriver");

		    	} 
		    	catch (ClassNotFoundException cnf) 
		    	{
		    	System.out.println("Driver could not be loaded: " + cnf);
		    	}
		    }
		    
		    public static Connection GetConnection(String User,String Password){	
		    	Connection SQLConnection = null;
		    	System.out.println("Trying to connect...");
		        try {
		        	SQLConnection = DriverManager.getConnection("jdbc:oracle:thin:" + User + "/" + Password + "@ora.cs.wit.edu:1521:orcl");
		            System.out.println("Connected");
		            return SQLConnection;
		        } catch (SQLException ex) {
		            System.out.println("SQLException: " + ex.getMessage());
		            //getConnection(User,Password);
		            return null;
		        }	
		    	
		    }
	    
		    public void PrintQuery(String SQL,String Column) throws SQLException{
		    	ResultSet QueryResults = WentworthAir.createStatement().executeQuery(SQL);
		    	System.out.println(Column + ":");
		    	while(QueryResults.next()){
		    		System.out.println(QueryResults.getObject(Column));
		    	}
		    }

			public static Object[][] getPlaneData() {
				Object[][] PlaneArray = new Object[Planes.keySet().size()][5];
				
				int index = 0;
				for (String key : Planes.keySet())
				{
				    Plane P = Planes.get(key);
				    PlaneArray[index][0] = Planes.get(key).getFlightNumber(); //k
				    PlaneArray[index][1] = Planes.get(key).getTimeOfDeparture(); //dep date
				    PlaneArray[index][2] = Planes.get(key).getTimeOfArrival(); //arr date
				    PlaneArray[index][3] = Planes.get(key).getOrigin(); // origin
				    PlaneArray[index][4] = Planes.get(key).getDestination(); //dest
				    index++;
				}

				return PlaneArray;
			}

		    
		  
	
}
	
