package ServePack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * Servlet implementation class RegDocServ
 */
@WebServlet("/RegDocServ")
public class RegDocServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegDocServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	

		doctor doc=new doctor();
		response.setContentType("text/html");
		PrintWriter outgoing = null;
		String userin = "username";
		int id = 0;
		String FName = "Fname";
		String LName = "LName";
		String pswdin="password";
		
		String phonein="phone";
		String emailin="email@email.com";
		String specialty="None";
		String clinicName="clinicname";
		
		
		try {
			
			outgoing=response.getWriter();
			
			userin = request.getParameter("user");
			pswdin = request.getParameter("password");
			id=Integer.parseInt(request.getParameter("id"));
			FName = request.getParameter("FName");
			LName = request.getParameter("LName");
			phonein = request.getParameter("phone");
			emailin=request.getParameter("email");
			specialty=request.getParameter("Specialty");
			clinicName=request.getParameter("clinicname");

			doc.setUsername(userin);
			doc.setSpecialty(specialty);
			doc.setPhone(phonein);
			doc.setPassword(pswdin);
			doc.setLastName(LName);
			doc.setID(id);
			doc.setFirstName(FName);
			doc.setEmail(emailin);
			doc.setClinicName(clinicName);
			
			
			//new as of 11-18: (added import and dbconnect file)
			
			//uncomment
			//dbConnect db= new dbConnect();
			//Statement statement = db.conn.createStatement();
			//PreparedStatement query=db.conn.prepareStatement("INSERT INTO DOCTOR)
			
			
			 /*Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			 if(conn==null) {
					outgoing.println("NULL connect");

			 }
			String query="INSERT INTO DRUGS VALUES(?,?,?)";
			PreparedStatement prep=conn.prepareStatement(query);
			if(prep==null) {
				
				outgoing.println("NULL PREPARE");
			}
			prep.setString(1, userin);
			prep.setString(2, emailin);
			prep.setString(3, clinicName);
			
			//prep.execute();
			
			//ResultSet res=prep.executeQuery();
			prep.executeUpdate();
			
			
			
			conn.close();
			*/
			/*
			String query="INSERT INTO DOCTOR VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prep=db.conn.prepareStatement(query);
			prep.setInt(1, id);
			prep.setString(2, FName);
			prep.setString(3, LName);
			prep.setString(4, emailin);
			prep.setString(5, specialty);
			prep.setString(6, clinicName);
			prep.setString(7, userin);
			prep.setString(10, phonein);
			prep.setString(11, pswdin);
			
			if(specialty=="Specialist"||specialty.equalsIgnoreCase("Specialist")){
				prep.setInt(8, 1);
				prep.setInt(9, 0);
			}
			else if(specialty=="Family Doctor"||specialty.equalsIgnoreCase("Family Doctor")) {
				prep.setInt(9, 1);
				prep.setInt(8, 0);
				
			}
			else {
				prep.setInt(8, 0);
				prep.setInt(9, 0);
			}
			
			prep.executeUpdate();
			
			db.conn.close();
			*/
			/*String query="INSERT INTO DOCTOR(ID, Username, Password, Email, Phone, FirstName, LastName, Spe_Flag, Specialty, Fam_Flag, ClinicName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prep=db.conn.prepareStatement(query);
			prep.setInt(1, id);
			prep.setString(2, userin);
			prep.setString(3, pswdin);
			prep.setString(4, emailin);
			prep.setString(5, phonein);
			prep.setString(6, FName);
			prep.setString(7, LName);
			prep.setString(9, specialty);
			prep.setString(11, clinicName);
			
			if(specialty=="Specialist"||specialty.equalsIgnoreCase("Specialist")){
				prep.setInt(8, 1);
				prep.setInt(10, 0);
			}
			else if(specialty=="Family Doctor"||specialty.equalsIgnoreCase("Family Doctor")) {
				prep.setInt(10, 1);
				prep.setInt(8, 0);
				
			}
			else {
				prep.setInt(8, 0);
				prep.setInt(10, 0);
			}
			
			prep.executeUpdate();
			
			db.conn.close();
			//end of new */
			
			//new again:
			//!!!!!!!MUST CHECK COUNT(*) FOR USERNAME!!!
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			
			 String query="INSERT INTO DOCTOR VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement prep=conn.prepareStatement(query);
				
				prep.setInt(1, id);
				prep.setString(2, FName);
				prep.setString(3, LName);
				prep.setString(4, emailin);
				prep.setString(5, specialty);
				prep.setString(6, clinicName);
				prep.setString(7, userin);
				
				if(specialty=="Family Doctor"||specialty.equalsIgnoreCase("Family Doctor")) {
					prep.setInt(8, 0);
					prep.setInt(9, 1);
				}
				else if(specialty=="Specialist"||specialty.equalsIgnoreCase("Specialist")) {
					prep.setInt(8, 1);
					prep.setInt(9, 0);
				}
				else {
					prep.setInt(8, 0);
					prep.setInt(9, 0);
				}
				
				
				
				prep.setString(10, phonein);
				prep.setString(11, pswdin);
				
				
				
				prep.executeUpdate();
				
				conn.close();
			
			
			//________end of new again
			
			
		}catch(Exception e) {
			outgoing.println("Error: " + e.getMessage());
			
		}finally {
			HttpSession mysession=request.getSession();
			
			mysession.setAttribute("FirstName",doc.getFirstName());
			mysession.setAttribute("LastName",doc.getLastName());
			//mysession.setAttribute("id",doc.getFirstName());
			mysession.setAttribute("user",doc.getUsername());
			//mysession.setAttribute("FirstName",doc.getFirstName());
//...

			//uncomment!!!
			response.sendRedirect("DocHome.jsp");
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
