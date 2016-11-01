package com.anthony;

import sun.plugin2.message.JavaObjectOpMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class WaterfallAgileGUI extends JFrame {

    private JTextField tbxProjectName;
    private JCheckBox cbxDeadline;
    private JCheckBox cbxProgrammersExp;
    private JCheckBox cbxQualityControl;
    private JCheckBox cbxEarlyInt;
    private JCheckBox cbxWorkingModel;
    private JButton recommendButton;
    private JPanel rootPanel;
    private JLabel lblRecommendation;
    private JTextField tbxProgrammerNum;

    protected WaterfallAgileGUI(){
        setContentPane(rootPanel); // Set content pane to the rootPanel
        pack(); // Pack
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); // Make panel visible
        recommendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { // Exception handling for tbxProgrammerNum
                    if (!Objects.equals(tbxProjectName.getText(), "") && !Objects.equals(tbxProgrammerNum.getText(), "")
                            && Integer.parseInt(tbxProgrammerNum.getText()) > 0) {
                        lblRecommendation.setText("Your project, " + tbxProjectName.getText()
                                + agileOrWaterfall(Integer.parseInt(tbxProgrammerNum.getText()),
                                cbxDeadline.isSelected(), cbxProgrammersExp.isSelected(),
                                cbxQualityControl.isSelected(), cbxEarlyInt.isSelected(),
                                cbxWorkingModel.isSelected()));
                    } else if (tbxProjectName.getText().equals("")){
                        JOptionPane.showMessageDialog(WaterfallAgileGUI.this, "Please enter a project name.");
                    }else if (tbxProgrammerNum.getText().equalsIgnoreCase("") || tbxProgrammerNum.getText().equalsIgnoreCase("0")) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(WaterfallAgileGUI.this,
                            "Please choose an integer amount of programmers.\nMake sure your integer is greater than 0");
                }
            }
        });

    }
    // Method to generate an opinion
    private static String agileOrWaterfall(int programmers, boolean firmDeadlineSchedule,
                                           boolean programmerExperience, boolean qualityControl,
                                           boolean earlyIntegration, boolean customerWorkingModels) {
        // Closer to 0 if more fit for waterfall closer to 12 more fit for agile
        int methodFit = 6;
        // Each influences the methodFit to be closer to agile (12) or waterfall (0)
        // based on the arguments sent to the method
        if (programmers > 1){
            methodFit --;
        } else { methodFit ++; }
        if (firmDeadlineSchedule){
            methodFit --;
        } else { methodFit ++; }
        if (programmerExperience){
            methodFit ++;
        } else { methodFit --; }
        if (qualityControl){
            methodFit --;
        } else { methodFit ++; }
        if (earlyIntegration){
            methodFit ++;
        } else { methodFit --; }
        if (customerWorkingModels){
            methodFit ++;
        } else { methodFit --; }
//        System.out.println(methodFit); // Test for value of methodFit
        if (methodFit < 4){
            return " would be best suited with the Waterfall method.";
        }
        if (methodFit >= 4 && methodFit <= 6){
            return " leans toward the Waterfall method.";
        }
        if (methodFit >= 7 & methodFit <= 9){
            return " leans towards the Agile method.";
        }
        if (methodFit > 9){
            return " would be best suited with the Agile method.";
        }
        else {
            // Gets rid of an error
            return "Unable to calculate a recommendation";
        }
    }

}
