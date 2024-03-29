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
 * Servlet implementation class DocLogServ
 */
@WebServlet("/DocLogServ")
public class DocLogServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocLogServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//setting up the response, printwriter for display and temporary string for result
		response.setContentType("text/html");
		PrintWriter outgoing = null;
		String userin = "username";
		String pswdin = "password";
		
		//IMPORTANT: NEED TO ESTABLISH SESSION VARIABLES AS SOON AS THEY LOGIN!!! THese can be accessed by the jsp
		
		try {
			outgoing=response.getWriter();
			userin = request.getParameter("user");
			pswdin = request.getParameter("password");
			
			//outgoing.println("Username submitted is: " + userin + "<br>");
			
			//outgoing.println("Password submitted is: " + pswdin + "<br>");
			
			
			//new:------------------------------------------------------------------------------------
			
			//1. Search database for username and password submitted
		
				//HERE
			
			
			
			//2. if(found) -> set session attributes and redirect to dochomepage
				//else -> give incorrect login/login not found and prompt re-entry or back to home or register
			
		
			
			
			HttpSession mysession=request.getSession();
			
			mysession.setAttribute("user", userin);
		
			//TEMPORARY FOR TEST:
			mysession.setAttribute("FirstName", userin);
			mysession.setAttribute("LastName", pswdin);
			//CLOSE TEMP
			//mysession.setAttribute("FirstName",doc.getFirstName());
			//mysession.setAttribute("LastName",doc.getLastName());
			//mysession.setAttribute("id",doc.getFirstName());
			//mysession.setAttribute("user",doc.getUsername());
			//mysession.setAttribute("FirstName",doc.getFirstName());
//...

			
			//new as of 11-19:
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			
			 String query="SELECT COUNT(*) FROM DOCTOR WHERE username=?";
				PreparedStatement prep=conn.prepareStatement(query);
				
			prep.setString(1, userin);
			
			ResultSet result = prep.executeQuery();
			
			
			
			if(result!=null) {
				result.next();
				int number=result.getInt(1);
				if(number==0) {
					conn.close();
					outgoing.println("<style type=text/css> body{ background-color:#D2BD96;} </style> "
							+ "<center> The number of users with this username is:" + number + "</center><br>" + "Please register here: <a href=registrationSelect.html>Register Here</a>");

				}
				else {
				//conn.close();
				//outgoing.println("<center> The number of users with this username is:" + number + "</center><br>");
				//set session attributes first!
				String query2="SELECT * FROM DOCTOR WHERE username=?";
				PreparedStatement prep2=conn.prepareStatement(query2);
				prep2.setString(1, userin);
				
			ResultSet res = prep2.executeQuery();
			int id=0;
			String firstname=" ";
			String lastname=" ";
			String usern=" ";
			String ps= " ";
			String special=" ";
			if(res!=null) {
				while(res.next()) {
					id=res.getInt(1);
					firstname=res.getString(2);
					lastname=res.getString(3);
					
					usern=res.getString(7);
					ps=res.getString(11);
					mysession.setAttribute("FirstName", firstname);
					mysession.setAttribute("LastName", lastname);
					mysession.setAttribute("Username",usern);
					mysession.setAttribute("ID",id);
				
				}
			}
					
					conn.close();
					if(ps==pswdin||ps.equals(pswdin)) {
						//outgoing.println("<center> Incorrect password. <br> Please try again here: <a href=pharmlogin.html>Try Again</a>");
						//outgoing.close();
						//outgoing.println("ps: " + ps + " does not matches: " + pswdin);
						//response.sendRedirect("pharmlogin.html");
						response.sendRedirect("DocHome.jsp");
					}else {
						//outgoing.println("ps: " + ps + " does not matches: " + pswdin);
						outgoing.println("<style type=text/css> body{ background-color:#D2BD96;} </style> "
								+ "<center><b> Incorrect password.</b> <br> Please try again here: <a href=doctorlogin.html>Try Again</a>");

					//outgoing.close();
					//response.sendRedirect("PharmHome.jsp");
				
					}
				}
			}
			else {
				outgoing.println("<center> No users exist in dB matching username!</center>");
			}
			
				
				
				
				
				
				
				
				
				//prep.executeUpdate();
				
				conn.close();
			
			
			
			
			
			
			//end of new (but include redirect underneath from previous
			//response.sendRedirect("DocHome.jsp");
			//--------------------------------------------------------------------end new

		} catch(Exception e) {
			//nothing yet
			outgoing.println("Error: " + e.getMessage());
		}finally {
			
			
			//add logout here
			
			outgoing.println("<br>To goto main page <a href=HomePage.html>Click here</a>");
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
