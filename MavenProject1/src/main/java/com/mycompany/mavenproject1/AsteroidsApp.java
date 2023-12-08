/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author personal
 */

import javafx.scene.layout.Pane;
import javafx.application.Application;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.Random;
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.text.Text;
/**
 *
 * @author personal
 */
public class AsteroidsApp extends Application {

    public static int WIDTH = 300;
    public static int HEIGHT = 200;
    public static Pane pane = new Pane();
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    @Override
    public void start(Stage window){
        //HashMap to record keys
        HashMap<KeyCode,Boolean> pressedKeys = new HashMap<>();
        
        //List of projectiles
        List<Projectile> projectiles = new ArrayList<>();
        
        //Setting up the pane
        pane.setPrefSize(WIDTH, HEIGHT);
        
        //Ship
        Ship ship = new Ship(WIDTH/2,HEIGHT/2);
        
        //Asteroid 
        ArrayList<Asteroid> asts = new ArrayList<>();
        for(int i=0;i<5;i++){
            Random rnd = new Random();
            Asteroid asteroid = new Asteroid(rnd.nextInt(WIDTH/3),rnd.nextInt(HEIGHT));
            asts.add(asteroid);
        }
        
        //Points
        Text text = new Text(10,20,"Points: 0");
        pane.getChildren().add(text);
        AtomicInteger points = new AtomicInteger(0);
        
        
        //Adding to the pane
        pane.getChildren().add(ship.getCharacter());
        asts.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));
        
        //Scene and the window pop up
        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.setTitle("Asteroids");
        window.show();
        
        //Recording the Key presses
        scene.setOnKeyPressed(event->{
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(event->{
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });
        
        
        
        //animation timer
        new AnimationTimer(){
            @Override
            public void handle(long now){
                if(pressedKeys.getOrDefault(KeyCode.LEFT, Boolean.FALSE)){
                    ship.turnLeft();
                }
                if(pressedKeys.getOrDefault(KeyCode.RIGHT, Boolean.FALSE)){
                    ship.turnRight();
                }
                if(pressedKeys.getOrDefault(KeyCode.UP, Boolean.FALSE)){
                    ship.accelerate();
                }
                if(pressedKeys.getOrDefault(KeyCode.SPACE, Boolean.FALSE)&& projectiles.size()<3){
                    Projectile projectile = new Projectile((int)ship.getCharacter().getTranslateX(),(int)ship.getCharacter().getTranslateY());
                    projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
                    projectiles.add(projectile);
                    projectile.accelerate();
                    projectile.setMovement(projectile.getMovement().normalize().multiply(3));
                    
                    
                    pane.getChildren().add(projectile.getCharacter());
                }
                
                
                ship.move();
                projectiles.forEach(projectile -> projectile.move());
                asts.forEach(asteroid-> asteroid.move());
                asts.forEach(asteroid ->{
                    if(ship.collide(asteroid)){
                        stop();
                    }
                });
                
                projectiles.forEach(projectile ->{
                    asts.forEach(asteroid ->{
                        if(projectile.collide(asteroid)){
                            projectile.setAlive(false);
                            asteroid.setAlive(false);
                        }
                    });
                    if(!projectile.isAlive()){
                        text.setText("Points: " + points.addAndGet(1000));
                    }
                });
                projectiles.stream()
                        .filter(projectile ->!projectile.isAlive())
                        .forEach(projectile -> pane.getChildren().remove(projectile.getCharacter()));
                projectiles.removeAll(projectiles.stream().filter(projectile -> !projectile.isAlive()).collect(Collectors.toList()));
                asts.stream()
                        .filter(asteroid -> !asteroid.isAlive())
                        .forEach(asteroid -> pane.getChildren().remove(asteroid.getCharacter()));
                asts.removeAll(asts.stream().filter(asteroid -> !asteroid.isAlive()).collect(Collectors.toList()));
                
                //Random Addition of Asteroids
                if(Math.random() < 0.01){
                    Asteroid asteroid = new Asteroid(WIDTH,HEIGHT);
                    if(!asteroid.collide(ship)){
                        asts.add(asteroid);
                        pane.getChildren().add(asteroid.getCharacter());
                    }
                }
                
            }
        }.start();
        
    }
    
    
    
}


