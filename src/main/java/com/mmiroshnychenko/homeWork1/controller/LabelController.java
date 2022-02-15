package com.mmiroshnychenko.homeWork1.controller;

import com.mmiroshnychenko.homeWork1.model.Label;
import com.mmiroshnychenko.homeWork1.service.LabelService;
import com.mmiroshnychenko.homeWork1.view.LabelView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LabelController {
    private static final char labelCreate = '1';
    private static final char labelUpdate = '2';
    private static final char labelDelete = '3';
    private static final char labelExit = '4';

    private final LabelView labelView = new LabelView();
    private final LabelService labelService = new LabelService();

    public void list() throws IOException {
        labelView.list(labelService.getList());
        labelView.listMenu();
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            c = (char) br.read();
            processLabelsListChoice(c);
            if (c != labelExit && c != '\n') {
                labelView.list(labelService.getList());
                labelView.listMenu();
            }
        } while (c != labelExit);
    }

    public void create() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        labelView.enterName();
        String name = br.readLine();
        if (name.toLowerCase().equals("stop")) {
            return;
        }

        labelService.save(name);
    }

    public void update() throws IOException {
        labelView.selectForUpdating();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long id;
        try {
            String str = br.readLine();
            if (!str.toLowerCase().equals("stop")) {
                id = Long.parseLong(str);
            } else {
                return;
            }
        } catch (NumberFormatException e) {
            labelView.incorrectId();
            return;
        }

        Label label = labelService.getById(id);
        if (label == null) {
            labelView.incorrectId();
            return;
        }

        labelView.enterName();
        String name = br.readLine();
        if (name.toLowerCase().equals("stop")) {
            return;
        }

        labelService.update(label, name);
    }

    public void delete() throws IOException {
        labelView.selectForDeleting();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long id;
        try {
            String str = br.readLine();
            if (!str.toLowerCase().equals("stop")) {
                id = Long.parseLong(str);
            } else {
                return;
            }
        } catch (NumberFormatException e) {
            labelView.incorrectId();
            return;
        }

        Label label = labelService.getById(id);
        if (label == null) {
            labelView.incorrectId();
            return;
        }
        labelService.delete(id);
    }

    private void processLabelsListChoice(char choice) throws IOException {
        switch (choice) {
            case labelCreate:
                create();
                break;
            case labelUpdate:
                update();
                break;
            case labelDelete:
                delete();
                break;
            case labelExit:
            case '\n':
                break;
            default:
                System.out.println("Incorrect menu number! Try again:");
        }
    }
}
