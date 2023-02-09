package JA_home5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class home5 {
    private static final String TASK_MENU = "Выберите нужный пункт меню: "
            + "\n\t1 - Задача 1"
            + "\n\t2 - Задача 2"
            + "\n\t3 - Задача 3"
            + "\n\t0 - Выход";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(TASK_MENU);
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    task1();
                    break;
                case 2:
                    task2();
                    break;
                case 3:
                    task3();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Такого действия не существует!");
                    break;
            }
        }
    }

    public static void task1() {
        // Реализуйте структуру телефонной книги
        // с помощью HashMap, учитывая, что 1 человек
        // может иметь несколько телефонов.

        Map<String, List<String>> book = new HashMap<>();
        book.put("Ivan", List.of("8 927 555 77 77", "8 965 425 85 65"));
        book.put("Alex", List.of("8 937 856 85 56", "8 917 456 54 58"));
        book.put("Max", List.of("8 954 247 55 38", "8 927 852 21 23"));

        menu(book);
    }

    public static String scan() {
        Scanner scanner = new Scanner(System.in);
        String scan = scanner.nextLine();
        return scan;
    }

    public static void find(Map<String, List<String>> phoneBook)
    // Поиск абонента
    {
        System.out.print("Имя абонента: ");
        String name = scan();
        System.out.println("Имя: " + name + "\nТелефон(ы): " + phoneBook.get(name));
    }

    public static void allBook(Map<String, List<String>> AllBook)
    // Печать книги
    {
        for (var item : AllBook.entrySet()) {
            System.out.println("Имя: " + item.getKey() + "\nНомер" + item.getValue());
        }
    }

    public static Map<String, List<String>> add(Map<String, List<String>> book)
    // Добавление в тел. книгу
    {
        System.out.print("Имя абонента: ");
        String name = scan();
        List<String> data = new ArrayList<>();
        while (true) {
            System.out.println("Если номеров больше нет, введите '5'");
            System.out.print("Введите номер: ");
            String nomer = scan();
            if (nomer.equals("5")) {
                break;
            } else {
                data.add(nomer);
            }
        }
        book.put(name, data);

        return book;
    }

    public static Map<String, List<String>> menu(Map<String, List<String>> book) {
        while (true) {
            System.out.println("    --------------\n   Телефонная Книга\n    --------------\n" +
                    "        Меню: \n1. Найти контакт \n2. Добавить контакт" +
                    " \n3. Печать телефонной книги \n4. Выход");
            System.out.print("\nвыберите пункт меню-> ");
            String comands = scan();
            if (comands.equals("4")) {
                break;
            } else {
                switch (comands) {
                    case "1":
                        find(book);
                        break;
                    case "2":
                        add(book);
                        break;
                    case "3":
                        allBook(book);
                        break;
                    default:
                        break;
                }
            }
        }
        return book;
    }

    public static void task2() {

        // Написать программу, которая найдет и выведет повторяющиеся имена с
        // количеством повторений. Отсортировать по убыванию популярности Имени.

        Map<String, Integer> listWorkers = new HashMap<>();
        String workers = "Иван Иванов Светлана Петрова Кристина Белова Анна Мусина Анна Крутова " +
                "Иван Юрин Петр Лыков Павел Чернов Петр Чернышов Мария Федорова " +
                "Марина Светлова Мария Савина Мария Рыкова Марина Лугова " +
                "Анна Владимирова Иван Мечников Петр Петин Иван Ежов ";

        String[] NamesSurnames = workers.split(" ");
        for (int i = 0; i < NamesSurnames.length; i += 2) {
            if (listWorkers.containsKey(NamesSurnames[i])) {
                listWorkers.replace(NamesSurnames[i], listWorkers.get(NamesSurnames[i]) + 1);
            } else {
                listWorkers.put(NamesSurnames[i], 1);
            }
        }
        System.out.println("\nСписок имен:");
        System.out.println(listWorkers);

        System.out.println("\nСписок имен по популярности:");
        listWorkers.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
    }

    public static void task3() {
        Board board = new Board(8);
        nextTurn(board);
    }

    // На шахматной доске расставить 8 ферзей так,
    // чтобы они не били друг друга. И вывести Доску. 0x00000 0000x00 00x0000
    public static class queen // класс ферзь
    {
        int x, y;
        static int count = 0;

        public queen(Board board) {
            while (true) {
                Random rnd = new Random();
                int x = rnd.nextInt(8);
                int y = rnd.nextInt(8);
                if (board.cell[x][y] == 0) {
                    this.x = x;
                    this.y = y;
                    count++;
                    break;
                }
            }
        }
    }

    public static class Board // Класс Board (доска)
    {
        int size;
        int[][] cell;

        public Board(int size) {
            this.size = size;
            this.cell = new int[this.size][this.size];
        }

        public void addQueen(queen queen, int index) // добавить королеву
        {
            this.cell[queen.x][queen.y] = index + 2;
            for (int i = 1; i < 8; i++) {
                int x = queen.x;
                int y = queen.y;

                if ((x + i) < 8 && (y + i) < 8 && (x + i) >= 0 && (y + i) >= 0 && this.cell[x + i][y + i] == 0) {
                    this.cell[x + i][y + i] = 1;
                }
                if ((x + i) < 8 && (y - i) < 8 && (x + i) >= 0 && (y - i) >= 0 && this.cell[x + i][y - i] == 0) {
                    this.cell[x + i][y - i] = 1;
                }
                if ((y + i) < 8 && (y + i) >= 0 && this.cell[x][y + i] == 0) {
                    this.cell[x][y + i] = 1;
                }
                if ((y - i) < 8 && (y - i) >= 0 && this.cell[x][y - i] == 0) {
                    this.cell[x][y - i] = 1;
                }
                if ((x - i) < 8 && (y + i) < 8 && (x - i) >= 0 && (y + i) >= 0 && this.cell[x - i][y + i] == 0) {
                    this.cell[x - i][y + i] = 1;
                }
                if ((x - i) < 8 && (y - i) < 8 && (x - i) >= 0 && (y - i) >= 0 && this.cell[x - i][y - i] == 0) {
                    this.cell[x - i][y - i] = 1;
                }
                if ((x + i) < 8 && (x + i) >= 0 && this.cell[x + i][y] == 0) {
                    this.cell[x + i][y] = 1;
                }
                if ((x - i) < 8 && (x - i) >= 0 && this.cell[x - i][y] == 0) {
                    this.cell[x - i][y] = 1;
                }
            }
        }

        public boolean checkBoard() // Проверить Доску
        {
            for (int[] row : this.cell) {
                for (int elem : row) {
                    if (elem == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void print() // метод печати
        {
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    if (this.cell[j][i] == 0 || this.cell[j][i] == 1) {
                        System.out.print(" 0 ");
                    } else {
                        System.out.print(" X ");
                        // System.out.print(" " + (this.cell[j][i] - 1) + " ");
                        // если хотим вывести их по очереди
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void nextTurn(Board board) // следующий ход
    {
        queen[] queens = new queen[8];
        for (int i = 0; i < 8; i++) {
            if (board.checkBoard()) {
                board = new Board(8);
                nextTurn(board);
                return;
            }
            queens[i] = new queen(board);
            board.addQueen(queens[i], i);
            // board.print(); для поэтапного вывода расстановки ферзя
        }
        board.print();
    }

}
