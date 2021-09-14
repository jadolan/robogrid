package com.example.robogrid.model;

public class Robot {

    private int x;

    private int y;

    private int direction;

    public Integer getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setPositionAndDirection(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnRight() {

        this.direction = (this.direction + 1) % 4;

    }

    public void turnAround() {

        this.direction = (this.direction + 2) % 4;

    }

    public void walk(int steps) {

        int x = this.getX();
        int y = this.getY();

        switch(direction) {
            case 0:
                this.setY(applyGridLimit(y - steps));
                break;
            case 1:
                this.setX(applyGridLimit(x + steps));
                break;
            case 2:
                this.setY(applyGridLimit(y + steps));
                break;
            case 3:
                this.setX(applyGridLimit(x - steps));
                break;
        }
    }

    private int applyGridLimit(Integer calculatedValue) {
        return Math.abs(calculatedValue) % 5;
    }
}
