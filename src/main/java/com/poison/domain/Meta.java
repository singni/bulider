package com.poison.domain;

/**
 * Created by Rian on 2019/3/4.
 * Copyright © 2018 Rian. All rights reserved
 */
public class Meta {

    private String icon;

    private String title;


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}
