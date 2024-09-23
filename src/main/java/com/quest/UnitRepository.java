package com.quest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UnitRepository implements Serializable {
    private static Path FILE_WITH_QUESTIONS = Paths.get("/WEB-INF/resources/questions.txt");
    private static final long serialVersionUID = 1L;
    private Map<Integer, Unit> questions;

    public UnitRepository() {
        initQuestions();
    }

    public static void setFileWithQuestions(Path fileWithQuestions) {
        FILE_WITH_QUESTIONS = fileWithQuestions;
    }

    private void initQuestions() {
        this.questions = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_WITH_QUESTIONS.toFile()),
                StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                init(line);
            }

        } catch (IOException ex) {
            throw new QuestInitializerException("Не удалось инициализировать вопросы для квестов.");
        }
    }

    private void init(String line) {
        Unit unit;
        String[] data = line.split(";");

        if (data.length != 4) {
            return;
        }
        unit = new Unit();
        unit.setId(IdGenerator.generateId());
        unit.setQuestion(data[0]);
        unit.setCorrectAnswer(data[1]);
        unit.setWrongAnswer(data[2]);
        unit.setFailureDescription(data[3]);

        questions.put(unit.getId(), unit);
    }

    public Map<Integer, Unit> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Integer, Unit> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "UnitRepository{" +
                "questions=" + questions +
                '}';
    }
}
