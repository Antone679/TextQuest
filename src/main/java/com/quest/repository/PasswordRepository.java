package com.quest.repository;

import java.util.HashMap;
import java.util.Map;

public class PasswordRepository {
    private Map<String, String> users;

    public PasswordRepository() {
        this.users = new HashMap<>();
        users.put("Antone679", "4a14967c35a7b05ff0dbfde587ecd7b1694b21bd1d3ef563bf7a043b47954166");
    }

    public Map<String, String> getUsers() {
        return users;
    }

}
