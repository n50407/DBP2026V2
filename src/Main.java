package at.campus02;

import java.io.PrintWriter;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello Campus02! :-)\n");



        Connection con = null;

        try
        {
            con =DriverManager.getConnection(
                    "jdbc:sqlite:C:\\LVs\\DBP2026\\UrlaubsplanungSQLiteDB.db"
                    //Open Module Settings
                    //Add external Jar File - Java -- sqlite-jdbc-3.14.2

            );

            DriverManager.setLogWriter( new PrintWriter( System.out ) );
            Statement stmt = con.createStatement();
            //stmt.executeQuery() --select ---> ResultSet
            //stmt.executeUpdate CREATE TABLE; INSERT, UPDATE;  DELETE, ALTER;

           // String createDummy="CREATE TABLE myDummy(i int)";
            //stmt.executeUpdate(createDummy);

            String search ="u";

            String myQueryString = "SELECT * FROM Kunden WHERE vorname like '%" +  search + "%'";

            String searchPrep = "%u%";
            String myQueryStringPrep = "SELECT * FROM Kunden WHERE vorname like ? ";
            PreparedStatement pStmt =  con.prepareStatement(myQueryStringPrep);
            pStmt.setString(1,searchPrep);


            //Update Urlaube set Tage = 17 WHERE ID = 1    Update Urlaube Set Tage = ? WHERE ID = ?
            //p.setInt(1, 17)
            //p.setInt(2,1)
            //PreparedStatment -- executeUpdate
            //INSERT INTO Urlaube(ID, Tag, ART) VALUES(?,?,?)
            //p.setInt(1,37);
            //p.setInt(2,19);
            //p.setString(3,"Erholung")
            //p.executeUpdate();
            //where Punkte > ?
            //DELETE FROM Kunden Where ID = ?




//            ResultSet rs = stmt.executeQuery( myQueryString );
            ResultSet rs = pStmt.executeQuery();

            while ( rs.next() ){
                String vorname=rs.getString("Vorname");
                int punkte = rs.getInt("Punkte");
                if ( rs.wasNull() )
                    System.out.println( "Punkte sind SQL-NULL" );


                System.out.printf( "%d, %s %s%n", rs.getInt(1),
                       vorname, punkte );
            }


            rs.close();

            stmt.close();
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( con != null )
                try { con.close(); } catch ( SQLException e ) { e.printStackTrace(); }
        }

    }
}