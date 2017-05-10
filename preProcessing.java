// before running the code you need to change the following direcoty paths:
// newDirectoryAddress, rootDirectoryAddress, stopWordListAddress
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Properties;
//
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
//import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
//import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
//import edu.stanford.nlp.pipeline.Annotation;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.util.CoreMap;



public class preProcessing {
	public static void readFileContent(String directoryName, String folderName, String fileName, String newDirectoryAddress){
		String docAddress = directoryName + folderName + "\\" + fileName;
		try {
			System.out.println("test");
			File file = new File(docAddress);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			//System.out.println(stringBuffer.toString());
			StanfordLemmatizer lem = new StanfordLemmatizer();

			File newFile = new File(newDirectoryAddress + "\\" + folderName);
			newFile.mkdir();
			newFile.createNewFile();
			PrintWriter writer = new PrintWriter(newDirectoryAddress + "\\" + folderName + "\\" + fileName, "UTF-8");
		    writer.println(lem.lemmatize(stringBuffer.toString()));
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readFileAndDirectoryNames(String directoryName, String newDirectoryAddress){
		File rootfolder = new File(directoryName);
		File[] listOfFolders = rootfolder.listFiles();
	
	    for (int i = 0; i < listOfFolders.length; i++) {
	  
			File innerfolder = new File(directoryName + listOfFolders[i].getName());
			System.out.println(innerfolder.toString());
//			System.out.println(innerfolder.listFiles());
			File[] listOfFiles = innerfolder.listFiles();
			
			for (int j=0; j<listOfFiles.length; j++){
				readFileContent(directoryName , listOfFolders[i].getName() , listOfFiles[j].getName(), newDirectoryAddress);
			}
	    }
	}

	public static void main(String[] args) throws IOException {
		String newDirectoryAddress = "C:\\Users\\Dota\\Desktop\\output_20\\";
		String rootDirectoryAddress = "C:\\Users\\Dota\\Desktop\\20_newsgroups\\";
		
		File newFile = new File(newDirectoryAddress);
		newFile.mkdir();
		newFile.createNewFile();
		readFileAndDirectoryNames(rootDirectoryAddress,newDirectoryAddress);	
		
		
		
		}
	}
