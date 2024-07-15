import java.util.*;

// Объявление класса `HangmanGame`, представляющего игру "Виселица"
public class HangmanGame {
    private GameState gameState; // Поле для хранения состояния игры
    private static final String[] DICTIONARY = {"example", "hangman", "java", "programming"}; // Словарь возможных слов для игры

    // Конструктор, который инициализирует игру с выбранным случайным словом
    public HangmanGame() {
        String word = chooseWord(); // Выбор случайного слова
        this.gameState = new GameState(word); // Инициализация состояния игры с выбранным словом
    }

    // Метод для выбора случайного слова из словаря
    private String chooseWord() {
        Random random = new Random();
        return DICTIONARY[random.nextInt(DICTIONARY.length)];
    }

    // Метод для начала игры
    public void start() {
        Scanner scanner = new Scanner(System.in);

        do {
            // Цикл, который продолжается, пока игра не закончится
            while (isGameContinues()) {
                displayGameState(); // Отображение текущего состояния игры
                System.out.print("Введите букву: ");
                char guessedLetter = scanner.next().toLowerCase().charAt(0); // Ввод буквы и перевод её в нижний регистр

                // Проверка, использовалась ли уже эта буква
                if (!gameState.getUsedLetters().contains(guessedLetter)) {
                    gameState.getUsedLetters().add(guessedLetter); // Добавление буквы в список использованных
                    if (gameState.getWord().contains(String.valueOf(guessedLetter))) {
                        updateWordMask(guessedLetter); // Обновление маски слова, если буква угадана
                    } else {
                        gameState.setMistakes(gameState.getMistakes() + 1); // Увеличение количества ошибок, если буква не угадана
                    }
                } else {
                    System.out.println("Вы уже использовали эту букву.");
                }
            }

            // Проверка, выиграна ли игра
            if (isGameWon()) {
                System.out.println("Поздравляем, вы выиграли! Загаданное слово: " + gameState.getWord());
            } else {
                displayHangman(); // Отображение финального состояния виселицы
                System.out.println("К сожалению, вы проиграли. Загаданное слово: " + gameState.getWord());
            }
        } while (restartGame(scanner));
    }

    private boolean restartGame(Scanner scanner) {
        System.out.println("Если хотите начать игру заново, введите 'yes'");
        String str = scanner.next();
        if ("yes".equals(str)) {
            gameState = new GameState(chooseWord());
            return true;
        }
        return false;
    }

    // Метод для отображения текущего состояния игры
    private void displayGameState() {
        displayHangman(); // Отображение текущего состояния виселицы
        System.out.println("Слово: " + gameState.getWordMask()); // Отображение маски слова
        System.out.println("Ошибки: " + gameState.getMistakes() + "/" + gameState.getMaxMistakes()); // Отображение количества ошибок
        System.out.println("Использованные буквы: " + gameState.getUsedLetters()); // Отображение списка использованных букв
    }

    // Метод для отображения текущего состояния виселицы
    private void displayHangman() {
        switch (gameState.getMistakes()) {
            case 0:
                System.out.println("""
                        +---+
                        |   |
                            |
                            |
                            |
                            |
                        =========
                        """);
                break;
            case 1:
                System.out.println("""
                        +---+
                        |   |
                        O   |
                            |
                            |
                            |
                        =========
                        """);
                break;
            case 2:
                System.out.println("""
                        +---+
                        |   |
                        O   |
                        |   |
                            |
                            |
                        =========
                        """);
                break;
            case 3:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                        /|   |
                             |
                             |
                         =========
                        """);
                break;
            case 4:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                        /|\\  |
                             |
                             |
                         =========
                        """);
                break;
            case 5:
                System.out.println("""
                         +---+
                         |   |
                         O   |
                        /|\\  |
                        /    |
                             |
                         =========
                        """);
                break;
            default: // Для случая 6 и более ошибок
                System.out.println("""
                         +---+
                         |   |
                         O   |
                        /|\\  |
                        / \\  |
                             |
                         =========
                        """);
                break;
        }
    }

    // Метод для обновления маски слова, если угадана буква
    private void updateWordMask(char guessedLetter) {
        for (int i = 0; i < gameState.getWord().length(); i++) {
            if (gameState.getWord().charAt(i) == guessedLetter) {
                gameState.getWordMask().setCharAt(i, guessedLetter);
            }
        }
    }

    // Метод для проверки, продолжается ли игра
    private boolean isGameContinues() {
        return gameState.getMistakes() < gameState.getMaxMistakes() && gameState.getWordMask().toString().contains("_");
    }

    // Метод для проверки, выиграна ли игра
    private boolean isGameWon() {
        return !gameState.getWordMask().toString().contains("_");
    }
}
