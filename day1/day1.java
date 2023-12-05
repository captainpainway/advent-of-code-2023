import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;
import java.io.IOException;

class Day1 {
    public static void main(String... args) {
        Path file = Paths.get("./input.txt");
        List<String> fileArray;
        try {
            fileArray = Files.readAllLines(file);
            part1(fileArray);
            part2(fileArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void part1(List<String> fileArray) {
        Integer sum = 0;
        for (String line : fileArray) {
            Integer start = 0;
            Integer end = line.length() - 1;
            String first = null;
            String last = null;
            while (first == null || last == null) {
                if (Character.isDigit(line.charAt(start))) {
                    first = new StringBuilder().append(line.charAt(start)).toString();
                } else {
                    start++;
                }
                if (Character.isDigit(line.charAt(end))) {
                    last = new StringBuilder().append(line.charAt(end)).toString();
                } else {
                    end--;
                }
            }
            int num = Integer.parseInt(first + last);
            sum += num;
        }
        System.out.println(sum);
    }

    private static Map<String, String> numbers() {
        Map<String, String> numbers = new HashMap<>();
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six", "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");
        return numbers;
    }

    private static void part2(List<String> fileArray) {
        Map<String, String> numbers = numbers();
        Set<String> numSet = numbers.keySet();
        int sum = 0;
        for (String line : fileArray) {
            Integer start = 0;
            Integer end = line.length() - 1;
            String first = null;
            String last = null;
            while (first == null || last == null) {
                if (first == null) {
                    if (Character.isDigit(line.charAt(start))) {
                        first = new StringBuilder().append(line.charAt(start)).toString();
                    } else {
                        for (String n : numSet) {
                            if (line.substring(start).indexOf(n) == 0) {
                                first = numbers.get(n);
                            }
                        }
                        start++;
                    }
                }
                if (last == null) {
                    if (Character.isDigit(line.charAt(end))) {
                        last = new StringBuilder().append(line.charAt(end)).toString();
                    } else {
                        for (String n : numSet) {
                            if (line.substring(end).indexOf(n) == 0) {
                                last = numbers.get(n);
                            }
                        }
                        end--;
                    }
                }
            }
            int num = Integer.parseInt(first + last);
            sum += num;
        }
        System.out.println(sum);
    }
}