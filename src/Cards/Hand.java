/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cards;

import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Garrett
 */
public class Hand {
    // This class is used to determine the actioins of the players hand
    // Also controls/stores the players hand
    
    private String    hand = "";  // this is used to hold the games hand infor
    private int numOfCards = 0;   // tells the class how many cards you want in hand string                     // good to have for future possible versions of the game.
    
    private int clicks = 0;       // click stats keeper
    private int random = 0;       // randomize stat keeper
    private int wins   = 0;       // win stat keeper
    
    public Hand(int xCards) {
        // sets up the players initial hand by...
        // getting number of cards and then randomizing said cards
        numOfCards = xCards;
        randomSuitString();
    }
    
    private void randomSuitString() {
        // creates a random player hand string based on numOfCards
        hand = "";
        for(int x = 0; x < numOfCards; x++) {
            hand = hand + randomizer();
        }
    }
    
    public String randomizer(){
        // this will return a randomized suit to be used in the hand string
        String[] suits = new String[]{"C", "D", "H", "S"};
        
        Random randomGen = new Random();
        int randomInt = randomGen.nextInt(4);
        
        return suits[randomInt];
    }
    
    public void update(int cardNum) {
        // used when player clicks a button and updates certain card number based off of cardNum
        // will determine if the entire hand should be randomized based on 20%
        // if not randomized then update the cardNum in the hand string
        
        Random randomGen = new Random();
        int randomInt = randomGen.nextInt(5); // generate new hand 20% of the time 100/5 = 20%
        if(randomInt == 0) {                  // 0 = represents the 20% we are looking for (can be any int 0-4 but I choose 0 for simplicity
            JOptionPane.showMessageDialog(null, "Hand Was Randomized.", "Randomized", JOptionPane.INFORMATION_MESSAGE);
            randomSuitString();
            random++;
        }
        else {
            hand = hand.substring(0, cardNum) + randomizer() + hand.substring(cardNum+1, hand.length());
        }
        clicks++;
        
    }
    
    public String showHands(){
        // used to output the hand string in text field
        return hand;
    }
    
    public boolean isWon() {
        // is used to tell if player has won
        // returns true if player has won by testing if all suits are the same.  
        // ATM it is hardcoded to check 4 cards... making numOfCards pointless
        if( hand.substring(0,1).equals(hand.substring(1,2)) &&
                hand.substring(1,2).equals(hand.substring(2,3)) &&
                hand.substring(2,3).equals(hand.substring(3,4))) {
            
                wins++;
                
                return true;
        }
        return false;
    }
    
    public String stats() {
        // returns the stats for the statsPane as a HTML string.
        String htmlStats = "<html><center><b><u>Stats</u></b></center><br>Clicks: " + clicks + " <br>Randomize: " + random + "<br> Wins: " + wins + " </html>";
        return htmlStats;
    }
}
