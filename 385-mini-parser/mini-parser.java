/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[')
            return new NestedInteger(Integer.parseInt(s));

        NestedInteger[] stack = new NestedInteger[s.length()];
        int top = -1, num = 0, sign = 1;
        boolean hasNum = false;

        for (int i = 0, n = s.length(); i < n; i++) {
            char c = s.charAt(i);

            if (c == '[') {
                NestedInteger cur = new NestedInteger();

                if (top >= 0)
                    stack[top].add(cur);

                stack[++top] = cur;

            } else if (c == '-') {
                sign = -1;

            } else if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                hasNum = true;

            } else {
                if (hasNum) {
                    stack[top].add(new NestedInteger(num * sign));
                    num = 0;
                    sign = 1;
                    hasNum = false;
                }

                if (c == ']')
                    top--;
            }
        }

        return stack[0];
    }
}