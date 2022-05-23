package com.lockme;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

	static File folder = null;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		boolean exit = false;
		boolean showHeader = true;

		Integer option = 0;

		while (!exit) {
			if (showHeader) {
				createDirectory();
				header();
			}
			optionsSelection();
			try {

				option = Integer.parseInt(scanner.nextLine());
				printMessage("Selected option is: " + option);

				switch (option) {
				case 1:
					showHeader = false;
					getAllFiles();
					break;
				case 2:
					showHeader = false;
					createFile();
					break;
				case 3:
					showHeader = false;
					deleteFile();
					break;
				case 4:
					showHeader = false;
					searchFiles();
					break;
				case 5:
					System.out.println("Closing your application... \nThank you!");
					exit = true;
					break;
				default:
					System.out.println("You have made an invalid option!");
					exit = true;
				}
			} catch (NumberFormatException e) {
				printMessage("Please print only numbers");
			}
		}

	}

	private static void createDirectory() {
		File[] rootDrive = File.listRoots();
		folder = new File(rootDrive[0] + "//lockme");
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

	private static void printMessage(String message) {
		System.out.println(" ----------------------");
		System.out.println(" " + message);
		System.out.println(" ----------------------");
	}

	private static void header() {
		System.out.println("");
		System.out.println("****************************************************");
		System.out.println("*********** Welcome to Lockers Pvt. Ltd. ***********");
		System.out.println("****************************************************");
		System.out.println("*************** Owner: Sachin Tank *****************");
		System.out.println("****************************************************");
	}

	private static void optionsSelection() {
		String[] arr = { "\n1. Display all the files ", "2. Add a new file ", "3. Delete a file ",
				"4. Search for a file", "5. Close the application" };
		int[] arr1 = new int[arr.length];
		int slen = arr1.length;
		for (int i = 0; i < slen; i++) {
			System.out.println(arr[i]);
		}

		System.out.println("\nEnter your choice:\t");
	}

	private static void getAllFiles() {
		// System.out.println("In getAllFiles");

		if (folder.list().length > 0) {
			File[] listOfFiles = folder.listFiles();
			for (File myFile : listOfFiles) {
				System.out.println(myFile.getName());
			}
		} else {
			printMessage("The directory " + folder.getPath() + " is empty");
		}
	}

	private static void createFile() {
		// System.out.println("In createFile");

		FileWriter writer = null;
		try {
			System.out.println("What is the name of your new file?");
			File newFile = new File(folder + "//" + scanner.nextLine());
			writer = new FileWriter(newFile);

			System.out.println("And the contents in your file:");
			writer.write(scanner.nextLine());
			writer.close();
			printMessage("File created successfully and added to the " + folder);

		} catch (Exception ex) {
			printMessage(ex.getMessage());
		}

	}

	private static void deleteFile() {
		// System.out.println("In deleteFile");

		try {

			System.out.println("Write the name of the file you want to delete:");
			File fileToDelete = new File(folder + "//" + scanner.nextLine());

			if (fileToDelete.exists()) {
				fileToDelete.delete();
				printMessage("File deleted successfully.");
			} else {
				printMessage("File does not exist in " + folder);
			}

		} catch (Exception e) {
			printMessage("There was an error deleting the file");
		}

	}

	private static void searchFiles() {
		// System.out.println("In searchFiles");

		try {

			System.out.println("Write the name of the file you want to find:");
			File fileTofind = new File(folder + "//" + scanner.nextLine());

			if (fileTofind.exists()) {
				printMessage("File exists");
			} else {
				printMessage("File does not exist in " + folder);
			}

		} catch (Exception e) {
			printMessage("There was an error searching the file.");
		}
	}
}
