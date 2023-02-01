package ServePack;

import java.io.Serializable;
import java.sql.*;

public class nurse implements Serializable {
    private int ID;
    private String Username;
    private String Password;
    private String Specialty;
    private String FirstName;
    private String LastName;
    private String ClinicName;
    public Connection connx;

    nurse()
    {

    }
    void setFirstName(String FirstName)
    {
        this.FirstName=FirstName;
    }

    void setLastName(String Last)
    {
        this.LastName=Last;
    }

    void setClinicName(String c)
    {
        this.ClinicName=c;
    }

    void setID(int ID)
    {
        this.ID=ID;
    }

    void setUsername(String u)
    {
        this.Username=u;
    }

    void setPassword(String p)
    {
        this.Password=p;
    }

    void setSpecialty(String s)
    {
        this.Specialty=s;
    }

    int getID()
    {
        return this.ID;
    }

    String getUsername()
    {
        return this.Username;
    }

    String getPassword()
    {
        return this.Password;
    }

    String getSpecialty()
    {
        return this.Specialty;
    }

    String getFirstName()
    {
        return this.FirstName;
    }

    String getLastName()
    {
        return this.LastName;
    }

    String getClinicName()
    {
        return this.ClinicName;
    }

    
    Boolean AssignClinic(String user, String ClinicName) {
    	Connection conn; //= DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");

		String query;
		PreparedStatement prep;
	ResultSet result;
	int number=0;
		
		
    	try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			
			query="SELECT COUNT(*) FROM CLINIC WHERE Name=?";
			
			prep=conn.prepareStatement(query);
			prep.setString(1, ClinicName);
			
			
		
			result=prep.executeQuery();
			
			if(result!=null) {
				result.next();
				number=result.getInt(1);
			}
			
			if(number<=0) {
				conn.close();
				return false;
			}
			else {
				query="UPDATE NURSE SET ClinicName=? WHERE Username=?";
				prep=conn.prepareStatement(query);
				prep.setString(1, ClinicName);
				prep.setString(2, user);
				prep.executeUpdate();
				conn.close();
				return true;
				}
			
			//conn.close();
    	}catch(Exception e) {
    		// false;
    		
    		//conn.close();
    		return false;
    	}
    	//conn.close();
    	//return false;
    	//return true;
    }
    
    
    ResultSet getSchedule(String u)
    {
        try{
        connx= DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
        String q="SELECT Fname, Lname, ClinicName, Hours, VacatonDays, Days FROM nurse, nurseschedule WHERE nurse.username=nurseschedule.username AND nurseschedule.username=?";
            PreparedStatement p=connx.prepareStatement(q);
            p.setString(1, u);
            ResultSet r=p.executeQuery();
           // conn.close();
            return r;
        }
        catch(Exception E)
        {
        	
            return null;
        }
    }
    
    
    Boolean updateSchedule(String username, int hours, String vacaDays, String days) 
    {
      int elv=0;
        try{
       Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
       
      //int got_id=0;
       
       String check="SELECT COUNT(*) FROM nurseschedule WHERE username=?";
        PreparedStatement j=conn.prepareStatement(check);
        j.setString(1, username);
        ResultSet r=j.executeQuery();

        if(r!=null)
        {
            r.next();
          //  got_id=r.getInt(1);
            elv=r.getInt(1);
        }

        if(elv==0)
        {
        	int the_id=0;
        	String get_id="SELECT ID FROM NURSE WHERE Username=?";
        	PreparedStatement oo=conn.prepareStatement(get_id);
        	oo.setString(1, username);
        	ResultSet got_id=oo.executeQuery();
        	if(got_id!=null) {
        		got_id.next();
        		the_id=got_id.getInt(1);
        	
        	String balan="INSERT INTO nurseschedule VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ostche=conn.prepareStatement(balan);
            ostche.setInt(1, the_id);


            ostche.setInt(2, hours);
            ostche.setString(3, vacaDays);
            ostche.setString(4, days);
            ostche.setString(5, username);

            ostche.executeUpdate();
            conn.close();
            return true;
        	}

        }
        if(elv==1)
        { 
        String q= "UPDATE nurseschedule SET hours=?, vacatondays=?, days=? WHERE Username=?";
        PreparedStatement p= conn.prepareStatement(q);
        p.setInt(1, hours);
        p.setString(2, vacaDays);
        p.setString(3, days);
        p.setString(4, username);

        p.executeUpdate();
            conn.close();
        return true;
        }
       }
       catch(Exception e)
       {
    	   //throw e;
    	   return false;
       }
       return false;

    }

    ResultSet getClinic(String username)
    {
        try{
            connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String q="SELECT address, email, phone, name FROM nurse AS n JOIN clinic AS c ON clinicname=name WHERE n.username=?";
            PreparedStatement ouchy=connx.prepareStatement(q);
            ouchy.setString(1, username);
            ResultSet pp=ouchy.executeQuery();
            //conn.close();
            return pp;
        }
        catch(Exception E)
        {
            return null;
        }
    }

    
    void closeConn() {
    	try {
    	connx.close();
    	}
    	catch(Exception e) {
    		//do nothing
    	}
    }
    
    ResultSet viewAppointment(String username)
    {
        try
        {
            connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String q="SELECT HAS.AppointmentNumber, AppointmentType, TimeDate, Appointment.ID FROM Appointment, HAS WHERE HAS.NurseUser=? AND Appointment.AppointmentNumber=HAS.AppointmentNumber";
            PreparedStatement pre=connx.prepareStatement(q);
            pre.setString(1, username);
            ResultSet result=pre.executeQuery();
            //conn.close();
            return result;
        }
        catch(Exception e)
        {
            return null;
        }

    }


    
}
