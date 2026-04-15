package edu.sdccd.cisc191;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Module 5 Lab: Recursion + Algorithms
 *
 * Reflection Questions:
 * 1. What is the base case for your recursive binary search?
 * ///// The base case is when the lower bound is greater than the right bound, meaning that there are no more accessible indexes in the range
 * 2. Why is recursion natural for the bracket tree?
 * ///// Recursion is natural for the bracket tree because the pattern of the bracket tree is recursive/repeating in on itself like a fractal
 * 3. Why might the iterative tile-counting method be safer on a very large map?
 * ///// Iteration based tile-counting would be safer on a very large map because it would prevent stack overflow caused by too many method calls
 * 4. Which problems in this lab felt more natural with loops, and which felt more natural with recursion?
 * ///// Finding the match ID felt more natural with loops, while the tile problem and bracket tree felt best with recursion
 */
public class GameAlgorithms {

    /**
     * Searches a sorted array of match IDs recursively.
     *
     * @param sortedMatchIds sorted in ascending order
     * @param target the match ID to find
     * @return index of target, or -1 if not found
     */
    public static int findMatchRecursive(int[] sortedMatchIds, int target) {
        return findMatchRecursiveHelper(sortedMatchIds, target, 0, sortedMatchIds.length - 1);
    }

    /**
     * Helper method for recursive binary search.
     *
     * @param sortedMatchIds sorted in ascending order
     * @param target the match ID to find
     * @param low starting index of the current search range
     * @param high ending index of the current search range
     * @return index of target, or -1 if not found
     */
    private static int findMatchRecursiveHelper(int[] sortedMatchIds, int target, int low, int high) {
        if (low > high) { // base case
            return -1;
        }

        int mid = low + (high - low) / 2; // determine midpoint
        if (sortedMatchIds[mid] == target) {
            return mid; // found match ID
        } else if (target < sortedMatchIds[mid]) { // "shift" right bound and self-call
            return findMatchRecursiveHelper(sortedMatchIds, target, low, mid - 1);
        } else { // (target > sortedMatchIds[mid]), "shift left bound and self-call
            return findMatchRecursiveHelper(sortedMatchIds, target, mid + 1, high);
        }
    }

    /**
     * Searches a sorted array of match IDs iteratively.
     *
     * @param sortedMatchIds sorted in ascending order
     * @param target the match ID to find
     * @return index of target, or -1 if not found
     */
    public static int findMatchIterative(int[] sortedMatchIds, int target) {
        int leftBound = 0;
        int rightBound = sortedMatchIds.length - 1;

        while (leftBound <= rightBound) { // loops until "base case" true
            int midpoint = leftBound + (rightBound - leftBound) / 2; // prevent overflow

            if (target == sortedMatchIds[midpoint]) { // check midpoint
                return midpoint;
            } else if (target < sortedMatchIds[midpoint]) { // shift right bound to past midpoint
                rightBound = midpoint - 1;
            } else if (target > sortedMatchIds[midpoint]) { // shift left bound to past midpoint
                leftBound = midpoint + 1;
            }
        }

        return -1;
    }

    /**
     * Counts connected walkable tiles recursively.
     * Walkable tiles are represented by '.'.
     * Blocked tiles can be any other character.
     *
     * This method should count the size of the connected region starting at (startRow, startCol).
     * Count only vertical and horizontal neighbors, not diagonals.
     *
     * @param map mutable map of tiles
     * @param startRow starting row
     * @param startCol starting column
     * @return number of connected walkable tiles
     */
    public static int countConnectedTilesRecursive(char[][] map, int startRow, int startCol) {
        if (isOutOfBounds(map, startRow, startCol) || map[startRow][startCol] != '.') { // Base case
            return 0;
        }

        map[startRow][startCol] = '!'; // mark as visited to ensure no repeated counts
        int count = 1; // count the current tile marked

        /**
         * Remember that a 2D array starts from the top left, rows = top to bottom, columns = left to right
         *    0  1  2
         * 0  -  X  X
         * 1  -  X  X
         * 2  X  -  X
         */

        // The goal is to check the nearby elements and basically expand outward, increasing for each valid tile encountered
        count += countConnectedTilesRecursive(map, startRow - 1, startCol); // Check upwards and do the rest recursively
        count += countConnectedTilesRecursive(map, startRow + 1, startCol); // Check down
        count += countConnectedTilesRecursive(map, startRow, startCol - 1); // Check to the left
        count += countConnectedTilesRecursive(map, startRow, startCol + 1); // Check to the right

        /**
         * TLDR: Each step evaluates if its tile is valid
         * Then proceeds to create more of itself onto neighbouring tiles
         * Until the full area is marked as valid
         * (will return 0 neighbouring tiles and DOES NOT MAKE MORE RECURSION CALLS)
         */

        return count;
    }

    /**
     * Counts connected walkable tiles iteratively using an explicit stack.
     *
     * @param map mutable map of tiles
     * @param startRow starting row
     * @param startCol starting column
     * @return number of connected walkable tiles
     */
    public static int countConnectedTilesIterative(char[][] map, int startRow, int startCol) {
        return -999;
    }

    /**
     * Returns true if the tournament bracket contains a match with the given target name.
     * This public method should call a recursive helper.
     *
     * @param root root of the bracket tree
     * @param target match name to search for
     * @return true if found, false otherwise
     */
    public static boolean containsMatch(BracketNode root, String target) {
        return containsMatchHelper(root, target);
    }

    /**
     * Helper method for recursive bracket tree search.
     *
     * @param node current node
     * @param target match name to search for
     * @return true if found, false otherwise
     */
    private static boolean containsMatchHelper(BracketNode node, String target) {
        if (node == null) { // End of tree branch (no node found), stop recursion
            return false;
        }

        if (node.getMatchName().equals(target)) { // Check current node
            return true;
        } else {
            return containsMatchHelper(node.getLeft(), target) || containsMatchHelper(node.getRight(), target);
            // Will try recursion down the left subtree first and then do the right if no match found
        }
    }

    /**
     * Optional utility students may use if they want to avoid repeating bounds checks.
     */
    public static boolean isOutOfBounds(char[][] map, int row, int col) {
        return row < 0 || row >= map.length || col < 0 || col >= map[row].length;
    }

    /**
     * Optional utility students may use in the iterative flood-fill.
     */
    public static void pushNeighbor(Deque<CellPosition> stack, int row, int col) {
        stack.push(new CellPosition(row, col));
    }
}
