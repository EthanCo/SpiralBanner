package com.heiko.spiralbannersample;

/**
 * MyData
 *
 * @author Heiko
 * @date 2019/12/10
 */
public class MyData {
    private String image;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MyData(String title, String image) {
        this.title = title;
        this.image = image;
    }
}
