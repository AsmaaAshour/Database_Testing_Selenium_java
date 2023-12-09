package CrudMethodTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DatabaseTesting {
	Connection con = null;
	Statement stm = null;
	ResultSet rs;

	@BeforeTest
	public void beforetest() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "asmaa12345");
	}

	@Test(priority = 1)
	public void AddOneCustomer() throws SQLException {
		stm = con.createStatement();

		String InsertQuery = "insert into customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,city,country)"
				+ "values(77,'ABC','Automation','Tester','123-456-7890','Amman','Amman','jordan')";

		int insertedrow = stm.executeUpdate(InsertQuery);

		if (insertedrow > 0) {
			System.out.println("data added");
		} else {
			System.out.println("data cannot be added");
		}
	}

	@Test(priority = 2)
	public void UpdateCustomerNumber() throws SQLException {

		stm = con.createStatement();

		String UpdateQuery = "update customers set city='Irbid' where customerNumber=77;";

		int updaterow = stm.executeUpdate(UpdateQuery);

		if (updaterow > 0) {
			System.out.println("updated");
		} else {
			System.out.println("nothing is updated");
		}

	}

	@Test(priority = 3)
	public void SelectOneCustomer() throws SQLException {
		stm = con.createStatement();
		rs = stm.executeQuery("select * from customers where customerNumber=77");
		while (rs.next()) {
//			int customerNumber = rs.getInt("customerNumber");
//			String customerName = rs.getString("customerName");
//			String contactLastName = rs.getString("contactLastName");
//			String contactFirstName = rs.getString("contactFirstName");
			String city = rs.getString("city");
//
//			System.out.println("customerNumber: " + customerNumber);
//			System.out.println("customerName: " + customerName);
//			System.out.println("contactLastName: " + contactLastName);
//			System.out.println("contactFirstName: " + contactFirstName);
//			System.out.println("city: " + city);

			if (city.equals("Irbid")) {
				System.out.println("updated process is correct and the city now is irbid");
			} else {
				System.out.println("somthing wrong in updated process");
			}
		}
	}

	@Test(priority = 4)
	public void DeleteCustomer() throws SQLException {
		stm = con.createStatement();

		String DeleteQuery = "delete from customers where customerNumber=77";

		int deleterow = stm.executeUpdate(DeleteQuery);

		if (deleterow > 0) {
			System.out.println("deleted");
		} else {
			System.out.println("nothing is deleted");
		}
	}

	@AfterTest
	public void aftertest() {
	}

}
