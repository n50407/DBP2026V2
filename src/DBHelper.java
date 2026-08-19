import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBHelper {

    private Connection con;

    public void openConnection() {
        try {
            con = DriverManager.getConnection(
                    "jdbc:sqlite:C:/LVs/DBP2026/Students.db"
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
            stmt.executeUpdate("DROP TABLE IF EXISTS Students");
            System.out.println("\nTable Students deleted successfully.");

            if (con != null) {
                con.close();
                System.out.println("Connection successfully closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createStudentTable() {
        Statement stmt = null;
        try {
            stmt = con.createStatement();

            System.out.println("Table Students created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(Student student) {
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "INSERT INTO Student (vorname, nachname, matrikelnummer, studienfach) VALUES (?, ?, ?, ?)"
            );
            pstmt.setString(1, student.getVorname());
            pstmt.setString(2, student.getNachname());
            pstmt.setInt(3, student.getMatrikelnummer());
            pstmt.setString(4, student.getStudienfach());

            pstmt.executeUpdate();

            //key auslesen
            student.setId(12);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int updateStudentsStudienFach(int id, String studienfach) {
        int rowsAffected = -1;
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "UPDATE Students SET studienfach = ? WHERE id = ?"
            );
            pstmt.setString(1, studienfach);
            pstmt.setInt(2, id);
            rowsAffected = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public Student getStudentById(int id){
        Student sDummy =new Student();
        //List nur einen Studierenden
        return  sDummy;
    }
    //List Student
    public void readAllStudents() {
        try {
            Statement stmt = con.createStatement();
            var rs = stmt.executeQuery("SELECT ID, Vorname, Nachname, Matrikelnummer, Studienfach FROM Students");

            while (rs.next()) {
                Student sRead = getStudentById(rs.getInt("ID"));
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Vorname: " + rs.getString("vorname") +
                                ", Nachname: " + rs.getString("nachname") +
                                ", Matrikelnummer: " + rs.getInt("matrikelnummer") +
                                ", Studienfach: " + rs.getString("studienfach")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readStudentsWithStudienfach() {
        try {
            Statement stmt = con.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM Students WHERE studienfach IS NOT NULL");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Vorname: " + rs.getString("vorname") +
                                ", Nachname: " + rs.getString("nachname") +
                                ", Matrikelnummer: " + rs.getInt("matrikelnummer") +
                                ", Studienfach: " + rs.getString("studienfach")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int deleteStudent(int id) {
        int rowsaffected=-1;
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "DELETE FROM Students WHERE id = ?"
            );
            pstmt.setInt(1, id);
            rowsaffected=pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  rowsaffected;
    }

    public void readStudentsFilterByStudienfach(String studienfach) {
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "SELECT * FROM Students WHERE studienfach = ?"
            );
            pstmt.setString(1, studienfach);
            var rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Vorname: " + rs.getString("vorname") +
                                ", Nachname: " + rs.getString("nachname") +
                                ", Matrikelnummer: " + rs.getInt("matrikelnummer") +
                                ", Studienfach: " + rs.getString("studienfach")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
 