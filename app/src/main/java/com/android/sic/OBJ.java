package com.android.sic;

import java.util.HashMap;
/***Copyright 2019, Mahynour , All rights reserved.***/

public class OBJ {

    HashMap<String, String> OBJ = new HashMap<String, String>();


    void CreateObj() {

        OBJ.put("ADD", "18");
        OBJ.put("AND", "40");
        OBJ.put("STA", "0C");
        OBJ.put("STX", "10");
        OBJ.put("STCH", "54");
        OBJ.put("STL", "14");
        OBJ.put("LDA", "00");
        OBJ.put("LDCH", "50");
        OBJ.put("LDX", "04");
        OBJ.put("COMP", "28");
        OBJ.put("MUL", "20");
        OBJ.put("DIV", "24");
        OBJ.put("SUB", "1C");
        OBJ.put("OR", "44");
        OBJ.put("LDL", "08");
        OBJ.put("AND", "2C");
        OBJ.put("JSUB", "48");
        OBJ.put("RSUB", "4C");
        OBJ.put("JUMP", "3C");
        OBJ.put("JEQ", "30");
        OBJ.put("JGT", "34");
        OBJ.put("JLT", "38");
    }

    String getObj(String instruction) {

       return OBJ.get(instruction);
    }

}
