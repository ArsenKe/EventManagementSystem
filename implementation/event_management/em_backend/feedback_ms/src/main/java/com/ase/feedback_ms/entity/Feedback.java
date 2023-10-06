package com.ase.feedback_ms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table(name = "Feedback")
@Entity
@Data
@Setter
@Getter
public class Feedback {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long feedbackId;
    private Long eventId;
    private String content_review;
    private int suitable_location;
    private int description_accuracy;
    private int rate;

    public Feedback() {}

    public Feedback(Long eventId, String content_review, int suitable_location, int description_accuracy, int rate) {
        this.eventId = eventId;
        this.content_review = content_review;
        this.suitable_location = suitable_location;
        this.description_accuracy = description_accuracy;
        this.rate = rate;
    }

    public Feedback(Long feedbackId, Long eventId, String content_review, int suitable_location, int description_accuracy, int rate) {
        this.feedbackId = feedbackId;
        this.eventId = eventId;
        this.content_review = content_review;
        this.suitable_location = suitable_location;
        this.description_accuracy = description_accuracy;
        this.rate = rate;
    }

}





