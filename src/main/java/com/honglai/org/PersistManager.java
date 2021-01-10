package com.honglai.org;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PersistManager {
    public void insert(Persistable newItem) {
        try (FileWriter fileWriter = new FileWriter("D:/workspace/data.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(newItem.getPersistString() + "\n");
        } catch (IOException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    public void update(Integer rowNumber, Persistable item) {

    }
}
