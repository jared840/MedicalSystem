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
 * Servlet implementation class RegNurServ
 */
@WebServlet("/RegNurServ")
public class RegNurServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegNurServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		nurse nur=new nurse();
		response.setContentType("text/html");
		PrintWriter outgoing = null;
		
		try {
			outgoing=response.getWriter();
			
			nur.setID(Integer.parseInt(request.getParameter("id")));
			nur.setFirstName(request.getParameter("FName"));
			nur.setLastName(request.getParameter("LName"));
			nur.setUsername(request.getParameter("user"));
			nur.setPassword(request.getParameter("password"));
			nur.setSpecialty(request.getParameter("specialty"));
			nur.setClinicName(request.getParameter("clinicname"));
			
			
			
			//new as of 11-18:
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			
			 String query="INSERT INTO NURSE VALUES(?,?,?,?,?,?,?)";
				PreparedStatement prep=conn.prepareStatement(query);
				
				prep.setInt(1, nur.getID());
				prep.setString(2, nur.getFirstName());
				prep.setString(3, nur.getLastName());
				prep.setString(4, nur.getClinicName());
				prep.setString(5, nur.getSpecialty());
				prep.setString(6, nur.getUsername());
				prep.setString(7, nur.getPassword());
				
				
				
				
				
				
				
				
				prep.executeUpdate();
				
				conn.close();
			
			//end of new
			
			
		}catch(Exception e) {
			outgoing.println("Error: " + e.getMessage());
		}finally {
HttpSession mysession=request.getSession();
			
			mysession.setAttribute("FirstName",nur.getFirstName());
			mysession.setAttribute("LastName",nur.getLastName());
			//...
			
			response.sendRedirect("NurHome.jsp");
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
