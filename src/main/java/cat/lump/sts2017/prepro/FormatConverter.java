package cat.lump.sts2017.prepro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
}
