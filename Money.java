import java.util.Random;
public class Money {
    private double money;
    private Random rand = new Random();
    int rng;
    double multiplier = 1; // determines extra money you can get

    public Money() {
        money = 1000; // initial starting cash
    }

    public Money(int amount) { // adds given amount to your money
        money = amount;
    }

    public void setMultiplier(double n) { // reward multiplier in the shop
        multiplier = n;
    }
    
    public String coinFlip(boolean call, double bet) { // coin flip method used in the coin flip. changes money depending on the call and returns the coin flip result
        rng = rand.nextInt(2); // gets random number betwen 0 and 1
        System.out.println(rng);
        if (rng == 0) {
            money -= bet;
            if (call) {
                return "tails";
            }
            return "heads";
        } else {
            money += bet * multiplier;
            if (call) {
                return "heads";
            }
            return "tails";
        }
    }

    public void addAmount(int amount) { // used to add amount to money
        money += amount*multiplier;
    }

    public void loseAmount(int amount) { // used to subtract amount from money
        money -= amount;
    }

    public double getMoney() { // returns money 
        return money;
    }

    public String toString() { // returns money as a string
        return Double.toString(money);
    }

    
}
