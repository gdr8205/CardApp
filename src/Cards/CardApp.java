/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cards;

/**
 *
 * @author Garrett
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardApp extends JFrame implements ActionListener {
    
    
    private JTextField cardShow;                                  // shows current hand
    private JTextPane showStats;
    private static final int BUTTONCOUNT = 4;                     // how many buttons to put on screen
    
    private JButton[] buttons = new JButton[BUTTONCOUNT];         // array of buttons based on BUTTONCOUNT
    private final String[] labels; 
    
    Hand hand = new Hand(4);                                      // start new hand that will initially randomize.
    
    public CardApp() {
        // sets up the display for the program
        
        this.labels = new String[]{"1", "2", "3", "4"};           // labels for buttons
        
        JPanel buttonHolder = new JPanel();                       // panel to holder buttons
            buttonHolder.setLayout(new GridLayout(0, 4));
            
        
        
        for(int x = 0; x < BUTTONCOUNT; x++) {                    // button setup
            buttons[x] = new JButton(labels[x]);
            buttons[x].addActionListener(this);
            buttonHolder.add(buttons[x]);
        }
    
        cardShow = new JTextField();                             // place to show current hand
        
        cardShow.setText(hand.showHands());                      // displays initial hand
        
        cardShow.setEditable(false);                             // make it so the user cant "cheat/edit" the display
    
        
        JPanel cardHolder   = new JPanel(new GridLayout(0,1));   
        cardHolder.add(cardShow);
        JPanel mainPanel    = new JPanel(new BorderLayout(1,1)); // 
        mainPanel.add(cardHolder, BorderLayout.PAGE_START);      // Put on top
        mainPanel.add(buttonHolder, BorderLayout.CENTER);        // Put in "Center" but ends up being botton left of program window
        
        
        // start setting up stats
        JPanel statsPane = new JPanel();
        
        showStats = new JTextPane();
        showStats.setContentType("text/html");                   // turn HTML interp 
        //showStats.setBackground(new Color(0,0,0,0));           // for whatever reason, making it transparent causes drawing issues...
        
        showStats.setText(hand.stats());                         // show initial stats (all zeros)
        showStats.setEditable(false);                            // turn off editing of stats pane
        statsPane.add(showStats);                                // add stats to pane
        mainPanel.add(statsPane, BorderLayout.EAST);             // put on right side of screen

        //mainPanel.add(buttonHolder);
        
        getContentPane().add(mainPanel);                         // put everything on the screen
        setSize(600,200);                                        // set size to 600x200
        setVisible(true);                                        // 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          // 

    }
    
    public void actionPerformed(ActionEvent e) {
        // ties cardapp and hand together based on user actions.
        
        for(int x=0; x<BUTTONCOUNT;x++) {                        // simplifies the button press check
            if(e.getSource() == buttons[x]){                     //
                hand.update(x);                                  // lets update the correct card in the hand based on what button user pressed
                cardShow.setText(hand.showHands());              // update the hand output on screen
                
                showStats.setText(hand.stats());                 // update stats every click
            }
        }
        
        if(hand.isWon()) { // check is the player has won
            showStats.setText(hand.stats());                     // update screen b4 we thru joptionpane since it causes new focus till user clicks ok
            JOptionPane.showMessageDialog(null, "Winning hand: " + hand.showHands(), "Winner", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    
    public static void main(String[] args) {
        // start
        CardApp s = new CardApp();
        
    }
}
