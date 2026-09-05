package LeetCode692;

import java.util.*;

/**
 * LeetCode 692: Top K Frequent Words
 * Related / similar problem from today's NeetCode practice.
 * Pattern: Arrays & Hashing
 *
 * Problem:
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 * Example 1:
 *
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 *
 * Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 * Output: ["the","is","sunny","day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Constraints:
 *
 * - 1 <= words.length <= 500
 *
 * - 1 <= words[i].length <= 10
 *
 * - words[i] consists of lowercase English letters.
 *
 * - k is in the range [1, The number of unique words[i]]
 *
 * Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 *
 * Link: https://leetcode.com/problems/top-k-frequent-words/
 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> freqMap = new HashMap<String, Integer>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word,0)+1);
        }
        PriorityQueue<String> minHeap = new PriorityQueue<String>((a, b)->{
            int f1 = freqMap.get(a);
            int f2 = freqMap.get(b);
            if (f1 != f2) {
                return f1-f2;
            }
            return b.compareTo(a);
        });
        for(String word : freqMap.keySet()) {
            minHeap.offer(word.toString());
            if (minHeap.size()>k) {
                minHeap.poll();
            }
        }
        List<String> result = new ArrayList();
        for (int i=0; i<k; i++) {
            result.add(minHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.topKFrequent(new String[]{"i","love","leetcode","i","love","coding"}, 2)); // ["i","love"]
        System.out.println(sol.topKFrequent(new String[]{"the","day","is","sunny","the","the","the","sunny","is","is"}, 4)); // ["the","is","sunny","day"]
    }
}
