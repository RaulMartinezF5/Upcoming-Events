package com.Upcoming.Events.demo.models;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void testGetStyle() {
        Event event = new Event();
        event.setStyle("Hola");
        assertEquals("Hola", event.getStyle());
    }



    }

