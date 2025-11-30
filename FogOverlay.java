import greenfoot.*;

/**
 * FogOverlay - Fog of War effect
 * Membuat pemain hanya bisa melihat area sekitar mereka
 */
public class FogOverlay extends Actor
{
    private MyWorld world;
    private int visionRadius = 3; // Jarak pandang pemain
    private int fogAlpha = 0; // Start transparent
    
    public FogOverlay(MyWorld w)
    {
        world = w;
        createFogImage();
    }
    
    public void act()
    {
        if (world.isShowingPreview()) {
            return; // Don't update fog during preview
        }
        updateFog();
    }
    
    public void setTransparency(int alpha)
    {
        fogAlpha = alpha;
        updateFog();
    }
    
    private void createFogImage()
    {
        // Create large image to cover entire world
        GreenfootImage fog = new GreenfootImage(world.getWidth() * 30, world.getHeight() * 30);
        fog.setColor(new Color(0, 0, 0, 220)); // Almost black fog
        fog.fill();
        setImage(fog);
    }
    
    private void updateFog()
    {
        Player player = world.getPlayer();
        if (player == null) return;
        
        // Recreate fog
        GreenfootImage fog = new GreenfootImage(world.getWidth() * 30, world.getHeight() * 30);
        fog.setColor(new Color(0, 0, 0, fogAlpha));
        fog.fill();
        
        // Only create visibility if fog is active
        if (fogAlpha > 0) {
            // Clear area around player with gradient
            int playerX = player.getX();
            int playerY = player.getY();
            
            // Create visibility circle with gradient
            for (int radius = visionRadius; radius >= 0; radius--) {
                int alpha = fogAlpha - (int)((float)(visionRadius - radius) / visionRadius * fogAlpha);
                fog.setColor(new Color(0, 0, 0, alpha));
                
                int pixelRadius = radius * 30;
                int centerX = playerX * 30;
                int centerY = playerY * 30;
                
                fog.fillOval(
                    centerX - pixelRadius,
                    centerY - pixelRadius,
                    pixelRadius * 2,
                    pixelRadius * 2
                );
            }
            
            // Complete transparency at center
            fog.setColor(new Color(0, 0, 0, 0));
            int centerSize = 25;
            fog.fillOval(
                playerX * 30 - centerSize / 2,
                playerY * 30 - centerSize / 2,
                centerSize,
                centerSize
            );
        }
        
        setImage(fog);
    }
}