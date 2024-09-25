package com.quest.utils;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class IdGeneratorTest {

    @Test
    public void testIfGenerateId() {
        assertEquals(0, IdGenerator.getCurrentId());

        IdGenerator.generateId();
        assertEquals(1, IdGenerator.getCurrentId());
    }

    @Test
    public void testIfSetId() {

        IdGenerator.setGeneratorId(5);
        assertEquals(5, IdGenerator.getCurrentId());
    }
}