import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AStar {
    public static void solvePuzzle(int[] puzzles) {
        Node oldPuzzle = new Node(puzzles);
        Node newNode;
        System.out.println("Start Puzzle:");
        oldPuzzle.printPuzzle();
        int step = 1;
        newNode = getNewPuzzle(oldPuzzle);
        while (!newNode.isTarget()) {
            newNode = getNewPuzzle(oldPuzzle);
            oldPuzzle = newNode;
            newNode.printCurrentPuzzle(step);
            step++;
        }
        System.out.println("Final Puzzle:");
        newNode.printPuzzle();
    }

    private static Node getNewPuzzle(Node oldPuzzle) {
        int minH = Integer.MAX_VALUE;
        Node newPuzzleMin = null;
        for (int i : getElementsToSwap(oldPuzzle)) {
            Node newPuzzle = new Node(swapZeroWith(oldPuzzle, i));
            newPuzzle.setH(Node.sumDistsFromTarget(newPuzzle)); //меняем функцию
            if (newPuzzle.getH() < minH) {
                minH = newPuzzle.getH();
                newPuzzleMin = newPuzzle;
            }
        }
        return newPuzzleMin;
    }

    private static int[] swapZeroWith(Node oldPuzzle, int k) {
        int placeOfZero = getPlaceOfZero(oldPuzzle);
        int[] newPuzzles = Arrays.copyOf(oldPuzzle.getPuzzles(), oldPuzzle.getPuzzles().length);
        int temp = newPuzzles[placeOfZero];
        newPuzzles[placeOfZero] = newPuzzles[k];
        newPuzzles[k] = temp;
        return newPuzzles;
    }

    private static List<Integer> getElementsToSwap(Node node) {
        int placeOfZero = getPlaceOfZero(node);
        List<Integer> neighboors = null;
        if (placeOfZero == 0) {
            neighboors = new ArrayList<>(List.of(1, 3));
            Collections.shuffle(neighboors);
            return neighboors;
        } else if (placeOfZero == 1) {
            neighboors = new ArrayList<>(List.of(0, 4, 2));
            Collections.shuffle(neighboors);
            return neighboors;
        } else if (placeOfZero == 2) {
            neighboors = new ArrayList<>(List.of(1, 5));
            Collections.shuffle(neighboors);
            return neighboors;
        } else if (placeOfZero == 3) {
            neighboors = new ArrayList<>(List.of(0, 4, 6));
            Collections.shuffle(neighboors);
            return neighboors;
        } else if (placeOfZero == 4) {
            neighboors = new ArrayList<>(List.of(1, 3, 5, 7));
            Collections.shuffle(neighboors);
            return neighboors;
        } else if (placeOfZero == 5) {
            neighboors = new ArrayList<>(List.of(2, 4, 8));
            Collections.shuffle(neighboors);
            return neighboors;
        } else if (placeOfZero == 6) {
            neighboors = new ArrayList<>(List.of(3, 7));
            Collections.shuffle(neighboors);
            return neighboors;
        } else if (placeOfZero == 7) {
            neighboors = new ArrayList<>(List.of(6, 4, 8));
            Collections.shuffle(neighboors);
            return neighboors;
        } else if (placeOfZero == 8) {
            neighboors = new ArrayList<>(List.of(7, 5));
            Collections.shuffle(neighboors);
            return neighboors;
        }
        return neighboors;
    }

    private static int getPlaceOfZero(Node node) {
        for (int i = 0; i < node.getPuzzles().length; i++) {
            if (node.getPuzzles()[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
