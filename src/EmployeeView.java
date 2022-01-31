
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nolen
 */
public class EmployeeView implements ViewInterface {
    
    @Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {
                    
		case "select": return selectOperation(modelData);
		case "select.gui": return selectGUI(modelData);
		}
		
		return new ViewData("EmployeeLoginMenuView", "");
	}
	
	ViewData selectOperation(ModelData modelData) throws Exception {
		ResultSet resultSet = modelData.resultSet;
                System.out.println();
                System.out.println("Id\tName\t Start Date\tEnd Date     Status\tDescription");
                if (resultSet != null) {
			while (resultSet.next()) {
				// Retrieve by column first_name
				int project_id = resultSet.getInt("project_id");
                                String project_name = resultSet.getString("project_name");
                                String project_start_date = resultSet.getString("project_start_date");
                                String project_end_date = resultSet.getString("project_end_date");
                                String project_description = resultSet.getString("project_description");
                                int project_status = resultSet.getInt("project_status");
                                
                                // Display values
				System.out.print(project_id + "\t");
				System.out.print(project_name + "\t");
				System.out.print(" " + project_start_date + "\t");
				System.out.print(project_end_date + "   ");
                                System.out.print(" %" + project_status + "\t");
                                System.out.print(project_description + "\t");
				
				
                                System.out.println();
			}
			resultSet.close();	
		}
		                
		return new ViewData("EmployeeLoginMenu", "");
	}
	     
	Map<String, Object> getWhereParameters() throws Exception {
            
		System.out.println("\nEnter the project informations..");
		Integer project_id = getInteger("Project Id : ", true);// short project id ama useraccount integer olduğu için aynı şekilde yapıldı
                String project_name = getString("Project Name : ", true);
                String project_start_date = getString("Project Start Date : ", true);
                String project_end_date = getString("Project End Date : ", true);
                String project_description = getString("Description : ", true);
                Integer project_status = getInteger("Status : ", true);
		
		Map<String, Object> whereParameters = new HashMap<>();                
		if (project_id != null) whereParameters.put("project_id", project_id);
		if (project_name != null) whereParameters.put("project_name", project_name);
		if (project_start_date != null) whereParameters.put("project_start_date", project_start_date);
		if (project_end_date != null) whereParameters.put("project_end_date", project_end_date);
		if (project_description != null) whereParameters.put("project_description", project_description);
		if (project_status != null) whereParameters.put("project_status", project_status);
                               
		return whereParameters;
	}
	
	ViewData selectGUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("Employee", "select", parameters);
	}

	@Override
	public String toString() {
		return "Employee View";
	}
    
}
