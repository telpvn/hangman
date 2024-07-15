import java.util.ArrayList;
import java.util.List;

// Объявление класса `GameState` (Этот класс содержит состояние игры, включая загаданное слово, маску слова (скрытые буквы), использованные буквы и количество ошибок)
public class GameState {
    private final String word; // Поле для хранения загаданного слова
    private final StringBuilder wordMask; // Поле для хранения маски слова (скрытые буквы)
    private final List<Character> usedLetters; // Поле для хранения списка использованных букв
    private int mistakes; // Поле для хранения количества ошибок

    // Конструктор, принимающий загаданное слово и инициализирующий остальные поля
    public GameState(String word) {
        this.word = word; // Инициализация загаданного слова
        this.wordMask = new StringBuilder("_".repeat(word.length())); // Инициализация маски слова с таким же количеством символов, как и в загаданном слове
        this.usedLetters = new ArrayList<>(); // Инициализация списка использованных букв
        this.mistakes = 0; // Инициализация количества ошибок
    }

    // Метод для получения загаданного слова
    public String getWord() {
        return word;
    }

    // Метод для получения маски слова
    public StringBuilder getWordMask() {
        return wordMask;
    }

    // Метод для получения списка использованных букв
    public List<Character> getUsedLetters() {
        return usedLetters;
    }

    // Метод для получения количества ошибок
    public int getMistakes() {
        return mistakes;
    }

    // Метод для установки количества ошибок
    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    // Метод для получения максимального количества допустимых ошибок
    public int getMaxMistakes() {
        return 6; // Максимальное количество ошибок - 6
    }
}
