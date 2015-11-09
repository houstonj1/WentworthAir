import java.sql.Timestamp;

public class Plane {

	String PlaneID;
	String Type;
	int Capacity;
	double Cost;
	String Destination;
	String Origin;
	Timestamp TimeOfDeparture;
	Timestamp TimeOfArrival;
	Object[][] PlaneArray;
	
	public Plane(){
		
	}
	
	public void setTimeOfDeparture(Object time){
		TimeOfDeparture = (Timestamp) time;
	}
	
	public Timestamp getTimeOfDeparture(){
		return TimeOfDeparture;
	}
	
	public void setOrigin(Object location){
		Origin = (String) location;
	}
	
	public String getOrigin(){
		return Origin;
	}

	public void setDestination(Object location){
		Destination = (String) location;
		
	}
	
	public String getDestination(){
		return Destination;
	}
	
	public void setTimeOfArrival(Object time) {
		TimeOfArrival = (Timestamp) time;
	}
	
	public Timestamp getTimeOfArrival(){
		return TimeOfArrival;
	}
	
	public String toString(){
		return  Origin + " to " + Destination ;
	}
	
	public Object[][] toArray(){
		return PlaneArray;
	}
	
	public void setFlightNumber(Object object){
		PlaneID = (String) object;
	}

	public Object getFlightNumber() {
		return PlaneID;
	}
	
}
