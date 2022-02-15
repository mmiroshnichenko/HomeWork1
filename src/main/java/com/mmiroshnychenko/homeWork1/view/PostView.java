package com.mmiroshnychenko.homeWork1.view;

import com.mmiroshnychenko.homeWork1.model.Post;

import java.util.List;

public class PostView {
    public void list(List<Post> posts) {
        System.out.println("===             Posts             ===");
        System.out.println("ID      Content     Writer     Status");
        System.out.println("=====================================");
        for (Post post : posts) {
            System.out.println(post.getId() + "   " + post.getContent().substring(0, 10) + "...     "
                    + post.getWriter().getFirstName() + " " + post.getWriter().getLastName() + "    " + post.getStatus());
        }
        System.out.println("=====================================");
    }

    public void listMenu() {
        System.out.println("===Posts menu===");
        System.out.println("1.       Create");
        System.out.println("2.       View");
        System.out.println("3.       Update status");
        System.out.println("4.       Exit");
        System.out.println("===============");
        System.out.println("Enter post menu number: ");
    }

    public void view(Post post) {
        System.out.print("Labels: ");
        post.getLabels().stream().forEach(v -> System.out.print(v.getName() + " "));
        System.out.println();
        System.out.println();
        System.out.println("Writer: " + post.getWriter().getFirstName() + " " + post.getWriter().getLastName());
        System.out.println();
        System.out.println("Content:");
        System.out.println(post.getContent());
        System.out.println();
        System.out.println("Status: " + post.getStatus());
    }

    public void statuses() {
        System.out.println("=====Statuses=======");
        System.out.println("1   ACTIVE");
        System.out.println("2   UNDER_REVIEW");
        System.out.println("3   DELETED");
        System.out.println("====================");
        System.out.println();
        System.out.println("Enter status number(or 'stop' for exit):");
    }

    public void selectLabels() {
        System.out.println("Enter labels number through a comma(or 'stop' for exit):");
    }

    public void selectWriter() {
        System.out.println("Enter writer number(or 'stop' for exit):");
    }

    public void incorrectLabelsNumbers() {
        System.out.println("Incorrect labels numbers");
    }

    public void incorrectStatusNumber() {
        System.out.println("Incorrect status number");
    }

    public void enterContent() {
        System.out.println("Enter content and 'stop' in the end");
    }

    public void selectForView() {
        System.out.println("Enter post ID for view(or 'stop' for exit):");
    }

    public void selectForUpdateStatus() {
        System.out.println("Enter post ID for update status(or 'stop' for exit):");
    }

    public void selectForUpdating() {
        System.out.println("Enter post ID for updating(or 'stop' for exit):");
    }

    public void enterName() {
        System.out.println("Enter post name(or 'stop' for exit):");
    }

    public void incorrectId() {
        System.out.println("Incorrect ID. Post not found.");
    }
}
