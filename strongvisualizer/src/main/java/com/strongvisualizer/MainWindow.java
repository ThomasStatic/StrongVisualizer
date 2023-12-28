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

    /**
     * The default constructor creates the main frame and file upload button, 
     * but does not implement them. 
     */
    MainWindow(){
        this.fileUpFrame = new JFrame("Strong Data Visualizer");
        this.fileUpButton = new JButton("Upload File");
        this.filepath = null;
        this.dataExtractor = null;
    }

    /**
     * createWindow initializes the frame and buttons.
     */
    public void createWindow(){

        // Create and set up the window
        this.fileUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

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
                this.dataExtractor.printAllData();
                this.fileUpFrame.dispatchEvent(new WindowEvent(this.fileUpFrame, WindowEvent.WINDOW_CLOSING));
                

            }


        }

    }

    


}

