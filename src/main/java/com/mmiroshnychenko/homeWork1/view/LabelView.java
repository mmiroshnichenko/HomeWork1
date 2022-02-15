package com.mmiroshnychenko.homeWork1.view;

import com.mmiroshnychenko.homeWork1.model.Label;

import java.util.List;

public class LabelView {
    public void list(List<Label> labels) {
        System.out.println("===       Labels       ===");
        System.out.println("ID          Name");
        System.out.println("===========================");
        for (Label label : labels) {
            System.out.println(label.getId() + "      " + label.getName());
        }
        System.out.println("===========================");
    }

    public void listMenu() {
        System.out.println("===Labels menu===");
        System.out.println("1.       Create");
        System.out.println("2.       Update");
        System.out.println("3.       Delete");
        System.out.println("4.       Exit");
        System.out.println("===============");
        System.out.println("Enter labels menu number: ");
    }

    public void selectForDeleting() {
        System.out.println("Enter label ID for deleting:");
    }

    public void selectForUpdating() {
        System.out.println("Enter label ID for updating(or 'stop' for exit):");
    }

    public void enterName() {
        System.out.println("Enter label name(or 'stop' for exit):");
    }

    public void incorrectId() {
        System.out.println("Incorrect ID. Label not found.");
    }
}
