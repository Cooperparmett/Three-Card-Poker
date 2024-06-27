import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.lang.model.util.ElementScanner14;
import javax.sound.sampled.LineListener;
import java.util.Arrays;
import java.util.Vector;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.util.Random;


public class TCPPanel extends JPanel
{
    //card member data
    private CardDeck deck = new CardDeck();
    private Card[] cards;
    private Card[] playerHand;
    private Card[] dealerHand;
    
    private int playerBalance = 500;
    private int bet;      
    private int pairplus;
    private int ante;
    private int sixcard;
    private int totalBet;
    private int prime;
    private JLabel playText = new JLabel("PLAY");
    
    private JLabel betAmountHeader = new JLabel("Bet Amounts:");
    private JLabel anteText = new JLabel("ANTE");
    private AnteHandler aH = new AnteHandler();    
    private JLabel anteDisplayText = new JLabel("Ante: 0");

    private JLabel pairText = new JLabel("PAIR");
    private JLabel plusText = new JLabel("PLUS");
    private PairPlusHandler ppH = new PairPlusHandler();
    private JLabel pairPlusDisplayText = new JLabel("Pair Plus: 0");


    private JLabel payoutsDisplayHeader = new JLabel("Payouts: ");
    private JLabel antePayout = new JLabel("Ante: " + ante);
    private JLabel pairPlusPayout = new JLabel("Pair Plus: " + pairplus);
    private JLabel primePayout = new JLabel("Prime: " + prime);
    private JLabel sixCardPayout = new JLabel("Six Card Bonus: " + sixcard);


    private JLabel primeText = new JLabel("PRIME");
    private PrimeHandler primeH = new PrimeHandler();
    private JLabel primeDisplayText = new JLabel("Prime: 0");

    private JLabel sixCardText = new JLabel("6 CARD");
    private JLabel bonusText = new JLabel("BONUS");
    private SixCardHandler scH = new SixCardHandler();
    private JLabel sixCardDisplayText = new JLabel("6 Card Bonus: 0");

    private JLabel playerBalanceText = new JLabel("Player balance: " + Integer.toString(playerBalance));
    private JLabel[] labelArray = new JLabel[52];
    private JLabel[] chipArray = new JLabel[4];
    private JLabel[] cardBacks = new JLabel[3];
    private JLabel[] playerCardLocations = new JLabel[3];
    private JLabel[] dealerCardLocations = new JLabel[3];



    private JRadioButton chipButton1 = new JRadioButton("$1");
    private RadioButtonHandler chipHandler1 = new RadioButtonHandler(1);
    private JRadioButton chipButton10 = new JRadioButton("$10");
    private RadioButtonHandler chipHandler10 = new RadioButtonHandler(10);
    private JRadioButton chipButton25 = new JRadioButton("$25");
    private RadioButtonHandler chipHandler25 = new RadioButtonHandler(25);
    private JRadioButton chipButton100 = new JRadioButton("$100");
    private RadioButtonHandler chipHandler100 = new RadioButtonHandler(100);
  

    private ButtonGroup chipButtonGroup = new ButtonGroup();
    
    private JButton playButton = new JButton("Deal Hand");
    PlayButtonHandler pbH = new PlayButtonHandler();

    private JButton resetBets = new JButton("Reset Bets");
    private ResetBetButtonHandler resetBH = new ResetBetButtonHandler();

    private JButton callButton = new JButton("Call");
    private JButton foldButton = new JButton("Fold");
    private JButton nextRoundButton = new JButton("Click to begin next round of betting.");
    NextRoundButtonHandler nRH = new NextRoundButtonHandler();
    private JButton rulesButton = new JButton("Rules");
    private RulesButtonHandler rBH = new RulesButtonHandler();
    private JButton payoutsButton = new JButton("Payouts");
    private PayoutsButtonHandler payBH = new PayoutsButtonHandler();

    private boolean dealerQualify = false;
    private boolean playerWins = false;
    private boolean push = false;
    

    

    public TCPPanel()       //constructor
    {
        setLayout(null);
        String temp = "";
        String clubs = "_of_clubs.png";
        String diamonds = "_of_diamonds.png";
        String hearts = "_of_hearts.png";
        String spades = "_of_spades.png";
        int count = 0;
        int suitCount = 0;

        cards = deck.randomCard();
        playerHand = new Card[3];
        dealerHand = new Card[3];

        for(int i = 0; i < 3; i++)
        {
            playerHand[i] = cards[i];
        }
        dealerHand = new Card[3];

        for(int i = 0; i < 3; i++)
        {
            dealerHand[i] = cards[i + 3];
        }

        
        

        //Add all pictures of cards into an array
        for(int i = 2; i <= 10; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                switch (suitCount) 
                {
                    case 0:
                        temp = String.format("Cards/%d%s", i, clubs);
                        suitCount++;
                        break;
                    case 1:
                        temp = String.format("Cards/%d%s", i, diamonds);
                        suitCount++;   
                        break;
                    case 2:
                        temp = String.format("Cards/%d%s", i, hearts);
                        suitCount++;
                        break;
                    case 3:
                        temp = String.format("Cards/%d%s", i, spades);
                        suitCount = 0;
                        break;
                    
                
                    default:
                        break;
                }
                
                labelArray[count] = new JLabel(new ImageIcon(getClass().getResource( temp ))); 
                count++;
                temp = "";
            }
        }
        
        
        
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/jack_of_clubs.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/jack_of_diamonds.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/jack_of_hearts.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/jack_of_spades.png"))); 
        count++;
        
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/queen_of_clubs.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/queen_of_diamonds.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/queen_of_hearts.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/queen_of_spades.png"))); 
        count++;
        
        
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/king_of_clubs.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/king_of_diamonds.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/king_of_hearts.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/king_of_spades.png"))); 
        count++;
        
        
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/ace_of_clubs.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/ace_of_diamonds.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/ace_of_hearts.png"))); 
        count++;
        labelArray[count] = new JLabel(new ImageIcon(getClass().getResource("Cards/ace_of_spades.png"))); 
        count++;
        //end of adding pictures of cards to the array

        //add pictures of backs of cards to an array
        cardBacks[0] = new JLabel(new ImageIcon(getClass().getResource("Cards/back.png")));
        cardBacks[1] = new JLabel(new ImageIcon(getClass().getResource("Cards/back.png")));
        cardBacks[2] = new JLabel(new ImageIcon(getClass().getResource("Cards/back.png")));


        //add chip pictures to the array
        chipArray[0] = new JLabel(new ImageIcon(getClass().getResource("Chips/Red_Chip.png"))); 
        chipArray[1] = new JLabel(new ImageIcon(getClass().getResource("Chips/Green_Chip.png"))); 
        chipArray[2] = new JLabel(new ImageIcon(getClass().getResource("Chips/Blue_Chip.png"))); 
        chipArray[3] = new JLabel(new ImageIcon(getClass().getResource("Chips/Black_Chip.png"))); 

        //add Radiobuttons for chips
        chipButton1.setOpaque(false);
        chipButton1.setForeground(Color.WHITE);
        chipButton1.setFocusable(false);
        chipButton1.setFont(new Font("Serif", Font.BOLD, 25));

        chipButton10.setOpaque(false);
        chipButton10.setForeground(Color.WHITE);
        chipButton10.setFocusable(false);
        chipButton10.setFont(new Font("Serif", Font.BOLD, 25));

        chipButton25.setOpaque(false);
        chipButton25.setForeground(Color.WHITE);
        chipButton25.setFocusable(false);
        chipButton25.setFont(new Font("Serif", Font.BOLD, 25));

        chipButton100.setOpaque(false);
        chipButton100.setForeground(Color.WHITE);
        chipButton100.setFocusable(false);
        chipButton100.setFont(new Font("Serif", Font.BOLD, 25));

        chipButtonGroup.add(chipButton1);
        chipButtonGroup.add(chipButton10);
        chipButtonGroup.add(chipButton25);
        chipButtonGroup.add(chipButton100);

        //add chip (JRadioButton) handlers
        chipButton1.addItemListener(chipHandler1);
        chipButton10.addItemListener(chipHandler10);
        chipButton25.addItemListener(chipHandler25);
        chipButton100.addItemListener(chipHandler100);

        //adding handlers for side bets
        anteText.addMouseListener(aH);
        pairText.addMouseListener(ppH);
        plusText.addMouseListener(ppH);
        primeText.addMouseListener(primeH);
        sixCardText.addMouseListener(scH);
        bonusText.addMouseListener(scH);

        playButton.addActionListener(pbH);
        playButton.setFocusable(false);

        callButton.addActionListener(new CallButtonHandler());
        callButton.setFocusable(false);

        foldButton.addActionListener(new FoldButtonHandler());
        foldButton.setFocusable(false);

        rulesButton.addActionListener(rBH);
        payoutsButton.addActionListener(payBH);

        resetBets.addActionListener(resetBH);

        //adding components
        add(playText);
        add(anteText);
        add(pairText);
        add(plusText);
        add(primeText);
        add(sixCardText);
        add(bonusText);
        add(betAmountHeader);
        add(anteDisplayText);
        add(pairPlusDisplayText);
        add(primeDisplayText);
        add(sixCardDisplayText);
        add(chipButton1);
        add(chipButton10);
        add(chipButton25);
        add(chipButton100);
        add(chipArray[0]);
        add(chipArray[1]);
        add(chipArray[2]);
        add(chipArray[3]);
        add(playerBalanceText);
        add(playButton);
        add(rulesButton);
        add(payoutsButton);
        add(resetBets);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        setBackground(new Color(71, 113, 72));          //set table to Green Felt Color
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));

        

        //setting chip radio button bounds 
        chipArray[0].setBounds(getWidth() / 8 - 75, getHeight() / 6 * 5, chipArray[0].getPreferredSize().width, chipArray[0].getPreferredSize().height);
        chipArray[1].setBounds(getWidth() / 8 + 75, getHeight() / 6 * 5, chipArray[0].getPreferredSize().width, chipArray[0].getPreferredSize().height);
        chipArray[2].setBounds(getWidth() / 8 + 225, getHeight() / 6 * 5, chipArray[0].getPreferredSize().width, chipArray[0].getPreferredSize().height);
        chipArray[3].setBounds(getWidth() / 8 + 375, getHeight() / 6 * 5, chipArray[0].getPreferredSize().width, chipArray[0].getPreferredSize().height);

        chipButton1.setBounds(getWidth() / 8 - 50, getHeight() / 6 * 5 + 15, chipButton1.getPreferredSize().width + 25, chipButton1.getPreferredSize().height + 25);
        chipButton10.setBounds(getWidth() / 8 + 94, getHeight() / 6 * 5 + 15, chipButton1.getPreferredSize().width + 25, chipButton1.getPreferredSize().height + 25);
        chipButton25.setBounds(getWidth() / 8 + 244, getHeight() / 6 * 5 + 15, chipButton1.getPreferredSize().width + 25, chipButton1.getPreferredSize().height + 25);
        chipButton100.setBounds(getWidth() / 8 + 387, getHeight() / 6 * 5 + 15, chipButton1.getPreferredSize().width + 30, chipButton1.getPreferredSize().height + 30);
        chipButton1.setOpaque(false);

        //set the bounds of the backs of the cards
        cardBacks[0].setBounds(getWidth() / 4 * 3 - 200, getHeight() / 8, cardBacks[0].getPreferredSize().width, cardBacks[0].getPreferredSize().height);
        cardBacks[1].setBounds(getWidth() / 4 * 3 - 100, getHeight() / 8, cardBacks[1].getPreferredSize().width, cardBacks[1].getPreferredSize().height);
        cardBacks[2].setBounds(getWidth() / 4 * 3, getHeight() / 8, cardBacks[2].getPreferredSize().width, cardBacks[2].getPreferredSize().height);

        //set the bounds of the play, call, and fold buttons
        playButton.setBounds(getWidth() / 8 + 525, getHeight() / 6 * 5 + 30, playButton.getPreferredSize().width, playButton.getPreferredSize().height);
        callButton.setBounds(getWidth() / 8 + 525, getHeight() / 6 * 5 - 50, callButton.getPreferredSize().width, callButton.getPreferredSize().height);
        foldButton.setBounds(getWidth() / 8 + 525, getHeight() / 6 * 5 - 130, foldButton.getPreferredSize().width, foldButton.getPreferredSize().height);



        //"Play bet area"
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(getWidth() / 5, getHeight() / 8 * 5, 200, 100, 10, 10);
        //drawing diamond in rectangle
        g.setColor(Color.ORANGE);

            //Diamond around "PLAY"
        g.drawLine(getWidth() / 5 + 100, getHeight() / 8 * 5, getWidth() / 5 + 175 , getHeight() / 8 * 5 + 50);     //top of diamond to right vertex
        g.drawLine(getWidth() / 5 + 175, getHeight() / 8 * 5 + 50, getWidth() / 5 + 100, getHeight() / 8 * 5 + 100);  //right vertex to bottom vertex
        g.drawLine(getWidth() / 5 + 100, getHeight() / 8 * 5 + 100, getWidth() / 5 + 25, getHeight() / 8 * 5 + 50);   //bottom vertex to left vertex
        g.drawLine(getWidth() / 5 + 25, getHeight() / 8 * 5 + 50, getWidth() / 5 + 100, getHeight() / 8 * 5);          //top vertex to left vertex

            //Diamond for ante
        g.drawLine(getWidth() / 5 + 100, getHeight() / 8 * 5 - 100, getWidth() / 5 + 175 , getHeight() / 8 * 5 - 50);     //top of diamond to right vertex
        g.drawLine(getWidth() / 5 + 175, getHeight() / 8 * 5 - 50, getWidth() / 5 + 100, getHeight() / 8 * 5);  //right vertex to bottom vertex
        g.drawLine(getWidth() / 5 + 100, getHeight() / 8 * 5, getWidth() / 5 + 25, getHeight() / 8 * 5 - 50);   //bottom vertex to left vertex
        g.drawLine(getWidth() / 5 + 25, getHeight() / 8 * 5 - 50, getWidth() / 5 + 100, getHeight() / 8 * 5 - 100);          //top vertex to left vertex


        //add the bets amounts jlabels to the top left of the screen
        betAmountHeader.setForeground(Color.WHITE);
        betAmountHeader.setFont(new Font("Serif", Font.BOLD, 25));
        betAmountHeader.setBounds(0, 50, betAmountHeader.getPreferredSize().width, betAmountHeader.getPreferredSize().height);

        anteDisplayText.setForeground(Color.WHITE);
        anteDisplayText.setFont(new Font("Serif", Font.BOLD, 25));
        anteDisplayText.setBounds(0, 75, anteDisplayText.getPreferredSize().width, anteDisplayText.getPreferredSize().height);

        pairPlusDisplayText.setForeground(Color.WHITE);
        pairPlusDisplayText.setFont(new Font("Serif", Font.BOLD, 25));
        pairPlusDisplayText.setBounds(0, 100, pairPlusDisplayText.getPreferredSize().width, pairPlusDisplayText.getPreferredSize().height); 

        primeDisplayText.setForeground(Color.WHITE);
        primeDisplayText.setFont(new Font("Serif", Font.BOLD, 25));
        primeDisplayText.setBounds(0, 125, primeDisplayText.getPreferredSize().width, primeDisplayText.getPreferredSize().height);

        sixCardDisplayText.setForeground(Color.WHITE);
        sixCardDisplayText.setFont(new Font("Serif", Font.BOLD, 25));
        sixCardDisplayText.setBounds(0, 150, sixCardDisplayText.getPreferredSize().width, sixCardDisplayText.getPreferredSize().height);

        rulesButton.setBounds(getWidth() - rulesButton.getPreferredSize().width, 0, rulesButton.getPreferredSize().width, rulesButton.getPreferredSize().height);
        payoutsButton.setBounds(getWidth() - payoutsButton.getPreferredSize().width, rulesButton.getPreferredSize().height, payoutsButton.getPreferredSize().width, payoutsButton.getPreferredSize().height);
        
        resetBets.setBounds(0, getHeight() - 200, resetBets.getPreferredSize().width, resetBets.getPreferredSize().height);
        
        //Circle and label placementfor Pair Plus
        g.setColor(Color.YELLOW);
        g.drawOval(getWidth() / 5 - 50, getHeight() / 3, 100, 100);
        pairText.setFont(new Font("Serif", Font.BOLD, 25));
        pairText.setForeground(Color.YELLOW);
        pairText.setBounds(getWidth() / 5 - 30, getHeight() / 3 + 10, playText.getPreferredSize().width, playText.getPreferredSize().height);
        plusText.setFont(new Font("Serif", Font.BOLD, 25));
        plusText.setForeground(Color.YELLOW);
        plusText.setBounds(getWidth() / 5 - 30, getHeight() / 3 + 40, playText.getPreferredSize().width, playText.getPreferredSize().height);

            //Circle and label placement for Prime
        g.setColor(Color.BLACK);
        g.drawOval(getWidth() / 5 + 150, getHeight() / 3, 100, 100);
        primeText.setFont(new Font("Serif", Font.BOLD, 25));
        primeText.setForeground(Color.RED);
        primeText.setBounds(getWidth() / 5 + 157, getHeight() / 3 + 25, playText.getPreferredSize().width + 10, playText.getPreferredSize().height + 10);
        
            //Circle and label placement for 6 Card Bonus
        g.setColor(Color.WHITE);
        g.drawOval(getWidth() / 5 + 50, getHeight() / 3 - 50, 100, 100);
        sixCardText.setFont(new Font("Serif", Font.BOLD, 18));
        sixCardText.setForeground(Color.ORANGE);
        sixCardText.setBounds(getWidth() / 5 + 65, getHeight() / 3 - 35, playText.getPreferredSize().width, playText.getPreferredSize().height);
        bonusText.setForeground(Color.ORANGE);
        bonusText.setFont(new Font("Serif", Font.BOLD, 18));
        bonusText.setBounds(getWidth() / 5 + 65, getHeight() / 3 - 15, playText.getPreferredSize().width, playText.getPreferredSize().height);

        //Location for dealer's cards
        g.drawRoundRect(getWidth() / 4 * 3 - 200, getHeight() / 8, 100, 145, 10, 10);
        g.drawRoundRect(getWidth() / 4 * 3 - 100, getHeight() / 8, 100, 145, 10, 10);
        g.drawRoundRect(getWidth() / 4 * 3, getHeight() / 8, 100, 145, 10, 10);

        //Location for player's cards
        g.drawRoundRect(getWidth() / 4 * 3 - 200, getHeight() / 8 * 7 - 145, 100, 145, 10, 10);
        g.drawRoundRect(getWidth() / 4 * 3 - 100, getHeight() / 8 * 7 - 145, 100, 145, 10, 10);
        g.drawRoundRect(getWidth() / 4 * 3, getHeight() / 8 * 7 - 145, 100, 145, 10, 10);

        //text For Play Bet
        playText.setFont(new Font("Serif", Font.BOLD, 30));
        playText.setForeground(Color.ORANGE);
        playText.setBounds(getWidth() / 5 + 60, getHeight() / 8 * 5 + 30, playText.getPreferredSize().width + 10, playText.getPreferredSize().height + 10);
        
        //text for Ante Bet
        anteText.setFont(new Font("Serif", Font.BOLD, 30));
        anteText.setForeground(Color.ORANGE);
        anteText.setBounds(getWidth() / 5 + 60, getHeight() / 8 * 5 - 70, playText.getPreferredSize().width + 10, playText.getPreferredSize().height + 10);

        //text for playerBalance
        playerBalanceText.setFont(new Font("Serif", Font.BOLD, 30));
        playerBalanceText.setForeground(Color.WHITE);
        playerBalanceText.setBounds(0, 0, playerBalanceText.getPreferredSize().width, playerBalanceText.getPreferredSize().height);
        
    }

    //other functions
    public void addBets()   //must be called whenever ante, pairplus, or prime is changed
    {
        totalBet = ante + pairplus + prime + sixcard;
    }
    
    public void playGame()      //only to be called in PlayButtonHandler
    {
        
        int index = 0;
        
        //find players' cards to display
        for(int i = 0; i < 3; i++)
        {
            index = (playerHand[i].getValue() - 2) * 4 + playerHand[i].getSuit() - 1;
            labelArray[index].setBounds(getWidth() / 4 * 3 - 200  + 100 * i, getHeight() / 8 * 7 - 145, labelArray[index].getPreferredSize().width, labelArray[index].getPreferredSize().height);
            playerCardLocations[i] = labelArray[index];
            
        }

        //find dealers'' cards to display
        for(int i = 0; i < 3; i++)
        {
            index = (dealerHand[i].getValue() - 2) * 4 + dealerHand[i].getSuit() - 1;
            labelArray[index].setBounds(getWidth() / 4 * 3 - 200 + 100 * i, getHeight() / 8, cardBacks[0].getPreferredSize().width, cardBacks[0].getPreferredSize().height);
            dealerCardLocations[i] = labelArray[index];
        }

        //add the cards to the screen
        add(playerCardLocations[0]);
        add(playerCardLocations[1]);
        add(playerCardLocations[2]);
        add(cardBacks[0]);
        add(cardBacks[1]);
        add(cardBacks[2]);

        //add the call and fold buttons and remove the play button
        add(callButton);
        add(foldButton);
        remove(playButton);


        //get the strength of the side bets
        int pairPlusStrength = checkPairPlus(sortCards(playerHand));
        int primeStrength = checkPrime(cards);
        int sixCardStrength = checkSixCard(sortCards(cards));

        //checks if dealer has better than a high card
        if(checkPairPlus(sortCards(dealerHand)) > 0)
        {
            dealerQualify = true;
        }
        //check if dealer has Queen High
        else
        {
            if(dealerHand[0].getValue() >= 12 || dealerHand[1].getValue() >= 12 || dealerHand[2].getValue() >= 12)
            {
                dealerQualify = true;
            }
            else
            {
                dealerQualify = false;
            }
        }

        
        //check if the player has a better hand than the dealer
        if(pairPlusStrength > checkPairPlus(sortCards(dealerHand)))
        {
            playerWins = true;
        }
        //check if the dealer has a better hand than the player
        else if(pairPlusStrength < checkPairPlus(sortCards(dealerHand)))
        {
            playerWins = false;
        }
        //if both the dealer and player have hands of the same strength, determine who has the higher card to determine the winner
        else
        {

            Pair player = new Pair(pairPlusStrength, sortCards(playerHand));
            Pair dealer = new Pair(checkPairPlus(sortCards(dealerHand)), sortCards(dealerHand));

            if(Pair.betterHand(player, dealer) == 1)
            {
                playerWins = true;
            }
            else if(Pair.betterHand(player, dealer) == 2)
            {
                playerWins = false;
            }
            else
            {
                push = true;
            } 
        }
        

        //checks if player has a bet on pair plus 
        if(pairplus > 0)
        {
            switch (pairPlusStrength) 
            {
                case 6:
                    pairplus = pairplus * 200 + pairplus;
                    break;
                case 5:
                    pairplus = pairplus * 40 + pairplus;
                    break;
                case 4:
                    pairplus = pairplus * 30 + pairplus;
                    break;
                case 3:
                    pairplus = pairplus * 6 + pairplus;
                    break;
                case 2:
                    pairplus = pairplus * 3 + pairplus;
                    break;
                case 1:
                    pairplus = pairplus * 1 + pairplus;
                    break;
                default:
                    pairplus = 0;
                    break;
            }
        }

        //checks if user has a bet on prime
        if(prime > 0)
        {
            switch (primeStrength) 
            {
                case 2:
                    prime = prime * 4 + prime;
                    break;
                case 1:
                    prime = prime * 3 + prime;
                    break;
            
                default:
                    prime = 0;
                    break;
            }
        }

        //checks if user has a bet on six card bonus
        if(sixcard > 0)
        {
            switch (sixCardStrength) 
            {
                case 7:
                    sixcard = sixcard * 1000 + sixcard;
                    break;
                case 6:
                    sixcard = sixcard * 200 + sixcard ;
                    break;
                case 5:
                    sixcard = sixcard * 50 + sixcard;
                    break;
                case 4:
                    sixcard = sixcard * 25 + sixcard;
                    break;
                case 3:
                    sixcard = sixcard * 20 + sixcard;
                    break;
                case 2:
                    sixcard = sixcard * 10 + sixcard;
                    break;
                case 1:
                    sixcard = sixcard * 5 + sixcard;
                    break;
                default:
                    sixcard = 0;
                    break;
            }
        }
        
        //set up the jlabels for the payouts that display after the hand
        pairPlusPayout.setForeground(Color.WHITE);
        payoutsDisplayHeader.setForeground(Color.WHITE);
        primePayout.setForeground(Color.WHITE);
        sixCardPayout.setForeground(Color.WHITE);

        pairPlusPayout.setText("Pair Plus: " + pairplus);
        primePayout.setText("Prime: " + prime);
        sixCardPayout.setText("Six Card Bonus: " + sixcard);

        payoutsDisplayHeader.setFont(new Font("Serif", Font.BOLD, 25));
        pairPlusPayout.setFont(new Font("Serif", Font.BOLD, 25));
        primePayout.setFont(new Font("Serif", Font.BOLD, 25));
        sixCardPayout.setFont(new Font("Serif", Font.BOLD, 25));

        payoutsDisplayHeader.setBounds(0, 200, payoutsDisplayHeader.getPreferredSize().width, payoutsDisplayHeader.getPreferredSize().height);
        pairPlusPayout.setBounds(0, 250, pairPlusPayout.getPreferredSize().width, pairPlusPayout.getPreferredSize().height);
        primePayout.setBounds(0, 275, primePayout.getPreferredSize().width, primePayout.getPreferredSize().height);
        sixCardPayout.setBounds(0, 300, sixCardPayout.getPreferredSize().width, sixCardPayout.getPreferredSize().height);

        //update players' balance
        playerBalance += (pairplus + prime + sixcard);
        playerBalanceText.setText("Player balance: " + Integer.toString(playerBalance));

        repaint();
        
    }

    //determine cards for the next hand
    public void nextHand()
    {
        cards = deck.randomCard();
        for(int i = 0; i < 3; i++)
        {
            playerHand[i] = cards[i];
        }
        dealerHand = new Card[3];

        for(int i = 0; i < 3; i++)
        {
            dealerHand[i] = cards[i + 3];
        }
    }

    //sort an array of cards
    public Card[] sortCards(Card[] cardArr)
    {
        Card[] newArr = new Card[cardArr.length];
        for(int i = 0; i < cardArr.length; i++)
        {
            newArr[i] = cardArr[i];
            
        }
        Arrays.sort(newArr);
        return newArr;

    }

    //check strength of pair plus
    public int checkPairPlus(Card[] cardArr)
    {
        boolean flush = (cardArr[0].getSuit() == cardArr[1].getSuit() && cardArr[0].getSuit() == cardArr[2].getSuit());
        boolean straight = (cardArr[0].getValue() + 1 == cardArr[1].getValue() && cardArr[1].getValue() + 1 == cardArr[2].getValue());
        boolean threeKind = (cardArr[0].getValue() == cardArr[1].getValue() && cardArr[0].getValue() == cardArr[2].getValue());
        boolean pair = (cardArr[0].getValue() == cardArr[1].getValue() || cardArr[0].getValue() == cardArr[2].getValue() || cardArr[1].getValue() == cardArr[2].getValue());

        //check for A,2,3 straight
        if(cardArr[2].getValue() == 14 && cardArr[0].getValue() == 2 && cardArr[1].getValue() == 3)
        {
            straight = true;
        }
        
        //Check for stright flush & mini royal
        if(flush && straight)
        {
            if(cardArr[2].getValue() == 14 && cardArr[1].getValue() == 13 && cardArr[0].getValue() == 12)
            {
                return 6;
            }
            else
            {
                return 5;
            }
        }
      
        if(threeKind)
        {
            return 4;
        }

        if(straight)
        {
            return 3;
        }

        if(flush)
        {
            return 2;
        }

        if(pair)
        {
            return 1;
        }

        return 0;
    }

    //check strength of prime
    public int checkPrime(Card[] cardArr)
    {    
        //Clubs = 1, Diamonds = 2, Hearts = 3, Spades = 4

        //sets array to either true or false if the card is black or not
        boolean[] black = new boolean[6];
        for(int i = 0; i < 6; i++)
        {
            black[i] = (cardArr[i].getSuit() == 1 || cardArr[i].getSuit() == 4);
        }

        //sets array to either true or false if the card is red or not
        boolean[] red = new boolean[6];
        for(int i = 0; i < 6; i++)
        {
            red[i] = (cardArr[i].getSuit() == 2 || cardArr[i].getSuit() == 3);
        }


        //if all cards are black
        if(black[0] && black[1] && black[2] && black[3] && black[4] && black[5])
        {
            return 2;
        }

        //if all cards are red
        if(red[0] && red[1] && red[2] && red[3] && red[4] && red[5])
        {
            return 2;
        }

        //if players' cards are black
        if(black[0] && black[1] && black[2])
        {
            return 1;
        }

        //if player's cards are red
        if(red[0] && red[1] && red[2])
        {
            return 1;
        }


        return 0;
    }

    //check strength of 6 card bonus
    public int checkSixCard(Card[] cardArr)
    {
        boolean royalFlush = false;
        boolean straightFlush = false;
        boolean flush = false;
        boolean straight = false;

        //check for flush
        int clubs = 0;
        int diamonds = 0;
        int hearts = 0;
        int spades = 0;

        //Check for flush
        for(int i = 0; i < 6; i++)
        {
            switch (cardArr[i].getSuit()) 
            {
                case 1:
                    clubs++;
                    break;
                case 2:
                    diamonds++;
                    break;
                case 3:
                    hearts++;
                    break;
                case 4:
                    spades++;
                    break;
                default:
                    break;
            }
        }
        flush = (clubs >= 5 || diamonds >= 5 || hearts >= 5 || spades >= 5);

        int index = 1;
        int[] diffNums = new int[6];
        int currentNum = cardArr[0].getValue();
        diffNums[0] = cardArr[0].getValue();
        int straightCount = 0;
        //make array of each different number
        for(int i = 1; i < 6; i++)
        {
            if(cardArr[i].getValue() != currentNum)
            {
                currentNum = cardArr[i].getValue();
                diffNums[index] = cardArr[i].getValue();
                index++;
            }
        }

        //check if straight occurs by having 5 consecutive numbers
        for(int i = 0; i < index - 1; i++)
        {
            if(diffNums[i] + 1 == diffNums[i + 1])
            {
                straight = true;
                straightCount++;
            }
            else
            {
                if(straightCount == 4)
                {
                    straight = true;
                    break;
                }
                straight = false;
                break;
            }
        }

        straightCount = 0;
        //check again for a straight with a different starting index
        if(!straight)
        {
            for(int i = 1; i < index - 1; i++)
            {
                if(diffNums[i] + 1 == diffNums[i + 1])
                {
                    straight = true;
                    straightCount++;
                }
                else
                {
                    if(straightCount == 4)
                    {
                        straight = true;
                        break;
                    }
                    straight = false;
                    break;
                }
            }
        }

        //see if it is necessary to check for a A-5 straight
        boolean checkA5Straight = false;
        for(int i = 0; i < 6; i++)
        {
            if(diffNums[i] == 14)
            {
                checkA5Straight = true;
            }
        }

        //manually check if a 2, 3, 4, and 5 are all in the array with an ace to make a straight
        if(checkA5Straight)
        {
            if(diffNums[0] == 2 && diffNums[1] == 3 && diffNums[2] == 4 && diffNums[3] == 5)
            {
                straight = true;
            }
        }


        //set checkSuit to the suit that the flush occurred with
        int checkSuit = 0;
        if(clubs >= 5)
        {
            checkSuit = 1;
        }
        else if(diamonds >= 5)
        {
            checkSuit = 2;
        }
        else if(hearts >= 5)
        {
            checkSuit = 3;
        }
        else if(spades >= 5)
        {
            checkSuit = 4;
        }
        
        int[] flushCards = new int[6];
        index = 0;
        straightFlush = false;
        straightCount = 0;
        //check for straight flush the same way as a normal straight but with all the cards present in the flush
        if(flush)
        {
            for(int i = 0; i < 6; i++)
            {
                if(cardArr[i].getSuit() == checkSuit)
                {
                    flushCards[index] = cardArr[i].getValue();
                    index++;
                }
            }
            for(int i = 0; i < index - 1; i++)
            {
                if(flushCards[i] + 1 == flushCards[i + 1])
                {
                    straightFlush = true;
                    straightCount++;
                }
                else
                {
                    if(straightCount == 4)
                    {
                        straightFlush = true;
                        break;
                    }
                    straightFlush = false;
                    break;
                }
            }


            if(!straightFlush)
            {
                for(int i = 1; i < index - 1; i++)
                {
                    if(flushCards[i] + 1 == flushCards[i + 1])
                    {
                        straightFlush = true;
                        straightCount++;
                    }
                    else
                    {
                        if(straightCount == 4)
                        {
                            straightFlush = true;
                            break;
                        }
                        straightFlush = false;
                        break;
                    }
                }
            }
        }


        //check if an A-5 straight flush occurs
        boolean checkA5StraightFlush = false;
        boolean checkRoyalFlush = false;
        int aceIndex = 0;
        for(int i = 0; i < 6; i++)
        {
            if(flushCards[i] == 14)
            {
                checkA5StraightFlush = true;
                checkRoyalFlush = true;
                aceIndex = i;
            }
        }

        if(checkA5StraightFlush)
        {
            if(flushCards[0] == 2 && flushCards[1] == 3 && flushCards[2] == 4 && flushCards[3] == 5)
            {
                straightFlush = true;
            }
        }

        if(checkRoyalFlush)
        {
            if(flushCards[aceIndex] == 14 && flushCards[aceIndex - 1] == 13 && flushCards[aceIndex - 2] == 12 && flushCards[aceIndex - 3] == 11 && flushCards[aceIndex - 4] == 10 )
            {
                royalFlush = true;
            }
        }



        if(royalFlush)
        {
            return 7;
        }
        if(straightFlush)
        {
            return 6;
        }






        //check 4 of a kind
        int count = 0;
        int lowest = 0;
        //check how many different card values there are because if there are 4 or more different cards, a 4 of a kind is impossible
        for(int i = 0; i < 6; i++)
        {
            if(cardArr[i].getValue() > lowest)
            {
                count++;
                lowest = cardArr[i].getValue();
            }
        }  

        //if there's 3 different card values, check for a 4 of a kind
        int current = cardArr[0].getValue();
        int count2 = 0;
        if(count <= 3)
        {
            for(int i = 0; i < 6; i++)
            {
                if(cardArr[i].getValue() == current)
                {
                    count2++;
                }
                else
                {
                    current = cardArr[i].getValue();
                    count2 = 1;
                }
                if(count2 == 4)
                {
                    return 5;
                }
            }
            //if there are 3 different cards and its not a four of a kind, that means it has to be a full house
            return 4;
        }

        if(flush)
        {
            return 3;
        }

        if(straight)
        {
            return 2;
        }

        count = 0;
        current = cardArr[0].getValue();
        //check for a 3 of a kind
        for(int i = 0; i < 6; i++)
        {
            if(cardArr[i].getValue() == current)
            {
                count++;
            }
            else
            {
                current = cardArr[i].getValue();
                count = 1;
            }
            if(count == 3)
            {
                return 1;
            }
        }

        

        return 0;
    }
    
    
    //=========================
    //NESTED CLASSES
    //=========================
    private class RadioButtonHandler implements ItemListener
    {
        private int b;
        public RadioButtonHandler(int bparam)
        {
            b = bparam;
        }

        public void itemStateChanged(ItemEvent event)
        {
            if(event.getStateChange() == ItemEvent.SELECTED)
            {
                bet = b; 
            }
        }
    }

    private class RulesButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "After the round of betting, The Player will be dealt their cards face-up, The dealer will be dealt their cards face down.\nIf the dealer qualifies with a queen-high or better, and the player wins, the player will be paid out 1:1 on their 'ante' bet and 'play' bet. \nIf the dealer doesn't qualify, the 'ante' bet will be pushed and the 'play' bet will be paid out 1:1 if the player wins.\nThe side bets will be paid out whether or not the player calls or folds.\n ");
        }
    }

    private class PayoutsButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "6 card bonus\n------------\nRoyal Flush        1000:1\nStraight Flush      200:1\nFour of a kind        50:1\nFull House              25:1\nFlush                       20:1\nStraight                   10:1\nThree of a Kind       5:1\n\nPair Plus\n---------\nMini Royal           200:1\nStraight Flush       40:1\nThree of a Kind    30:1\nStraight                   6:1\nFlush                       3:1\nPair                          1:1\n\nPrime\n---------\nPlayer Monotone Hand          3:1\nAll Cards Monotone               4:1");
        }
    }

    private class ResetBetButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playerBalance += totalBet;
            playerBalanceText.setText("Player balance: " + Integer.toString(playerBalance));    //update player balance text
            ante = 0;
            anteDisplayText.setText("Ante : 0");
            bet = 0;
            pairplus = 0;
            pairPlusDisplayText.setText("Pair Plus: 0");
            sixcard = 0;
            sixCardDisplayText.setText("6 card bonus: 0");
            prime = 0;
            primeDisplayText.setText("Prime: 0");
            addBets();
            chipButtonGroup.clearSelection();
        }
    }

    private class AnteHandler implements MouseListener
    {
        public void mouseClicked(MouseEvent event)       //only important one currently
        {
            int anteDummy = ante + bet;
            int playerBalanceDummy = playerBalance - bet;
            if(anteDummy <= playerBalanceDummy)
            {
                ante += bet;
                addBets();
                playerBalance -= bet;
                playerBalanceText.setText("Player balance: " + Integer.toString(playerBalance));    //update player balance text
                anteDisplayText.setText("Ante: " + Integer.toString(ante));
            }
            else
                JOptionPane.showMessageDialog(null, "You don't have enough chips for this bet. You need at least " + ante + " chips to match your ante.");
        }

        //abstract methods must be implemented. Don't need them currently, so left bodies empty
        public void mouseReleased(MouseEvent e)
        {

        }

        public void mouseExited(MouseEvent e) 
        {

        }

        public void mouseEntered(MouseEvent e)
        {

        }

        public void mousePressed(MouseEvent e)
        {
            
        }
    }

    private class PairPlusHandler implements MouseListener
    {
        public void mouseClicked(MouseEvent event)       //only important one currently
        {
            int playerBalanceDummy = playerBalance - bet;
            if(ante <= playerBalanceDummy && ante > 0)
            {
                pairplus += bet;
                addBets();
                playerBalance -= bet;
                playerBalanceText.setText("Player balance: " + Integer.toString(playerBalance));    //update player balance text
                pairPlusDisplayText.setText("Pair Plus: " + Integer.toString(pairplus));
            }
            else if(ante == 0)
                JOptionPane.showMessageDialog(null, "You must place a bet on ante before making side bets.");
            else
                JOptionPane.showMessageDialog(null, "You don't have enough chips for this bet. You need at least " + ante + " chips to match your ante.");
        }

        //abstract methods must be implemented. Don't need them currently, so left bodies empty
        public void mouseReleased(MouseEvent e)
        {

        }

        public void mouseExited(MouseEvent e) 
        {

        }

        public void mouseEntered(MouseEvent e)
        {

        }

        public void mousePressed(MouseEvent e)
        {
            
        }
    }

    private class PrimeHandler implements MouseListener
    {
        public void mouseClicked(MouseEvent event)       //only important one currently
        {
            int playerBalanceDummy = playerBalance - bet;
            if(ante <= playerBalanceDummy && ante > 0)
            {
                prime += bet;
                addBets();
                playerBalance -= bet;
                playerBalanceText.setText("Player balance: " + Integer.toString(playerBalance));    //update player balance text
                primeDisplayText.setText("Prime: " + Integer.toString(prime));
            }
            else if(ante == 0)
                JOptionPane.showMessageDialog(null, "You must place a bet on ante before making side bets.");
            else
                JOptionPane.showMessageDialog(null, "You don't have enough chips for this bet. You need at least " + ante + " chips to match your ante.");
        }

        //abstract methods must be implemented. Don't need them currently, so left bodies empty
        public void mouseReleased(MouseEvent e)
        {

        }

        public void mouseExited(MouseEvent e) 
        {

        }

        public void mouseEntered(MouseEvent e)
        {

        }

        public void mousePressed(MouseEvent e)
        {
            
        }
    }
    
    private class SixCardHandler implements MouseListener
    {
        public void mouseClicked(MouseEvent event)       //only important one currently
        {
            int playerBalanceDummy = playerBalance - bet;
            if(ante <= playerBalanceDummy && ante > 0)
            {
                sixcard += bet;
                addBets();
                playerBalance -= bet;
                playerBalanceText.setText("Player balance: " + Integer.toString(playerBalance));    //update player balance text
                sixCardDisplayText.setText("Six Card: " + Integer.toString(sixcard));
            }
            else if(ante == 0)
                JOptionPane.showMessageDialog(null, "You must place a bet on ante before making side bets.");
            else
                JOptionPane.showMessageDialog(null, "You don't have enough chips for this bet. You need at least " + ante + " chips to match your ante.");
        }

        //abstract methods must be implemented. Don't need them currently, so left bodies empty
        public void mouseReleased(MouseEvent e)
        {

        }

        public void mouseExited(MouseEvent e) 
        {

        }

        public void mouseEntered(MouseEvent e)
        {

        }

        public void mousePressed(MouseEvent e)
        {
            
        }
    }

    private class PlayButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(ante > 0)
            {
                remove(resetBets);
                chipButtonGroup.clearSelection();
                anteText.removeMouseListener(aH);
                pairText.removeMouseListener(ppH);
                plusText.removeMouseListener(ppH);
                primeText.removeMouseListener(primeH);
                sixCardText.removeMouseListener(scH);
                bonusText.removeMouseListener(scH);
                chipButton1.removeItemListener(chipHandler1);
                chipButton10.removeItemListener(chipHandler10);
                chipButton25.removeItemListener(chipHandler25);
                chipButton100.removeItemListener(chipHandler100);
                
                JOptionPane.showMessageDialog(null, "Betting has ended. The game will now begin.");
                playGame(); 
            }
            else
                JOptionPane.showMessageDialog(null, "You must place a bet on ante before playing.");
        }
    }

    private class FoldButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                //reveal dealer cards

                ante = 0;
                antePayout.setText("Ante: 0");
                //bet = 0;
                remove(cardBacks[0]);
                remove(cardBacks[1]);
                remove(cardBacks[2]);
                add(payoutsDisplayHeader);
                add(antePayout);
                add(pairPlusPayout);
                add(primePayout);
                add(sixCardPayout);
                add(dealerCardLocations[0]);
                add(dealerCardLocations[1]);
                add(dealerCardLocations[2]);
                remove(callButton);
                remove(foldButton);
                

                nextRoundButton.setBounds(getWidth() / 4 * 3 - 200, getHeight() / 2, nextRoundButton.getPreferredSize().width, nextRoundButton.getPreferredSize().height);
                add(nextRoundButton);
                nextRoundButton.addActionListener(nRH);
                repaint();

                nextHand();


                
        }
    }


    private class NextRoundButtonHandler implements ActionListener      //same as fold. only an option after you call to begin next round of betting
    {
        public void actionPerformed(ActionEvent e)      
        {
            ante = 0;
            bet = 0;
            pairplus = 0;
            prime = 0;
            sixcard = 0;
            anteDisplayText.setText("Ante: " + Integer.toString(ante));
            pairPlusDisplayText.setText("Pair Plus: " + Integer.toString(pairplus));
            sixCardDisplayText.setText("Six Card: " + Integer.toString(sixcard));
            primeDisplayText.setText("Prime: " + Integer.toString(prime));

            remove(payoutsDisplayHeader);
            remove(antePayout);
            remove(pairPlusPayout);
            remove(primePayout);
            remove(sixCardPayout);
            remove(dealerCardLocations[0]);
            remove(dealerCardLocations[1]);
            remove(dealerCardLocations[2]);
            remove(playerCardLocations[0]);
            remove(playerCardLocations[1]);
            remove(playerCardLocations[2]);
            remove(callButton);
            remove(foldButton);
            nextRoundButton.removeActionListener(nRH);
            remove(nextRoundButton);
            add(playButton);
            add(resetBets);
            repaint();    
            anteText.addMouseListener(aH);
            pairText.addMouseListener(ppH);
            plusText.addMouseListener(ppH);
            primeText.addMouseListener(primeH);
            sixCardText.addMouseListener(scH);
            bonusText.addMouseListener(scH);
            chipButton1.addItemListener(chipHandler1);
            chipButton10.addItemListener(chipHandler10);
            chipButton25.addItemListener(chipHandler25);
            chipButton100.addItemListener(chipHandler100);
            nextHand();
        }
    }

    private class CallButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playerBalance -= ante;      //match ante to play


            


            if(push)
            {
                playerBalance += ante * 2;
            }
            else
            {
                if(!(dealerQualify))
                {
                    playerBalance += ante;
                    antePayout.setText("Ante: " + ante);
                    if(playerWins)
                    {
                        playerBalance += ante * 2;
                        antePayout.setText("Ante: " + ante * 2);
                    }
                }
                else
                {
                    if(playerWins)
                    {
                        playerBalance += ante * 4;
                        antePayout.setText("Ante: " + ante * 4);
                    }
                    else
                    {
                        antePayout.setText("Ante: " + 0);
                    }
                }
            }
            
            
            
            
            
            
            
            antePayout.setForeground(Color.WHITE);
            antePayout.setFont(new Font("Serif", Font.BOLD, 25));
            antePayout.setBounds(0, 225, antePayout.getPreferredSize().width, antePayout.getPreferredSize().height);
            add(antePayout);
            playerBalanceText.setText("Player balance: " + Integer.toString(playerBalance));    //update player balance text
            add(nextRoundButton);
            nextRoundButton.setBounds(100, 100, nextRoundButton.getPreferredSize().width, nextRoundButton.getPreferredSize().height);
            nextRoundButton.addActionListener(nRH);
            repaint();
            add(payoutsDisplayHeader);
            add(pairPlusPayout);
            add(primePayout);
            add(sixCardPayout);
            remove(cardBacks[0]);
            remove(cardBacks[1]);
            remove(cardBacks[2]);
            chipButton1.removeItemListener(chipHandler1);
            chipButton10.removeItemListener(chipHandler10);
            chipButton25.removeItemListener(chipHandler25);
            chipButton100.removeItemListener(chipHandler100);
        //find cards to display

            add(dealerCardLocations[0]);
            add(dealerCardLocations[1]);
            add(dealerCardLocations[2]);
            repaint();
            remove(foldButton);
            remove(callButton);



            
            
            
        }
    }

    private class Pair
    {
        int handType;
        int[] high = new int[3];

        public Pair()
        {
            
        }

        public Pair(int ht, Card[] h)
        {
            handType = ht;
            for(int i = 0; i < 3; i++)
                high[i] = h[i].getValue();
        }

        public static int betterHand(Pair p1, Pair p2)  //returns 1 if p1 is better. returns 2 if 2 is better. returns 0 otherwise
        {
            if(p1.handType > p2.handType)
                return 1;
            else if(p1.handType < p2.handType)
                return 2;
            else
            {
                for(int i = 2; i >= 0; i--)
                {
                    if(p1.high[i] > p2.high[i])
                        return 1;
                    else if(p1.high[i] < p2.high[i])
                        return 2;
                }
            }
            return 0;
        } 
    }
}