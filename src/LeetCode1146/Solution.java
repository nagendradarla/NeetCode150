package LeetCode1146;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * LeetCode 1146: Snapshot Array
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Binary Search
 *
 * Problem:
 * Implement a SnapshotArray that supports the following interface:
 *
 * - SnapshotArray(int length) initializes an array-like data structure with the given length. Initially, each element equals 0.
 *
 * - void set(index, val) sets the element at the given index to be equal to val.
 *
 * - int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 *
 * - int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 * Example 1:
 *
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5); // Set array[0] = 5
 * snapshotArr.snap(); // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0); // Get the value of array[0] with snap_id = 0, return 5
 *
 * Constraints:
 *
 * - 1 <= length <= 5 * 10^4
 *
 * - 0 <= index < length
 *
 * - 0 <= val <= 10^9
 *
 * - 0 <= snap_id < (the total number of times we call snap())
 *
 * - At most 5 * 10^4 calls will be made to set, snap, and get.
 *
 * Link: https://leetcode.com/problems/snapshot-array/
 */
class SnapshotArray {
    int snap_id = 0;
    List<int[]>[] array = null;

    public SnapshotArray(int length) {
        array = new ArrayList[length];
    }

    public void set(int index, int val) {
        List<int[]> value = array[index];
        if (value == null) {
            value = new ArrayList<int[]>();
            array[index] = value;
        }
        value.add(new int[] { snap_id, val });
    }

    public int snap() {
        return snap_id++;
    }

    public int get(int index, int snap_id) {
        List<int[]> value = array[index];
        if (value == null)
            return 0;
        int l = 0, r = value.size() - 1;
        int retVal = 0;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (value.get(mid)[0] <= snap_id) {
                retVal = value.get(mid)[1];
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return retVal;
    }

    public static void main(String[] args) {
        SnapshotArray snapshotArray = new SnapshotArray(3);
        snapshotArray.set(0, 5);
        snapshotArray.snap();
        snapshotArray.set(0, 6);
        System.out.println(snapshotArray.get(0, 0) == 5 ? "Pass" : "Fail");
        snapshotArray.set(0, 7);
        System.out.println(snapshotArray.get(0, 1) == 7 ? "Pass" : "Fail");
        snapshotArray.set(0, 8);
        System.out.println(snapshotArray.get(0, 2) == 8 ? "Pass" : "Fail");
        snapshotArray.set(0, 9);
        System.out.println(snapshotArray.get(0, 3) == 9 ? "Pass" : "Fail");
        snapshotArray.set(0, 10);
        System.out.println(snapshotArray.get(0, 4) == 10 ? "Pass" : "Fail");
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
