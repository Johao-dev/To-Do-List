package model.domain;

public enum Category {
    
    NORMAL(1, "Pueden ser realizadas o no."),
    IMPORTANTE(2, "Deben ser realizadas pero pueden posponerse."),
    URGENTE(3, "Deben ser realizadas y no pueden posponerse.");
    
    private final int categoryId;
    private final String categoryDescription;
    
    private Category(int categoryId, String desc) {
        this.categoryId = categoryId;
        this.categoryDescription = desc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }
}
