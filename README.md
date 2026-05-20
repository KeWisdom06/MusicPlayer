# Music Player (Monolithic Version)

A simple Java WAV music player built using Swing and the Java Sound API.

This version is the original monolithic implementation before the application was refactored into separate UI, controller, and audio classes.

## Features
* Load .wav audio files
* Play audio
* Pause / Resume audio
* Loop toggle
* Progress bar
* Elapsed time display

## Requirements
* Java JDK 17+ installed
* Terminal / Command Prompt
* WAV audio files


## How to Run
1. Open terminal

Navigate to the project directory using cd.

```bash
// For example
cd "/Users/yourname/Downloads/Sample Slicer"

```
2. Compile the Program(s)

   * Compile all java files:

      * Compile the Music Player program
```bash
javac JavaGUIMusicPlayerJFrame.java
```

 * (Optional) Compile the Main program to run the Progress Bar demo 
```bash
javac Main.java
```

3. Run the Program(s)
   
* Launch the program by running the following command on the JavaGUIMusicPlayerJFrame class (in your terminal):
```bash
java JavaGUIMusicPlayerJFrame
```
If you'd like to launch the progress bar aplication, the same 'java' command is is carried out in the terminal

## How to Use the JavaGUIMusicPlayerJFrame Program

1. Launch the JavaGuiMusicPlayerJFrame application
2. Click Choose File
3. Select a .wav file
4. Use:
    * Play
    * Pause / Resume
    * Loop
5. Watch the progress bar and elapsed time update during playback

## Further Plans and Final Notes

This program only supports .wav files at the moment, as MP3, OGG or AAC support is yet to be implemented

It was built using Java Swing components and the Java Sound API (Clip, AudioInputStream)

I am currently working on a refactored modular architecture for the program, as well as adding extra functionality like a Seek Slider

I hope to someday develop it into a sampler with a DAW (Digital Audio Workstation) inspired workflow, but for now i want to set the foundation...

This project was also created with the intent of developing my skills as a Software programmer using java, as well as practicing using Github as a source code control tool. 






