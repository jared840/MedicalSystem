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
 * Servlet implementation class SearchDrugServ
 */
@WebServlet("/SearchDrugServ")
public class SearchDrugServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDrugServ() {
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
		try {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
		
		 String query="SELECT * FROM DRUGS WHERE Name=?";
			PreparedStatement prep=conn.prepareStatement(query);
			
			String DrugName=request.getParameter("DrugName");
		prep.setString(1, DrugName);
		
		ResultSet result = prep.executeQuery();
		out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
		
		out.println("<table border=1 width=50% height=50%>");
		out.println("<tr><th>Company</th><th>SideEffects</th><th>Name</th>");
		while(result.next()) {
			String comp=result.getString(1);
			String sides=result.getString(2);
			String drgname=result.getString(3);
			
			out.println("<tr><td>"
					+ comp + "</td>"+
					"<td>" + sides + "</td>"+
					"<td>" + drgname + "</td></tr>");
			
		}
		out.println("</table>");
		out.println("<br> <a href=SearchDrug.html>New Search</a>");
	
		conn.close();
		//to be continued
		}catch (Exception e) {
			out.println("<br> Error finding the drug!<br> <a href=SearchDrug.html>Try Again</a><a href=PharmHome.jsp>Back Home</a>");
			
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
