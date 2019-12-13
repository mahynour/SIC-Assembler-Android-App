package com.android.sic;
import java.util.ArrayList;
import java.util.List;
/***Copyright 2019, Mahynour , All rights reserved.***/


public class PassOne {
    ArrayList<String[]> line = new ArrayList<String[]>();
    String tokens[];
    String lineSplited[];
    final Integer LABEL = 0;
    final Integer INSTRUCTION = 1;
    final Integer OPERAND = 2;
    List<String> Labels = new ArrayList<String>();
    List<String> Address = new ArrayList<String>();
    List<String> error;
    List<String> objectCode = new ArrayList<String>();
    boolean isStrat = false;
    boolean faild = false;
    boolean haveObjCode = false;
    PassTwo passTwo;
    SymTable sym;
    OBJ obj;
    int inc = 0;


    public void createObjCode(String input) {
        passTwo = new PassTwo();
        createAddressToAllLines(input);
        if (faild)
            return;
        objectCode = new ArrayList<String>();
        obj = new OBJ();
        obj.CreateObj();
        for (int i = 1; i < line.size(); i++) {

            if (IsBasicDirective(i) && i == line.size() - 1 && !isStrat) {
                return;
            }
            if (Instruction(i)) {
                objectCode.add(obj.getObj(getInstruction(i)) + Integer.toHexString(Integer.parseInt(sym.Get_sym_address(getOperand(i)))));
            }
            if (IsDirective(i) && haveObjCode) {
                String Objcode = Integer.toHexString(Integer.parseInt(getOperand(i)));
                objectCode.add(Objcode);
            }
        }


    }

    public String getobj() {
        return passTwo.createHeadAndTeals(Labels.get(0), Address.get(0), objectCode);

    }

    public void createAddressToAllLines(String input) {
        sym = new SymTable();
        Set_lines(input);
        if (faild)
            return;
        int address;
        for (int i = 0; i < line.size(); i++) {

            // if line is first line or end line check IsDirective
            if (i == 0 || i == line.size() - 1) {
                if (IsBasicDirective(i) && i == 0 && isStrat) {
                    Labels.add(line.get(i)[LABEL]
                            .replaceAll(" ", "")
                            .replaceAll("\t", ""));
                    Address.add(line.get(i)[OPERAND]
                            .replaceAll(" ", "")
                            .replaceAll("\t", ""));

                } else if (IsBasicDirective(i) && i == line.size() - 1)
                    return;
            }
            if (i == 0 && !isStrat) {
                error.add("at line" + (i + 1) + "it's not a start ");
                return;
            }


            if (haveLabel(i) && Instruction(i)) {
                Labels.add(line.get(i)[LABEL]
                        .replaceAll(" ", "")
                        .replaceAll("\t", ""));
                // address = Integer.parseInt(Address.get(i - 1)) + 3;
                address = Integer.parseInt(Address.get(i - 1), 10) + 3;

                Address.add(String.valueOf(address));
                sym.set_sym_address(Labels.get(i), address);

            } else if (Instruction(i)) {
                //  address = Integer.parseInt(Address.get(i - 1)) + 3;
                address = Integer.parseInt(Address.get(i - 1), 10) + 3;

                Address.add(String.valueOf(address));

            }

            if (haveLabel(i) && IsDirective(i)) {
                Labels.add(line.get(i)[LABEL]
                        .replaceAll(" ", "")
                        .replaceAll("\t", ""));
                address = Integer.parseInt(Address.get(i - 1), 10) + inc;

                Address.add(String.valueOf(address));
                sym.set_sym_address(Labels.get(i), address);

            }

            if (!IsDirective(i) && !haveLabel(i) && !Instruction(i))
                error.add("at line" + (i + 1) + "it's not a directive or instruction");
        }

    }

    private boolean Instruction(int i) {
        String Instruction = line.get(i)[INSTRUCTION].replaceAll(" ", "").replaceAll("\t", "");

        switch (Instruction) {
            case "ADD":
                return true;
            case "AND":
                return true;
            case "STA":
                return true;
            case "STX":
                return true;
            case "STCH":
                return true;
            case "STL":
                return true;
            case "LDA":
                return true;
            case "LDCH":
                return true;
            case "LDX":
                return true;
            case "COMP":
                return true;
            case "MUL":
                return true;
            case "DIV":
                return true;
            case "SUB":
                return true;
            case "OR":
                return true;
            case "LDL":
                return true;
            case "TIX":
                return true;
            default:
                return false;

        }
    }


    private boolean IsDirective(int i) {
        String IsDirective = line.get(i)[INSTRUCTION].replaceAll(" ", "").replaceAll("\t", "");
        String str = line.get(i)[OPERAND].replaceAll(" ", "").replaceAll("\t", "");

        switch (IsDirective) {

            case "WORD":
                inc = 3;
                haveObjCode = true;
                return true;
            case "BYTE":
                haveObjCode = true;
                inc = line.get(i)[OPERAND].replaceAll(" ", "").replaceAll("\t", "").length();
                return true;
            case "RESB":
                haveObjCode = false;
                inc = str.length();//inc by num of bits
                return true;
            case "RESW":
                haveObjCode = false;
                inc = Integer.parseInt(str/*, 16*/) * 3;
                return true;
            default:
                return false;

        }
    }

    //split all line
    public void Set_lines(String input) {
        error = new ArrayList<String>();
        tokens = input.split("\n");
            split_line(tokens);
    }

    public void split_line(String[] input) {


        for (int i = 0; i < input.length; i++) {
            if (input[i].length() >= 15) { //smalest number

                lineSplited = splitToChar(input[i], 7, 3);
                line.add(lineSplited);
            } else {
                error.add("error check spaces");
                faild = true;
                return;
            }
        }
    }

    public Boolean IsBasicDirective(int i) {

        String IsDirective = line.get(i)[INSTRUCTION].replaceAll(" ", "").replaceAll("\t", "");
        if (IsDirective.equals("START")) {
            isStrat = true;
            return true;
        }
        if (IsDirective.equals("END")) {
            isStrat = false;
            return true;
        } else return false;

    }

    public Boolean haveLabel(int i) {

        String haveLabel = line.get(i)[LABEL].replaceAll(" ", "").replaceAll("\t", "");
        if (haveLabel != "" || haveLabel.equals("") || !haveLabel.isEmpty())
            return true;
        else return false;

    }


    /**
     * Split text into n number of characters.
     *
     * @param text the text to be split.
     * @param size the split size.
     * @return an array of the split text.
     */
    private static String[] splitToChar(String text, int size, int limit) {
        List<String> parts = new ArrayList<>();
        int checkLimit = 1;
        int length = text.length();
        for (int i = 0; i < length && checkLimit <= limit; i += size) {
            parts.add(text.substring(i, Math.min(length, i + size)));
        }
        return parts.toArray(new String[0]);
    }


    public String getOperand(int i) {
        return line.get(i)[OPERAND].replaceAll(" ", "").replaceAll("\t", "");
    }

    public String getInstruction(int i) {
        return line.get(i)[INSTRUCTION].replaceAll(" ", "").replaceAll("\t", "");
    }


    public boolean isFaild() {
        return faild;
    }

    public String[] errors() {
    return error.toArray(new String[0]);
    }
}
