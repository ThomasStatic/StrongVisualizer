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
        LinkedHashMap<String, Float> tempMap = new LinkedHashMap<>();
        for(String[] row : this.masterSet){

            // If the row of data isn't for our dersired exercise, skip it
            if(!(row[2].equals(this.exercise))){
                continue;
            }
            
            // Generate a 1RM estimate for the current row
            float rmEstimate = estimateOneRepMax(Integer.valueOf(row[4]), Integer.valueOf(row[6]));

            // If we already have an entry for this date, check if its a greater 1RM
            if(tempMap.containsKey(row[0])){
                
                // If the entry for this date has a greater RM keep it
                if(tempMap.get(row[0]) > rmEstimate){
                    continue;
                }
            }

            // Otherwise, insert this entry into the map
            tempMap.put(row[0], rmEstimate);

        }

        // Convert the hash set into an array list
        ArrayList<String> listOfKeys = new ArrayList<String>(tempMap.keySet());

        for(int i = 0; i <tempMap.size(); i++){
            String[] tempStr = new String[3];
            tempStr[2] = listOfKeys.get(i);
            tempStr[1] = this.exercise;
            Integer tempInt = tempMap.get(listOfKeys.get(i)).intValue();
            tempStr[0] = String.valueOf(tempInt);
            this.RmDataSet.add(tempStr);
        }

        
    }

    /**
     * Average the 1 RM formula functions to get an estimate 1 RM based on the user's
     * historical weight and reps for their selected exercise
     * @param weight The weight the user used on any given set
     * @param reps   The number of reps the user did at that weight
     * @return  The estimated 1 RM of the user on the given exercise
     */
    private float estimateOneRepMax(int weight, int reps){

        float brzycki = Brzycki(weight, reps);
        float epley = Epley(weight, reps);
        float lombardi = Lombardi(weight, reps);
        float oconner = OConner(weight, reps);

        return  Math.round((brzycki+epley+lombardi+oconner)/4);

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
            return 1;
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
            return 1;
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
            return 1;
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
            return 1;
        }
    }

    
}
