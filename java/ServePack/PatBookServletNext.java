package ServePack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PatBookServletNext
 */
@WebServlet("/PatBookServletNext")
public class PatBookServletNext extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatBookServletNext() {
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
		String clinic_req=request.getParameter("clinic");
		int appno_req=Integer.parseInt(request.getParameter("appnum"));
		int patid_req=Integer.parseInt(request.getParameter("patid"));
		
		out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
		
		ArrayList<String> DocLNames=new ArrayList<String>();
		//boolean worked=false;
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");

			String the_query="SELECT Lname FROM DOCTOR WHERE ClinicName=?";
			PreparedStatement ppp=conn.prepareStatement(the_query);
			
			ppp.setString(1, clinic_req);
			ResultSet docsfrom=ppp.executeQuery();
			while(docsfrom.next()) {
				String toEnter="Dr. ";
				toEnter+=docsfrom.getString(1);
				DocLNames.add(toEnter);
			}
			request.setAttribute("DocsFromClin", DocLNames);
			
			
			String cl_req2=new String();
			for(int i=0;i<clinic_req.length();i++) {
				if(clinic_req.charAt(i)==' ') {	//or use ascii for space
					cl_req2+='+';
				}
				else {
					cl_req2+=clinic_req.charAt(i);
				}
			}
			
			request.setAttribute("Clin_Name", cl_req2);
			request.setAttribute("PatIDreq", patid_req);
			request.setAttribute("App_numb", appno_req);

//out.println("Cl: "+clinic_req + " And: "+cl_req2);

			request.getRequestDispatcher("ChooseDocApp.jsp").forward(request, response);
		}catch(Exception e) {
			out.println("<br><center>Error with booking, please try again</center><br> <a href=PatHome.jsp>Go Home</a>");
//out.println(e.getMessage());
		}
		
		out.println("<br><center>Error with booking, please try again</center><br> <a href=PatHome.jsp>Go Home</a>");
		//out.println(e.getMessage());

		
		//HttpSession mysession=request.getSession();
		//String usename=Objects.toString(mysession.getAttribute("user"));
		
		

		
		//out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
