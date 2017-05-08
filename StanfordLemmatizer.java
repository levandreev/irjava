import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class StanfordLemmatizer { 
protected StanfordCoreNLP pipeline; 

	public static int checkStopWord(String text) throws IOException{
		String stopWordListAddress = "\\Users\\Win 10\\Desktop\\stopwordlist";
		ArrayList<String> StopWordsList = new ArrayList<String>();
		StopWordsList = (ArrayList<String>) Files.readAllLines(Paths.get(stopWordListAddress));
		int checkStopWord =0;
		// i must be less equal than the number of the lines in the stopwords text file.
		for(int i=0; i<StopWordsList.size(); i++){
			if (new String(text).equals(StopWordsList.get(i))){
				checkStopWord = 1;
				break;
			}
		}
		// checkstopword=0 means the word is not a stop word.
		return checkStopWord;
	}
	public StanfordLemmatizer() { 
		// Create StanfordCoreNLP object properties, with POS tagging 
		// (required for lemmatization), and lemmatization 
		Properties props; 
		props = new Properties(); 
		props.put("annotators", "tokenize, ssplit, pos, lemma"); 
		 
		// StanfordCoreNLP loads a lot of models, so you probably 
		// only want to do this once per execution 
		pipeline = new StanfordCoreNLP(props); 
	} 
	 
	public List<String> lemmatize(String documentText) throws IOException { 
	List<String> lemmas = new LinkedList<String>(); 
	 
	// create an empty Annotation just with the given text 
	Annotation document = new Annotation(documentText); 
	 
	// run all Annotators on this text 
	pipeline.annotate(document); 
	 
	// Iterate over all of the sentences found 
	List<CoreMap> sentences = document.get(SentencesAnnotation.class); 
	for (CoreMap sentence : sentences) { 
		// Iterate over all tokens in a sentence 
		for (CoreLabel token : sentence.get(TokensAnnotation.class)) { 
			// Retrieve and add the lemma for each word into the 
			// list of lemmas 
			String tempToken = token.get(LemmaAnnotation.class);
			if (checkStopWord(tempToken)==0){
				lemmas.add(tempToken); 
			}
		} 
	} 
	 
	return lemmas; 
	} 
}
