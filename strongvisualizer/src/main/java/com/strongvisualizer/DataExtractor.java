package com.strongvisualizer;

import java.io.FileReader;
import java.util.*;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;



/**
 * @author Thomas Boyle
 * @version 1.0
 * @since 1.0
 */


/*
 * This class extracts relevant data from the user's uploaded Strong data file, 
 * and then cleans it up into a format usable by the Visualizations class
 */
public class DataExtractor {
    private String filepath;
    List<String[]> allData; 

    /**
     * This constructor sets up the filepath to be whatever MainWindow's
     * ActionPerformed passes into this class.
     * 
     * @param filepathIN A String of the absolute path of the Strong data file
     */
    DataExtractor(String filepathIN){
        this.filepath = filepathIN;
        this.allData = null;
        readData();

    }

    /**
     * Using the openCSV library, read all the data from the user supplied file into
     * the allData attribute of this class
     */
    private void readData(){
        try{
        FileReader fileReader = new FileReader(this.filepath);
        
         CSVParser csvParser = new CSVParserBuilder()
         .withSeparator(';')
        // .withIgnoreQuotations(true)
         .build();


        CSVReader csvReader = new CSVReaderBuilder(fileReader)
        //.withSkipLines(1)
        .withCSVParser(csvParser)
        .build();


        this.allData = csvReader.readAll();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Prints to the console all data read in from a csv file into the allData attribute 
     */
    public void printAllData(){
        for(String[] row : this.allData){
            for(String cell : row){
                System.out.println(cell + "\t");
            }
            System.out.println("\n");
        }
    }

    public String[] getExercises(){


        Set<String> tempSet = new HashSet<>();
        

        for(String[] row : this.allData){
            tempSet.add(row[2].trim());
        }
        tempSet = new TreeSet<String>(tempSet);
        String[] uniqueExercises = tempSet.toArray(new String[0]);

        return uniqueExercises;
        
    }

    public List<String[]> getAllData(){
        List<String[]> allDataCopy = new ArrayList<String[]>(this.allData);
        
        for(String[] row : allDataCopy){
            String[] cleanDate = row[0].split(" ");
            row[0] = cleanDate[0];

        }
        return allDataCopy;
    }
}
