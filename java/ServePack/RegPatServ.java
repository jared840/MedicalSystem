package ServePack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegPatServ
 */
@WebServlet("/RegPatServ")
public class RegPatServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegPatServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
	 patient patient=new patient();
		response.setContentType("text/html");
		PrintWriter outgoing = null;

		try {
			outgoing=response.getWriter();
			
			patient.setID(Integer.parseInt(request.getParameter("id")));
			patient.setFirstName(request.getParameter("FName"));
			patient.setLastName(request.getParameter("LName"));
			patient.setUsername(request.getParameter("user"));
			patient.setPassword(request.getParameter("password"));
			patient.setPhone(request.getParameter("phone"));
			patient.setEmail(request.getParameter("email"));
			patient.setGender(request.getParameter("Gender"));
			patient.setDOB(request.getParameter("dob"));
			
			String type=request.getParameter("Type");
			
			
			
//new as of 11-18:
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			
			 String query="INSERT INTO PATIENT VALUES(?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement prep=conn.prepareStatement(query);
				
			prep.setInt(1, patient.getID());
			prep.setString(2, patient.getFirstName());
			prep.setString(3, patient.getLastName());
			prep.setString(4, patient.getEmail());
			prep.setString(5, patient.getDOB());
			prep.setString(6, patient.getGender());
			prep.setString(9, patient.getUsername());
			prep.setString(10, patient.getPassword());
			
			if(type=="Admitted"||type.equalsIgnoreCase("Admitted")) {
				prep.setInt(7, 1);
				prep.setInt(8, 0);
			}
			else if(type=="Outpatient"||type.equalsIgnoreCase("Outpatient")) {
				prep.setInt(7, 0);
				prep.setInt(8, 1);
			}
			else {
				prep.setInt(7, 0);
				prep.setInt(8, 0);
			}
			
				
				
				
				
				
				
				
				
				prep.executeUpdate();
				
				conn.close();
			
			//end of new
			
			
			
			
		}catch(Exception e) {
			outgoing.println("Error: " + e.getMessage());
		}finally
		{
			HttpSession mysession=request.getSession();
			
			mysession.setAttribute("FirstName",patient.getFirstName());
			mysession.setAttribute("LastName",patient.getLastName());
			//...
			
			response.sendRedirect("PatHome.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
