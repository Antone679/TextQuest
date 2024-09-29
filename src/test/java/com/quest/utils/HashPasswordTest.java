package com.quest.utils;

import com.quest.exceptions.HashPasswordException;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashPasswordTest {
@Test
public void testIfThrowsExceptionOnNullPassword(){
    Exception exception = assertThrows(HashPasswordException.class, () -> {
        HashPassword.hashPassword(null);
    });
    String expectedMessage = "Не удалось захешировать пароль.";
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
}
    @Test
    public void testIfHashPasswordCorrectly(){
    String password = "Antone679";
    String expectedHash = "4a14967c35a7b05ff0dbfde587ecd7b1694b21bd1d3ef563bf7a043b47954166";

    String actualHash = HashPassword.hashPassword(password);

    assertEquals(expectedHash, actualHash);
    }
}