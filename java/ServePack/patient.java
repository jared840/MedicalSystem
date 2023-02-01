package ServePack;

import java.io.Serializable;
import java.sql.*;

public class patient implements Serializable {
    private int ID;
    private String Username;
    private String Email;
    private String DOB;
    private String Phone;
    private String Gender;
    private String FirstName;
    private String LastName;
    private String Password;
    public Connection connx;

    patient()
    {

    }

    void setUsername(String a)
    {
        this.Username=a;
    }

    String getUsername()
    {
        return this.Username;
    }

    void setID(int i)
    {
        this.ID=i;
    }

    int getID()
    {
        return this.ID;
    }

    void setEmail(String j)
    {
        this.Email=j;
    }

    String getEmail()
    {
        return this.Email;
    }

    void setDOB(String DOB)
    {
        this.DOB=DOB;
    }

    String getDOB()
    {
        return this.DOB;
    }

    void setPhone( String s)
    {
        this.Phone=s;
    }

    String getPhone()
    {
        return this.Phone;
    }

    void setGender(String g)
    {
        this.Gender=g;
    }

    String getGender()
    {
        return this.Gender;
    }

    void setFirstName(String first)
    {
        this.FirstName=first;
    }

    String getFirstName()
    {
        return this.FirstName;
    }

    void setLastName(String gh)
    {
        this.LastName=gh;
    }

    String getLastName()
    {
        return this.LastName;
    }

    void setPassword(String h)
    {
        this.Password=h;
    }

    String getPassword()
    {

        return this.Password;
    }
    
  //prescribes table, matching patient ID
    ResultSet viewPrescriptions(int pID)
    {

        try
        {
            connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String qu="SELECT Name,Prescribed,DoctorNotes,Dosage FROM prescribes WHERE PatientID=?";
            PreparedStatement pre=connx.prepareStatement(qu);
            pre.setInt(1, pID);
            ResultSet re=pre.executeQuery();
            return re;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    Boolean populateHas(int DID,int NID,int PID,int ANum,String PUser,String DUser,String Nuser,String TD)
    {
     try
     {
         Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
         String str="INSERT INTO HAS VALUES(?,?,?,?,?,?,?,?)";
         PreparedStatement p=conn.prepareStatement(str);
         p.setInt(1, DID);
         p.setInt(2, NID);
         p.setInt(3, PID);
         p.setInt(4, ANum);
         p.setString(5, PUser);
         p.setString(6, DUser);
         p.setString(7, Nuser);
         p.setString(8, TD);

         p.executeUpdate();
         conn.close();
         // "true";
         return true;

     }
     catch(Exception e)
     {
    	 //return e.getMessage();
         return false;
     }
    }

    Boolean deleteAppointment(int pID, int appNum)
    {
        try
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String s="DELETE FROM Appointment WHERE ID=? AND AppointmentNumber=?";
            PreparedStatement prepared=conn.prepareStatement(s);
            prepared.setInt(1, pID);
            prepared.setInt(2, appNum);
            prepared.executeUpdate();
            conn.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    ResultSet viewApp(int personID)
    {
        try
        {
            connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            //String q="SELECT AppointmentNumber, AppointmentType FROM Appointment WHERE ID=?";
            String q="SELECT HAS.AppointmentNumber, AppointmentType, TimeDate FROM Appointment, HAS WHERE HAS.PatientID=? AND Appointment.AppointmentNumber=HAS.AppointmentNumber";

            PreparedStatement pre=connx.prepareStatement(q);
            pre.setInt(1, personID);
            ResultSet result=pre.executeQuery();
            //conn.close();
            return result;
        }
        catch(Exception e)
        {
            return null;
        }

    }

    
    int getPatientID(String f, String l)
    {
        try
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String str="SELECT ID FROM patient WHERE FName=? AND LName=?";
            PreparedStatement pre=conn.prepareStatement(str);
            pre.setString(1, f);
            pre.setString(2, l);
            ResultSet re=pre.executeQuery();
            if(re.next())
            {
                int toReturn=re.getInt(1);
            	conn.close();
            	return toReturn;
                //return re.getInt(1);
            }
            else
            {
                conn.close();
                return -1;
            }
            
        }
        catch(Exception e)
        {
            return -1;
        }
    }

    


    int pgetID(String user)
    {
        try
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String query="SELECT ID FROM patient WHERE username=?";
            PreparedStatement pp=conn.prepareStatement(query);
            pp.setString(1, user);
            ResultSet r=pp.executeQuery();
            if(r.next())
            {
                
            	int toReturn=r.getInt(1);
            	conn.close();
            	return toReturn;
                //return r.getInt(1);
            }
            else
            {
            	
                conn.close();
                return -1;
            }

        }
        catch(Exception e)
        {
            return -1;
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



    Boolean bookAppointment(int aID,int aNum, String aType)
    {
        try
        {
           int i=0;
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String query="SELECT COUNT(*) FROM Appointment WHERE ID=?";
            PreparedStatement p=conn.prepareStatement(query);
            p.setInt(1, aID);
            ResultSet r=p.executeQuery();
            if(r!=null)
            {
                r.next();
                i=r.getInt(1);
            }
            if(i==1)
            {
                conn.close();
                return false;
            }
            if(i==0)
            {
                String qq="INSERT INTO Appointment VALUES(?,?,?)";
                PreparedStatement prep=conn.prepareStatement(qq);
                prep.setInt(1, aID);
                prep.setInt(2, aNum);
                prep.setString(3, aType);
                prep.executeUpdate();
                conn.close();
                return true;
            }
            conn.close();
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

}
