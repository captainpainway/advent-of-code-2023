import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;

class Day3 {
    public static void main(String[] args) {
        Path file = Paths.get("./input.txt");
        try {
            List<String> fileArray = Files.readAllLines(file);
            List<List<String>> matrix = fileArray.stream()
                .map(line -> Arrays.asList(line.split("")))
                .collect(Collectors.toList());
            part1(matrix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isNum(String str) {
        if (str == null) return false;
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void part1(List<List<String>> matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.size(); i++) {
            String currNum = "";
            boolean isPartNumber = false;
            for (int j = 0; j < matrix.get(i).size(); j++) {
                String curr = matrix.get(i).get(j);

                int top = i - 1 > 0 ? i - 1 : 0;
                int bottom = i + 1 < matrix.size() - 1 ? i + 1 : matrix.size() - 1;
                int left = j - 1 > 0 ? j - 1 : 0;
                int right = j + 1 < matrix.get(i).size() - 1 ? j + 1 : matrix.get(i).size() - 1;

                String tl = matrix.get(top).get(left);
                String tc = matrix.get(top).get(j);
                String tr = matrix.get(top).get(right);
                String ml = matrix.get(i).get(left);
                String mr = matrix.get(i).get(right);
                String bl = matrix.get(bottom).get(left);
                String bc = matrix.get(bottom).get(j);
                String br = matrix.get(bottom).get(right);

                // Check for symbol in the vicinity of a number.
                if (isNum(curr) && 
                ((!isNum(tl) && !".".equals(tl)) || (!isNum(tc) && !".".equals(tc)) || (!isNum(tr) && !".".equals(tr)) ||
                (!isNum(ml) && !".".equals(ml)) || (!isNum(mr) && !".".equals(mr)) ||
                (!isNum(bl) && !".".equals(bl)) || (!isNum(bc) && !".".equals(bc)) || (!isNum(br) && !".".equals(br)))) {
                    isPartNumber = true;
                }

                if (isNum(curr)) {
                    currNum += curr;
                    // End of line
                    if (currNum != "" && isPartNumber && j == matrix.get(i).size() - 1) {
                        sum += Integer.parseInt(currNum);
                    }
                } else {
                    if (currNum != "" && isPartNumber) {
                        sum += Integer.parseInt(currNum);
                    }
                    isPartNumber = false;
                    currNum = "";
                }
            }
            currNum = "";
        }
        System.out.println(sum);
    }
}