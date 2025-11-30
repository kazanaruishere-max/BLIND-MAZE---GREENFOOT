import greenfoot.*;

/**
 * Player - Karakter yang dikontrol pemain
 * Bergerak dengan WASD atau Arrow Keys
 */
public class Player extends Actor
{
    private MyWorld world;
    private int moveDelay = 8;
    private int moveTimer = 0;
    private String lastDirection = "";
    
    public Player(MyWorld w)
    {
        world = w;
        createImage();
    }
    
    private void createImage()
    {
        GreenfootImage img = new GreenfootImage(25, 25);
        img.setColor(new Color(0, 150, 255));
        img.fillOval(0, 0, 25, 25);
        
        // Add eyes
        img.setColor(Color.WHITE);
        img.fillOval(7, 8, 5, 5);
        img.fillOval(13, 8, 5, 5);
        
        img.setColor(Color.BLACK);
        img.fillOval(9, 10, 2, 2);
        img.fillOval(15, 10, 2, 2);
        
        setImage(img);
    }
    
    public void act()
    {
        if (world.isShowingPreview()) {
            return; // Can't move during preview
        }
        
        if (world.isGameWon()) {
            world.checkRestart();
            return;
        }
        
        moveTimer++;
        if (moveTimer >= moveDelay) {
            checkKeys();
            checkGoal();
        }
    }
    
    private void checkKeys()
    {
        boolean moved = false;
        String direction = "";
        
        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) {
            if (canMove(0, -1)) {
                setLocation(getX(), getY() - 1);
                moved = true;
                direction = "up";
            }
        }
        else if (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) {
            if (canMove(0, 1)) {
                setLocation(getX(), getY() + 1);
                moved = true;
                direction = "down";
            }
        }
        else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            if (canMove(-1, 0)) {
                setLocation(getX() - 1, getY());
                moved = true;
                direction = "left";
            }
        }
        else if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            if (canMove(1, 0)) {
                setLocation(getX() + 1, getY());
                moved = true;
                direction = "right";
            }
        }
        
        if (moved) {
            moveTimer = 0;
            world.incrementMoves();
            lastDirection = direction;
            
            try {
                Greenfoot.playSound("move.wav");
            } catch (Exception e) {
                // No sound
            }
        }
    }
    
    private boolean canMove(int dx, int dy)
    {
        int newX = getX() + dx;
        int newY = getY() + dy;
        
        return !world.isWall(newX, newY);
    }
    
    private void checkGoal()
    {
        Goal goal = (Goal) getOneIntersectingObject(Goal.class);
        if (goal != null) {
            world.winGame();
        }
    }
    
    public String getLastDirection()
    {
        return lastDirection;
    }
}