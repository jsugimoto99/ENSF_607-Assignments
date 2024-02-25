import java.sql.*;
import java.util.*;
/**
 * 
 * ENSF 607 Assignment 3 Exercise 1
 * 
 * @author Jeremy Sugimoto
 * 
 * Description: This program interacts with a mySQL 
 * database installed using a JDBC driver.
 *
 */
public class Exercise1 {

    public static void main(String[] args) throws SQLException {

        try {
            // Load the MySQL JDBC driver.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a scanner to input the database password.
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your Password: ");
            String password = scanner.next();
            scanner.close();

            // Establish a database connection with the provided URL, username, and password.
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_database", "root", password);
            System.out.println("\nConnection Established successfully\n");

            // Create a statement for executing SQL queries.
            Statement st = con.createStatement();

            // Define SQL queries for the three tables.
            String query1 = "select * from student";
            String query2 = "select * from course";
            String query3 = "select * from registration";

            // Execute Query 1 and store data in the ResultSet 'student'.
            ResultSet student = st.executeQuery(query1);
            System.out.printf(" Student Table\n");
            System.out.printf("---------------------------------------------------------------------------------------------%n");
            System.out.printf("| %-10s | %-20s | %-20s | %-30s |\n", "StudentId", "FirstName", "LastName", "Location");
            System.out.printf("---------------------------------------------------------------------------------------------%n");

            // Iterate through the 'student' ResultSet and print the data in a tabular format.
            while (student.next()) {
                String studentId = student.getString("StudentId");
                String firstName = student.getString("FirstName");
                String lastName = student.getString("LastName");
                String location = student.getString("Location");
                System.out.printf("| %-10s | %-20s | %-20s | %-30s |\n", studentId, firstName, lastName, location);
            }
            System.out.printf("---------------------------------------------------------------------------------------------%n\n");

            // Execute Query 2 and store data in the ResultSet 'course'.
            ResultSet course = st.executeQuery(query2);
            System.out.printf(" Course Table\n");
            System.out.printf("------------------------------------------------------------\n");
            System.out.printf("| %-10s | %-20s | %-20s |\n", "CourseId", "CourseName", "CourseTitle");
            System.out.printf("------------------------------------------------------------\n");

            // Iterate through the 'course' ResultSet and print the data in a tabular format.
            while (course.next()) {
                String courseId = course.getString("CourseId");
                String courseName = course.getString("CourseName");
                String courseTitle = course.getString("CourseTitle");
                System.out.printf("| %-10s | %-20s | %-20s |\n", courseId, courseName, courseTitle);
            }
            System.out.printf("------------------------------------------------------------\n");

            // Execute Query 3 and store data in the ResultSet 'registration'.
            ResultSet registration = st.executeQuery(query3);
            System.out.printf(" Registration Table\n");
            System.out.printf("----------------------------------------\n");
            System.out.printf("| %-10s | %-10s | %-10s |\n", "RegistrationId", "CourseId", "StudentId");
            System.out.printf("----------------------------------------\n");

            // Iterate through the 'registration' ResultSet and print the data in a tabular format.
            while (registration.next()) {
                String registrationId = registration.getString("RegistrationId");
                String courseId = registration.getString("CourseId");
                String studentId = registration.getString("StudentId");
                System.out.printf("| %-10s | %-10s | %-10s |\n", registrationId, courseId, studentId);
            }
            System.out.printf("----------------------------------------\n\n");

            // Close the database connection.
            con.close();

            System.out.println("Query executed...");

        } catch (Exception e) {
            // Handle any exceptions that may occur during database connection or query execution.
            System.out.println(e);
        }
    }
}

