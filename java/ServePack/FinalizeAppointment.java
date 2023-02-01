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
 * Servlet implementation class FinalizeAppointment
 */
@WebServlet("/FinalizeAppointment")
public class FinalizeAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalizeAppointment() {
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
		String docselect=request.getParameter("DoctorSelect");
		String apptime=request.getParameter("app_time");
		String appmonth=request.getParameter("app_month");
		String appday=request.getParameter("app_day");
		String appyear=request.getParameter("app_year");
		String cli=request.getParameter("clinic_requ");
		
		String app_timedate=apptime+","+appmonth+"/"+appday+"/"+appyear;




		
		int patid_request=Integer.parseInt(request.getParameter("pat_id_req"));
		int appnum_request=Integer.parseInt(request.getParameter("appno_req"));
		//String pleasework="?";
		
		out.println("<style type=text/css> body{ background-color:#D2BD96;} </style> ");
		try {
			patient reqpat=new patient();
			int did=-1;
			int nid=-1;
			String docfetch="?";
			String nurfetch="?";
			String patfetch="?";
			//String docfetch="?";
			String newcli=new String();
			for(int cc=0;cc<cli.length();cc++) {
				if(cli.charAt(cc)=='+') {
					newcli+=" ";
				}
				else {
					newcli+=cli.charAt(cc);
				}
			}
			cli=newcli;

			String realdoc=new String();
			for(int thru=4;thru<docselect.length();thru++) {
				realdoc+=docselect.charAt(thru);
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");

			String the_query="SELECT ID, Username FROM DOCTOR WHERE LName=? AND ClinicName=?";
			PreparedStatement ppp=conn.prepareStatement(the_query);
			
			
			ppp.setString(1, realdoc);
			ppp.setString(2, cli);

			
			ResultSet docid=ppp.executeQuery();
			if(docid.next()) {
				did=docid.getInt(1);
				docfetch=docid.getString(2);
			}
			
			the_query="SELECT ID, Username FROM NURSE WHERE ClinicName=? ORDER BY RAND() LIMIT 1";
		//order randomly???
			ppp=conn.prepareStatement(the_query);
			
			ppp.setString(1, cli);
			
			ResultSet nurid=ppp.executeQuery();
			if(nurid.next()) {
				nid=nurid.getInt(1);
				nurfetch=nurid.getString(2);
			}
			
			the_query="SELECT Username FROM PATIENT WHERE ID=?";
			ppp=conn.prepareStatement(the_query);

			ppp.setInt(1, patid_request);
			ResultSet patid=ppp.executeQuery();
			if(patid.next()) {
				
				patfetch=patid.getString(1);
			}
			
			//out.println(did+" "+nid+" "+patid_request+" "+patfetch+" "+docfetch+ " "+nurfetch+ " "+ app_timedate);
			boolean pleasework=reqpat.populateHas(did, nid, patid_request, appnum_request, patfetch, docfetch, nurfetch, app_timedate);
			//out.println("<center>"+pleasework+"</center>");
			if(pleasework==true) {
				out.println("<br><center>Appointment Booked!</center><br> <a href=PatHome.jsp>Go Home</a>");

			}
			else {
				out.println("<br><center>Bookine unsuccessful, Try Again or alter criteria</center><br> <a href=PatHome.jsp>Go Home</a>");
				







				
			}
			
			
			conn.close();
			
		}catch(Exception e) {
			out.println("<br><center>Error with booking, please try again</center><br> <a href=PatHome.jsp>Go Home</a>");
			//out.println(pleasework);
//out.println(e.getStackTrace());
			//out.println(pleasework);
			//out.println(e.getLocalizedMessage());
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
