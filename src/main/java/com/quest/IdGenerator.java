package com.quest;

public class IdGenerator {
    private static int newId;

    public static int generateId(){
        return newId++;
    }
    public static void setGeneratorId(int count){
        newId = count;
    }

    public static int getCurrentId() {
        return newId;
    }
}
