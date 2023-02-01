package ServePack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DocGetClinicServlet
 */
@WebServlet("/DocGetClinicServlet")
public class DocGetClinicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocGetClinicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		ArrayList<String> toSend=new ArrayList<String>();
		ArrayList<Integer> toSend2=new ArrayList<Integer>();

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
			try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			
			 String query="SELECT Name FROM CLINIC";
			 
				PreparedStatement prep=conn.prepareStatement(query);
				
				
			
			ResultSet result = prep.executeQuery();
			out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
			//ArrayList<String> toSend=new ArrayList<String>();
			while(result.next()) {
				toSend.add(result.getString(1));
			}
			String query2="SELECT ID FROM NURSE";
			 
			PreparedStatement prep2=conn.prepareStatement(query2);
			
			
		
		ResultSet result2 = prep2.executeQuery();
		out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
		//ArrayList<String> toSend=new ArrayList<String>();
		while(result2.next()) {
			toSend2.add(Integer.parseInt(result2.getString(1)));
		}
			
			/*out.println("<table border=1 width=50% height=50%>");
			out.println("<tr><th>Name<th>Email<th>Phone</th><th>Address</th><b><th>VIEW CLINIC DOCTORS</th>");
			while(result.next()) {
				String addr=result.getString(1);
				String mail=result.getString(2);
				String num=result.getString(3);
				String cl_name=result.getString(4);

				out.println("<tr><td><b>"
						+ cl_name + "</b></td>"+
						"<td>" + mail + "</td>"+
						"<td>" + num +"</td><td>"
						+ addr +"</td><td>"
						+"<a href=ViewClinicDocs?ClinicName="+cl_name+"><b>VIEW DOCTORS AT THIS CLINIC</b></a>"
						+ "</td></tr>");
				//ERROR HERE: If clinic name has spaces it cuts it off??? (line 63)
			}
			out.println("</table>");
			out.println("<br> <a href=PatHome.jsp>Back to Home</a>");*/
		
			
			//ADD HERE: ADD WHERE WHAT CLINIC NURSE IS PART OF!!!
			
			
			conn.close();
			//to be continued
			}catch (Exception e) {
				out.println("<br> Error finding clinics<br> <a href=DocHome.jsp>Go Home</a>");
				
				
			}finally{
				
				request.setAttribute("Clinics", toSend);
				request.setAttribute("Nurses", toSend2);

				request.getRequestDispatcher("DocClins.jsp").forward(request, response);
				//response.sendRedirect("PatHome.jsp");
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
