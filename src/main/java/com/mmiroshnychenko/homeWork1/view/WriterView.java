package com.mmiroshnychenko.homeWork1.view;

import com.mmiroshnychenko.homeWork1.model.Writer;

import java.util.List;

public class WriterView {
    public void list(List<Writer> writers) {
        System.out.println("===       Writers       ===");
        System.out.println("ID     FistName     LastName");
        System.out.println("===========================");
        for (Writer writer : writers) {
            System.out.println(writer.getId() + "      " + writer.getFirstName() + "     " + writer.getLastName());
        }
        System.out.println("===========================");
    }

    public void listMenu() {
        System.out.println("===Writers menu===");
        System.out.println("1.       Create");
        System.out.println("2.       Update");
        System.out.println("3.       Delete");
        System.out.println("4.       Exit");
        System.out.println("===============");
        System.out.println("Enter writers menu number: ");
    }

    public void selectForDeleting() {
        System.out.println("Enter writer ID for deleting:");
    }

    public void selectForUpdating() {
        System.out.println("Enter writer ID for updating(or 'stop' for exit):");
    }

    public void enterFirstName() {
        System.out.println("Enter writer First name(or 'stop' for exit):");
    }

    public void enterLastName() {
        System.out.println("Enter writer Last name(or 'stop' for exit):");
    }

    public void incorrectId() {
        System.out.println("Incorrect ID. Writer not found.");
    }

}
