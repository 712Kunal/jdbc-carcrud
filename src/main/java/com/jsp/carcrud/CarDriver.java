package com.jsp.carcrud;

import java.util.Scanner;

public class CarDriver {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // has-a relationship

		boolean flag = true;

		while (flag) {
			System.out.println("\nCAR'S OPERATIONS ->");
			System.out.println("1. INSERT A CAR");
			System.out.println("2. UPDATE THE PROPERTY OF A CAR");
			System.out.println("3. DELETE THE PROPERTY OF A CAR");
			System.out.println("4. READ THE PROPERTIES OF A CAR");
			System.out.println("5. EXIT");
			System.out.println();

			System.out.println("\nWhich operation do you want to perform?");

			int choice = sc.nextInt();

			switch (choice) {

			case 1: {
				CarRepo.insertCar(sc);
				System.out.println("\nData added successfully!!!!");
				break;
			}

			case 2: {
				CarRepo.updateCar(sc);
				System.out.println("\nData updated successfully!!!!");
				break;
			}

			case 3: {
				CarRepo.deleteCar(sc);
				System.out.println("\nData deleted successfully!!!!");
				break;
			}

			case 4: {
				CarRepo.readCar(sc);
				System.out.println("\nData fetched successfully!!!!");
				break;
			}
			case 5: {
				System.out.println("5. Do you want to exit the program? \n1. YES \n2. NO");
				int id = sc.nextInt();

				if (id == 1) {
					sc.close();
					CarRepo.close();
					flag = false;
				} else {
					continue;
				}
				break;
			}

			default: {
				System.out.println("Invalid Choice!");
				break;
			}
			}
		}
	}
}
