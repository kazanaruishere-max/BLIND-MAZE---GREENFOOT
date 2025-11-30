import greenfoot.*;

/**
 * Goal - Titik tujuan akhir
 */
public class Goal extends Actor
{
    private int animationCount = 0;
    
    public Goal()
    {
        updateImage();
    }
    
    public void act()
    {
        animationCount++;
        if (animationCount % 5 == 0) {
            updateImage();
        }
    }
    
    private void updateImage()
    {
        GreenfootImage img = new GreenfootImage(28, 28);
        
        // Animated glowing effect
        int pulse = (int)(Math.sin(animationCount * 0.1) * 20 + 235);
        
        // Outer glow
        img.setColor(new Color(255, 215, 0, 100));
        img.fillOval(0, 0, 28, 28);
        
        // Main star
        img.setColor(new Color(255, 215, 0, pulse));
        img.fillOval(4, 4, 20, 20);
        
        // Inner bright core
        img.setColor(new Color(255, 255, 200));
        img.fillOval(9, 9, 10, 10);
        
        setImage(img);
    }
}