package btd.model;

public class Player {
    private int health;
    private int score;
    private int coins;
    private int rank;

    public Player() {
        // Inizializza attributi del giocatore
        this.health = 100; // valore iniziale health?
        this.score = 0;
        this.coins = 0;
        this.rank = 1; // valore iniziale per il rank
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCoins() {
        return this.coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    // gestione salute, guadagni punti, coins

    public void loseHealth(int damage) {
        // Il giocatore perde salute
        this.health -= damage;
    }

    public void gainScore(int points) {
        // Il giocatore guadagna punti
        this.score += points;
    }

    public void gainCoins(int coins) {
        // Il giocatore guadagna coin
        this.coins += coins;
    }

    public void levelUp() {
        // Il giocatore sale di livello
        this.rank++;
    }
}
