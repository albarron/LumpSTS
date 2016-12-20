package cat.lump.sts2017.prepro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import cat.lump.aq.basics.io.files.FileIO;

/**
 * Set of methods to convert among different annotation's formats
 *   
 * @author cristina
 * @since Dec 1, 2016
 *
 */
public class FormatConverter {
	

	public final static String lineSeparator = System.lineSeparator();

	/**
	 * Converts a file in the conll format (tokens separated by tabs) into a file
	 * where tokens are separated by pipes.
	 * 
	 * @param fIn
	 * 			Input file
	 * @param fOut
	 * 			Output file
	 */
	public static void conllLema2Factors(File fIn, File fOut){

		// Initilise the writer
		FileIO.deleteFile(fOut);
	    FileWriter fw = null;
	    BufferedWriter bw = null;
		try {
			fw = new FileWriter(fOut, true);
			bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Read the input
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(fIn);
		    sc = new Scanner(inputStream, "UTF-8");
		    int i = 0;
		    while (sc.hasNext()) {
		        String line = sc.nextLine();
		        // This is the true conversion
		        Matcher m = Pattern.compile("(.+\t)+").matcher(line);
		        String lineFactors = "";
		        if (m.find()){
			        lineFactors = line.replace("\t", "|");	
			        bw.append(lineFactors+" ");
		        } else {
		        	bw.newLine();
		        }
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
		        	bw.newLine();
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
		
	}	

	
	/**
	 * Converts a file in the conll format (tokens separated by tabs) into a String
	 * where tokens are separated by pipes.
	 * 
	 * @param fIn
	 * 			Input file
	 * @return factors
	 * 	 		String with the sentence(s) in the format of factors 
	 */
	public static String conllLema2Factors(File fIn){

		String factors = "NON CONVERTED";
		
		// Initilise the writer
		StringBuffer sb = new StringBuffer();
	
		// Read the input
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(fIn);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNext()) {
		        String line = sc.nextLine();
		        // This is the true conversion
		        Matcher m = Pattern.compile("(.+\t)+").matcher(line);
		        String lineFactors = "";
		        if (m.find()){
			        lineFactors = line.replace("\t", "|");	
			        sb.append(lineFactors+" ");
		        } else {
		        	sb.append(lineSeparator);
		        }
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
		        sb.append(lineSeparator);
		    }
		}
		factors = sb.toString();
		
		return factors;
		
	}


	/**
	 * Converts a file with raw text, a sentence per line into an xml file ready to
	 * be used by MADAMIRA
	 * 
	 * @param inputRaw
	 * 			Input file
	 * @param outputMada
	 * 			Output file
	 * @param config
	 * 			Constant string in ConfigConstants.java with the configuration needed 
	 * 			for the task
	 */
	public static void raw2mada(File inputRaw, File outputMada, String config) {

		// Initilise the writer
		FileIO.deleteFile(outputMada);
	    FileWriter fw = null;
	    BufferedWriter bw = null;
		try {
			fw = new FileWriter(outputMada, true);
			bw = new BufferedWriter(fw);
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        	bw.newLine();
	    	bw.append("<madamira_input xmlns=\"urn:edu.columbia.ccls.madamira.configuration:0.1\">");
        	bw.newLine();
		    bw.append(config);
		    bw.append("<in_doc id=\""+inputRaw.toString()+"\">");
        	bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		// Read the input
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			Integer i = 1;
		    inputStream = new FileInputStream(inputRaw);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNext()) {
		        String line = sc.nextLine();
		        bw.append("\t<in_seg id=\"SENT");
		        bw.append(i.toString());
		        bw.append("\"> ");
		        bw.append(line);
		        bw.append(" </in_seg>");
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
					bw.append("</in_doc>");
		        	bw.newLine();
			        bw.append("</madamira_input>");
		        	bw.newLine();
					bw.close();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		
	}	


	/**
	 * Converts an xml file in the output format given by MADAMIRA, into raw text, a sentence per line
	 * file where different annotation layers for a token are separated by pipes. 
	 * 
	 * @param outputMada
	 * 			Input file
	 * @param outputRaw
	 * 			Output file
	 * @param ann
	 * 			Layer of annotation. Currently supports tokenisation (tok) and lemmatisation (lem)
	 */
	public static void mada2raw(File outputMada, File outputRaw, String ann) {

		// Initilise the writer
		FileIO.deleteFile(outputRaw);
	    FileWriter fw = null;
	    BufferedWriter bw = null;
		try {
			fw = new FileWriter(outputRaw, true);
			bw = new BufferedWriter(fw);
			bw.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Initialise the xml parser
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        List<Sentence> sentList = null;
	        if (ann.equalsIgnoreCase("tok"))  {
	        	MADATokeniserHandler handler = new MADATokeniserHandler();
		        saxParser.parse(outputMada, handler);
		        sentList = handler.getSentenceList();
	        } else if (ann.equalsIgnoreCase("lem"))  {
	        	MADALemmatiserHandler handler = new MADALemmatiserHandler();
		        saxParser.parse(outputMada, handler);
		        sentList = handler.getSentenceList();
	        } 
	
	        // Write the requested annotations
	        for(Sentence sent : sentList){
		        bw.append(sent.toString());
	        	bw.newLine();
	        }

	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        e.printStackTrace();
	    }
	    
		try {
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
    
}