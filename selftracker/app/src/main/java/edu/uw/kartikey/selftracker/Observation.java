package edu.uw.kartikey.selftracker;

/**
 * Created by kartikey on 1/24/2016.
 */
public class Observation {

    public String title;
    public String count;
    public String comment;
    public String date;


    public Observation(String title, String count, String comment, String date){
        this.title = title;
        this.count = count;
        this.comment = comment;
        this.date = date;
    }

    //default constructor; empty event
    public Observation(){}

    public String toString(){
        return this.title;
        //+ " ("+this.count+")";
    }
}
