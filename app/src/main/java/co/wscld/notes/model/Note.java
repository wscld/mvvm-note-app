package co.wscld.notes.model;

import android.text.format.DateUtils;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import co.wscld.notes.BR;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {
    @PrimaryKey
    private  int id;
    private  String title;
    private String text;
    private long date;

    public Note(){

    }

    public Note(String title, String text){
        this.text = text;
        this.title = title;
        this.date = new Date().getTime();
        this.id = (int) UUID.randomUUID().getMostSignificantBits();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String note) {
        this.text = note;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateFormatted(){
        return DateUtils.getRelativeTimeSpanString(this.date, Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS).toString();
    }
}
