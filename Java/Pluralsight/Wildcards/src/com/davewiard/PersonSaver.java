package com.davewiard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class PersonSaver {

    private final RandomAccessFile file;

    public PersonSaver(final File file) throws FileNotFoundException {
        this.file = new RandomAccessFile(file, "rw");
    }

    public Person save(Person person) throws ClassNotFoundException {
        return null;
    }

    public void saveAll(final List<? extends Person> persons) throws IOException, ClassNotFoundException {
        for (Person person : persons) {
            save(person);
        }
    }
}
