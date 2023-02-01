package ServePack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchDocServ
 */
@WebServlet("/SearchDocServ")
public class SearchDocServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDocServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String searchtype=request.getParameter("SearchType");
		String query = "SELECT Fname,Lname,Email,Specialty,ClinicName,Phone FROM DOCTOR";
		
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			
			if(searchtype=="MyClinicOnly"||searchtype.equals("MyClinicOnly")) {
				
				
				
				query="SELECT D.Fname,D.Lname,D.Email,D.Specialty,D.ClinicName,D.Phone FROM DOCTOR AS D,NURSE AS N WHERE D.ClinicName=N.ClinicName AND N.Username=?";
			}
			
				PreparedStatement prep=conn.prepareStatement(query);
				//String one="D.ClinicName";
				//String two="N.ClinicName";
				//String three="N.Username";
				
				if(searchtype=="MyClinicOnly"||searchtype.equals("MyClinicOnly")) {
				HttpSession mysession=request.getSession();
				String four=mysession.getAttribute("user").toString();
						//mysession.getAttribute("user");
				
				//prep.setString(1, one);
				//prep.setString(2, two);
				//prep.setString(3, three);
				prep.setString(1, four);
				
				}
			ResultSet result = prep.executeQuery();
			out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
			
			out.println("<table border=1 width=50% height=50%>");
			out.println("<tr><th>FirstName,LastName<th>Email<th>Specialty</th><th>ClinicName</th><th>Phone</th>");
			while(result.next()) {
				String name=result.getString(1);
				name+=",";
				name+=result.getString(2);
				String mail=result.getString(3);
				String spec=result.getString(4);
				String cl_name=result.getString(5);
				String phon=result.getString(6);

				out.println("<tr><td><b>"
						+ name + "</b></td>"+
						"<td>" + mail + "</td>"+
						"<td>" + spec +"</td><td>"
						+ cl_name + "</td><td>"+ phon
						
						+"</td></tr>");
			}
			out.println("</table>");
			out.println("<br> <a href=NurHome.jsp>Back to Home</a>");
		
			
			//ADD HERE: ADD WHERE WHAT CLINIC NURSE IS PART OF!!!
			
			
			conn.close();
			//to be continued
			}catch (Exception e) {
				out.println("<br> Error finding clinics<br> <a href=NurHome.jsp>Nurse Home</a>");
				//out.println(e.getMessage());
				
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
