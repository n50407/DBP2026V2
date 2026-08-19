

import java.io.PrintWriter;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //
    //SELECT ID, Bezeichnung, Preis, Bewertung
    //FROM Produkte ;
    public static void AlleProdukteausgebenTeurerAls(float minPreis){
        Connection con = null;
        try
        {
            String connectionStringURL="jdbc:sqlite:C:/LVs/DBP2026/UrlaubsplanungSQLiteDB.db";
            con =DriverManager.getConnection(connectionStringURL);

            Statement stmt = con.createStatement();
            String selectProdukte="SELECT 17+2 AS Rechnung,  ID Nummer, Bezeichnung, Preis, Bewertung FROM Produkte ";
            selectProdukte="SELECT k.Id, k.Vorname, a.Bezeichnung\n" +
                    "FROM Kunden k LEFT OUTER JOIN KundenArten a\n" +
                    "ON k.ArtID_FK = a.ArtID";
           // String selectProdukte="SELECT * FROM Produkte ";
            //selectProdukte += " where preis > " + minPreis + " and preis not null ";
            //selectProdukte += " ORDER BY Preis DESC;";

            ResultSet rs = stmt.executeQuery(selectProdukte);

            ResultSetMetaData meta = rs.getMetaData();

            int numerics = 0;

            for ( int i = 1; i <= meta.getColumnCount(); i++ )
            {
                System.out.printf( "%-20s %-20s%n", meta.getColumnLabel( i ),
                        meta.getColumnTypeName( i ) );

                if ( meta.isSigned( i ) )
                    numerics++;
            }


            while ( rs.next() ){
                int id = rs.getInt("Nummer");
                String bezeichnung=rs.getString("Bezeichnung");
                int bewertung = rs.getInt("Bewertung");
                float preis = rs.getFloat("Preis");
                if ( rs.wasNull() )
                    System.out.println( "Preis ist SQL-NULL" );

                System.out.printf( "%d, %s %f %d %n", id, bezeichnung, preis,bewertung);
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

    public static void Studentsample(){
        int i=12;
Student s1=new Student();

    }

    public static void AlleProdukteausgebenTeurerAlsPreparedStmt(float minPreis){
        Connection con = null;
        try
        {
            String connectionStringURL="jdbc:sqlite:C:/LVs/DBP2026/Produktverwaltung.db";
            con =DriverManager.getConnection(connectionStringURL);


            String selectProdukte="SELECT 17+2 AS Rechnung,  ID Nummer, Bezeichnung, Preis, Bewertung FROM Produkte ";
            selectProdukte += " where preis > ? and preis not null ";
            selectProdukte += " ORDER BY Preis DESC;";

            PreparedStatement stmt = con.prepareStatement(selectProdukte);
            stmt.setFloat(1,minPreis);

            ResultSet rs = stmt.executeQuery();

            while ( rs.next() ){
                int id = rs.getInt("Nummer");
                String bezeichnung=rs.getString("Bezeichnung");
                int bewertung = rs.getInt("Bewertung");
                float preis = rs.getFloat("Preis");
                if ( rs.wasNull() )
                    System.out.println( "Preis ist SQL-NULL" );

                System.out.printf( "%d, %s %f %d %n", id, bezeichnung, preis,bewertung);
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

    public static void UpdateProdukt(int id, float neuerPreis){
        Connection con = null;
        try
        {
            String connectionStringURL="jdbc:sqlite:C:/LVs/DBP2026/Produktverwaltung.db";
            con =DriverManager.getConnection(connectionStringURL);

            con.setAutoCommit( false );
            String updateProdukte="UPDATE Produkte SET Preis = ? WHERE ID = ? ";
            PreparedStatement stmt = con.prepareStatement(updateProdukte);
            stmt.setFloat(1,neuerPreis);
            stmt.setInt(2,id);

            int rowsAffected = stmt.executeUpdate(); //Default BEGIN TRANSACTION - anschließen COMMIT TRANSACTION
            con.commit();
            //con.rollback();
            con.setAutoCommit( true );
            if (rowsAffected==1){
                System.out.println("Produkt wurde erfolgreich geändert");
            } else {
                System.out.println("Produkt wurde nicht gefunden");
            }

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

    public static int InsertProdukt(String bezeichnung, float preis, int bewertung){
        Connection con = null;
        int identityValue=0;
        try
        {
            String connectionStringURL="jdbc:sqlite:C:/LVs/DBP2026/Produktverwaltung.db";
            con =DriverManager.getConnection(connectionStringURL);

            String insterProdukte =" INSERT INTO Produkte(Bezeichnung,Preis,Bewertung) VALUES(?,?,?); ";
            PreparedStatement stmt = con.prepareStatement(insterProdukte, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,bezeichnung);
            stmt.setFloat(2,preis);
            stmt.setInt(3,bewertung);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected==1){
                System.out.println("Produkt wurde erfolgreich hinzugefügt");
            } else {
                System.out.println("Produkt wurde nicht hinzugefügt");
            }

            ResultSet rs = stmt.getGeneratedKeys(); // DO NOT wrap this in try-with-resources
            if (rs.next()) {
                identityValue = rs.getInt(1);
                rs.close();
            } else {
                rs.close();
            }

            //Autowert auslesen
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

        return  identityValue;
    }
    public static void AlleProdukteausgeben(){
        Connection con = null;
        try
        {
            String connectionStringURL="jdbc:sqlite:C:/LVs/DBP2026/Produktverwaltung.db";
            con =DriverManager.getConnection(connectionStringURL);

            Statement stmt = con.createStatement();
            String selectProdukte="SELECT 17+2 AS Rechnung,  ID Nummer, Bezeichnung, Preis, Bewertung FROM Produkte ORDER BY Preis DESC;";

            ResultSet rs = stmt.executeQuery(selectProdukte);

            while ( rs.next() ){
                int id = rs.getInt("Nummer");
                String bezeichnung=rs.getString("Bezeichnung");
                int bewertung = rs.getInt("Bewertung");
                float preis = rs.getFloat("Preis");
                if ( rs.wasNull() )
                    System.out.println( "Preis ist SQL-NULL" );

                System.out.printf( "%d, %s %f %d %n", id, bezeichnung, preis,bewertung);
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
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hallo Campus02\n");
        //AlleProdukteausgeben();
        AlleProdukteausgebenTeurerAls(100);
       // AlleProdukteausgebenTeurerAlsPreparedStmt(100);
        //UpdateProdukt(12,70);
       // int autoWert= InsertProdukt("Batterie",12,4);
        //System.out.println("Produkt mit der id " + autoWert + " wurde erstellt");
    }
    public static void mainOld(String[] args) {
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