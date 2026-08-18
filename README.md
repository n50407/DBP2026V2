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
2. 
