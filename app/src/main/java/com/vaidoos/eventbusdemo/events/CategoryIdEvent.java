package com.vaidoos.eventbusdemo.events;

public class CategoryIdEvent {
    public String CategoryId;

    public CategoryIdEvent() {
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }
}
