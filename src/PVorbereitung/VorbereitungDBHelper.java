package PVorbereitung;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VorbereitungDBHelper {

    private Connection con;

    public void openConnection() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:sqlite:C:\\LVs\\DBP2026\\Vorbereitung\\rechnungsverwaltung.db"
            );
            System.out.println("Connection successfully established.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            // delete the Students table if it exists
            Statement stmt = con.createStatement();


            if (con != null) {
                con.close();
                System.out.println("Connection successfully closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
        try (Statement stmt = con.createStatement()) {

            // Tabelle Kunde
            stmt.execute("CREATE TABLE Kunden\n" +
                    "(\n" +
                    "        KDNR INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "        Vorname VARCHAR(20),\n" +
                    "        Nachname VARCHAR(20),\n" +
                    "        Geschlecht Varchar(10),\n" +
                    "        Bonuspunkte INTEGER\n" +
                    ")");
            System.out.println("Tabelle Kunde erstellt!");


        } catch (SQLException e) {
            System.err.println("Fehler: Eine oder mehrere Tabellen existieren bereits!");
            e.printStackTrace();
        }
    }
    public Kunde insertKunde(Kunde k) {
        String sql = "INSERT INTO Kunden(Vorname, Nachname, Geschlecht, Bonuspunkte) VALUES (?, ?, ?,?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, k.getVorname());
            pstmt.setString(2, k.getNachname());
            pstmt.setString(3, k.getGeschlecht());
            pstmt.setInt(4, k.getBonuspunkte());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Einfügen fehlgeschlagen, keine Zeilen betroffen.");
            }

            ResultSet rs = pstmt.getGeneratedKeys(); // DO NOT wrap this in try-with-resources
            if (rs.next()) {
                int id = rs.getInt(1);
                rs.close(); // explizit geschlossen, weil außerhalb von try-catch
                k.setKDNR(id);
            } else {
                rs.close();
            }

        } catch (SQLException e) {
            System.err.println("Fehler beim Einfügen von Kunde: " + e.getMessage());
            e.printStackTrace();
        }
        return k;
    }


    public Kunde getKundeById(int kdnr) {
       Kunde k =null;
        String sql = "SELECT KDNR, Vorname, Nachname, Geschlecht, Bonuspunkte FROM Kunden WHERE KDNR = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, kdnr);
            ResultSet rs =pstmt.executeQuery();
            if (rs.next()){
                k =new Kunde(rs.getInt("Kdnr"),
                        rs.getString("Vorname"),
                        rs.getString("Nachname"),
                        rs.getString("Geschlecht"),
                        rs.getInt("Bonuspunkte"));
            }
            else{
                k=new Kunde(-1,"nicht gefunden","","",0);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return k;
    }

    public List<Kunde> getKunden() {
        List<Kunde> kunden = new ArrayList<>();
        String sql = "SELECT KDNR FROM Kunden";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                kunden.add(getKundeById(rs.getInt("KDNR")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kunden;
    }

    public int updateKunde(Kunde k) {
        String sql = "UPDATE Kunden SET Vorname=?, Nachname=?, Geschlecht=?, Bonuspunkte=? WHERE kdnr=?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, k.getVorname());
            pstmt.setString(2, k.getNachname());
            pstmt.setString(3, k.getGeschlecht());
            pstmt.setInt(4, k.getBonuspunkte());
            pstmt.setInt(5, k.getKDNR());

            int rows = pstmt.executeUpdate();
            if (rows == 1) {
                System.out.println("Erfolgreich geändert");
                return 1;
            } else {
                System.out.println("Kunde wurde nicht gefunden");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void transferPoints(int kdnrVon, int kdnrAn, int punkte) {
        try {
            Kunde von = getKundeById(kdnrVon);
            Kunde an = getKundeById(kdnrAn);
            von.setBonuspunkte(von.getBonuspunkte()-punkte);
            an.setBonuspunkte(an.getBonuspunkte()+punkte);
            con.setAutoCommit(false);
            updateKunde(von);
            updateKunde(an);
            if (von.getBonuspunkte()>0){
                con.commit();
            } else {
                con.rollback();
            }
            con.setAutoCommit(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
