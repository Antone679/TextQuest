package com.quest.services;

import com.quest.entity.Unit;
import com.quest.repository.UnitRepository;
import com.quest.utils.IdGenerator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface ServletService {
     void setAllAttributes(HttpSession session, List<String> answers, Map<Integer, Unit> questions, Integer counter, String failureDescription);

     List<String> getAnswers(Unit unit);

     void redirectIfWin(HttpServletResponse resp, HttpSession session) throws IOException;

     void incrementCorrectAnswers(HttpSession session);

    public Integer processCounter(HttpSession session);

    public Map<Integer, Unit> initQuestions(ServletContext context, HttpSession session);


    public boolean checkIfAnswerIsIncorrect(HttpServletResponse resp, Boolean isCorrect) throws IOException;

}
