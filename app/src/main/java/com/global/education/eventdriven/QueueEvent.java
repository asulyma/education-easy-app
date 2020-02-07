package com.global.education.eventdriven;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QueueEvent {

    private Object o;
    private EventType type;
}
