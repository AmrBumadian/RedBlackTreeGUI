package Drawer;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import Listeners.*;
import RedBlackTree.RedBlackTree;

public class TreeDrawer {

    private JFrame theFrame;
    private DrawingPanel drawingPanel;
    private JScrollPane scrollPane;
    private final RedBlackTree theTree;

    public TreeDrawer() {
        theTree = new RedBlackTree();
        initialize();
    }

    private void initialize() {
        ArrayList<Integer> screenDimensions = getScreenDimensions();
        int screenWidth = screenDimensions.get(0);
        int screenHeight = screenDimensions.get(1);

        drawingPanel = DrawingPanel.getNewDrawingPanel(theTree);
        scrollPane = constructScrollPane(screenWidth, screenHeight);
        theFrame = constructFrame(screenWidth, screenHeight);

        JPanel buttonPanel = constructButtonsPanel();
        setContentContainer(buttonPanel, screenWidth);

        theFrame.setVisible(true);
    }

    private ArrayList<Integer> getScreenDimensions() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ArrayList<Integer> dimensions = new ArrayList<>();
        dimensions.add((int) (toolkit.getScreenSize().getWidth()));
        dimensions.add((int) (toolkit.getScreenSize().getHeight()));
        return dimensions;
    }

    private JFrame constructFrame(int screenWidth, int screenHeight) {
        JFrame frame = new JFrame("Drawing Red Black Tree");
        frame.setSize(screenWidth, screenHeight);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private JScrollPane constructScrollPane(int screenWidth, int screenHeight) {
        JScrollPane scroll = new JScrollPane(drawingPanel);
        scroll.setPreferredSize(new Dimension(screenWidth - 150, screenHeight - 200));
        return scroll;
    }

    private JPanel constructButtonsPanel() {
        ArrayList<JButton> buttons = constructButtons();
        JPanel buttonPanel = new JPanel();
        for (var button : buttons) {
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    private ArrayList<JButton> constructButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton addNodesButton = new JButton("Add random nodes to the tree");
        addNodesButton.addActionListener(new AddRandomNodesAction(theTree, drawingPanel, scrollPane));
        buttons.add(addNodesButton);

        JButton setDiameterButton = new JButton("Set the diameter of nodes");
        setDiameterButton.addActionListener(new SetNodeDiameterAction(theTree, drawingPanel));
        buttons.add(setDiameterButton);

        JButton addButton = new JButton("Add a node to the tree");
        addButton.addActionListener(new AddNodeAction(theTree, drawingPanel, scrollPane));
        buttons.add(addButton);

        JButton deleteButton = new JButton("Remove a node from the tree");
        deleteButton.addActionListener(new DeleteNodeAction(theTree, drawingPanel));
        buttons.add(deleteButton);

        JButton clearButton = new JButton("Clear the tree");
        clearButton.addActionListener(new ClearPanelAction(theTree, drawingPanel));
        buttons.add(clearButton);
        return buttons;
    }

    private void setContentContainer(JPanel buttonPanel, int screenWidth) {
        Container contentPane = theFrame.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, screenWidth, 20));
        contentPane.add(new JScrollPane(buttonPanel));
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }
}