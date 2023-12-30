package com.strongvisualizer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Thomas Boyle
 * @version 1.0
 * @since 1.0
 */



/**
 * This class is the default window where the user can upload a data file
 * and select the visualiations they want to see
 */
public class MainWindow implements ActionListener{

    private JFrame fileUpFrame;
    private JButton fileUpButton;
    private String filepath;
    private DataExtractor dataExtractor;
    private JFrame selectVisFrame;
    private JPanel visContainerPanel;
    private JPanel visSelectorsPanel;
    private JPanel visCurStatsPanel;
    private JComboBox<String> excerciseCb;

    /**
     * The default constructor creates the main frame and file upload button, 
     * but does not implement them. 
     */
    MainWindow(){
        this.fileUpFrame = new JFrame("Strong Data Visualizer");
        this.fileUpButton = new JButton("Upload File");
        this.filepath = null;
        this.dataExtractor = null;
        this.selectVisFrame = null;
        this.visContainerPanel = null;
        this.visSelectorsPanel = null;
        this.visCurStatsPanel = null;
        this.excerciseCb = null;
    }

    /**
     * createWindow initializes the frame and buttons.
     */
    public void createStartWindow(){

        // Create and set up the window
        this.fileUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

        initFileUpButton();

        // Display the window
        this.fileUpFrame.setLocationRelativeTo(null);
        this.fileUpFrame.pack(); // Pack sets the screensize to be big enough for all the components in it
        this.fileUpFrame.setVisible(true);
        

    }

    /**
     * This initializes the button that gets an input file (which should be the
     * Strong data file)
     */
    private void initFileUpButton(){
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));

        // Turn off the border around the button text
        this.fileUpButton.setFocusable(false);

        this.fileUpButton.addActionListener(this);

        buttonPanel.add(this.fileUpButton);
        containerPanel.add(buttonPanel);
        this.fileUpFrame.getContentPane().add(containerPanel);


    }


    /**
     * Override of actionPerformed so that MainWindow can implement ActionListener.
     * 
     * If fileUpButton is clicked, a file upload option pane appears and the filepath
     * that the user subsequently selects is saved into the filepath attribute of this
     * class. 
     * 
     * @param evt A runtime event (one of the buttons clicked)
     */
    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == this.fileUpButton){
            JFileChooser file_upload = new JFileChooser();
            FileNameExtensionFilter csvFileFilter = new FileNameExtensionFilter("CSV file", "csv");
            file_upload.setFileFilter(csvFileFilter);
            //file_upload.showOpenDialog(null);

            int response = file_upload.showOpenDialog(null);

            if(response  == JFileChooser.APPROVE_OPTION){
                this.filepath = file_upload.getSelectedFile().getAbsolutePath();
                System.out.println(filepath);

                this.dataExtractor = new DataExtractor(filepath);
                String[] exerciseOptions = this.dataExtractor.getExercises();


                this.selectVisFrame = new JFrame("Strong Data Visualizer");
                this.fileUpFrame.dispatchEvent(new WindowEvent(this.fileUpFrame, WindowEvent.WINDOW_CLOSING));
                createVisSelectWindow(exerciseOptions);
                

            }


        }

    }

    private void createVisSelectWindow(String[] exerciseOptions){

        // Create and set up the window
        this.selectVisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.visContainerPanel = new JPanel();
        this.visContainerPanel.setLayout(new GridLayout(1,2));
        this.selectVisFrame.add(this.visContainerPanel);

        initVisSelectorPanels();
        initExerciseCb(exerciseOptions);
        



        // Display the window
        this.selectVisFrame.setLocationRelativeTo(null);
        this.selectVisFrame.pack(); // Pack sets the screensize to be big enough for all the components in it
        this.selectVisFrame.setVisible(true);


    }

    private void initVisSelectorPanels(){

        // Panel to contain the combo box and generate visuals buttons
        this.visSelectorsPanel = new JPanel();
        this.visSelectorsPanel.setLayout(new GridLayout(4,1));
        this.visContainerPanel.add(this.visSelectorsPanel);

        // Panel to contain 1RM stats
        this.visCurStatsPanel = new JPanel();
        this.visCurStatsPanel.setLayout(new GridLayout(1,1));
        this.visContainerPanel.add(this.visCurStatsPanel);
        

    }

    private void initExerciseCb(String[] exerciseOptions){
        this.excerciseCb = new JComboBox<String>(exerciseOptions);
        this.visSelectorsPanel.add(this.excerciseCb);

        
    }

    


}

