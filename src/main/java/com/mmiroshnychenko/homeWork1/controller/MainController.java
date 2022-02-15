package com.mmiroshnychenko.homeWork1.controller;

import com.mmiroshnychenko.homeWork1.view.MainMenuView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    private static final char menuWriter = '1';
    private static final char menuLabel = '2';
    private static final char menuPost = '3';
    private static final char menuExit = '4';

    private static WriterController writerController;
    private static LabelController labelController;
    private static PostController postController;

    public static void main(String[] args) {
        initControllers();
        MainMenuView.showMainMenu();
        char c;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                c = (char) br.read();
                processMainChoice(c);
                if (c != menuExit && c != '\n') MainMenuView.showMainMenu();
            } while (c != menuExit);
        } catch (IOException e) {
            System.out.println("Input output exception: " + e);
        }
    }

    public static void processMainChoice(char choice) throws IOException {
        switch (choice) {
            case menuWriter:
                writerController.list();
                break;
            case menuLabel:
                labelController.list();
                break;
            case menuPost:
                postController.list();
                break;
            case menuExit:
            case '\n':
                break;
            default:
                System.out.println("Incorrect menu number! Try again:");
        }
    }

    public static void initControllers() {
        writerController = new WriterController();
        labelController = new LabelController();
        postController = new PostController();
    }
}
