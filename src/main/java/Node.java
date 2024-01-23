import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Node {
    private final int[] puzzles;
    private int h;

    public int[] getPuzzles() {
        return puzzles;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getH() {
        return h;
    }

    public Node(int[] puzzles) {
        this.puzzles = puzzles;
    }

    boolean isTarget() {
        int[] targetPuzzle = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        for (int counter = 0; counter < 9; counter++) {
            if (targetPuzzle[counter] != puzzles[counter]) {
                return false;
            }
        }
        return true;
    }

    void printPuzzle() {
        AtomicInteger counter = new AtomicInteger(0);
        Arrays.stream(puzzles).forEach(el -> {
            counter.getAndIncrement();
            if (counter.get() % 3 == 0) {
                System.out.printf("%d\n", el);
            } else {
                System.out.printf("%d\t", el);
            }
        });
    }

    public void printCurrentPuzzle(int step) {
        System.out.println("Step: " + step);
        AtomicInteger counter = new AtomicInteger(0);
        Arrays.stream(puzzles).forEach(el -> {
            counter.getAndIncrement();
            if (counter.get() % 3 == 0) {
                System.out.printf("%d\n", el);
            } else {
                System.out.printf("%d\t", el);
            }
        });
    }
    //h1
    public static int getDistFromTarget(Node node) {
        int sum = 0;
        for (int k = 0; k < 9; k++) {
            if (node.getPuzzles()[k] != 0) {
                if (node.getPuzzles()[k] != k + 1) {
                    sum++;
                }
            }
        }
        return sum;
    }
    //h2
    public static int sumDistsFromTarget (Node node) {
        int result = 0;
        int[][] helpPuzzle = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                helpPuzzle[i][j] = node.getPuzzles()[3 * i + j];
                if (helpPuzzle[i][j] != 0) {
                    result += getDistElToTarget(helpPuzzle[i][j], i, j);
                }
            }
        }
        return result;
    }
    public static int getDistElToTarget (int element, int i, int j) {
        int[][] matrixDist;
        if (element == 0) {
            matrixDist = new int[][]{{4, 3, 2},
                                    {3, 2, 1},
                                    {2, 1, 0}};
            return matrixDist[i][j];
        } else if (element == 1) {
            matrixDist = new int[][]{{0, 1, 2},
                                    {1, 2, 3},
                                    {2, 3, 4}};
            return matrixDist[i][j];
        } else if (element == 2) {
            matrixDist = new int[][]{{1, 0, 1},
                                    {2, 1, 2},
                                    {3, 2, 3}};
            return matrixDist[i][j];
        } else if (element == 3) {
            matrixDist = new int[][]{{2, 1, 0},
                                    {3, 2, 1},
                                    {4, 3, 2}};
            return matrixDist[i][j];
        } else if (element == 4) {
            matrixDist = new int[][]{{1, 2, 3},
                                    {0, 1, 2},
                                    {1, 2, 3}};
            return matrixDist[i][j];
        } else if (element == 5) {
            matrixDist = new int[][]{{2, 1, 2},
                                    {1, 0, 1},
                                    {2, 1, 2}};
            return matrixDist[i][j];
        } else if (element == 6) {
            matrixDist = new int[][]{{3, 2, 1},
                                    {2, 1, 0},
                                    {3, 2, 1}};
            return matrixDist[i][j];
        } else if (element == 7) {
            matrixDist = new int[][]{{2, 3, 4},
                                    {1, 2, 3},
                                    {0, 1, 2}};
            return matrixDist[i][j];
        } else if (element == 8) {
            matrixDist = new int[][]{{3, 2, 3},
                                    {2, 1, 2},
                                    {1, 0, 1}};
            return matrixDist[i][j];
        }
        return 0;
    }
}
