package btd.model;

/**
 * This class represents a player in the game.
 * It holds information about player's health, score, and coins.
 */
public class Player {
    private int health = 15;
    private int coins = 200;
    private int score;

    /**
     * Standard constructor for Player, sets initial score at 0.
     */
    public Player() {
        this.score = 0;
    }

    /**
     * Gets the current health of the player.
     *
     * @return player's health.
     */
    public int getHealth() {
        return this.health;
    }

     /**
     * Sets the health of the player.
     *
     * @param health new health value.
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Returns the current score of the player.
     *
     * @return player's score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the score of the player.
     *
     * @param score new score value.
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * Gets current number of coins the player has.
     *
     * @return player's coins.
     */
    public int getCoins() {
        return this.coins;
    }

    /**
     * Sets the number of coins the player has.
     *
     * @param coins new number of coins.
     */
    public void setCoins(final int coins) {
        this.coins = coins;
    }

    /**
     * Reduces player's health by a specified amount.
     *
     * @param damage amount of damage to subtract from the player's health.
     */
    public void loseHealth(final int damage) {
        // Il giocatore perde salute
        this.health -= damage;
    }

    /**
     * Increases the player's score by a specified number of points.
     *
     * @param points The number of points to add to the player's score.
     */
    public void gainScore(final int points) {
        // Il giocatore guadagna punti
        this.score += points;
    }

    /**
     * Increases the number of coins the player has by a specified amount.
     *
     * @param coins The number of coins to add to the player's total.
     */
    public void gainCoins(final int coins) {
        // Il giocatore guadagna coin
        this.coins += coins;
    }
}
