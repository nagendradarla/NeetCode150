package LeetCode1024;

/**
 * LeetCode 1024: Video Stitching
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Greedy
 *
 * Problem:
 * You are given a series of video clips from a sporting event that lasted time seconds. These video clips can be overlapping with each other and have varying lengths.
 *
 * Each video clip is described by an array clips where clips[i] = [starti, endi] indicates that the ith clip started at starti and ended at endi.
 *
 * We can cut these clips into segments freely.
 *
 * - For example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 *
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event [0, time]. If the task is impossible, return -1.
 *
 * Example 1:
 *
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
 * Output: 3
 * Explanation: We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 *
 * Example 2:
 *
 * Input: clips = [[0,1],[1,2]], time = 5
 * Output: -1
 * Explanation: We cannot cover [0,5] with only [0,1] and [1,2].
 *
 * Example 3:
 *
 * Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], time = 9
 * Output: 3
 * Explanation: We can take clips [0,4], [4,7], and [6,9].
 *
 * Constraints:
 *
 * - 1 <= clips.length <= 100
 *
 * - 0 <= starti <= endi <= 100
 *
 * - 1 <= time <= 100
 *
 * Link: https://leetcode.com/problems/video-stitching/
 */
class Solution {
    public int videoStitching(int[][] clips, int time) {
        int[] fartest = new int[time];
        for (int[] clip : clips) {
            if (clip[0] < time) {
                fartest[clip[0]] = Math.max(fartest[clip[0]], clip[1]);
            }
        }
        int max = 0, last = 0, noOfClips = 0;
        for (int i = 0; i < time; i++) { // Iterate from 0 through time. (OR) iterate through fartest[]
            int start = i;
            int end = fartest[i];
            max = Math.max(max, end);
            // if max is less than or equal to i, it cannot go further. early exit.
            if (max <= i)
                return -1;
            if (i == last) {
                noOfClips++;
                last = max;
                // early return : if it is already more than or equal to time.
                if (max >= time) {
                    return noOfClips;
                }
                /*if (last >= time)
                    return noOfClips;*/
            }
        }
        return noOfClips;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] clips = { { 0, 2 }, { 4, 6 }, { 8, 10 }, { 1, 9 }, { 1, 5 }, { 5, 9 } };
        int time = 10;
        System.out.println(sol.videoStitching(clips, time) == 3 ? "Test Case 1 Passed" : "Test Case 1 Failed");
        int[][] clips2 = { { 0, 1 }, { 1, 2 } };
        int time2 = 5;
        System.out.println(sol.videoStitching(clips2, time2) == -1 ? "Test Case 2 Passed" : "Test Case 2 Failed");
        int[][] clips3 = { { 0, 1 }, { 6, 8 }, { 0, 2 }, { 5, 6 }, { 0, 4 }, { 0, 3 }, { 6, 7 }, { 1, 3 }, { 4, 7 },
                { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 4 }, { 4, 5 }, { 5, 7 }, { 6, 9 } };
        int time3 = 9;
        System.out.println(sol.videoStitching(clips3, time3) == 3 ? "Test Case 3 Passed" : "Test Case 3 Failed");
        int[][] clips4 = { { 0, 1 }, { 6, 8 }, { 0, 2 }, { 5, 6 }, { 0, 4 }, { 0, 3 }, { 6, 7 }, { 1, 3 }, { 4, 7 },
                { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 4 }, { 4, 5 }, { 5, 7 }, { 6, 9 } };
        int time4 = 9;
        System.out.println(sol.videoStitching(clips4, time4) == 3 ? "Test Case 4 Passed" : "Test Case 4 Failed");
        int[][] clips5 = { { 0, 1 }, { 6, 8 }, { 0, 2 }, { 5, 6 }, { 0, 4 }, { 0, 3 }, { 6, 7 }, { 1, 3 }, { 4, 7 },
                { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 4 }, { 4, 5 }, { 5, 7 }, { 6, 9 } };
        int time5 = 9;
        System.out.println(sol.videoStitching(clips5, time5) == 3 ? "Test Case 5 Passed" : "Test Case 5 Failed");
    }
}
