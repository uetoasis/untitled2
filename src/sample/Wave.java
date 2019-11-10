package sample;

import java.util.ArrayList;

public class Wave {

    private Enemy enemy;
    private ArrayList<Enemy> enemyList;

    public Wave(float x,  Enemy enemy){
        this.enemy = enemy;
        enemyList = new ArrayList<Enemy>();
    }

    public void Update(){
    }
}
