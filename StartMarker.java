import greenfoot.*;
import java.awt.Font;

/**
 * StartMarker - Indikator START (hilang setelah preview)
 */
public class StartMarker extends Actor
{
    private int animationCount = 0;
    
    public StartMarker()
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
        img.setColor(new Color(0, 255, 0, pulse));
        img.fillRect(5, 8, 70, 24);
        
        // Border
        img.setColor(new Color(0, 200, 0));
        img.drawRect(5, 8, 70, 24);
        img.drawRect(6, 9, 68, 22);
        
        // Text
        img.setColor(Color.WHITE);
        Font font = new Font("SansSerif", Font.BOLD, 18);
        img.drawString("START", 18, 28);
        
        setImage(img);
    }
}