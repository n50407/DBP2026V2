Datenbankprogrammierung???
JDBC ---> Java --><--- DB
Entity Framework (Microsoft C#)
Stored Procedure / Functions / PL-SQL / T-SQL

ANSI SQL (unterschiedliche Dialekte - Oracle, SQLite, MS SQL 
-- Normalformen -- Redundanzen vermeidet - Anomalien 1 Johann Grabner 8010 Graz
-- Deklarative Sprache SELECT Vorname FROM Kunden WHERE Kdnr = 1
-- DQL SELECT ........
-- DML INSERT, UPDATE, DELETE 
-- DDL CREATE TABLE, ALTER TABLE, DROP
-- DCL GRANT, REVOKE 

JDBC - Java Database Connectivity (Interface Connection, Statement, ResultSet
1. 1. Connection - DriverManager.getConnection(url - welcher Driver, welche DB, Kenwörtert)
   2. (--- external JAR - Driver (ZIP - CLASS)
   3. Statement - Connection + SQL-Text - execute Query / execute Update
   4. "SELECT Vorname FROM Kunden" ---> executeQuery --> ResultSet (Cursur - next())
   5. "UPDATE ...", "CREATE TABLE ...", "DELETE ..." ---> executeUpdate
   6. Resource-Handling - AutoCloseable -- .close(); 

Aufgabe 18.08.2026 - 08:45
1. SQLite Studio eine neue DB (Producktverwaltung) + 1 Tabelle Produkte (ID (PK), Bezeichnung, Preis, Bewertung (0-10))
2. 3 bis 5 Beispieldatensätze direkt in SQL Lite INSERT INTO
3. IntelliJ - AlleProdukteAusgeben (Connection - DriverManager, Statement - ExecuteQuery, ResultSet, next() + printf, AutoCloseable .close)
4. Auflösung 09:46 Uhr

5. Aufgabe 18.08.2026 - 10:15 -

   UpdateProduktPrice(int produktId, float neuerPreis)
   -- Produktpreis wurde erfolgreich angepasst
   --PreparedStatement UPDATE Produkte SET Preis=? WHERE Id=?
   -- pStmt.setFloat(1,neuerPreis)
   --pStamt.setIng(2, id)
    pStamt.executeUpdate();

   Auflösung 10:40 Uhr
   

   


