<div align="center">
  <img src="https://www.greenfoot.org/images/greenfoot-logo.png" alt="Greenfoot Logo" height="100">
  <h1>🕵️ Blind Maze</h1>
  <p>
    <b>Memorize the path. Survive the fog. Find the exit.</b>
  </p>
  <p>
    Created by <b>Kazanaru</b><br>
    Inspired by <i>Game Labirin Buta</i>
  </p>
  <br>
</div>

**Blind Maze** is a challenging puzzle-exploration game built with [Greenfoot](https://www.greenfoot.org/). It tests your memory and spatial awareness by plunging you into a procedurally generated labyrinth where vision is your most scarce resource.

---

## 🎮 Game Mechanics

### 1. The Memorization Phase 🧠
At the start of each level, you are granted a **3-second preview** of the entire maze.
*   **Goal:** Memorize the path from the **Start** (Top-Left) to the **Finish** (Bottom-Right).
*   **Strategy:** Identify dead ends and the critical path before the fog rolls in.

### 2. The Fog of War 🌫️
Once the timer hits zero, the lights go out.
*   **Vision:** You can only see a small radius around your character.
*   **Challenge:** Navigate the maze using only your memory and limited sight.
*   **Dynamic Lighting:** The fog smoothly fades at the edges of your vision, creating an immersive atmosphere.

### 3. Procedural Generation 🎲
No two runs are the same.
*   The maze is generated using a **Recursive Backtracking** algorithm.
*   Every restart creates a completely new layout, ensuring infinite replayability.

---

## 🕹️ Controls

| Key | Action |
| :--- | :--- |
| **W** / **Up Arrow** | Move Up |
| **S** / **Down Arrow** | Move Down |
| **A** / **Left Arrow** | Move Left |
| **D** / **Right Arrow** | Move Right |
| **R** | Restart Game (After Win) |

---

## 🚀 How to Play

1.  **Launch the Game:** Open `project.greenfoot` in the Greenfoot environment.
2.  **Start:** Press the **Run** button in Greenfoot.
3.  **Observe:** Quickly study the maze layout during the countdown.
4.  **Navigate:** Guide the **Blue Player** to the **Green Goal**.
5.  **Win:** Reach the goal to see your stats (Time & Moves).

---

## 🛠️ Technical Details

*   **Engine:** Greenfoot (Java)
*   **Classes:**
    *   `MyWorld`: Manages game state, maze generation, and UI.
    *   `Player`: Handles movement logic and collision detection.
    *   `FogOverlay`: Implements the dynamic lighting and vision system.
    *   `Wall` / `Goal`: Static maze elements.

---

## 👨‍💻 Author

Created by **Kazanaru**.

---
*Enjoy the maze!*
