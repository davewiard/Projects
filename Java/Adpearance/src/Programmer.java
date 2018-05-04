import java.util.*;

class Programmer {
    // using a HashSet here ensures the language collection contains unique values
    private Collection<String> languages = new HashSet<>();

    Collection<String> getLanguages() {
        return languages;
    }

    void addLanguage(String language) {
       languages.add(language);
    }
}

class ProgrammerTeacher extends Programmer {
    boolean teach(Programmer programmer, String language) {
        // teach must have learned the language already in order to teach it
        if (this.getLanguages().contains(language)) {
            programmer.addLanguage(language);
            return true;
        }

        // can't teach a language you've never learned
        return false;
    }

    public static void main(String[] args) {
        boolean taughtLanguage;

        ProgrammerTeacher teacher = new ProgrammerTeacher();
        teacher.addLanguage("Java");
        teacher.addLanguage("Python");

        Programmer programmer = new Programmer();

        // catch the return value even though we are currently not using it, return values should not be ignored
        taughtLanguage = teacher.teach(programmer, "Java");
        taughtLanguage = teacher.teach(programmer, "Python");

        for(String language : programmer.getLanguages())
            System.out.println(language);
    }
}