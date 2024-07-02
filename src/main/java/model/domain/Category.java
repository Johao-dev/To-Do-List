package model.domain;

public enum Category {
    
    NORMAL(1,"NORMAL", "Pueden ser realizadas o no."),
    IMPORTANTE(2, "IMPORTANTE", "Deben ser realizadas pero pueden posponerse."),
    URGENTE(3, "URGENTE", "Deben ser realizadas y no pueden posponerse.");
    
    private final int categoryId;
    private final String categoryName;
    private final String categoryDescription;
    
    private Category(int categoryId, String name, String desc) {
        this.categoryId = categoryId;
        this.categoryName = name;
        this.categoryDescription = desc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
}
