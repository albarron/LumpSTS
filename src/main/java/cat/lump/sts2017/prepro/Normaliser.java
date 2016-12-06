package cat.lump.sts2017.prepro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cat.lump.aq.basics.io.files.FileIO;

/**
 * Normalises an input text/file
 * 
 * @author cristina
 * @since Dec 5, 2016
 */
public class Normaliser {
	
	/**
	 * Normalises the text in a file by calling the normaliser for every line 
	 * 
	 * @param input
	 * 			Input file
	 * @param output
	 * 			Output file
	 * @param language
	 * 			Language of the text
	 */
	public static void normalise(File input, File output, String language){

		// Initilise the writer
		FileIO.deleteFile(output);
	    FileWriter fw = null;
	    BufferedWriter bw = null;
		try {
			fw = new FileWriter(output, true);
			bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read the input
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(input);
		    sc = new Scanner(inputStream, "UTF-8");
		    sc.useDelimiter("[\r\n]+");
		    int i = 0;
		    while (sc.hasNext()) {
		        String line = sc.next();
		        line = replacements(line, language);
		        bw.append(line);
		        bw.newLine();
		        // Write every 10000 lines
		        if (i%10000==0){
		        	bw.flush();
		        }
		        i++;
		    }
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close everything
		    if (inputStream != null) {
		        try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		    if (sc != null) {
		        sc.close();
		        try {
		        	bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
			
	}
	
	/**
	 * 
	 * Normalises an input string. Some language-dependent replacements are made.
	 * 
	 * @param input
	 * 			Input string
	 * @param lang
	 * 			Language of the text
	 * @return
	 */
	public static String replacements(String input, String lang){
		
		    input = input.replaceAll("\r","");
		    // remove extra spaces
		    //input = input.replaceAll("("," (");
		    //input = input.replaceAll(")",") "); 
		    //input = input.replaceAll("\) ([\.\!\:\?\;\,])","\)$1");
		    input = input.replaceAll("\\( ","(");
		    input = input.replaceAll(" \\)",")");
		    input = input.replaceAll(" %","%");
		    input = input.replaceAll(" :",":");
		    input = input.replaceAll(" ;",";");
		    // normalize unicode punctuation
		    input = input.replaceAll("`","\'");
		    input = input.replaceAll("\'\'"," \" ");
		    input = input.replaceAll("„","\"");
		    input = input.replaceAll("“","\"");
		    input = input.replaceAll("”","\"");
		    input = input.replaceAll("–","-");
		    input = input.replaceAll("—"," - "); 
		    //input = input.replaceAll(" +"," ");
		    input = input.replaceAll("´","\'");
		    input = input.replaceAll("‘","\"");
		    input = input.replaceAll("‚","\"");
		    input = input.replaceAll("’","\"");
		    input = input.replaceAll("''","\"");
		    input = input.replaceAll("´´","\"");
		    input = input.replaceAll("…","...");
		    // French quotes
		    input = input.replaceAll(" « "," \"");
		    input = input.replaceAll("« ","\"");
		    input = input.replaceAll("«","\"");
		    input = input.replaceAll(" » ","\" ");
		    input = input.replaceAll(" »","\"");
		    input = input.replaceAll("»","\"");
		    // handle pseudo-spaces
		    input = input.replaceAll(" %","%");
		    input = input.replaceAll("nº ","nº ");
		    input = input.replaceAll(" :",":");
		    input = input.replaceAll(" ºC"," ºC");
		    input = input.replaceAll(" cm"," cm");
		    //input = input.replaceAll(" \?","\?");
		    //input = input.replaceAll(" \!","\!");
		    input = input.replaceAll(" ;",";");
		    input = input.replaceAll(", ",", "); 
		    // remove non-printing chars
		    input = input.replaceAll("\\p{C}"," ");
		    // replaceAll unicode punctuation
		    input = input.replaceAll("，",",");
		    input = input.replaceAll("。 *",". ");
		    input = input.replaceAll("、",",");
		    input = input.replaceAll("”","\"");
		    input = input.replaceAll("“","\"");
		    input = input.replaceAll("∶",":");
		    input = input.replaceAll("：",":");
		    input = input.replaceAll("？","?");
		    input = input.replaceAll("《","\"");
		    input = input.replaceAll("》","\"");
		    input = input.replaceAll("）",")");
		    input = input.replaceAll("！","!");
		    input = input.replaceAll("（","(");
		    input = input.replaceAll("；",";");
		    input = input.replaceAll("１","1");
		    input = input.replaceAll("」","\"");
		    input = input.replaceAll("「","\"");
		    input = input.replaceAll("０","0");
		    input = input.replaceAll("３","3");
		    input = input.replaceAll("２","2");
		    input = input.replaceAll("５","5");
		    input = input.replaceAll("６","6");
		    input = input.replaceAll("９","9");
		    input = input.replaceAll("７","7");
		    input = input.replaceAll("８","8");
		    input = input.replaceAll("４","4");
		    input = input.replaceAll("～","~");
		    input = input.replaceAll("’","\'");
		    input = input.replaceAll("…","...");
		    input = input.replaceAll("━","-");
		    input = input.replaceAll("〈","<");
		    input = input.replaceAll("〉",">");
		    input = input.replaceAll("【","[");
		    input = input.replaceAll("】","]");
		    input = input.replaceAll("％","%");
		 
		    // French apostrophes are separated in some corpora
		    if (lang.equalsIgnoreCase("fr")) {
				input = input.replaceAll("c '","c'");
				input = input.replaceAll("d '","d'");
				input = input.replaceAll("l '","l'");
			 	input = input.replaceAll("n '","n'");
			 	input = input.replaceAll("s '","s'");
				input = input.replaceAll("j '","j'");
			 	input = input.replaceAll("qu '","qu'");
			 	input = input.replaceAll("C '","C'");
				input = input.replaceAll("D '","D'");
				input = input.replaceAll("L '","L'");
			 	input = input.replaceAll("N '","N'");
			 	input = input.replaceAll("S '","S'");
			 	input = input.replaceAll("J '","J'");
			 	input = input.replaceAll("Qu '","Qu'");
		    }
		    
		    return input;
	}
}
