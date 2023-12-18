/**
 * Sketch represents a game where snowflakes are falling.
 * Player can use w,a,s,d to move.
 * Player loses a life if snowflake collides with player.
 * Up and down arrow increase and decrease the speed of snowflakes respectively.
 */

import processing.core.PApplet;

public class Sketch extends PApplet {
  // Movement variables
  boolean wPressed = false;
  boolean sPressed = false;
  boolean dPressed = false;
  boolean aPressed = false;

  // Player variables
  int intPlayerX = 300;
  int intPlayerY = 400;

  // Snow variables
  float[] fltSnowX = new float[25];
  float[] fltSnowY = new float[25];
  boolean[] ballHideStatus = new boolean[25];

  // Lives
  int intLives = 3;
  boolean collision = false;

  // Snow speed variable
  float snowSpeed = 3;

  // Sive of screen
  public void settings() {
    size(1000, 1000);
  }

  /**
   * Presents the game.
   */
  public void setup() {
    background(210, 255, 173);

    for (int i = 0; i < fltSnowY.length; i++) {
      fltSnowY[i] = random(height);
      fltSnowX[i] = random(width);
      ballHideStatus[i] = false;
    }
  }

  /**
   * Allows the games function handling movement and collision
   */
  public void draw() {
    background(210, 255, 173);

    stroke(255);
    fill(255);

    for (int i = 0; i < fltSnowY.length; i++) {
      if (!ballHideStatus[i]) {
        ellipse(fltSnowX[i], fltSnowY[i], 25, 25);
        fltSnowY[i] += snowSpeed; // Use the snowSpeed variable

        if (fltSnowY[i] > height) {
          fltSnowY[i] = 0;
          fltSnowX[i] = random(width);
        }

        // Check for collision
        if (dist(intPlayerX, intPlayerY, fltSnowX[i], fltSnowY[i]) < 30 && !collision) {
          // Lives lost from collision
          intLives--;
          ballHideStatus[i] = true;
        }
      }
    }

    fill(130, 195, 250);
    ellipse(intPlayerX, intPlayerY, 30, 30);

    // Squares to represent lives
    fill(255, 0, 0);
    for (int i = 0; i < intLives; i++) {
      rect(10 + i * 30, 10, 20, 20);
    }

    if (intLives <= 0) {
      gameOver();
    }

    if (wPressed && intPlayerY >= 0) {
      intPlayerY -= 3;
    }
    if (sPressed && intPlayerY <= height - 30) {
      intPlayerY += 3;
    }
    if (aPressed && intPlayerX >= 0) {
      intPlayerX -= 3;
    }
    if (dPressed && intPlayerX <= width - 30) {
      intPlayerX += 3;
    }
  }
  /**
   * Keyboard inputs that are recorded executed when keyboard is pressed.
   */
  public void keyPressed() {
    if (key == 'W' || key == 'w') {
      wPressed = true;
    } else if (key == 'A' || key == 'a') {
      aPressed = true;
    } else if (key == 'S' || key == 's') {
      sPressed = true;
    } else if (key == 'D' || key == 'd') {
      dPressed = true;
    } else if (keyCode == UP) {
      snowSpeed = 1;
    } else if (keyCode == DOWN) {
      snowSpeed = 5; 
    }
  }
  /**
   * Keyboard inputs for when the key is released.
   */
  public void keyReleased() {
    if (key == 'W' || key == 'w') {
      wPressed = false;
    } else if (key == 'S' || key == 's') {
      sPressed = false;
    } else if (key == 'D' || key == 'd') {
      dPressed = false;
    } else if (key == 'A' || key == 'a') {
      aPressed = false;
    }
  }
  /**
   * Check is mouse pressed to remove snowflakes when clicked.
   */
  public void mousePressed() {
    // Check for mouse on snowflake.
    for (int i = 0; i < fltSnowY.length; i++) {
      if (!ballHideStatus[i] && dist(mouseX, mouseY, fltSnowX[i], fltSnowY[i]) <= 30) {
        ballHideStatus[i] = true;
      }
    }
  }
  //Displays white screen indicating game over.
  private void gameOver() {
    background(255);
  }
}