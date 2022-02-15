package com.mmiroshnychenko.homeWork1.controller;

import com.mmiroshnychenko.homeWork1.model.Writer;
import com.mmiroshnychenko.homeWork1.service.WriterService;
import com.mmiroshnychenko.homeWork1.view.WriterView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WriterController {
    private static final char writerCreate = '1';
    private static final char writerUpdate = '2';
    private static final char writerDelete = '3';
    private static final char writerExit = '4';

    private final WriterView writerView = new WriterView();
    private final WriterService writerService = new WriterService();

    public void list() throws IOException {
        writerView.list(writerService.getList());
        writerView.listMenu();
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            c = (char) br.read();
            processWritersListChoice(c);
            if (c != writerExit && c != '\n') {
                writerView.list(writerService.getList());
                writerView.listMenu();
            }
        } while (c != writerExit);
    }

    public void create() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        writerView.enterFirstName();
        String firstName = inputString(br);
        if (firstName.toLowerCase().equals("stop")) {
            return;
        }

        writerView.enterLastName();
        String lastName = inputString(br);
        if (lastName.toLowerCase().equals("stop")) {
            return;
        }

        writerService.save(firstName, lastName);
    }

    public void update() throws IOException {
        writerView.selectForUpdating();
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
            writerView.incorrectId();
            return;
        }

        Writer writer = writerService.getById(id);
        if (writer == null) {
            writerView.incorrectId();
            return;
        }

        writerView.enterFirstName();
        String firstName = inputString(br);
        if (firstName.toLowerCase().equals("stop")) {
            return;
        }

        writerView.enterLastName();
        String lastName = inputString(br);
        if (lastName.toLowerCase().equals("stop")) {
            return;
        }

        writerService.update(writer, firstName, lastName);
    }

    public void delete() throws IOException {
        writerView.selectForDeleting();
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
            writerView.incorrectId();
            return;
        }

        Writer writer = writerService.getById(id);
        if (writer == null) {
            writerView.incorrectId();
            return;
        }
        writerService.delete(id);
    }

    private String inputString(BufferedReader br) throws IOException {
        String str;
        do {
            str = br.readLine();
            if (str.length() > 1) {
                return str;
            }
        } while (!str.toLowerCase().equals("stop"));

        return str;
    }

    private void processWritersListChoice(char choice) throws IOException {
        switch (choice) {
            case writerCreate:
                create();
                break;
            case writerUpdate:
                update();
                break;
            case writerDelete:
                delete();
                break;
            case writerExit:
            case '\n':
                break;
            default:
                System.out.println("Incorrect menu number! Try again:");
        }
    }
}
