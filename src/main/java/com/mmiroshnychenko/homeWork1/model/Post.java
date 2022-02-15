package com.mmiroshnychenko.homeWork1.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Post {
    private Long id;

    private Writer writer;

    private String content;

    private Date created;

    private Date updated;

    private List<Label> labels;

    private PostStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public void addLabel(Label label) {
        this.labels.add(label);
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!id.equals(post.id)) return false;
        if (!writer.equals(post.writer)) return false;
        if (!content.equals(post.content)) return false;
        if (!created.equals(post.created)) return false;
        if (!updated.equals(post.updated)) return false;
        if (!labels.equals(post.labels)) return false;
        return status == post.status;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + writer.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + created.hashCode();
        result = 31 * result + updated.hashCode();
        result = 31 * result + labels.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", labels=" + labels +
                ", status=" + status +
                '}';
    }
}
