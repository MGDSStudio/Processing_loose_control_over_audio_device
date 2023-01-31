package com.example.test_two_activities_with_sound;

import processing.core.PApplet;
import processing.sound.SoundFile;
public class AndroidLauncher extends PApplet {
    private SoundFile file;

    @Override
    public void settings() {


        size(width, height, P2D);

    }

    @Override
    public void setup() {
        final boolean saveInRAM = false;
        file = new SoundFile(this, "Sound.wav", saveInRAM);

        //file = new SoundFile();
    }


    public void draw() {
        background(200,25,random(25));
        if (frameCount%200 == 0) {
           file.play();
           println("Audio was played at " + millis()/1000 + " second");
        }

    }


    public void mouseReleased(){
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.toSecondActivity();
    }

}
