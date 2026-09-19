class Solution {
    public boolean checkOverlap(
        int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {

        int closestXDistance = calculateDistanceToRange(x1, x2, xCenter);

        int closestYDistance = calculateDistanceToRange(y1, y2, yCenter);

        return closestXDistance * closestXDistance
             + closestYDistance * closestYDistance
             <= radius * radius;
    }

    public int calculateDistanceToRange(int rangeStart, int rangeEnd, int point) {

        if (rangeStart <= point && point <= rangeEnd) {
            return 0;
        }

        return point < rangeStart
            ? rangeStart - point
            : point - rangeEnd;
    }
}