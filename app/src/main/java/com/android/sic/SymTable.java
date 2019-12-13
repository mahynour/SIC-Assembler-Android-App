package com.android.sic;
import java.util.HashMap;
/***Copyright 2019, Mahynour , All rights reserved.***/

public class SymTable {

    HashMap<String, Integer> symtable = new HashMap<String, Integer>();

    public String Get_sym_address(String operaned) {

       return String.valueOf(symtable.get(operaned));
    }


    public void set_sym_address(String operand, Integer address) {

        symtable.put(operand, address);

    }


}
