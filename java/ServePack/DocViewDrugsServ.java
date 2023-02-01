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

/**
 * Servlet implementation class DocViewDrugsServ
 */
@WebServlet("/DocViewDrugsServ")
public class DocViewDrugsServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocViewDrugsServ() {
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
		String username_in=request.getParameter("param");
		
		out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
		
		try {
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
				
			 String query="SELECT * FROM DRUGS";
				PreparedStatement prep=conn.prepareStatement(query);
			
			
			
			ResultSet result = prep.executeQuery();
			
			
//out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
			
			out.println("<table border=1 width=50% height=50%>");
			out.println("<tr><th>Name</th><th>View Drug Info</th>");
			while(result.next()) {
				String comp=result.getString(1);
				String SE=result.getString(2);
				String nam=result.getString(3);
				//String cl_name=result.getString(4);

				out.println("<tr><td><b>"
						 + nam 
						+"</b></td><td><a href=SearchDrugDocServ?DrugName="+nam+">View This Drug Info</a>"
						+"</td></tr>");
				
			}
			out.println("</table>");
			out.println("<br> <a href=DocHome.jsp>Back to Home</a> <br>");
			out.println("<br> <a href=DocGetDrugInfo.html>Search a Specific Drug</a>");
			//out.println("<a href=DocViewDrugsServ?param=<%= request.getSession().getAttribute(user) %>>Back to View Available Drugs</a>");
		
			
			//ADD HERE: ADD WHERE WHAT CLINIC NURSE IS PART OF!!!
			
			
			conn.close();
			
			
		}catch(Exception e) {
			out.println("<br> Error finding Drugs<br> <a href=DocHome.jsp>Home</a>");

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
