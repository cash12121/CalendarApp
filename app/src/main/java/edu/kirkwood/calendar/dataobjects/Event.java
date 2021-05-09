package edu.kirkwood.calendar.dataobjects;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event implements Serializable {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String title, String desc, LocalDateTime startTime, LocalDateTime endTime) {
        setTitle(title);
        setDescription(desc);
        setStartTime(startTime);
        setEndTime(endTime);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getEventDate() {
        LocalDate date;

        date = startTime.toLocalDate();

        return date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
