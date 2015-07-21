package com.tw.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule {

    private int id;
    private String time;
    private Course course;

    public Schedule() {
    }

    public Schedule(String time, Course course) {

        this.time = time;
        this.course = course;
    }

    public Schedule(int id, String time, Course course) {
        this.id = id;
        this.time = time;
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Column(name = "time")
    public String getTime() {
        return time;
    }

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "course_id")
    public Course getCourse() {
        return course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
