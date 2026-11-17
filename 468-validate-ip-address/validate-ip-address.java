class Solution {
    public String validIPAddress(String IP) {
        int n = IP.length(), parts = 0, start = 0;
        char sep = IP.indexOf('.') >= 0 ? '.' : ':';
        boolean ipv4 = sep == '.';
        int expected = ipv4 ? 4 : 8;

        for (int i = 0; i <= n; i++) {
            if (i == n || IP.charAt(i) == sep) {
                if (++parts > expected || !valid(IP, start, i, ipv4))
                    return "Neither";
                start = i + 1;
            } else if (IP.charAt(i) == '.' || IP.charAt(i) == ':') {
                return "Neither";
            }
        }

        return parts == expected ? (ipv4 ? "IPv4" : "IPv6") : "Neither";
    }

    public boolean valid(String s, int start, int end, boolean ipv4) {
        int len = end - start;

        if (len == 0) return false;

        if (ipv4) {
            if (len > 3 || (len > 1 && s.charAt(start) == '0'))
                return false;

            int value = 0;
            for (int i = start; i < end; i++) {
                char c = s.charAt(i);
                if (c < '0' || c > '9') return false;
                value = value * 10 + c - '0';
            }
            return value <= 255;
        }

        if (len > 4) return false;

        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (!((c >= '0' && c <= '9') ||
                  (c >= 'a' && c <= 'f') ||
                  (c >= 'A' && c <= 'F')))
                return false;
        }
        return true;
    }
}