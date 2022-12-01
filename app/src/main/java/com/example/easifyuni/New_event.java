package com.example.easifyuni;

public class New_event {
    private String link, loc;
    private int cap,att_req,fees;
    private String date;
    private String Time;


    public New_event(int cap, int att_req, String link, String loc, int fees, String date, String Time) {
        this.cap = cap;
        this.att_req = att_req;
        this.link = link;
        this.loc = loc;
        this.fees=fees;
        this.date = date;
        this.Time = Time;
    }

    public String getLink() {
        return link;
    }
    public String getloc() {
        return loc;
    }
    public int getAtt_req() {
        return att_req;
    }
    public int getCap() {
        return cap;
    }
    public int getFees() {
        return fees;
    }
    public String getdate() {
        return date;
    }
    public String getTime() {
        return Time;
    }
}
