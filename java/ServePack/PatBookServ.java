package ServePack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 * Servlet implementation class PatBookServ
 */
@WebServlet("/PatBookServ")
public class PatBookServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatBookServ() {
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
		
		//String user=request.getParameter("param");
		//int appNo=Integer.parseInt(request.getParameter("appNo"));
		String appType=request.getParameter("appType");
		HttpSession mysession=request.getSession();
		String usename=Objects.toString(mysession.getAttribute("user"));
		
		

		
		out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
		
		
		
		
		patient pat=new patient();
		try {
			  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
	            String getid="SELECT ID FROM PATIENT WHERE USERNAME=?";
	            PreparedStatement prp=conn.prepareStatement(getid);
	            prp.setString(1, usename);
	            
	            ResultSet res=prp.executeQuery();
	            res.next();
	            int gottheid=res.getInt(1);
	           String  getid2="SELECT MAX(AppointmentNumber) FROM APPOINTMENT";
	            PreparedStatement prp2=conn.prepareStatement(getid2);
	            ResultSet res2=prp2.executeQuery();
	            res2.next();
	            int gottheid2=res2.getInt(1);//or =res.getint(1) for ptient's id to be apptnumber???
	            gottheid2+=1;
		Boolean updated=pat.bookAppointment(gottheid, gottheid2, appType);
		if(updated==true) {
			out.println("Appointment Instance created<br> <a href=PatHome.jsp>Home</a>");
			//request.setAttribute("Drugs", toSend_drugs);
			
			//new as of 12-01:+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			
			 //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
				
			 String query="SELECT * FROM CLINIC";
				PreparedStatement prep=conn.prepareStatement(query);
			
			
			
			ResultSet result = prep.executeQuery();
			
			
//out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
			
			out.println("<table border=1 width=50% height=50%>");
			out.println("<tr><th>Address</th><th>Email</th><th>Phone</th><th>Name</th><th>Book this Clinic</th>");
			while(result.next()) {
				String cl_add=result.getString(1);
				String cl_mail=result.getString(2);
				String cl_num=result.getString(3);
				String cl_name=result.getString(4);
				
				String cl_name2=new String();
				for(int i=0;i<cl_name.length();i++) {
					if(cl_name.charAt(i)==' ') {	//or use ascii for space
						cl_name2+='+';
					}
					else {
						cl_name2+=cl_name.charAt(i);
					}
				}
				

				out.println("<tr><td><b>"
						 + cl_add 
						+"</td><td>"+cl_mail+"</td><td>"+cl_num+"</td><td>"+cl_name+"</td><td>"+
						 "<a href=PatBookServletNext?clinic="+cl_name2+"&appnum="+gottheid2+"&patid="+gottheid+">Book This Clinic!</a>"
						+"</td></tr>");
				
			}
			out.println("</table>");
			out.println("<br> <a href=PatHome.jsp>Back to Home</a> <br>");
			//out.println("<br> <a href=DocGetDrugInfo.html>Search a Specific Drug</a>");
			//out.println("<a href=DocViewDrugsServ?param=<%= request.getSession().getAttribute(user) %>>Back to View Available Drugs</a>");
		
			
			//ADD HERE: ADD WHERE WHAT CLINIC NURSE IS PART OF!!!
			
			
			conn.close();
			
			
			
			
			
			//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			
			//request.setAttribute("AptNo", gottheid2);

			//request.getRequestDispatcher("PatBookStepTwo.jsp").forward(request, response);
			//out.println("<br> To continue to the next step (<i>Booking clinic and docs</i>), press the link here:"+"a href=PatBookStepTwo.jsp"
		}
		else {
			out.println("Could not book! Our policy is to not override existing bookings, and thus a patient can have only one. Please check if you have existing bookings <br> <a href=PatHome.jsp>Home</a>");

		}
		} catch(Exception e) {
			//out.println(e.getMessage());
			out.println("Error booking appointment<br> <a href=PatHome.jsp>Home</a>");
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
