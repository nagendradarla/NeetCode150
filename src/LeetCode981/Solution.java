package LeetCode981;

import java.util.*;

/**
 * NeetCode #33 | LeetCode 981: Time Based Key-Value Store
 * Pattern: Binary Search | Difficulty: Medium
 *
 * Approach hint:
 * Map key → list of (timestamp, value) sorted by time. Binary search latest ≤ timestamp.
 *
 * Similar problems:
 *   - Snapshot Array (LC 1146)
 *   - Online Election (LC 911)
 *
 * Problem:
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 * - TimeMap() Initializes the object of the data structure.
 *
 * - void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 *
 * - String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 *
 * Example 1:
 *
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 *
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1); // return "bar"
 * timeMap.get("foo", 3); // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4); // return "bar2"
 * timeMap.get("foo", 5); // return "bar2"
 *
 * Constraints:
 *
 * - 1 <= key.length, value.length <= 100
 *
 * - key and value consist of lowercase English letters and digits.
 *
 * - 1 <= timestamp <= 10^7
 *
 * - All the timestamps timestamp of set are strictly increasing.
 *
 * - At most 2 * 10^5 calls will be made to set and get.
 *
 * Link: https://leetcode.com/problems/time-based-key-value-store/
 */
class TimeMap {
    Map<String, List<Pair>> timeMap = null;

    private static class Pair {
        String value;
        int timestamp;

        public Pair(String v, int t) {
            value = v;
            timestamp = t;
        }
    }

    public TimeMap() {
        timeMap = new HashMap<String, List<Pair>>();
    }

    public void set(String key, String value, int timestamp) {
        List<Pair> values = timeMap.getOrDefault(key, new ArrayList<Pair>());
        values.add(new Pair(value, timestamp));
        timeMap.put(key, values);
    }

    public String get(String key, int timestamp) {
        List<Pair> values = timeMap.get(key);
        String retVal = "";
        if (values == null)
            return "";
        int l = 0, r = values.size() - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (values.get(mid).timestamp <= timestamp) {
                retVal = values.get(mid).value;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return retVal;
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        System.out.println(timeMap.get("foo", 1) == "bar" ? "Pass" : "Fail");
        System.out.println(timeMap.get("foo", 3) == "bar" ? "Pass" : "Fail");
        timeMap.set("foo", "bar2", 4);
        System.out.println(timeMap.get("foo", 4) == "bar2" ? "Pass" : "Fail");
        System.out.println(timeMap.get("foo", 5) == "bar2" ? "Pass" : "Fail");
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 */
