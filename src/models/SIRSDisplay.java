package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import models.SIRSModel;

public class SIRSDisplay {
    public SIRSModel model;
    public GraphicsContext g;
    public Color
        nonInfected = Color.LIGHTGREEN,
        infected = Color.RED,
        recovered = Color.BLUE,
        infectedDeath = Color.BLACK,
        nonInfectedDeath = Color.DARKGREEN,
        recoveredDeath = Color.CYAN;
    public Color current = Color.BLACK;
    public void setModel(SIRSModel model) {
        this.model = model;
    }

    private synchronized void draw(int i, int j, Color col) {
        if(g == null) return;
        g.setFill(col);
        g.fillRect(i, j, 1, 1);
    }

    public void draw(int i, int j) {
        g.fillRect(i, j, 1, 1);
    }

    public void show(int i, int j) {
        if(model == null) return;
        SIRSModel.State person = model.grid[i][j];
        if(person == null) return;
        if(model.grid[i][j] == model.buffer[i][j]) return;
        switch(person) {
            case INFECTED:
                draw(i, j, infected);
                break;
            case NON_INFECTED:
                draw(i, j, nonInfected);
                break;
            case RECOVERED:
                draw(i, j, recovered);
                break;
        }
    }

    public void setCanvas(GraphicsContext gr) {
        g = gr;
    }

    private boolean running = true;
    public void stop() {
        running = false;
    }
}
