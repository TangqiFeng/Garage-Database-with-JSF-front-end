import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class FindVehicleController {
	ArrayList<FindCar> findVehicles;
	DAO dao;
	
	//convert result
	ArrayList<FindCar> result = new ArrayList<FindCar>();
	public FindVehicleController() throws Exception {
		this.findVehicles = new ArrayList<FindCar>();
		this.dao = new DAO();
	}
	
	
	
	public ArrayList<FindCar> getFindVehicles() {
		return findVehicles;
	}



	public void setFindVehicles(ArrayList<FindCar> findVehicles) {
		this.findVehicles = findVehicles;
	}



	public String loadVehicles(FindVehicle m) throws SQLException{
		result.clear();
		result = dao.SearchVehicles(m);
		System.out.println(findVehicles);
		return "find_vehicle";
	}
	
	
	public void loadVehicles() throws SQLException{
		findVehicles.clear();
		findVehicles = result;
	}
	
	
	
	
	
	
}
