package employee;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import books.DBConnection;

import java.sql.Connection;
//import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class EmployeeInfo
 */
@WebServlet("/EmployeeInfo")
public class EmployeeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		Connection con = DBConnection.getDBInstance();
		DBUtility.useDB(con, "employee");
		
		String employee_name = request.getParameter("employee_name");
		String employee_address = request.getParameter("employee_address");
		String employee_city = request.getParameter("employee_city");
		String employee_phone = request.getParameter("employee_phone");
		String employee_email = request.getParameter("employee_email");
	//	String employee_address = request.getParameter("employee_address");
		


		String q = "insert into `employee`.employee(employee_name, employee_address, employee_city, employee_phone, employee_email) values(?,?,?,?,?)";
	//	java.sql.Connection con = DBConnection.getDBInstance();
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(q);
			//stmt.executeUpdate();
			stmt.setString(1, employee_name);
			stmt.setString(2, employee_address);
			stmt.setString(3, employee_city);
			stmt.setString(4, employee_phone);
			stmt.setString(5, employee_email);
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		response.setContentType("text/html");
	//	employee_name, employee_address, employee_city, employee_email, employee_phone
		
		//String q = "INSERT INTO `employee`.`employee`(`employee_name`, `employee_address`, `employee_city`, `employee_email`, `employee_phone`) values(employee_name, employee_address, employee_city, employee_email, employee_phone);";

		if(DBUtility.executeUpdate(con, q)) {
			response.getWriter().append("<meta http-equiv='refresh' content='3;URL=empList'><p style='color:red; '> Employee saved succesffully</p>");
		}
		else {
			response.getWriter().append("<meta http-equiv='refresh' content='3;URL=employee.jsp'><p style='color:red; '> Employee saved unsuccesffully</p>");
		}
		doGet(request, response);
	}

}

