// importing jframe components
import javax.swing.*;
import java.awt.*;
// importing jbutton components
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// importing timer components
import java.util.Timer;
import java.util.TimerTask;
// importing random class
import java.util.Random;

/*
 *  to do list
 *  - make things look nice
 *  - finish mystery item
 */


public class Game {

    // frame width and height
    final int frame_width = 600;
    final int frame_height = 600;

    // adding some classes and variables
    JPanel cards = new JPanel(new CardLayout()); // will contain most panels
    JPanel loreCards = new JPanel(new CardLayout()); // will contain the storyline panels at the start

    //required classes
    CardLayout cardLayout = new CardLayout(); // cardLayoutobject for creating cards
    Money myMoney = new Money(); // object for counting money and earnings
    Revolver rev = new Revolver(); // objects for roulette games
    Timer timer = new Timer(); // timer for delays
    final int waitTime = 3000; // delays will be 3 seconds
    Random rand = new Random(); // randomizer object

    // coin flip
    double betAmount;
    String side;
    String headsOrTails;

    // roulette
    // information about your opponent in roulette
    int opponentBalance;
    int opponentAge = 10;
    String opponentName = "Ben Zhou";
    // amount of lives left
    int lives = 1;
    // how many rounds left in roulette
    int bullets;
    // variables used for setting up buttons in roulette
    int xButton = 0;
    int yButton = 90;


    // story panels at the start
    int currLore = 0; // used to interate through story panels
    // creating all story panels
    JPanel lore1 = new JPanel();
    JPanel lore2 = new JPanel();
    JPanel lore3 = new JPanel();
    JPanel lore4 = new JPanel();
    JPanel lore5 = new JPanel();
    JPanel[] loreArr = { lore1, lore2, lore3, lore4, lore5}; // adding to array to iterate through later

    // creating JPanels (will be added to cards laters)
    JPanel panel = new JPanel(); // main panel

    JPanel coinPanel = new JPanel(); // coin flip panels
    JPanel betPanel = new JPanel();
    JPanel resultPanel = new JPanel();

    JPanel roulettePanel = new JPanel(); // roulette panels
    JPanel roulettePrepPanel = new JPanel();
    JPanel opponentInfoPanel = new JPanel();
    JPanel opponentMovePanel = new JPanel();
    JPanel nextRoundPanel = new JPanel();
    JPanel wonRoulettePanel = new JPanel();
    JPanel lostRoulettePanel = new JPanel();
    JPanel gameOverPanel = new JPanel();

    JPanel storePanel = new JPanel(); // store panels
    JPanel shopPanel = new JPanel();
    JPanel boughtPanel = new JPanel();

    JPanel laborPanel = new JPanel(); // labor panel

    JPanel winPanel = new JPanel(); // panel for when you reach 1,000,000 money (win)
    JPanel losePanel = new JPanel(); // panel for when you lose roulette

    // displaying money and lives
    JLabel displayMoney = new JLabel("current balance: " + myMoney.toString());
    JLabel displayLives = new JLabel("lives left: " + lives);

    // lore objects
    // adding images for the story at the start
    JLabel loreImg1 = new JLabel(new ImageIcon("imgs/lore1.png"));
    JLabel loreImg2 = new JLabel(new ImageIcon("imgs/lore2.png"));
    JLabel loreImg3 = new JLabel(new ImageIcon("imgs/lore3.png"));
    JLabel loreImg4 = new JLabel(new ImageIcon("imgs/lore4.png"));
    JLabel loreImg5 = new JLabel(new ImageIcon("imgs/lore5.png"));
    // adding labels for the story at the start
    JLabel loreLabel = new JLabel("you are a 19 year-old university student", SwingConstants.CENTER);
    JLabel loreLabel2 = new JLabel("<html>one day, you recieve an eviction notice", SwingConstants.CENTER);
    JLabel loreLabel3 = new JLabel("this month's rent is 1,000,000 money", SwingConstants.CENTER);
    JLabel loreLabel4 = new JLabel("you only have 1,000 money", SwingConstants.CENTER);
    JLabel loreLabel5 = new JLabel("you go to the casino to make money", SwingConstants.CENTER);
    // putting labels and images to array to make it easier to loop through them
    JPanel[] lorePanels = {lore1, lore2, lore3, lore4, lore5 };
    JLabel[] loreImgs = {loreImg1, loreImg2, loreImg3, loreImg4, loreImg5};
    JLabel[] loreLabels = {loreLabel, loreLabel2, loreLabel3, loreLabel4, loreLabel5};
    JButton loreButton = new JButton("continue"); // button used to go next story panel

    // betting objects
    JLabel betLabel = new JLabel("how much do you want to bet?");
    JTextField betField = new JTextField(); // bet field for collecting input for bets
    JButton betButton = new JButton("submit"); // submit your bet
    JButton allInButton = new JButton("ALL IN!!!"); // button used for betting everything

    // labels used to show "loading screen" and results
    JLabel flippingLabel = new JLabel();
    JLabel winningsLabel = new JLabel();

    JLabel callCoinLabel = new JLabel("call a side"); // coin flip label
    // heads and tails buttons when flipping coin
    JButton heads = new JButton("heads");
    JButton tails = new JButton("tails");

    // roulette objects
    JLabel pickLabel = new JLabel("pick a number. one will make you lose");
    JLabel oppBalanceLabel = new JLabel();
    JLabel rouletteStartLabel = new JLabel(
            "<html>you and your opponent will bet your entire lives and balance in a game of roulette.<br><br>both players will choose a number between 1 and 6. if someone chooses wrong, they lose the game.<br><br>the game will keep going until someone wins.<br><br>are you sure you want to continue?<html>");
    JButton startRoulette = new JButton("start"); // button for starting roulette game
    JButton confirmRoulette = new JButton("next round"); // button for continuing next round in roulette
    // labels in opponent's move screen
    JLabel opponentLabel = new JLabel("<html>you survived!<br><br>opponent is choosing number....");
    JLabel nextRoundLabel = new JLabel("<html>you and your opponent both survived!<br><br>start next round?");
    // if you lose roulette
    JLabel gameOverImg = new JLabel(new ImageIcon("imgs/gameover.png")); // game over screen
    JLabel lostRouletteImg = new JLabel(new ImageIcon("imgs/lost.png")); // lost roulette screen
    JLabel lostRouletteLabel = new JLabel();
    JLabel wompWomp = new JLabel(""); // label used when you win roulette

    // store panel objects
    // variables used to count how many items you have
    boolean haveMultiplier = false; // can only buy one multiplier
    int numRaise = 0;
    int itemPrice = 0;
    // store objects
    JLabel storeLabel = new JLabel("buy what you need");
    JLabel itemLabel = new JLabel();
    JLabel priceLabel = new JLabel();
    // adding store item images
    ImageIcon img1 = new ImageIcon("imgs/multiplier.png");
    ImageIcon img2 = new ImageIcon("imgs/life.png");
    ImageIcon img3 = new ImageIcon("imgs/raise.png");
    // storing store images as jlabels
    JLabel itemImg = new JLabel();
    JLabel itemImg1 = new JLabel(img1);
    JLabel itemImg2 = new JLabel(img2);
    JLabel itemImg3 = new JLabel(img3);
    // creating buttons for shop items
    JButton item1 = new JButton("reward multiplier");
    JButton item2 = new JButton("extra life");
    JButton item3 = new JButton("salary increase");
    JButton item4 = new JButton("mystery item");
    // creating purchase buttons for each item
    JButton buyItem1 = new JButton("buy!");
    JButton buyItem2 = new JButton("buy!");
    JButton buyItem3 = new JButton("buy!");
    JButton buyItem4 = new JButton("buy!");
    JButton dontBuy = new JButton("no thanks"); // cancel purchase button
    JLabel boughtLabel = new JLabel(); // label for if you buy somethhing

    // home button
    JButton homeButton = new JButton("go home");

    // win button and win screen
    JButton winButton = new JButton("pay rent!!!"); // win button
    JLabel winScreen = new JLabel(new ImageIcon("imgs/won.png")); // win screen

    // check win
    boolean hasWon = false; // becomes true when money becomes 1,000,000

    public Game() {
        // sets up the entire game at the start
        // prepares the panels and objects
        setup();
        displaySetup();
        loreSetup();
        betSetup();
        flipSetup();
        homeSetup();
        coinSetup();
        rouletteSetup();
        laborSetup();
        storeSetup();

        // main buttons in the menu screen
        JButton button = new JButton("flip a coin"); // first button: coin flip
        button.setBounds(50, 100, 200, 200);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                betAmount = 0;
                betting("coin panel", coinPanel); // takes you the coin flipping menu
            }
        });
        JButton button2 = new JButton("roulette"); // second button: roulette
        button2.setBounds(350, 100, 200, 200);
        panel.add(button2);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                bullets = 6;
                roulette(); // takes you to the roulette menu
            }
        });

        JButton button3 = new JButton("go to the store"); // third button: store
        button3.setBounds(50, 350, 200, 200);
        panel.add(button3);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // System.out.println("hello world3");  
                switchPanel("store panel"); // opens the store panel with items
                showMoney(storePanel);
                home(storePanel);
            }
        });
        JButton button4 = new JButton("work your job"); // fourth button: job
        button4.setBounds(350, 350, 200, 200);
        panel.add(button4);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switchPanel("labor panel"); // opens the job menu
                showMoney(laborPanel);
                home(laborPanel);
            }
        });

        // creating the frame and window
        JFrame frame = new JFrame("window");
        frame.setSize(frame_width, frame_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // cannot resize the window
        frame.setLocationRelativeTo(null);
        frame.add(cards); // adding cards and panels to the frame
        frame.setVisible(true);
    }
    // method used to begin the coin flip
    public void betting(String nPanel, JPanel nPane) {
        home(betPanel); // adding home button
        showMoney(betPanel); // adding money label
        switchPanel("bet panel"); // opening the bet panel
        betButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    betAmount = Double.parseDouble(betField.getText()); // getting value from the bet field if it is a double
                    if (betAmount <= 0) { // do not allow values of zero or less
                        betField.setText("");
                        System.out.println("cannot bet negative or zero");
                    } else if (betAmount > myMoney.getMoney()) { // do not allow values greater than current balance
                        betField.setText("");
                        System.out.println("you do not have enough money");
                    } else { // else: proceed with the coin flip
                        showMoney(nPane);
                        betField.setText("");
                        if (nPanel.equals("coin panel")) {
                            flip(); // proceeds with the coin flip
                        }
                    }
                } catch (NumberFormatException e) {
                    betField.setText("");
                    System.out.println("error");
                }
            }
        });
    }

    // beginning of coin flip method
    public void flip() {
        switchPanel("coin panel"); // opens the coin panel that contains the heads or tails
        // calling coin
    }

    // method used after either heads or tails is chosen
    public void coinFlip(boolean sideBool) {
        showMoney(resultPanel);
        switchPanel("results panel"); // opens the results panel
        flippingLabel.setText("coin is flipping...."); // adds a short loading screen label
        newPerson();
        if (sideBool) {
            side = "heads";
        } else {
            side = "tails";
        }
        // add a three second timer
        timer.schedule(new TimerTask() {
            public void run() {
                headsOrTails = myMoney.coinFlip(sideBool, betAmount); // flips a coin in another class
                flippingLabel.setText("the coin flipped " + headsOrTails + "!"); // displays what was flipped
                if (headsOrTails.equals(side)) { // if what you chose is correct
                    if (haveMultiplier) { // if you have 1.5x multiplier: extra rewards
                        winningsLabel.setText("you won " + betAmount*1.5 + " money!");
                    } else {
                        winningsLabel.setText("you won " + betAmount + " money!");
                    }
                } else { // else: you lose mnoney
                    winningsLabel.setText("you lost " + betAmount + " money!");
                }
                displayMoney.setText("current balance: " + myMoney.toString());
                home(resultPanel);

            }
        }, 3000);
    }

    public void roulette() {
        showMoney(roulettePrepPanel);
        home(roulettePrepPanel);
        oppBalanceLabel.setText("<html>your opponent: " + opponentName + "<br>age: " + opponentAge
                + "<br>opponent's money: " + opponentBalance); // label for displaying opponent's information
        roulettePrepPanel.add(oppBalanceLabel);
        // roulettePrepPanel.add(confirmRoulette);
        switchPanel("prep roulette panel");
    }

    public void rouletteButtons() {
        // used to set up the buttons in a certain postition
        yButton = 90;
        xButton = 0;
        for (int i = 0; i < 6; i += 1) {
            if (i >= 3) {
                yButton = 300;
            }
            if (i == 3) {
                xButton = 0;
            }
            // each button has a chance to win or lose
            JButton rouletteButton = new JButton("" + (i + 1));
            rouletteButton.setBounds(30 + xButton * 190, yButton, 150, 150);
            roulettePanel.add(rouletteButton);
            rouletteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    // System.out.println("you have a chance from 0 to " + (bullets-1));
                    if (rev.revolver(bullets)) {
                        opponentMove(); // you didn't lose - next round
                    } else {
                        lostRoulette(); // you lost - end screen
                    }
                    roulettePanel.remove(rouletteButton);
                }
            });
            xButton += 1;
        }
    }

    public void opponentMove() {
        // open new panel for opponents move
        switchPanel("opponent panel");
        // wait a few seconds
        timer.schedule(new TimerTask() {
            public void run() {
                // System.out.println("opponent has a chance from 0 to " + (bullets - 1));
                if (rev.revolver(bullets)) { // you did not win this round
                    switchPanel("next round panel");
                    nextRoundPanel.add(confirmRoulette);
                } else { // you win
                    switchPanel("won roulette panel");
                    home(wonRoulettePanel);
                    if (haveMultiplier) {
                        opponentBalance *= 1.5;
                    }
                    wompWomp.setText("<html>you defeated " + opponentName + " in roulette!<br>you won "
                            + opponentBalance + " money!");
                }
                bullets -= 1;
            }
        }, waitTime);
    }

    // what happens after you lose the roulette game
    public void lostRoulette() {
        lives -= 1;
        displayLives.setText("lives left: " + lives);
        lostRouletteLabel.setText("<html>you lost the roulette game!<br>you only have " + lives + " lives left!");
        if (lives == 0) { // if all lives are gone
            switchPanel("game over panel"); // open the game over screen
        } else { // if there are still lives left
            switchPanel("lost roulette panel"); // open the lost game screen - can keep playing
            home(lostRoulettePanel);
        }
    }

    // method used to add the displayMoney label to panels
    public void showMoney(JPanel currPanel) {
        // adds lives and money labels to the current panel
        currPanel.add(displayLives);
        currPanel.add(displayMoney);
    }

    // method used to switch between panels
    public void switchPanel(String panelName) {
        cardLayout.show(cards, panelName); // shows the panel given int he parameters
    }

    // method used to add the home button back to the main menu
    public void home(JPanel currPanel) {
        // adds the home button and reloads the panel
        currPanel.add(homeButton);
        currPanel.revalidate();
        currPanel.repaint();
    }

    // method used to reroll the roulette opponent information - get a new person each time
    public void newPerson() {
        rev.newPerson();
        opponentName = rev.getName(); // reroll name
        opponentBalance = rev.getWealth(); // reroll their wealth
        opponentAge = rev.getAge(); // reroll age
    }

    // method used to check if you have won the game
    public void checkWin() {
        if (myMoney.getMoney() > 1000000) { // checks if you have more than 1,000,000 money
            if (!hasWon) {
                System.out.println("rent button added"); // adds the win button
                hasWon = true; // in case you go back below 1,000,000 money
                panel.add(winButton);
            }
        } else if (hasWon) {
            System.out.println("rent button removed");
            hasWon = false;
            panel.remove(winButton);
        }
    }

    // method used to clear a panel completely
    public void resetPanel(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    // sets up the main parts of the game at the start
    public void setup() {
        // setting cards to cardLayout
        cards.setLayout(cardLayout);
        loreCards.setLayout(cardLayout);
        // setting all panels to null layout
        lore1.setLayout(null);
        lore2.setLayout(null);
        lore3.setLayout(null);
        lore4.setLayout(null);
        lore5.setLayout(null);
        
        panel.setLayout(null);
        coinPanel.setLayout(null);
        betPanel.setLayout(null);
        resultPanel.setLayout(null);
        roulettePanel.setLayout(null);
        roulettePrepPanel.setLayout(null);
        opponentInfoPanel.setLayout(null);
        opponentMovePanel.setLayout(null);
        nextRoundPanel.setLayout(null);
        gameOverPanel.setLayout(null);
        wonRoulettePanel.setLayout(null);
        lostRoulettePanel.setLayout(null);
        laborPanel.setLayout(null);
        storePanel.setLayout(null);
        shopPanel.setLayout(null);
        boughtPanel.setLayout(null);
        winPanel.setLayout(null);
        losePanel.setLayout(null);

        // adding story panels to their card
        loreCards.add(lore1, "lore panel");
        loreCards.add(lore2, "lore2");
        loreCards.add(lore3, "lore3");
        loreCards.add(lore4, "lore4");
        loreCards.add(lore5, "lore5");
        // story card
        cards.add(loreCards, "lore cards");
        // main panel
        cards.add(panel, "main panel");
        // coin panels
        cards.add(coinPanel, "coin panel");
        cards.add(betPanel, "bet panel");
        cards.add(resultPanel, "results panel");
        // roulette panels
        cards.add(roulettePrepPanel, "prep roulette panel");
        cards.add(opponentInfoPanel, "opponent info panel");
        cards.add(roulettePanel, "roulette panel");
        cards.add(opponentMovePanel, "opponent panel");
        cards.add(wonRoulettePanel, "won roulette panel");
        cards.add(lostRoulettePanel, "lost roulette panel");
        cards.add(nextRoundPanel, "next round panel");
        // store panels
        cards.add(storePanel, "store panel");
        cards.add(shopPanel, "shop panel");
        cards.add(boughtPanel, "bought panel");
        cards.add(laborPanel, "labor panel");
        // win and lose screen
        cards.add(winPanel, "win panel");
        cards.add(gameOverPanel, "game over panel");
        // new person for roulette
        newPerson();
    }

    // sets up the life and money display and also the win button
    public void displaySetup() {
        displayMoney.setBounds(25, 25, 300, 50);
        displayLives.setBounds(25, 50, 300, 50);
        winScreen.setBounds(0, 0, 600, 600);
        winButton.setBounds(350, 50, 200, 50);
        winButton.addActionListener(new ActionListener() { // creates win button
            public void actionPerformed(ActionEvent event) {
                switchPanel("win panel");
            }
        });
        winPanel.add(winScreen);
    }

    // sets up the story panels
    public void loreSetup() {
        // lore dimensions
        loreButton.setBounds(150, 450, 300, 50);
        loreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (currLore == loreArr.length - 1) {
                    switchPanel("main panel");
                } else {
                    if (currLore == loreArr.length - 2) {
                        loreButton.setText("start gambling");
                    }
                    cardLayout.next(loreCards);
                    currLore += 1;
                    loreArr[currLore].add(loreButton);
                }
            }
        });
        lore1.add(loreButton);
        for (int i = 0; i < 5; i += 1) {
            loreImgs[i].setBounds(150, 125, 300, 300);
            loreLabels[i].setBounds(150, 50, 300, 50);
            lorePanels[i].add(loreImgs[i]);
            lorePanels[i].add(loreLabels[i]);
        }
    }

// method used to set up the betting panel
    public void betSetup() {
        // bet dimensions
        betLabel.setBounds(150, 130, 300, 50);
        betField.setBounds(150, 200, 300, 50);
        betButton.setBounds(150, 270, 300, 50);
        allInButton.setBounds(150, 330, 300, 50);
        betPanel.add(betLabel);
        betPanel.add(betField);
        betPanel.add(betButton);
        betPanel.add(allInButton);
        allInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (myMoney.getMoney() == 0) {
                    System.out.println("no money :(");
                } else {
                    betAmount = myMoney.getMoney();
                    flip();
                    betField.setText("");
                }
                betAmount = myMoney.getMoney();
                flip();
                betField.setText("");
            }
        });
    }


    // method used to set up the home button
    public void homeSetup() {
        // home button dimnsions
        homeButton.setBounds(350, 50, 200, 50);
        showMoney(panel);
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                checkWin();
                switchPanel("main panel");
                winningsLabel.setText("");
                showMoney(panel);
            }
        });
    }

    // method used to set up the coin flipping panel
    public void flipSetup() {
        // result dimesnsions
        flippingLabel.setBounds(240, 240, 300, 50);
        winningsLabel.setBounds(240, 270, 300, 50);
        resultPanel.add(flippingLabel);
        resultPanel.add(winningsLabel);
    }

    // method used to set up the coin call panel
    public void coinSetup() {
        // coin dimensions
        callCoinLabel.setBounds(250, 50, 300, 50);
        heads.setBounds(10, 100, 280, 450);
        tails.setBounds(310, 100, 280, 450);
        coinPanel.add(heads);
        coinPanel.add(tails);
        coinPanel.add(callCoinLabel);
        heads.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                coinFlip(true);
            }
        });
        tails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // wait a few seconds
                coinFlip(false);
            }
        });
    }

    // method used to set up the roulette panel
    public void rouletteSetup() {
        // roulette dimensions
        lostRouletteLabel.setBounds(200, 150, 200, 50);
        gameOverImg.setBounds(0, 0, 600, 600);
        lostRouletteImg.setBounds(200, 200, 200, 200);
        oppBalanceLabel.setBounds(200, 480, 300, 50);
        opponentLabel.setBounds(200, 240, 300, 50);
        nextRoundLabel.setBounds(150, 150, 300, 50);
        pickLabel.setBounds(200, 50, 300, 50);
        rouletteStartLabel.setBounds(170, 100, 300, 200);
        confirmRoulette.setBounds(190, 300, 150, 150);
        wompWomp.setBounds(150, 400, 300, 50);

        // formatting labels
        nextRoundLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // starts roulette and sets up the buttons; used at start of the game
        startRoulette.setBounds(225, 300, 150, 150);
        startRoulette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                rouletteButtons();
                switchPanel("roulette panel");
            }
        });
        // starts next round of roulette
        confirmRoulette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switchPanel("roulette panel");
            }
        });

        // adding things to the roulette panel
        roulettePrepPanel.add(rouletteStartLabel);
        roulettePrepPanel.add(startRoulette);
        roulettePanel.add(pickLabel);
        opponentMovePanel.add(opponentLabel);
        nextRoundPanel.add(nextRoundLabel);
        roulettePrepPanel.add(oppBalanceLabel);
        gameOverPanel.add(gameOverImg);
        lostRoulettePanel.add(lostRouletteImg);
        lostRoulettePanel.add(lostRouletteLabel);

        // button used then you win roulette
        JButton yayButton = new JButton("i won!!!");
        yayButton.setBounds(225, 200, 150, 150);
        wonRoulettePanel.add(yayButton);
        yayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                myMoney.addAmount(opponentBalance);
                displayMoney.setText("current balance: " + myMoney.toString());
                switchPanel("main panel");
                showMoney(panel);

                newPerson();
                resetPanel(roulettePanel);
                roulettePanel.add(pickLabel);
            }
        });
        wonRoulettePanel.add(wompWomp);
    }


    public void storeSetup() {
        // setting bounds for each object in the store
        storeLabel.setBounds(50, 100, 300, 50);
        itemLabel.setBounds(150, 100, 300, 50);
        priceLabel.setBounds(150, 120, 300, 50);

        itemImg.setBounds(150, 125, 300, 300);
        itemImg1.setBounds(150, 125, 300, 300);
        itemImg2.setBounds(150, 125, 300, 300);
        itemImg3.setBounds(150, 125, 300, 300);

        item1.setBounds(100, 150, 200, 200);
        item2.setBounds(300, 150, 200, 200);
        item3.setBounds(100, 350, 200, 200);
        item4.setBounds(300, 350, 200, 200);
        
        buyItem1.setBounds(150, 400, 300, 50);
        buyItem2.setBounds(150, 400, 300, 50);
        buyItem3.setBounds(150, 400, 300, 50);
        buyItem4.setBounds(150, 400, 300, 50);

        dontBuy.setBounds(150, 450, 300, 50);

        boughtLabel.setBounds(125, 150, 350, 50);

        // formatting text
        storeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        boughtLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // adding items to the store panel
        storePanel.add(item1);
        storePanel.add(item2);
        storePanel.add(item3);
        storePanel.add(item4);
        storePanel.add(storeLabel);
        // adding descriptors to the confirm purchase panel
        shopPanel.add(itemLabel);
        shopPanel.add(priceLabel);
        boughtPanel.add(boughtLabel);
        boughtPanel.add(itemImg);

        item1.addActionListener(new ActionListener() { // first item: reward multiplier
            public void actionPerformed(ActionEvent event) {
                buyItem("a");
            }
        });
        item2.addActionListener(new ActionListener() { // second item: extra life
            public void actionPerformed(ActionEvent event) {
                buyItem("b");
            }
        });
        item3.addActionListener(new ActionListener() { // third item: raise
            public void actionPerformed(ActionEvent event) {
                buyItem("c");
            }
        });
        item4.addActionListener(new ActionListener() { // fourth item: mystery
            public void actionPerformed(ActionEvent event) {
                buyItem("d");
            }
        });
        buyItem1.addActionListener(new ActionListener() { // first button: reward multiplier
            public void actionPerformed(ActionEvent event) {
                if (myMoney.getMoney() < 250000) {
                    System.out.println("not enough money!");
                } else if (haveMultiplier) {
                    System.out.println("already bought a multiplier!");
                } else {
                    haveMultiplier = true;
                    myMoney.setMultiplier(1.5);
                    myMoney.loseAmount(250000);
                    displayMoney.setText("current balance " + myMoney.toString());
                    boughtItem("reward multipler", "250,000", img1);
                }
            }
        });
        buyItem2.addActionListener(new ActionListener() { // second button: extra life
            public void actionPerformed(ActionEvent event) {
                if (myMoney.getMoney() < 50000) {
                    System.out.println("not enough money!");
                } else {
                    lives += 1;
                    myMoney.loseAmount(50000);
                    displayMoney.setText("current balance " + myMoney.toString());
                    displayLives.setText("lives left: " + lives);
                    boughtItem("extra life", "50,000", img2);
                }
            }
        });
        buyItem3.addActionListener(new ActionListener() { // third button: raise
            public void actionPerformed(ActionEvent event) {
                if (myMoney.getMoney() < 100000) {
                    System.out.println("not enough money!");
                } else {
                    numRaise += 1;
                    myMoney.loseAmount(100000);
                    displayMoney.setText("current balance " + myMoney.toString());
                    boughtItem("raise", "100,000", img3);
                }
            }
        });
        buyItem4.addActionListener(new ActionListener() { // fourth button: literally just takes your money
            public void actionPerformed(ActionEvent event) {
                if (myMoney.getMoney() < 10000000) {
                    System.out.println("not enough money!");
                } else {
                    myMoney.loseAmount(10000000);
                }
            }
        });
        dontBuy.addActionListener(new ActionListener() { // cancel purcahse
            public void actionPerformed(ActionEvent event) {
                switchPanel("store panel");
                showMoney(storePanel);
            }
        });
    }
    
    public void buyItem(String item) {
        resetPanel(shopPanel); // resetting the shop
        shopPanel.add(itemLabel);
        shopPanel.add(priceLabel);
        shopPanel.add(dontBuy);
        showMoney(shopPanel);
        switchPanel("shop panel");
        if (item == "a") {
            priceLabel.setText("price: 250,000 money");
            itemLabel.setText("get a 1.5x multiplier in all rewards you gain");
            shopPanel.add(itemImg1);
            shopPanel.add(buyItem1);
        } else if (item == "b") {
            priceLabel.setText("price: 50,000 money");
            itemLabel.setText("receive an extra life for roulette games");
            shopPanel.add(itemImg2);
            shopPanel.add(buyItem2);
        } else if (item == "c") {
            priceLabel.setText("price: 100,000 money");
            itemLabel.setText("get a raise of 200 money in your job");
            shopPanel.add(itemImg3);
            shopPanel.add(buyItem3);
        } else { // else if (item == "d")
            priceLabel.setText("price: 10,000,000 money");
            itemLabel.setText("???");
            shopPanel.add(buyItem4);
        }
    }

    public void boughtItem(String item, String price, ImageIcon img) {
        boughtLabel.setText(item + " has been bought for " + price + " money!");
        // boughtPanel.add(img);
        itemImg.setIcon(img);
        showMoney(boughtPanel);
        home(boughtPanel);
        switchPanel("bought panel");
    }

    public void laborSetup() {
        // manual labor
        JButton laborButton = new JButton("work at the office");
        laborButton.setBounds(150, 150, 300, 300);
        laborPanel.add(laborButton);
        laborButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                myMoney.addAmount(rand.nextInt(100, 200) + (200*numRaise));
                displayMoney.setText("current balance " + myMoney.toString());
                // home(laborPanel);
            }
        });
    }

    public static void main(String[] args) {
        new Game();
    }
}
