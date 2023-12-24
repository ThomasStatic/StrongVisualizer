import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainWindow implements ActionListener{

    private JFrame mainFrame;
    private JButton fileUpButton;
    private String filepath;

    MainWindow(){
        this.mainFrame = new JFrame("Strong Data Visualizer");
        this.fileUpButton = new JButton("Upload File");
        this.filepath = null;
    }

    public void createWindow(){

        // Create and set up the window
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        initFileUpButton();

        // Display the window
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.pack(); // Pack sets the screensize to be big enough for all the components in it
        this.mainFrame.setVisible(true);
        

    }

    private void initFileUpButton(){
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));

        // Turn off the border around the button text
        this.fileUpButton.setFocusable(false);

        this.fileUpButton.addActionListener(this);

        buttonPanel.add(this.fileUpButton);
        containerPanel.add(buttonPanel);
        this.mainFrame.getContentPane().add(containerPanel);


    }

    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == this.fileUpButton){
            JFileChooser file_upload = new JFileChooser();
            //file_upload.showOpenDialog(null);

            int response = file_upload.showOpenDialog(null);

            if(response  == JFileChooser.APPROVE_OPTION){
                this.filepath = file_upload.getSelectedFile().getAbsolutePath();
                System.out.println(filepath);

            }
        }

    }

    


}

