package btd.model.entity;

import btd.utils.Position;
import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ShootingTower implements Tower {

    private final static Integer SELL_PRICE_FACTOR = 100;
    private String name;

    private Integer power;

    private Integer price;

    private Position position;

    private BufferedImage towerImage;

    //ENUM
    private Pair<Integer,Integer> hittingRange;

    public ShootingTower(String name,Integer power,Integer price, Position position){
        super();
        this.name = name;
        this.power = power;
        this.price = price;
        this.position = position;
        try {
            this.towerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/towers/tower1/Upgrade0/tower0")));
        } catch (IOException e) {
            //To be checked
            System.out.println(e);
        }
        //Hitting Range is a rectangle
    }

    //IF PLAYER HAS ENOUGH MONEY (playerMoney >= towerPrice) RETURN TRUE, ELSE FALSE
    @Override
    public boolean upgradable(Integer playerMoney) {
        return playerMoney - this.price >= 0;
    }

    //CHANGE APPEARANCE, CHANGE PRICE, CHANGE HIT RANGE, (OPTIONAL) CHANGE NAME
    @Override
    public void upgrade() {
        changeAppearance();
    }

    private void changeAppearance() {
        try{
            this.towerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/towers/tower1/Upgrade1/tower1")));
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @Override
    public Integer sell() {
        //When player sells a tower the price is lower than the initial price
        return getPrice() - SELL_PRICE_FACTOR;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public Optional<Position> getPosition() {
        return Optional.empty();
    }

    @Override
    public String getName() {
        return this.name;
    }


    //TO BE CHECKED
    @Override
    public void setPosition(double x, double y) {
        Position newPosition = new Position(x,y);
        this.position = new Position(x,y);
    }
}
