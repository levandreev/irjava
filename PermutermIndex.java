
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PermutermIndex {

	public static void main(String[] args) throws IOException {
		String b = null;
		b = textToString("text.txt");
		System.out.println("The used String:" + b);

		ArrayList<String> al = new ArrayList<String>();
		al = tokenToArray(b);
		System.out.println("The created Array List: " + al);

		HashMap<String, String> hm = new HashMap<String, String>();
		hm = fillHashMap(al);
		System.out.println("The created Hash Map: " + hm);

		System.out.println("Enter a word you are searching for: ");
		Scanner sc = new Scanner(System.in);
		String t = sc.next()+"$";
		sc.close();
		System.out.println("Searched word(s): "+wildcard(t,hm));
	}

	/**
	 * reads text data line for line and returns one single string
	 */
	static ArrayList<String> wildcard(String w, HashMap<String, String> hm) {
		w = w.toLowerCase();
		ArrayList<String> b = new ArrayList<String>();
		String term = "";
		String term2 = "";
		for (int i = 0; i <= w.length() - 1; i++) {
			if (!(term + w.charAt(i)).equals(term + "*"))
				term = term + "" + w.charAt(i);
			else {
				term2 = term;
				term = "";
			}
		}
		term = term + term2;
		System.out.println("The word we are looking for: " + term);

		for (Iterator<String> iterator = hm.keySet().iterator(); iterator.hasNext();) {
			String a = "";
			String next = iterator.next();

			if (term.length() <= next.length())
				for (int i = 0; i <= term.length() - 1; i++) {
					a = a + "" + next.charAt(i);
				}
			
			if (term.equals(a)) {
				b.add(hm.get(next));
				System.out.println(term + " --> " + next + " = " + hm.get(next));
			}
		}
		return b;
	}

	/**
	 * compares elements of an arraylist with each other to identify doublets,
	 * creates all permutations of each element as keys and the original as
	 * value and finally returns hashmap
	 */
	static HashMap<String, String> fillHashMap(ArrayList<String> al) {
		HashMap<String, String> hm = new HashMap<String, String>();

		for (int i = al.size() - 1; i >= 0; i--) {
			boolean doublets = false;
			String original = al.get(i);
			String original$ = original+"$";

			for (int j = i - 1; j >= 0; j--) {
				String original2 = al.get(j);
				if (original.equals(original2))
					doublets = true;
			}

			if (doublets == false) {
				for (int k = 0; k <= original$.length() - 1; k++) {
					String permutation = "";
					for (int p = 1; p <= original$.length(); p++) {
						permutation = permutation + "" + original$.charAt((p + k) % original$.length());
					}
					hm.put(permutation, original);
				}
			}
		}
		return hm;
	}

	/**
	 * separates a string into tokens and returns an arraylist with the token,
	 * wandelt groß- in kleinbuchstaben um, eleminiert eine
	 * selbstgewählte auswahl an sonderzeichen und gibt die arraylist aus
	 */
	static ArrayList<String> tokenToArray(String string) {
		String a = null;
		string = string.toLowerCase();
		ArrayList<String> al = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(string, " .,:()_");
		while (st.hasMoreTokens()) {
			a = st.nextToken();
			al.add(a);
		}
		return al;
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