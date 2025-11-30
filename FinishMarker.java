import greenfoot.*;
import java.awt.Font;

/**
 * FinishMarker - Indikator FINISH (hilang setelah preview)
 */
public class FinishMarker extends Actor
{
    private int animationCount = 0;
    
    public FinishMarker()
    {
        updateImage();
    }
    
    public void act()
    {
        animationCount++;
        if (animationCount % 10 == 0) {
            updateImage();
        }
    }
    
    private void updateImage()
    {
        GreenfootImage img = new GreenfootImage(80, 40);
        
        // Pulsing effect
        int pulse = (int)(Math.sin(animationCount * 0.15) * 20 + 235);
        
        // Background
        img.setColor(new Color(255, 215, 0, pulse));
        img.fillRect(5, 8, 70, 24);
        
        // Border
        img.setColor(new Color(255, 165, 0));
        img.drawRect(5, 8, 70, 24);
        img.drawRect(6, 9, 68, 22);
        
        // Text
        img.setColor(Color.BLACK);
        Font font = new Font("SansSerif", Font.BOLD, 16);
        img.drawString("FINISH", 15, 28);
        
        setImage(img);
    }
}