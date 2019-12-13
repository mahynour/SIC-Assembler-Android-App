package com.android.sic;

import java.util.ArrayList;
import java.util.List;
/***Copyright 2019, Mahynour , All rights reserved.***/

public class PassTwo {

    Integer FinalSize;
    Integer Address;
    Integer size;
    String objcodes;
    List<String> tails = new ArrayList<>();
    String Allobj;

    public String createHeadAndTeals(String label, String FirstAddress, List<String> objectCodeobj) {

        size = 3 * 2;
        Address = Integer.parseInt(FirstAddress);

        int linectr = 0;
        for (int i = 0; i < objectCodeobj.size(); i++) {
            size = 3 * 2;
            String zeros = "";

            for (int x = 4 - String.valueOf(Address).length() + 1; x >= 1; x--) {
                zeros += "0";
            }

            if (i == 0)
                objcodes = zeros + Address + "^" + Integer.toHexString(size) + "^";
            if (linectr == 0 && i != 0 && i != objectCodeobj.size() - 1) {
                Address = Integer.parseInt(String.valueOf(Address), 16) + size;
                objcodes = zeros + Integer.toHexString(Address) + "^" + Integer.toHexString(size) + "^";
            }

            if (i == objectCodeobj.size() - 1 && linectr == 0) {
                Address = Integer.parseInt(String.valueOf(Address), 16) + size;
                size = 3 * (linectr + 1);
                objcodes = zeros + Integer.toHexString(Address) + "^" + Integer.toHexString(size) + "^";
                FinalSize = Address + size - Integer.valueOf(FirstAddress);
            } else if (i == objectCodeobj.size() - 1) {
                FinalSize = Address + size - Integer.valueOf(FirstAddress);
            }

            objcodes += objectCodeobj.get(i) + "^";
            linectr++;
            if (linectr > 2 || i == objectCodeobj.size() - 1) {
                tails.add("T" + "^" + objcodes + "\n");
                linectr = 0;
            }
        }

        String head = "H" + "^" + label + "^" + Integer.toHexString(FinalSize) + "^" + Integer.toHexString(Integer.parseInt(FirstAddress)) + "\n";
        Allobj = head;
        for (String Tails : tails) {
            Allobj += Tails;
        }
        Allobj += "E^" + Integer.toHexString(Integer.parseInt(FirstAddress));

        return Allobj;


    }

}
