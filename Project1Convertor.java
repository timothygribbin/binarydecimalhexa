import java.util.Scanner;

public class Project1Convertor {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String answer;//Originally used int but was way too easy to make program throw an error
		do {
			//Display menu and prompt user for their choice 
			System.out.printf("Options: \n1: Binary to hexadecimal and decimal\n2: Hexadecimal to binary and decimal\n3: Decimal to binary and hexadecimal\n4: Exit\n");
			System.out.print("Enter your menu choice: ");
			answer = sc.nextLine();
			//Check if valid input(greater or lesser than given menu or not a length of 1)
			if(answer.charAt(0) > '4' || answer.charAt(0) < '1' || answer.length() != 1) {
				System.out.println("Invalid menu choice. Please enter a number between 1 and 4.");
			}
			//Different cases based on user's input
			switch(answer) {
			case "1":
				option1(sc);
				break;
			case "2":
				option2(sc);
				break;
			case "3": 
				option3(sc);
				break;
			}	
		} while(!(answer.equals("4")));
		System.out.println("Program terminated");
		sc.close();
	}
	public static void option1(Scanner sc) {
		//Prompt for original binary number
		System.out.print("Enter a binary number: ");
		String binary = sc.nextLine();
		//Validate user input
		if(validateBinary(binary) == false) {
			System.out.println("Invalid binary number (Only enter characters 0 and 1)");
		}
		//If valid input, convert and display
		else {
			String binToDec = binaryToDecimal(binary);
			String binToHex = binaryToHex(binary);
			System.out.printf("Binary number: %s\nDecimal number: %s\nHexadecimal number: %s\n",binary, binToDec, binToHex);
		}
	}
	public static void option2(Scanner sc) {
		System.out.print("Enter a hexadecimal number: ");
		String hex = sc.nextLine().toUpperCase();//Handles lowercase letters by making all letters uppercase
		//Validate user input
		if(validateHexadecimal(hex) == false) {
			System.out.println("Invalid hexadecimal number (Only enter characters between 0 and 9 and between A and F)");
		}
		//if input is valid, convert and display
		else {
			String hexToDec = hexToDecimal(hex);
			String hexToBin = hexToBinary(hex);
			System.out.printf("Hexadecimal number: %s\nDecimal number: %s\nBinary number: %s\n",hex, hexToDec, hexToBin);
		}
	}
	public static void option3(Scanner sc) {
		System.out.print("Enter a decimal number: ");
		String dec = sc.nextLine();
		//Validate user input
		if(validateDecimal(dec) == false) {
			System.out.println("Invalid decimal number (Only enter characters between 0 and 9)");
		}
		//If input is valid, convert and display
		else {
			String decToBin = decimalToBinary(dec);
			String decToHex = decimalToHex(dec);
			System.out.printf("Decimal number: %s\nBinary number: %s\nHexadecimal number: %s\n",dec, decToBin, decToHex);
		}
	}
	public static String binaryToDecimal(String binary) {
		//Get integer value of binary number
		long decimalInt = 0; //Use long to widen the range that can be converted
		for(int i = 0; i < binary.length(); i++) {
			decimalInt = decimalInt * 2 + (binary.charAt(i) - '0');
		}
		if(decimalInt ==0) {
			return "0";
		}
		//Convert decimal int to string
		String decStr = "";
		while(decimalInt > 0) {
			char digit = (char)(decimalInt % 10 + '0');
			decStr = digit + decStr;
			decimalInt /= 10;
		}
		return decStr;
	}
	public static String binaryToHex(String binary) {
		//Use other methods to convert from binary to hex
		String dec = binaryToDecimal(binary);
		return decimalToHex(dec);
	}
	public static String hexToDecimal(String hex) {
		//Get integer decimal value for the hexadecimal number
		long decimalInt = 0;//Use long to widen the range that can be converted
		for(int i = 0; i < hex.length(); i++) {
			if(hex.charAt(i) <= '9') {
				decimalInt = decimalInt * 16 + (hex.charAt(i) - '0');
			}
			else {
				decimalInt = decimalInt * 16 + (hex.charAt(i) - 'A' + 10);
			}
		}
		//Handle case if input is 0 (Blank answer rather than 0 before this fix)
		if(decimalInt == 0) {
			return "0";
		}
		//Convert integer value back to string
		String decStr = "";
		while(decimalInt > 0) {
			char digit = (char)(decimalInt % 10 + '0');
			decStr = digit + decStr;
			decimalInt /= 10;
		}
		return decStr;
	}
	public static String hexToBinary(String hex) {
		//Use other methods to convert from hex to binary
		String dec = hexToDecimal(hex);
		return decimalToBinary(dec);
	}
	public static String decimalToHex(String decimal) {
		//Get integer value of given value
		long decimalInt = 0; //Use long to widen the range that can be converted 
		for(int i = 0; i < decimal.length(); i++) {
			decimalInt = decimalInt * 10 + (decimal.charAt(i)- '0');
		}
		//Handle case if input is 0 (Blank answer rather than 0 before this fix)
		if(decimalInt == 0) {
			return "0";
		}
		//Convert integer value to hexadecimal 
		String hexStr = "";
		while(decimalInt  > 0) {
			long rem = decimalInt % 16;
			if(rem  < 10) {
				hexStr = (char)('0' + rem) + hexStr;
			}
			else {
				hexStr = (char)('A' + rem -10) + hexStr;
			}
			decimalInt /= 16;
		}
		return hexStr;
	}
	public static String decimalToBinary(String decimal) {
		//Get integer value of decimal
		long decimalInt = 0;
		for(int i = 0; i < decimal.length(); i++) {
			decimalInt = decimalInt * 10 + (decimal.charAt(i)- '0');
		}
		//Handle case if input is 0 (Blank answer rather than 0 before this fix)
		if(decimalInt == 0) {
			return "0";
		}
		//Convert int value to binary string
		String binaryStr = "";
		while(decimalInt  > 0) {
			long rem = decimalInt % 2;
			binaryStr = (char)(rem + '0') + binaryStr;
			decimalInt /= 2;
		}
		return binaryStr;
	}
	public static boolean validateHexadecimal(String input) {
		//Go through every character and check if input is valid for a hex number
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(!((c >= 'A' && c <= 'F'||(c >= '0' && c <= '9')))) {
				return false;
			}
		}
		return true;
	}
	public static boolean validateBinary(String input) {
		//Check if each character is valid 
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(!(c == '0' || c == '1')) {
				return false;
			}
		}
		return true;
	}
	public static boolean validateDecimal(String input) {
		//Check if each character is a valid decimal character 
		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(!(c >= '0' && c <= '9')) {
				return false;
			}
		}
		return true;
	}
}
