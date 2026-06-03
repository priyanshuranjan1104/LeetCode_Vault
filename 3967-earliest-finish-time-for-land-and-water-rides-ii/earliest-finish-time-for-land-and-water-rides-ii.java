class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {
        long ans = Long.MAX_VALUE;

        long minLandEnd = Long.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            minLandEnd = Math.min(minLandEnd, (long) landStartTime[i] + landDuration[i]);
        }

        for (int i = 0; i < waterStartTime.length; i++) {
            ans = Math.min(ans,
                    Math.max(minLandEnd, waterStartTime[i]) + (long) waterDuration[i]);
        }

        long minWaterEnd = Long.MAX_VALUE;
        for (int i = 0; i < waterStartTime.length; i++) {
            minWaterEnd = Math.min(minWaterEnd, (long) waterStartTime[i] + waterDuration[i]);
        }

        for (int i = 0; i < landStartTime.length; i++) {
            ans = Math.min(ans,
                    Math.max(minWaterEnd, landStartTime[i]) + (long) landDuration[i]);
        }

        return (int) ans;
    }
}