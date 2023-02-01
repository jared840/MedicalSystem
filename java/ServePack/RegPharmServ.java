package ServePack;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegPharmServ
 */
@WebServlet("/RegPharmServ")
public class RegPharmServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegPharmServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		pharmacist pharm=new pharmacist();
		response.setContentType("text/html");
		PrintWriter outgoing = null;
		String userin = "username";
		int id = 0;
		String FName = "Fname";
		String LName = "LName";
		String pswdin="password";
		String supply = "supply";
		
		try {
			//response.sendRedirect(url)
			outgoing=response.getWriter();
			
			userin = request.getParameter("user");
			pswdin = request.getParameter("password");
			id=Integer.parseInt(request.getParameter("id"));
			//id = Integer.parseInt(request.getParameter("id"));
			FName = request.getParameter("FName");
			LName = request.getParameter("LName");
			supply = request.getParameter("supply");
			
			pharm.setUsername(userin);
			pharm.setPassword(pswdin);
			pharm.setID(id);
			pharm.setFirstName(FName);
			pharm.setLastName(LName);
			pharm.setSupply(supply);
	
			//new as of 11-18:
			
			//dbConnect db = new dbConnect();
			
			 Class.forName("com.mysql.cj.jdbc.Driver");
			    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");

			
			String query="INSERT INTO PHARMACIST VALUES(?,?,?,?,?,?)";
			PreparedStatement prep=conn.prepareStatement(query);
			
			prep.setInt(1,id);
			prep.setString(2, FName);
			prep.setString(3, LName);
			prep.setString(4, supply);
			prep.setString(5, userin);
			prep.setString(6, pswdin);
			//prep.execute();
			
			//ResultSet res=prep.executeQuery();
			prep.executeUpdate();
			
			
			
			conn.close();
			
			
			
			//end of new
			
			
		}catch(Exception e) {
			outgoing.println("Error: " + e.getMessage());
		}finally {
			/*RequestDispatcher dispatcher = request.getRequestDispatcher("docHome.jsp");
			request.setAttribute("Name", n);
			dispatcher.forward(request, response);*/
			HttpSession mysession=request.getSession();
			//mysession.setAttribute("Pharmacist", pharm);
			
			//try to send entire pharmacist to jsp/session??? as above???
			
			mysession.setAttribute("FirstName",pharm.getFirstName());
			mysession.setAttribute("LastName", pharm.getLastName());
			response.sendRedirect("PharmHome.jsp");
			
			//....
			
			//outgoing.println(pharm.getUsername());
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
