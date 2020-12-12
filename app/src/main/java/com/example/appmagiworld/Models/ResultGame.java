package com.example.appmagiworld.Models;

public class ResultGame {
    private String result;
    public int imageId;
    private String section;
    private boolean isRow;

    public static ResultGame createRow(int imageId, String result) {
        ResultGame ret = new ResultGame();
        ret.result = result;
        ret.imageId = imageId;
        ret.isRow = true;
        return ret;
    }

    public static ResultGame createSection(String section) {
        ResultGame ret = new ResultGame();
        ret.section = section;
        ret.isRow = false;
        return ret;

    }

    public String getResult() {
        return result;
    }

    public String getSection(int i) {
        return section;
    }

    public boolean isRow() {
        return isRow;

    }
}