package com.example.easifyuni;

public class Register {
    private String name, desc,tag,dept,FacCo,Stuco;

    public Register(String name, String desc, String tag, String dept, String FaCco, String Stuco) {
        this.name = name;
        this.desc = desc;
        this.tag = tag;
        this.dept = dept;
        this.FacCo = FaCco;
        this.Stuco = Stuco;
    }

    public String getName() {
        return name;
    }

    public String getdesc() {
        return desc;
    }

    public String gettag() {
        return tag;
    }

    public String getdept() {
        return dept;
    }

    public String getFacCo() {
        return FacCo;
    }
    public String getStuco() {
        return Stuco;
    }
}

