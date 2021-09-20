package Listeners;

import Drawer.DrawingPanel;
import RedBlackTree.RedBlackTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetNodeDiameterAction implements ActionListener {
    private final DrawingPanel drawingPanel;

    private void changeDiameter(String newDiameter) {
        try {
            drawingPanel.setNodeDiameter(Integer.parseInt(newDiameter));
            drawingPanel.repaint();
        } catch (NumberFormatException exp) {
            JOptionPane.showMessageDialog(null, "Enter digits only");
        }
    }

    public SetNodeDiameterAction(RedBlackTree tree, DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public void actionPerformed(ActionEvent e) {
        String newDiameter = JOptionPane.showInputDialog("Enter the diameter");
        if (newDiameter == null) return;
        changeDiameter(newDiameter);
    }
}
