package Model;
import java.util.*;
/**
 *
 */
public class Date {
    private int day;
    private int month;
    private int year;
    private int hour;
    private int min;
    private int sec;
    public Date(){

    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static Date convertStringToDate(String dateString){
        StringTokenizer st = new StringTokenizer(dateString, "/");
        Date date = new Date();
        try {
            date.setDay(Integer.parseInt(st.nextToken()));
            date.setMonth(Integer.parseInt(st.nextToken()));
            date.setYear(Integer.parseInt(st.nextToken()));
        } catch(NumberFormatException e){
            return null;
        }
        return date;
    }
    public String convertDateToSqlString(){
        String monthString = month<10? "0" + month: month + "";
        String dayString = day<10? "0" + day: day + "";
        return year + "-" + monthString + "-" + dayString;
    }
    public static Date convertSqlStringToDate(String dateString, String timeString){
        StringTokenizer st = new StringTokenizer(dateString, "-");
        Date date = new Date();
        try {
            date.setYear(Integer.parseInt(st.nextToken()));
            date.setMonth(Integer.parseInt(st.nextToken()));
            date.setDay(Integer.parseInt(st.nextToken()));
        } catch(NumberFormatException e){
            return null;
        }
        StringTokenizer st2 = new StringTokenizer(timeString, ":");
        try {
            date.setHour(Integer.parseInt(st2.nextToken()));
            date.setMin(Integer.parseInt(st2.nextToken()));
            date.setSec((int) Double.parseDouble(st2.nextToken()));
        } catch(NumberFormatException e){
            return null;
        }
        return date;
    }
    public String convertDateToString(){
        return this.day + "/" + this.month + "/" + this.year;
    }
    public String convertTimeToSqlString(){
        return this.hour + ":" + this.min + ":" + this.sec;
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hour=" + hour +
                ", min=" + min +
                ", sec=" + sec +
                '}';
    }

    public String convertDateTimeToSqlString() {
        return convertDateToSqlString() + " " + convertTimeToSqlString();
    }
}