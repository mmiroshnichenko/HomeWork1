package com.mmiroshnychenko.homeWork1.controller;

import com.mmiroshnychenko.homeWork1.model.Label;
import com.mmiroshnychenko.homeWork1.model.Post;
import com.mmiroshnychenko.homeWork1.model.PostStatus;
import com.mmiroshnychenko.homeWork1.model.Writer;
import com.mmiroshnychenko.homeWork1.service.LabelService;
import com.mmiroshnychenko.homeWork1.service.PostService;
import com.mmiroshnychenko.homeWork1.service.WriterService;
import com.mmiroshnychenko.homeWork1.view.LabelView;
import com.mmiroshnychenko.homeWork1.view.PostView;
import com.mmiroshnychenko.homeWork1.view.WriterView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class PostController {
    private static final char postCreate = '1';
    private static final char postViewOne = '2';
    private static final char postUpdate = '3';
    private static final char postExit = '4';

    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();
    private final LabelView labelView = new LabelView();
    private final PostService postService = new PostService();
    private final WriterService writerService = new WriterService();
    private final LabelService labelService = new LabelService();

    public void list() throws IOException {
        postView.list(postService.getList());
        postView.listMenu();
        char c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            c = (char) br.read();
            processWritersListChoice(c);
            if (c != postExit && c != '\n') {
                postView.list(postService.getList());
                postView.listMenu();
            }
        } while (c != postExit);
    }

    public void create() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        labelView.list(labelService.getList());

        postView.selectLabels();
        String labelsStr = br.readLine();;
        if (labelsStr.toLowerCase().equals("stop")) {
            return;
        }
        String[] labelsIdsStr = labelsStr.split(",");
        List<Label> labels;
        try {
            List<Long> labelIds = Arrays.stream(labelsIdsStr).map(Long::valueOf).toList();
            labels = labelService.getListByIds(labelIds);
            if (labels.size() != labelIds.size()) {
                postView.incorrectLabelsNumbers();
                return;
            }
        } catch (NumberFormatException e) {
            postView.incorrectLabelsNumbers();
            return;
        }

        writerView.list(writerService.getList());
        postView.selectWriter();
        String writerIdStr = br.readLine();
        Writer writer;
        if (writerIdStr.toLowerCase().equals("stop")) {
            return;
        }
        try {
            Long writerId = Long.parseLong(writerIdStr);
            writer = writerService.getById(writerId);
            if (writer == null) {
                writerView.incorrectId();
                return;
            }
        } catch (NumberFormatException e) {
            writerView.incorrectId();
            return;
        }

        postView.enterContent();
        String content = inputText(br);
        if (content.isEmpty()) {
            return;
        }

        postService.save(content, writer, labels);
    }

    public void view() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        postView.selectForView();
        String postIdStr = br.readLine();
        Post post;
        if (postIdStr.toLowerCase().equals("stop")) {
            return;
        }
        try {
            Long postId = Long.parseLong(postIdStr);
            post = postService.getById(postId);
            if (post == null) {
                postView.incorrectId();
                return;
            }
        } catch (NumberFormatException e) {
            postView.incorrectId();
            return;
        }
        postView.view(post);

    }

    public void update() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        postView.selectForUpdateStatus();
        String postIdStr = br.readLine();
        Post post;
        if (postIdStr.toLowerCase().equals("stop")) {
            return;
        }
        try {
            Long postId = Long.parseLong(postIdStr);
            post = postService.getById(postId);
            if (post == null) {
                postView.incorrectId();
                return;
            }
        } catch (NumberFormatException e) {
            postView.incorrectId();
            return;
        }


        postView.statuses();
        String statusNumber = br.readLine();
        if (statusNumber.toLowerCase().equals("stop")) {
            return;
        }
        PostStatus status;
        if (statusNumber.equals("1")) {
            status = PostStatus.ACTIVE;
        } else if (statusNumber.equals("2")) {
            status = PostStatus.UNDER_REVIEW;
        } else if (statusNumber.equals("3")) {
            status = PostStatus.DELETED;
        } else {
            postView.incorrectStatusNumber();
            return;
        }

        postService.updateStatus(post, status);
    }


    private String inputText(BufferedReader br) throws IOException {
        String text  = "";
        String str;
        do {
            str = br.readLine();
            if (!str.toLowerCase().equals("stop")) text += str;
        } while (!str.toLowerCase().equals("stop"));

        return text;
    }


    private void processWritersListChoice(char choice) throws IOException {
        switch (choice) {
            case postCreate:
                create();
                break;
            case postViewOne:
                view();
                break;
            case postUpdate:
                update();
                break;
            case postExit:
            case '\n':
                break;
            default:
                System.out.println("Incorrect menu number! Try again:");
        }
    }
}
