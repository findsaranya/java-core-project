package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LockedMeApp {
	public static final String location = "C://Saranya/test/";
	public static Scanner sc = new Scanner(System.in);

	public static void fileListAndCount() {
		try {
			File fileObj = new File(location);
			String[] filesListArr = fileObj.list();
			List<String> fileList = Arrays.asList(filesListArr);
			Long filesCount = fileList.stream().sorted().filter(x -> {
				String fileLocation = location.concat(x);
				File fileItem = new File(fileLocation);
				return fileItem.isFile();
			}).count();
			Long foldersCount = fileList.stream().sorted().filter(x -> {
				String fileLocation = location.concat(x);
				File fileItem = new File(fileLocation);
				return fileItem.isDirectory();
			}).count();
			displayFileList(fileList,filesCount,foldersCount); 
			
			
			
		}catch(NullPointerException ex) {
			System.out.println("Invalid location path");
		}
		

	}

	public static void createAFile() {
		String fileName, fileExtension;
		BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the name of the file");
			fileName = br.readLine();
			System.out.println("Enter the file extension");
			fileExtension = br.readLine();
			if(!fileName.isBlank()) {
			String removeSpaces = fileExtension.isBlank() ? "txt" : fileExtension.trim().toLowerCase();
			String fileNameWithExtension = fileName.trim().toLowerCase().replace(" ", "_") + "." +removeSpaces ;
			String fileLocation = location.concat(fileNameWithExtension);

			File fileObj = new File(fileLocation);
			if (fileObj.createNewFile()) {
				displayOutput("File "+ fileLocation+" Created Successfully!!");
			} else {
				
				displayOutput("File " +fileLocation +" already Exists!!");
				
			}
			}else {
				displayOutput("Invalid fileName or file Extension");
			}	
			
		} catch (IOException e) {
			System.out.println("Location path doesn't exists/n");
		}
		
	}

	public static void deleteAFile() {
		String fileName;
		System.out.println("Enter the filename to be deleted");
		sc.nextLine();
		fileName = sc.nextLine();
		String fileLocation = location.concat(fileName);
		File fileObj = new File(fileLocation);
		String status = fileObj.delete() ? "File deleted SuccessFully!!" : "File Not found!!";
		displayOutput(status);
	}

	public static void searchAFile() {
		String fileName;
		System.out.println("Enter the filename to be searched");
		sc.nextLine();
		fileName = sc.nextLine();
		String fileLocation = location.concat(fileName);
		File fileObj = new File(fileLocation);
		boolean isExists = fileObj.exists();
		boolean isAFile = fileObj.isFile();
		String status = isExists && isAFile ? fileName + " found !!" : fileName + " Not found!!";
		displayOutput(status);
	}

	public static void main(String[] args) {
		displayOutput("Welcome to Company Lockers Pvt. Ltd.");
		displayOutput("Created by\nSaranya Govindaraj");
		loop: while (true) {
			
			try {
				System.out.println(
						"1.View File list\n2.File Operation\n3.Exit\n");
				System.out.println("Enter your choice\n");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					fileListAndCount();
					break;
				case 2:
					fileOperation();
					break;
				case 3:
					System.out.println("Thank you visit again.\n");
					break loop;
				default:
					System.out.println("Invalid option\n");

				}
			}catch(InputMismatchException e) {
				 sc.nextLine();
                 System.out.println("You must enter an interger. Try again.");
			}
			

		}
		sc.close();

	}
	public static void fileOperation() {
		
		operation : while(true) {
			
			try {
				System.out.println(
						"Manipulation of files.\n1.Create a file\n2.Delete a file\n3.Search a file\n4.Exit\n");
				System.out.println("Enter your choice\n");
				int option = sc.nextInt();
				switch(option) {
				case 1:
					createAFile();
					break;
				case 2:
					deleteAFile();
					break;
				case 3:
					searchAFile();
					break;
				case 4:
					break operation;
					default:
					  System.out.println("Invalid option");
				}
			}catch(InputMismatchException e) {
				 sc.nextLine();
                 System.out.println("You must enter an interger. Try again.");
			}
			
		}
		
	}
	
	public static void displayOutput(String result) {
		System.out.println(("*").repeat(100));
		System.out.println(result);
		System.out.println(("*").repeat(100)+"\n");
	}
	
	public static void displayFileList(List<String> fileList,Long fileCount,Long foldersCount) {
		System.out.println(("*").repeat(100)+"\n");
		System.out.println("Total files count = " + fileCount);
		System.out.println("Total folders count = " + foldersCount);
		System.out.println("Files and Folders in the current Directory\n");
		fileList.stream().sorted().forEach(System.out::println);
		System.out.println(("*").repeat(100)+"\n");
	}
}