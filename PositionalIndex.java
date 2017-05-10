import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class PositionalIndex {

	public static void main(String[] args) throws IOException {
		
		try(Stream<Path> paths = Files.walk(Paths.get("C:\\Users\\Marcus\\Documents\\eclipse\\Workplace\\IR&WS\\Dataset"))) {
		    paths.forEach(filePath -> {
		        if (Files.isRegularFile(filePath)) {
		            System.out.println(filePath);
		        }
		    });
		} 
		
		String document = null;
		document = textToString("text.txt");
		System.out.println("The used String:" + document);

		System.out.println("Enter query: ");
		Scanner sc = new Scanner(System.in);
		String q = sc.next();
		sc.close();
		
		String[][][] al;
		al = tokenToPositionalIndex(q,document);
		System.out.println("The positions as one single Sting: " + al[0][0][2]);
		
	}

	

	static String[][][] tokenToPositionalIndex(String querry, String doc) {
		String a = null;
		querry = querry.toLowerCase();
		doc = doc.toLowerCase();
		
		ArrayList<String> querryAL = new ArrayList<String>();
		StringTokenizer querryST = new StringTokenizer(querry, " .,:()_");
		while (querryST.hasMoreTokens()) {
			a = querryST.nextToken();
			querryAL.add(a);
		}
		
		ArrayList<String> docAL = new ArrayList<String>();
		StringTokenizer docST = new StringTokenizer(doc, " .,:()_");
		while (docST.hasMoreTokens()) {
			a = docST.nextToken();
			docAL.add(a);
		}
		
		String[][][] pIndex = new String[querryAL.size()][1][3];
		for (int i = 0; i <= querryAL.size() - 1; i++) {
			
			a = "";
			String positions = "";
			
			for (int j = 0; j <= docAL.size() - 1; j++) {
				
				if (querryAL.get(i).equals(docAL.get(j)))
					positions = positions  + String.valueOf(j)+ ",";

			}
			pIndex[i][0][2] = positions;
		}

		return pIndex;
	}

	/**
	 * reads text data line for line and returns one single string
	 */
	static String textToString(String file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String b = "";
		String a = br.readLine();
		while (a != null) {
			b = (b + " " + a);
			a = br.readLine();
		}
		br.close();
		return b;
	}
}