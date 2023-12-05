import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;
import java.io.IOException;

class Day2 {
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("./input.txt");
        List<String> fileArray;
        fileArray = Files.readAllLines(file);
        part1(fileArray);
        part2(fileArray);
    }

    private static void part1(List<String> fileArray) {
        Map<String, Integer> amounts = new HashMap<>();
        amounts.put("red", 12);
        amounts.put("green", 13);
        amounts.put("blue", 14);

        int sum = 0;
        for (String line : fileArray) {
            String[] parts = line.split(": ");
            int game = Integer.parseInt(parts[0].replaceAll("Game ", ""));
            String[] cubes = parts[1].split("; ");
            boolean possible = true;
            for (String cube : cubes) {
                String[] c = cube.split(", ");
                for (String cc : c) {
                    String[] amt = cc.split(" ");
                    possible &= amounts.get(amt[1]) >= Integer.parseInt(amt[0]);
                }
            }
            if (possible) {
                sum += game;
            }
        }
        System.out.println(sum);
    }

    private static void part2(List<String> fileArray) {
        int sum = 0;
        for (String line : fileArray) {
            int redMax = 0;
            int greenMax = 0;
            int blueMax = 0;
            String[] parts = line.split(": ");
            String[] cubes = parts[1].split("; ");
            for (String cube : cubes) {
                String[] c = cube.split(", ");
                for (String cc : c) {
                    String[] amt = cc.split(" ");
                    switch(amt[1]) {
                        case "red":
                            redMax = Math.max(redMax, Integer.parseInt(amt[0]));
                            break;
                        case "green":
                            greenMax = Math.max(greenMax, Integer.parseInt(amt[0]));
                            break;
                        case "blue":
                            blueMax = Math.max(blueMax, Integer.parseInt(amt[0]));
                            break;
                    }
                }
            }
            sum += redMax * greenMax * blueMax;
        }
        System.out.println(sum);
    }
}