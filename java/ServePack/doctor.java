package ServePack;

import java.io.Serializable;
import java.sql.*;

//public 0 arg con
//get set
//attributes private
//implement serializable
public class doctor implements Serializable{
    private String FirstName;
    private String LastName;
    private String Phone;
    private int ID;
    private String Email; 
    private String Username;
    private String Password;
    private String Specialty;
    private String ClinicName;
    public Connection connx;

    //def const
    doctor()
    {

    }

    void setUsername(String u)
    {
        this.Username=u;
    }

    String getUsername()
    {
        return this.Username;
    }

    void setPassword (String p)
    {
        this.Password=p;
    }

    String getPassword()
    {
        return this.Password;
    }

    void setSpecialty(String s)
    {
        this.Specialty=s;
    }

    String getSpecialty()
    {
        return this.Specialty;
    }

    void setClinicName(String c)
    {
        this.ClinicName=c;
    }

    String getClinicName()
    {
        return this.ClinicName;
    }

    void setFirstName(String FirstName)
    {
        this.FirstName=FirstName;
    }

    void setLastName(String Last)
    {
        this.LastName=Last;
    }

    void setPhone(String p)
    {
        this.Phone=p;
    }

    void setID(int ID)
    {
        this.ID=ID;
    }

    void setEmail(String e)
    {
        this.Email=e;
    }

    String getFirstName()
    {
        return this.FirstName;
    }

    String getLastName()
    {
        return this.LastName;
    }

    String getPhone()
    {
        return this.Phone;
    }

    int getID()
    {
        return this.ID;
    }

    String getEmail()
    {
        return this.Email;
    }
    
    Boolean insertDrug(String dName)
    {
        try
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String q="INSERT INTO Drugs Values(NULL, NULL, ?)";
            PreparedStatement p=conn.prepareStatement(q);
            p.setString(1, dName);
            p.executeUpdate();
            conn.close();
            return true;

        }
        catch(Exception e)
        {
            return false;
        }
    }

    
    
    Boolean insertHistory(int pID,String stuff)
    {
        try
        {
            int i=0;
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String str="SELECT COUNT(*) FROM PATIENT_MEDICALHIS WHERE ID=?";
            PreparedStatement pp=conn.prepareStatement(str);
            pp.setInt(1, pID);
            ResultSet r=pp.executeQuery();
            if(r.next())
            {
                i=r.getInt(1);
            }
            
            if(i==1)
            {
            	String s="SELECT MedicalHistory FROM PATIENT_MEDICALHIS WHERE ID=?";
            	PreparedStatement p=conn.prepareStatement(s);
            	p.setInt(1, pID);
            	String existing=new String();
            	ResultSet rr=p.executeQuery();
            	if(rr.next()) {
            		existing=rr.getString(1);
            	}
            	if(existing.length()>=1) {
            		stuff+=" , ";
            		stuff+=existing;
            	}
            s="UPDATE PATIENT_MEDICALHIS SET MedicalHistory=? WHERE ID=?";
            p=conn.prepareStatement(s);
            p.setString(1, stuff);
            p.setInt(2, pID);
            p.executeUpdate();
            conn.close();
            return true;
            }
            else
            {
                String ss="INSERT INTO PATIENT_MEDICALHIS VALUES(?,?)";
                PreparedStatement pre=conn.prepareStatement(ss);
                pre.setInt(1, pID);
                pre.setString(2, stuff);
                pre.executeUpdate();
                conn.close();
                return true;
            }
        }
        catch(Exception e)
        {
            return false;
        }
    }

    
    Boolean insertConditions(int pID,String stuff)
    {
        try
        {
            int i=0;
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String str="SELECT COUNT(*) FROM PATIENT_CONDITIONS WHERE ID=?";
            PreparedStatement pp=conn.prepareStatement(str);
            pp.setInt(1, pID);
            ResultSet r=pp.executeQuery();
            if(r.next())
            {
                i=r.getInt(1);
            }
            
            if(i==1)
            {
            	String s="SELECT Conditions FROM PATIENT_CONDITIONS WHERE ID=?";
            	PreparedStatement p=conn.prepareStatement(s);
            	p.setInt(1, pID);
            	String existing=new String();
            	ResultSet rr=p.executeQuery();
            	if(rr.next()) {
            		existing=rr.getString(1);
            	}
            	if(existing.length()>=1) {
            		stuff+=" , ";
            		stuff+=existing;
            	}
            s="UPDATE PATIENT_CONDITIONS SET Conditions=? WHERE ID=?";
            p=conn.prepareStatement(s);
            p.setString(1, stuff);
            p.setInt(2, pID);
            p.executeUpdate();
            conn.close();
            return true;
            }
            else
            {
                String ss="INSERT INTO PATIENT_CONDITIONS VALUES(?,?)";
                PreparedStatement pre=conn.prepareStatement(ss);
                pre.setInt(1, pID);
                pre.setString(2, stuff);
                pre.executeUpdate();
                conn.close();
                return true;
            }
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    
    ResultSet viewAppointmentDoc(int personID)
    {
        try
        {
            connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String q="SELECT HAS.AppointmentNumber, AppointmentType, TimeDate, Appointment.ID FROM Appointment, HAS WHERE HAS.DoctorID=? AND Appointment.AppointmentNumber=HAS.AppointmentNumber";
            PreparedStatement pre=connx.prepareStatement(q);
            pre.setInt(1, personID);
            ResultSet result=pre.executeQuery();
            //connx.close();
            return result;
        }
        catch(Exception e)
        {
            return null;
        }

    }

    ResultSet getConditions(int pID)
    {
        try
        {
             connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String w="SELECT Conditions FROM PATIENT_CONDITIONS WHERE ID=?";
            PreparedStatement e=connx.prepareStatement(w);
            e.setInt(1, pID);
            ResultSet ripe=e.executeQuery();
            //conn.close();
            return ripe;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    ResultSet viewHist(int pID)
    {
        try
        {
           connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String query="SELECT MedicalHistory FROM PATIENT_MEDICALHIS WHERE ID=?";
            PreparedStatement prepo=connx.prepareStatement(query);
            prepo.setInt(1, pID);
            ResultSet reso=prepo.executeQuery();
            //conn.close();
            return reso;
        }
        catch(Exception e)
        {
            return null;
        }
    }



    
    
    ResultSet getSchedule(String u)
    {
        try{
        connx= DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
        String q="SELECT Fname, Lname, ClinicName, Hours, VacatonDays, Days FROM doctor, doctorschedule WHERE doctor.username=doctorschedule.username AND doctorschedule.username=?";
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
    void closeConn() {
    	try {
    	connx.close();
    	}
    	catch(Exception e) {
    		//do nothing
    	}
    }
    
    Boolean updateSchedule(String username, int hours, String vacaDays, String days) 
    {
      int elv=0;
        try{
       Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
       
      //int got_id=0;
       
       String check="SELECT COUNT(*) FROM doctorschedule WHERE username=?";
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
        	String get_id="SELECT ID FROM DOCTOR WHERE Username=?";
        	PreparedStatement oo=conn.prepareStatement(get_id);
        	oo.setString(1, username);
        	ResultSet got_id=oo.executeQuery();
        	if(got_id!=null) {
        		got_id.next();
        		the_id=got_id.getInt(1);
        	
        	String balan="INSERT INTO doctorschedule VALUES(?, ?, ?, ?, ?)";
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
        String q= "UPDATE doctorschedule SET hours=?, vacatondays=?, days=? WHERE Username=?";
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
    
    Boolean assignNurses(int nID, String clinicname)
    {
        try
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String q="UPDATE nurse SET ClinicName=? WHERE ID=?";
            PreparedStatement p=conn.prepareStatement(q);
            p.setString(1, clinicname);
            p.setInt(2, nID);
           
            p.executeUpdate();
            conn.close();
            
           // return "true";
            return true;


        }

        catch (Exception e)
        {
            return false;
        //	return e.getMessage();
        }
    }
    
    //new nethods:
    int getdocID(String dUser)
    {
        try
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String s="SELECT ID FROM doctor WHERE username=?";
            PreparedStatement prepare=conn.prepareStatement(s);
            prepare.setString(1, dUser);
            ResultSet res=prepare.executeQuery();
            if(res.next())
            {
            	int ret=res.getInt(1);
                conn.close();
                return ret;
                //return res.getInt(1);
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

    
    ResultSet viewPrescriptions(int pID,String dUser)
    {

        try
        {
            connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String qu="SELECT Name,Prescribed,DoctorNotes,Dosage FROM prescribes WHERE PatientID=? AND username=?";
            PreparedStatement pre=connx.prepareStatement(qu);
            pre.setInt(1, pID);
            pre.setString(2, dUser);
            ResultSet re=pre.executeQuery();
            return re;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    Boolean docPrescribes(int dID, String dName, String dUser, String prescrib, int pID, String dNotes, String dosage)
    {
        try
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String q="INSERT INTO prescribes VALUES(?,?,?,?,?,?,?)";
            PreparedStatement p=conn.prepareStatement(q);
            p.setInt(1, dID);
            p.setString(2, dName);
            p.setString(3, dUser);
            p.setString(4, prescrib);
            p.setInt(5, pID);
            p.setString(6, dNotes);
            p.setString(7, dosage);
            p.executeUpdate();
            conn.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

//end of new methods
    
    //ADD ORDER DRUGS FOR DOCTOR!!!! (copy from pharmacist and change slightly)


    ResultSet findID(String f, String l)
    {
        try
        {
            connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String query="SELECT ID FROM nurse WHERE Fname=? AND Lname=?";
            PreparedStatement prepared=connx.prepareStatement(query);
            prepared.setString(1, f);
            prepared.setString(2, l);
            ResultSet r=prepared.executeQuery();
            //conn.close();
           return r;
//return "good";
        }
        catch(Exception e)
        {
  //      	return e.getMessage();
            return null;
        }
    }

    
    
    //May need to change database -> only one doctor and one drug tuple ever (PK)???
    /*
    Boolean assignDrugs(String drugName,String docUser, String prescribedDate, int patientID, String docNotes, String dose) {
    	int elv=0;
        try{
       Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
       
      //int got_id=0;
       
       String check="SELECT COUNT(*) FROM PRESCRIBES WHERE Username=? OR Name=?";
        PreparedStatement j=conn.prepareStatement(check);
        j.setString(1, docUser);
        j.setString(2, drugName);

        ResultSet r=j.executeQuery();

        if(r!=null)
        {
            r.next();
          //  got_id=r.getInt(1);
            elv=r.getInt(1);
        }
        int elv2=0;
        String check2="SELECT COUNT(*) FROM PRESCRIBES WHERE Username=? AND Name=?";
        PreparedStatement j2=conn.prepareStatement(check);
        j2.setString(1, docUser);
        j2.setString(2, drugName);

        ResultSet r2=j2.executeQuery();

        if(r2!=null)
        {
            r2.next();
          //  got_id=r.getInt(1);
            elv2=r2.getInt(1);
        }

        if(elv==0)
        {
        	//then not prescribed yet!!!
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

    }*/
}
