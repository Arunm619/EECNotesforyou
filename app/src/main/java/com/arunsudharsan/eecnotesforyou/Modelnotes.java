package com.arunsudharsan.eecnotesforyou;

/**
 * Created by Arun on 2/15/2017.
 */

public class Modelnotes {

    private String Unit1, Unit2, Unit3, Unit4, Unit5, Impq, Syllabus;
    private String Subcode, Subname;


    Modelnotes() {
    }

    Modelnotes(String Unit1, String Unit2, String Unit3, String Unit4, String Unit5, String Impq, String Syllabus, String Subcode, String Subname) {

        this.Unit1 = Unit1;
        this.Unit2 = Unit2;
        this.Unit3 = Unit3;
        this.Unit4 = Unit4;
        this.Unit5 = Unit5;
        this.Impq = Impq;
        this.Syllabus = Syllabus;
        this.Subcode = Subcode;
        this.Subname = Subname;

    }

    public String getSubname() {
        return Subname;
    }

    public void setSubname(String subname) {
        Subname = subname;
    }

    public String getUnit1() {
        return Unit1;
    }

    public void setUnit1(String unit1) {
        Unit1 = unit1;
    }

    public String getUnit2() {
        return Unit2;
    }

    public String getSubcode() {
        return Subcode;
    }

    public void setSubcode(String subcode) {
        Subcode = subcode;
    }

    public void setUnit2(String unit2) {
        Unit2 = unit2;
    }

    public String getUnit3() {
        return Unit3;
    }

    public void setUnit3(String unit3) {
        Unit3 = unit3;
    }

    public String getUnit4() {
        return Unit4;
    }

    public void setUnit4(String unit4) {
        Unit4 = unit4;
    }

    public String getUnit5() {
        return Unit5;
    }

    public void setUnit5(String unit5) {
        Unit5 = unit5;
    }

    public String getImpq() {
        return Impq;
    }

    public void setImpq(String impq) {
        Impq = impq;
    }

    public String getSyllabus() {
        return Syllabus;
    }

    public void setSyllabus(String syllabus) {
        Syllabus = syllabus;
    }
}
