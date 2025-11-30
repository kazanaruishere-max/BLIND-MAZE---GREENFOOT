import greenfoot.*;

/**
 * MyWorld - Dunia untuk Blind Maze Game
 * Player harus melewati labirin dengan pandangan terbatas (fog of war)
 */
public class MyWorld extends World
{
    private Player player;
    private int[][] maze;
    private int cellSize = 30;
    private int mazeWidth = 21;
    private int mazeHeight = 21;
    private boolean gameWon = false;
    private int moves = 0;
    private long startTime;
    private FogOverlay fogOverlay;
    private boolean showingPreview = true;
    private int previewTimer = 0;
    private int previewDuration = 180; // 3 detik (60 fps * 3)
    
    public MyWorld()
    {    
        super(21, 21, 30);
        startTime = System.currentTimeMillis();
        prepare();
    }
    
    private void prepare()
    {
        // Generate maze
        generateMaze();
        
        // Create walls based on maze array
        createWalls();
        
        // Add player at start position
        player = new Player(this);
        addObject(player, 1, 1);
        
        // Add goal at end position
        Goal goal = new Goal();
        addObject(goal, mazeWidth - 2, mazeHeight - 2);
        
        // Add START indicator
        StartMarker startMarker = new StartMarker();
        addObject(startMarker, 1, 1);
        
        // Add FINISH indicator  
        FinishMarker finishMarker = new FinishMarker();
        addObject(finishMarker, mazeWidth - 2, mazeHeight - 2);
        
        // Add fog overlay (harus terakhir agar di atas semua)
        // TAPI jangan aktifkan dulu, biarkan preview
        fogOverlay = new FogOverlay(this);
        fogOverlay.setTransparency(0); // Start invisible
        addObject(fogOverlay, mazeWidth / 2, mazeHeight / 2);
        
        // Show instructions
        showInstructions();
    }
    
    private void generateMaze()
    {
        maze = new int[mazeHeight][mazeWidth];
        
        // Initialize maze with walls
        for (int y = 0; y < mazeHeight; y++) {
            for (int x = 0; x < mazeWidth; x++) {
                maze[y][x] = 1; // 1 = wall
            }
        }
        
        // Generate path using recursive backtracking
        carvePath(1, 1);
        
        // Ensure start and end are clear
        maze[1][1] = 0;
        maze[mazeHeight - 2][mazeWidth - 2] = 0;
    }
    
    private void carvePath(int x, int y)
    {
        maze[y][x] = 0; // 0 = path
        
        // Directions: up, right, down, left
        int[][] directions = {{0, -2}, {2, 0}, {0, 2}, {-2, 0}};
        
        // Shuffle directions
        shuffleArray(directions);
        
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            
            if (isValid(newX, newY) && maze[newY][newX] == 1) {
                // Carve wall between current and new cell
                maze[y + dir[1] / 2][x + dir[0] / 2] = 0;
                carvePath(newX, newY);
            }
        }
    }
    
    private void shuffleArray(int[][] array)
    {
        for (int i = array.length - 1; i > 0; i--) {
            int j = Greenfoot.getRandomNumber(i + 1);
            int[] temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    
    private boolean isValid(int x, int y)
    {
        return x > 0 && x < mazeWidth - 1 && y > 0 && y < mazeHeight - 1;
    }
    
    private void createWalls()
    {
        for (int y = 0; y < mazeHeight; y++) {
            for (int x = 0; x < mazeWidth; x++) {
                if (maze[y][x] == 1) {
                    Wall wall = new Wall();
                    addObject(wall, x, y);
                }
            }
        }
    }
    
    public void act()
    {
        if (showingPreview) {
            previewTimer++;
            
            // Countdown display
            int secondsLeft = (previewDuration - previewTimer) / 60 + 1;
            if (secondsLeft > 0) {
                showText("Fog appears in " + secondsLeft + " seconds...", 10, 11);
            }
            
            if (previewTimer >= previewDuration) {
                showingPreview = false;
                fogOverlay.setTransparency(220); // Activate fog
                showText("", 10, 10); // Clear preview text
                showText("", 10, 11);
                
                // Remove markers
                removeObjects(getObjects(StartMarker.class));
                removeObjects(getObjects(FinishMarker.class));
            }
            return;
        }
        
        if (!gameWon) {
            updateStats();
        }
    }
    
    private void showInstructions()
    {
        showText("BLIND MAZE", 10, 1);
        showText("Moves: 0", 3, 20);
        showText("Time: 0s", 18, 20);
        
        // Preview message
        showText("MEMORIZE THE MAZE!", 10, 10);
        showText("Fog appears in 3 seconds...", 10, 11);
    }
    
    private void updateStats()
    {
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        showText("Moves: " + moves, 3, 20);
        showText("Time: " + elapsed + "s", 18, 20);
    }
    
    public void incrementMoves()
    {
        moves++;
    }
    
    public void winGame()
    {
        if (!gameWon) {
            gameWon = true;
            long elapsed = (System.currentTimeMillis() - startTime) / 1000;
            
            // Remove fog to show full maze
            if (fogOverlay != null) {
                removeObject(fogOverlay);
            }
            
            showText("YOU WIN!", 10, 8);
            showText("Moves: " + moves, 10, 10);
            showText("Time: " + elapsed + "s", 10, 11);
            showText("Press R to Restart", 10, 13);
            
            try {
                Greenfoot.playSound("win.wav");
            } catch (Exception e) {
                // No sound file
            }
        }
    }
    
    public void checkRestart()
    {
        if (Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
    
    public boolean isGameWon()
    {
        return gameWon;
    }
    
    public boolean isShowingPreview()
    {
        return showingPreview;
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    public boolean isWall(int x, int y)
    {
        if (x < 0 || x >= mazeWidth || y < 0 || y >= mazeHeight) {
            return true;
        }
        return maze[y][x] == 1;
    }
}