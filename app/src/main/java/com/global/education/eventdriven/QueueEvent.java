package com.global.education.eventdriven;

import com.education.common.dto.event.EventType;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class QueueEvent {

    private Object eventDto;
    private EventType type;
}
