package com.example.demo.object_calisthenics.good_case_principle1;

public class Board {
    private int[][] data;

    public Board(int[][] data) {
        this.data = data;
    }

    // Good Case
    public String board(){
        StringBuffer buf = new StringBuffer();
        collectRows(buf);
        return buf.toString();
    }

    private void collectRows(StringBuffer buf) {
        for (int i = 0; i < 10; i++) {
            collectRow(buf, i);
        }
    }

    private void collectRow(StringBuffer buf, int i) {
        for (int j = 0; j < 10; j++) {
            buf.append(data[i][j]);
        }
        buf.append("\n");
    }
}
