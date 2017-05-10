import java.util.ArrayList;


public class PositionalIndexing {

	private ArrayList<ArrayList<Integer>> docLists;
	private ArrayList<String> termList;
	private String[] myDocs;

	public PositionalIndexing(String[] docs) {

		ArrayList<Integer> docList;
		docLists = new ArrayList<ArrayList<Integer>>();
		termList = new ArrayList<String>();
		myDocs = docs;

		for (int i = 0; i < myDocs.length; i++) {
			String[] tokens = myDocs[i].split("\\s+");
			for (int j = 0; j < tokens.length; j++) {
			    String token = tokens[j];
				if (!termList.contains(token)) {// a new term
//					System.out.println("Added");
					termList.add(token);
					docList = new ArrayList<Integer>();
					docList.add(new Integer(i));
//					System.out.println(docList);
					docLists.add(docList);
				} else {// an existing term
					int index = termList.indexOf(token);
//					System.out.println(termList);
					System.out.println(token);
//					System.out.println(index);
					docList = docLists.get(index);
					if (!docList.contains(new Integer(i))) {
						docList.add(new Integer(i));
						docLists.set(index, docList);
					}
				}
			}
		}
	}

	public String toString() {
		String matrixString = new String();
		ArrayList<Integer> docList;
		for (int i = 0; i < termList.size(); i++) {
			matrixString += String.format("%-15s", termList.get(i));
			docList = docLists.get(i);
			for (int j = 0; j < docList.size(); j++) {
				matrixString += docList.get(j) + "\t";
			}
			matrixString += "\n";
		}
		return matrixString;
	}

	public static void main(String[] args) {
		String[] docs = { "put new returns between paragraphs", "houses which are new in jersey",
				"home sales new rise in july" };
		PositionalIndexing pi = new PositionalIndexing(docs);
		System.out.println(pi.docLists);
		System.out.print(pi);

	}
}
