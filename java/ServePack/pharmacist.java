package ServePack;

import java.sql.*;

public class pharmacist {
    private int ID;
    private String Username;
    private String FirstName;
    private String LastName;
    private String Password;
    private String Supply;
    public Connection connx;

    pharmacist()
    {

    }

    void setID(int i)
    {
        this.ID=i;
    }

    int getID()
    {
        return this.ID;
    }

    void setUsername(String u)
    {
        this.Username=u;
    }

    String getUsername()
    {
        return this.Username;
    }

    void setFirstName(String f)
    {
        this.FirstName=f;
    }

    String getFirstName()
    {
        return this.FirstName;
    }

    void setLastName(String l)
    {
        this.LastName=l;
    }

    String getLastName()
    {
        return this.LastName;
    }

    void setPassword(String p)
    {
        this.Password=p;
    }

    String getPassword()
    {
        return this.Password;
    }

    void setSupply( String s)
    {
        this.Supply=s;
    }

    String getSupply()
    {
        return this.Supply;
    }
    
    Boolean orderDrug(String name, String sideEffects, String company) {
		Connection conn; //= DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");

		String query;
		PreparedStatement prep;
	ResultSet result;
		
		
    	try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
			
			query="INSERT INTO DRUGS VALUES(?,?,?)";
			
			prep=conn.prepareStatement(query);
			prep.setString(1, company);
			prep.setString(2, sideEffects);
			prep.setString(3, name);
			
			
		
			prep.executeUpdate();
			
			conn.close();
    	}catch(Exception e) {
    		return false;
    	}
    	return true;
    }
    


    ResultSet getNullDrugs()
    {
        try
        {
            connx=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String q="SELECT Name FROM DRUGS WHERE Company IS NULL AND SideEffects IS NULL";
            PreparedStatement pre=connx.prepareStatement(q);
            ResultSet re=pre.executeQuery();
            //connx.close();
            return re;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    Boolean dataDrugs(String dName, String sides, String comp)
    {
        try
        {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalSystem", "root", "password");
            String s="UPDATE DRUGS SET SideEffects=?, Company=? WHERE Name=?";
            PreparedStatement p=conn.prepareStatement(s);
            p.setString(1, sides);
            p.setString(2, comp);
            p.setString(3, dName);
            p.executeUpdate();
            conn.close();
            return true;
        }

        catch(Exception e)
        {
            return false;
        }
    }
    
    void closeConn() {
    	try {
    		connx.close();
    	}catch(Exception e) {
    		return;
    	}
    }




}
