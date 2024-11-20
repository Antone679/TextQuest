package com.quest.repository;

import com.quest.entity.Unit;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.Map;

import static org.junit.Assert.*;

public class UnitRepositoryTest {

    private UnitRepository repository;

    @Before
    public void setUp() throws IOException {
        File tempFile = File.createTempFile("questions", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("question0;answer1;answer2;desc\n");
            writer.write("question1;answer1;answer2;desc\n");
        }
        UnitRepository.setFileWithQuestions(tempFile.toPath());
        repository = new UnitRepository();

    }

    @Test
    public void testInitQuestions() throws IOException {
        repository.initQuestions();
        Map<Integer, Unit> questions = repository.getQuestions();

        assertEquals(2, questions.size());
    }

//    @Test
//    public void testInitOneQuestion() throws IOException {
//        //проверяем именно работу метода init построчно
//
//        String validLine = "question2;answer1;answer2;desc";
//        String invalidLine = "question3;answer1;answer2";
//
//        repository.init(validLine);
//        repository.init(invalidLine);
//
//        Map<Integer, Unit> questions = repository.getQuestions();
//
//        assertEquals(3, questions.size());
//
//        //проверка верной инициализации вопросов
//        Unit testUnit = questions.get(2);
//        assertEquals("question2", testUnit.getQuestion());
//        assertEquals("answer1", testUnit.getCorrectAnswer());
//        assertEquals("answer2", testUnit.getWrongAnswer());
//        assertEquals("desc", testUnit.getFailureDescription());
//
//    }
}