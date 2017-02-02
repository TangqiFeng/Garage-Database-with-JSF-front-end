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
public class ManufacturerController {
	ArrayList<Manufacturer> manufacturers;
	DAO dao;
	

	public ManufacturerController() throws Exception{
		this.manufacturers = new ArrayList<Manufacturer>();
		this.dao = new DAO();
	}
	
	public ArrayList<Manufacturer> getManufacturers() {
		return manufacturers;
	}
	public void setManufacturers(ArrayList<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}
	
	public void loadManufacturers() throws SQLException{
		manufacturers.clear();
		manufacturers = dao.getManufacturers();
		System.out.println(manufacturers);
		
	}
	
	public String addManufacturers(Manufacturer m){
		try {
			dao.addManufacturers(m);
			FacesContext.getCurrentInstance().getExternalContext().redirect("add_manufacturer.xhtml");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		return "MngMft";
	}
	
	public String updateManufacturer(Manufacturer manufacturer){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("manufacturer", manufacturer);
		return "update_manufacturer.xhtml";
	}
	
	public String renewManufacturer(Manufacturer m){
		try {
			dao.renewManufacturers(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "MngMft";
	}
	
	public void deleteManufacturer(Manufacturer manufacturer){
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("manufacturer", manufacturer);
		try {
			dao.deleteManufacturer(manufacturer);
			FacesContext.getCurrentInstance().getExternalContext().redirect("MngMft.xhtml");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	
}
