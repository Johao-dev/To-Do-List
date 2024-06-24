package models.domain;

import java.util.Objects;

public class Task {
    
    private int taskId;
    private String taskName;
    private String taskDescription;
    private Category category;

    public Task() {}

    public Task(int taskId) {
        this.taskId = taskId;
    }

    public Task(String taskName, String taskDescription, Category category) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.category = category;
    }

    public Task(int taskId, String taskName, String taskDescription, Category category) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.category = category;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.taskName);
        hash = 97 * hash + Objects.hashCode(this.taskDescription);
        hash = 97 * hash + Objects.hashCode(this.category);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Task other = (Task) obj;
        if (!Objects.equals(this.taskName, other.taskName)) {
            return false;
        }
        if (!Objects.equals(this.taskDescription, other.taskDescription)) {
            return false;
        }
        return Objects.equals(this.category, other.category);
    }
}
