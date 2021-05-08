package com.example.demo.object_calisthenics.bad_case_principle1;

public class Board {
    private int[][] data;

    public Board(int[][] data) {
        this.data = data;
    }

    // Bad Case
    public String board(){
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buf.append(data[i][j]);
            }
            buf.append("\n");
        }
        return buf.toString();
    }
}
