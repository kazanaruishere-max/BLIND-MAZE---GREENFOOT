import greenfoot.*;

/**
 * Wall - Dinding labirin
 */
public class Wall extends Actor
{
    public Wall()
    {
        createImage();
    }
    
    private void createImage()
    {
        GreenfootImage img = new GreenfootImage(30, 30);
        
        // Dark stone wall
        img.setColor(new Color(60, 60, 80));
        img.fill();
        
        // Border for depth effect
        img.setColor(new Color(40, 40, 60));
        img.drawRect(0, 0, 29, 29);
        img.drawRect(1, 1, 27, 27);
        
        // Highlight
        img.setColor(new Color(80, 80, 100));
        img.drawLine(2, 2, 27, 2);
        img.drawLine(2, 2, 2, 27);
        
        setImage(img);
    }
}