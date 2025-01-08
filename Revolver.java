import java.util.Random;

public class Revolver {
    Random rand = new Random();
    String[] firstNames = { // list of random names for opponent
            "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda",
            "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica",
            "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Margaret",
            "Matthew", "Lisa", "Anthony", "Betty", "Mark", "Dorothy", "Donald", "Sandra",
            "Steven", "Ashley", "Paul", "Kimberly", "Andrew", "Donna", "Joshua", "Emily",
            "Kenneth", "Michelle", "George", "Carol", "Kevin", "Amanda", "Brian", "Melissa",
            "Edward", "Deborah", "Ronald", "Stephanie", "Timothy", "Rebecca", "Jason", "Laura",
            "Jeffrey", "Sharon", "Ryan", "Cynthia", "Jacob", "Kathleen", "Gary", "Amy",
            "Nicholas", "Shirley", "Eric", "Angela", "Stephen", "Helen", "Jonathan", "Anna",
            "Larry", "Brenda", "Justin", "Pamela", "Scott", "Nicole", "Brandon", "Samantha",
            "Frank", "Katherine", "Benjamin", "Christine", "Gregory", "Debra", "Samuel", "Rachel",
            "Raymond", "Catherine", "Patrick", "Carolyn", "Alexander", "Janet", "Jack", "Ruth",
            "Dennis", "Maria", "Jerry", "Heather"
    };
    String[] lastNames = { // list of random names for opponent
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
            "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas",
            "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White",
            "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson", "Walker", "Young",
            "Allen", "King", "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores",
            "Green", "Adams", "Nelson", "Baker", "Hall", "Rivera", "Campbell", "Mitchell",
            "Carter", "Roberts", "Gomez", "Phillips", "Evans", "Turner", "Diaz", "Parker",
            "Cruz", "Edwards", "Collins", "Reyes", "Stewart", "Morris", "Morales", "Murphy",
            "Cook", "Rogers", "Gutierrez", "Ortiz", "Morgan", "Cooper", "Peterson", "Bailey",
            "Reed", "Kelly", "Howard", "Ramos", "Kim", "Cox", "Ward", "Richardson",
            "Watson", "Brooks", "Chavez", "Wood", "James", "Bennett", "Gray", "Mendoza",
            "Ruiz", "Hughes", "Price", "Alvarez", "Castillo", "Sanders", "Patel", "Myers",
            "Long", "Ross", "Foster", "Jimenez"
    };

    int rng;
    String name;
    int age;
    int wealth;

    public Revolver() {
    };

    // 1 in n chance to return false, meant to simulate a version of russian
    // roulette
    public boolean revolver(int n) {
        rng = rand.nextInt(n);
        if (rng == 0) {
            return false; // dead
        }
        return true; // alive
    }

    // reroll the person
    public void newPerson() {
        name = firstNames[rand.nextInt(100)] + " " + lastNames[rand.nextInt(100)];
        age = rand.nextInt(13, 99);
        wealth = rand.nextInt(100, 200000);
    }

    // returns name of opponent
    public String getName() {
        return name;
    }

    // returns age of person
    public int getAge() {
        return age;
    }

    // returns money of person
    public int getWealth() {
        return wealth;
    }
}
