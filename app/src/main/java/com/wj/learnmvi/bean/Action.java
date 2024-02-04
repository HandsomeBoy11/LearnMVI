package com.wj.learnmvi.bean;

/**
 * Author:WJ
 * Date:2024/1/18 9:42
 * Describe：
 */
public class Action {
    private int type;
    private String text;

    public Action(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}

