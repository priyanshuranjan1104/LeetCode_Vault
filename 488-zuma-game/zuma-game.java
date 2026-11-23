class Solution {
    class State {
        long board;
        long hand;

        public State(long board, long hand) {
            this.board = board;
            this.hand = hand;
        }
        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            State object = (State) o;
            return object.board == this.board && object.hand == this.hand;
        }
        @Override
        public int hashCode() {
            int result = 1;
            result = result * 31 + Long.hashCode(board);
            result = result * 31 + Long.hashCode(hand);
            return result;
        }
    }
    int max = 10;
    public int findMinStep(String boardString, String handString) {
        long hand = encodeHand(handString);
        long board = encodeBoard(boardString);
        Set<State> isVisited = new HashSet<>();
        dfs(board, hand, 0, isVisited);
        return max == 10 ? -1 : max;
    }

    public void dfs(long board, long hand, int curr, Set<State> isVisited) {
        if (board == 0) {
            max = Integer.min(max, curr);
        }
        if (curr >= max || hand == 0 || !isVisited.add(new State(board, hand))) {
            return;
        }

        for (int i=0; i < 64; i+=3) {
            long boardBall = (board >> i) & 7;
            long nextBoardBall = (board >> (i + 3)) & 7;
            long prevBoardBall = i == 0 ? 0 : (board >> (i-3)) & 7;

            for (int j=0; j < 16; j+=3) {

                long currBall = (hand >> j) & 7;
                if (currBall == 0) break;

                long prevBall = j == 0 ? 0 : (hand >> (j - 3)) & 7;
                long newHand = ((hand >> (j+3)) << j) | (hand & ((1<<j)-1));

                if (currBall == prevBall) { continue; }
                if (currBall == prevBoardBall) { continue; }
                if ((curr != 0 && currBall != boardBall) || (curr == 0 && currBall != boardBall && boardBall != prevBoardBall)) { continue; }

                long newBoard = (board & ((1l<<i)-1)) | (currBall << i) | ((board>>i) << (i+3));
                if (currBall == boardBall && currBall == nextBoardBall) {
                    newBoard = trim(newBoard);
                }
                dfs(newBoard, newHand, curr+1, isVisited);
            }
            if (boardBall == 0) break;
        }
    }
    public long trim(long board) {
        long stack = 0;
        for (int i=0; i<64; i+=3) {
            long currBall = (board >> i) & 7;
            long top = stack & 7;
            if (top !=0 && currBall != top && (stack & 63) == ((stack >> 3) & 63)) {
                stack = stack >> 9;
                while ((stack & 7) == top) {
                    stack = stack >> 3;
                }
            }
            if (currBall == 0) break;
            stack = (stack<<3) | currBall;
        }
        return stack;
    }
    public void decode(long string) {
        for (int i=0; i<64; i+=3) {
            long ball = (string >> i) & 7;
            if (ball == 0) { break;}
            System.out.println(decodeChar(ball));
        }
    }

    public long encodeBoard(String board) {
        long boardEncoded = 0;
        for (int i=0; i < board.length(); i++) {
            boardEncoded = (boardEncoded << 3) | encodeChar(board.charAt(i));
        }
        return boardEncoded;
    }
    public long encodeHand(String hand) {
        long[] handEncoded = new long[hand.length()];
        for (int i=0; i < hand.length(); i++) {
            handEncoded[i] = encodeChar(hand.charAt(i));
        }
        Arrays.sort(handEncoded);
        long result = 0;
        for(long ball: handEncoded) {
            result = (result << 3) | ball;
        }
        return result;
    }
    public long encodeChar(char ch) {
        return switch(ch) {
            case 'R' -> 1;
            case 'Y' -> 2;
            case 'B' -> 3;
            case 'G' -> 4;
            case 'W' -> 5;
            default -> throw new IllegalArgumentException();
        };
    }
    public char decodeChar(long ch) {
        return switch((int) ch) {
            case 1 -> 'R';
            case 2 -> 'Y';
            case 3 -> 'B';
            case 4 -> 'G';
            case 5 -> 'W';
            default -> throw new IllegalArgumentException();
        };
    }

}