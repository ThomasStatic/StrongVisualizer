package com.strongvisualizer;

import java.util.*;

/** 
 * @author Thomas Boyle
 * @version 1.0
 * @since 1.0
 */

 /**
  * Generate windows of the various data visualizations
  */
public class VisualizationsWindow {

    private String exercise; 
    private List<String[]> masterSet;
    private List<String[]> RmDataSet;

    /**
     * Constructor
     * @param exerciseIN The user's selected exerise to generate visuals for
     * @param dataSetIN The dataset that will be used to generate the visuals
     */
    VisualizationsWindow(String exerciseIN, List<String[]> dataSetIN){
        this.exercise = exerciseIN;
        this.masterSet = new ArrayList<String[]>(dataSetIN);
        this.RmDataSet = new ArrayList<String[]>();
    }

    public void generateRmVisual(){
        LineChart rMChart = new LineChart(this.exercise+" 1 RM Estimates Over Time", 
        this.exercise+" 1 RM Estimates Over Time", 
        "Data", "Estimate (Lbs)", this.RmDataSet);
        rMChart.pack();
        rMChart.setVisible(true);
    }

    public void generateRmDataSet(){
        for(String[] row : this.masterSet){

            // If the row of data isn't for our dersired exercise, skip it
            if(!(row[2].equals(this.exercise))){
                continue;
            }

            // Create a String array to hold the 3 rows of data we care about
            String[] temp = new String[3];

            // Copy the data and exercise name into the temp array
            temp[2] = row[0];
            temp[1] = row[2];

            // Set the value column of temp to be the 1RM of each row
            temp[0] = estimateOneRepMax(Integer.valueOf(row[4]), Integer.valueOf(row[6]));

            this.RmDataSet.add(temp);

            System.out.println(temp[1] + "\t" + temp[2] + "\t" + temp[0]);
        }
        
    }

    /**
     * Average the 1 RM formula functions to get an estimate 1 RM based on the user's
     * historical weight and reps for their selected exercise
     * @param weight The weight the user used on any given set
     * @param reps   The number of reps the user did at that weight
     * @return  The estimated 1 RM of the user on the given exercise
     */
    private String estimateOneRepMax(int weight, int reps){

        float brzycki = Brzycki(weight, reps);
        float epley = Epley(weight, reps);
        float lombardi = Lombardi(weight, reps);
        float oconner = OConner(weight, reps);

        return  String.valueOf(Math.round((brzycki+epley+lombardi+oconner)/4));

    }

    /**
     * Estimate the user's 1 RM using the Brzycki formula
     * @param weight The weight the user used 
     * @param reps  The number of reps the user did at the given weight
     * @throws IllegalArgumentException Thrown if {@code weight < 0} or {@code reps <=0}
     * @return The calculated 1 RM estimate
     */
    private float Brzycki(int weight, int reps){
        try{
            if(weight < 0 || reps <=0){
                throw new IllegalArgumentException();
            }
            return Math.round(weight * (36/ (37 - reps)));
        }
        catch (Exception e){
            if(weight < 0){
                System.out.println("Invalid weight");
            }
            else{
                System.out.println("Invalid rep count");
            }
            return 0;
        }
    }

    /**
     * Estimate the user's 1 RM using the Epley formula
     * @param weight The weight the user used 
     * @param reps  The number of reps the user did at the given weight
     * @throws IllegalArgumentException Thrown if {@code weight < 0} or {@code reps <=0}
     * @return The calculated 1 RM estimate
     */
    private float Epley(int weight, int reps){
        try{
            if(weight < 0 || reps <=0){
                throw new IllegalArgumentException();
            }
            return  Math.round(weight*(1+(0.0333 * reps)));
        }
        catch (Exception e){
            if(weight < 0){
                System.out.println("Invalid weight");
            }
            else{
                System.out.println("Invalid rep count");
            }
            return 0;
        }
    }

    /**
     * Estimate the user's 1 RM using the Lombardi formula
     * @param weight The weight the user used 
     * @param reps  The number of reps the user did at the given weight
     * @throws IllegalArgumentException Thrown if {@code weight < 0} or {@code reps <=0}
     * @return The calculated 1 RM estimate
     */
    private float Lombardi(int weight, int reps){
        try{
            if(weight < 0 || reps <=0){
                throw new IllegalArgumentException();
            }
            return (int) Math.round(weight*(Math.pow(reps, 0.1)));
        }
        catch (Exception e){
            if(weight < 0){
                System.out.println("Invalid weight");
            }
            else{
                System.out.println("Invalid rep count");
            }
            return 0;
        }
    }

    /**
     * Estimate the user's 1 RM using the O'Conner formula
     * @param weight The weight the user used 
     * @param reps  The number of reps the user did at the given weight
     * @throws IllegalArgumentException Thrown if {@code weight < 0} or {@code reps <=0}
     * @return The calculated 1 RM estimate
     */
    private float OConner(int weight, int reps){
        try{
            if(weight < 0 || reps <=0){
                throw new IllegalArgumentException();
            }
            return Math.round(weight*(1+(0.025 * reps)));
        }
        catch (Exception e){
            if(weight < 0){
                System.out.println("Invalid weight");
            }
            else{
                System.out.println("Invalid rep count");
            }
            return 0;
        }
    }

    
}
