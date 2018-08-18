package hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class IOPractice {

	private static void readFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while(line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeToFile(String filename) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			String result = "Ana are mere";

			bw.write(result);
			bw.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(" ");

		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			System.out.println(line);
			if(line.equals("stop")) {
				break;
			}
		}

		readFile("input.txt");
		writeToFile("output.txt");

		Scanner sc = new Scanner(new FileReader("input.txt"));
		sc.useDelimiter(" ");
		System.out.println(sc.next());
		System.out.println(sc.next());

		Writer writer = new PrintWriter(System.out);
		writer.write("asd");



		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();

		Scanner sc2 = new Scanner(System.in);
		int n = Integer.valueOf(sc2.nextLine());
		for(int i = 0; i < n ; i++) {
			String line = sc2.nextLine();
			String[] asd = line.split(" ");
			int x = Integer.valueOf(asd[0]);
			int y = Integer.valueOf(asd[1]);

			writer.write("x: " + x + "y: " + y + " line: " + line);
		}

		writer.flush();
		writer.close();
	}

}
