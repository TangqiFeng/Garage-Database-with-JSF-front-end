import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class ModelController {

	ArrayList<Model> models;
	DAO dao;
	
	public ModelController() throws Exception {
		this.models = new ArrayList<Model>();
		this.dao = new DAO();
	}

	public ArrayList<Model> getModels() {
		return models;
	}

	public void setModels(ArrayList<Model> models) {
		this.models = models;
	}
	
	public void loadModels() throws SQLException{
		models.clear();
		models = dao.getModels();
		System.out.println(models);
	}
	
	public String addModels(Model m){
		try {
			dao.addModels(m);
			FacesContext.getCurrentInstance().getExternalContext().redirect("add_model.xhtml");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		return "MngMdl.xhtml";
	}
	
	
	
	
	
}
