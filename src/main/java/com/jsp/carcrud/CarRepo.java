package com.jsp.carcrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;

public class CarRepo {
	private static Connection conn; // Encapsulation

	static {
		try {
			Class.forName("org.postgresql.Driver");

			Dotenv dotenv = Dotenv.load();

			String url = dotenv.get("DB_URL");
			String username = dotenv.get("DB_USERNAME");
			String password = dotenv.get("DB_PASSWORD");

			conn = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void insertCar(Scanner sc) {
		try {
			String insertQuery = "INSERT INTO car VALUES(?,?,?,?,?,?,?)";
			PreparedStatement st = conn.prepareStatement(insertQuery); // Abstraction (prepareStatement)

			System.out.println("ENTER THE CAR'S DATA ->");
			System.out.print("Enter the Car ID: ");
			int id = sc.nextInt();

			System.out.print("Enter the Car Brand: ");
			String brand = sc.next();

			System.out.print("Enter the Car model: ");
			String model = sc.next();

			System.out.print("Enter the Car color: ");
			String color = sc.next();

			System.out.print("Enter the Car price: ");
			double price = sc.nextDouble();

			System.out.print("Enter the number of seats: ");
			int seats = sc.nextInt();

			System.out.print("Enter the avg of the car: ");
			double avg = sc.nextDouble();

			st.setInt(1, id);
			st.setString(2, brand);
			st.setString(3, model);
			st.setString(4, color);
			st.setDouble(5, price);
			st.setInt(6, seats);
			st.setDouble(7, avg);

			st.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateCar(Scanner sc) {
		try {
			String updateQuery = "UPDATE car SET color=? WHERE id=?";
			PreparedStatement st = conn.prepareStatement(updateQuery); // Inheritance (Statement => PreparedStatement)

			System.out.println("ENTER THE DATA FOR THE UPDATION ->");
			System.out.print("Enter the id of the car to update: ");
			int id = sc.nextInt();
			System.out.print("Enter the color of the car to updated: ");
			String color = sc.next();

			st.setString(1, color);
			st.setInt(2, id);

			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteCar(Scanner sc) {
		try {
			String deleteQuery = "DELETE FROM car WHERE id=?";
			PreparedStatement st = conn.prepareStatement(deleteQuery);

			System.out.println("ENTER THE DATA FOR THE DELETION ->");
			System.out.print("Enter the car's id to delete: ");
			int id = sc.nextInt();

			st.setInt(1, id);
			st.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void readCar(Scanner sc) {
		try {
			String readQuery = "SELECT * FROM car";
			PreparedStatement st = conn.prepareStatement(readQuery);
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				int id = rs.getInt(1); // Polymorphism ( getInt(columnNumber) or getInt(columnName))
				String brand = rs.getString(2);
				String model = rs.getString(3);
				String color = rs.getString(4);
				double price = rs.getDouble(5);
				int seats = rs.getInt(6);
				double avg = rs.getDouble(7);

				System.out.println(id + " | " + brand + " | " + model + " | " + color + " | " + price + " | " + seats
						+ " | " + avg);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		try {
			conn.close();
			System.out.println("Program exited successfully!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
