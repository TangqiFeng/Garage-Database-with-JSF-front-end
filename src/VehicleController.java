import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class VehicleController {

	ArrayList<Vehicle> vehicles;
	ArrayList<FullDetail> fulldetails;
	DAO dao;
	
	public VehicleController() throws Exception {
		this.vehicles = new ArrayList<Vehicle>();
		this.fulldetails = new ArrayList<FullDetail>();
		this.dao = new DAO();
	}

	public ArrayList<FullDetail> getFulldetails() {
		return fulldetails;
	}

	public void setFulldetails(ArrayList<FullDetail> fulldetails) {
		this.fulldetails = fulldetails;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public void loadVehicles() throws SQLException{
		vehicles.clear();
		vehicles = dao.getVehicles();
		System.out.println(vehicles);
	}
	
	public String addVehicles(Vehicle m){
		try {
			dao.addVehicles(m);
			FacesContext.getCurrentInstance().getExternalContext().redirect("add_vehicle.xhtml");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		return "MngVic.xhtml";
	}
	
	//convert result
		ArrayList<FullDetail> result = new ArrayList<FullDetail>();
		
		public String loadFullDetails(Vehicle vehicle) throws SQLException{

			System.out.println("KKKKKKKKKKKK");
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("vehicle", vehicle);
			System.out.println("jjjjjjjjjjjjj");
			result.clear();
			result = dao.getFullDetails(vehicle.getReg());
			return "FullDetail";
		}
		
		public void loadFullDetails() throws SQLException{
			fulldetails.clear();
			fulldetails = result;
		}
	
}
