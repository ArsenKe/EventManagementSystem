package com.ase.recommendation_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CustomReply {

    List<Event>     events;
    List<Feedback>  feedbacks;
}
