package model;

import java.util.UUID;

public class Fiction {
    private String id; // เพิ่มฟิลด์ id
    private String fictionName;
    private String fictionLanguage;
    private String[] fictionType;
    private String story;
    private double fictionPrice;

    // Getter and Setter methods
    public Fiction() {
        this.id = generateID();  // สร้าง ID ขึ้นมาเมื่อสร้างอ็อบเจกต์
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }

    // Getter and Setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for fictionName
    public String getFictionName() {
        return fictionName;
    }

    public void setFictionName(String fictionName) {
        this.fictionName = fictionName;
    }

    // Getter and Setter for fictionLanguage
    public String getFictionLanguage() {
        return fictionLanguage;
    }

    public void setFictionLanguage(String fictionLanguage) {
        this.fictionLanguage = fictionLanguage;
    }

    // Getter and Setter for fictionType
    public String[] getFictionType() {
        return fictionType;
    }

    public void setFictionType(String[] fictionType) {
        this.fictionType = fictionType;
    }

    // Getter and Setter for story
    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    // Getter and Setter for fictionPrice
    public double getFictionPrice() {
        return fictionPrice;
    }

    public void setFictionPrice(double fictionPrice) {
        this.fictionPrice = fictionPrice;
    }
    
    public void clearFiction() {
        this.id = generateID();  // สร้าง ID ใหม่อีกครั้ง
        this.fictionName = null;
        this.fictionLanguage = null;
        this.fictionType = null;
        this.story = null;
        this.fictionPrice = 0.0;
}

}
