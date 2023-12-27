package com.strongvisualizer;




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

    /**
     * This constructor sets up the filepath to be whatever MainWindow's
     * ActionPerformed passes into this class.
     * 
     * @param filepathIN A String of the absolute path of the Strong data file
     */
    DataExtractor(String filepathIN){
        this.filepath = filepathIN;
    }
}
