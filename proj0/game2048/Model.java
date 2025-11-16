package game2048;

import java.security.KeyStore;
import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     *
     * key:按行或列扫描(依输入的side而定)
     * 对于每一格dest，找其最终的tile，从该格往后找tile，然后看能否合并，最后放在该格子，以此遍历
     * 对于tile的情况，有四种
     * 1. 找到两个tile，score1 == score2，则合并后移动到dest
     * 2. 找到两个tile，score1 ！= score2，则只移动tile1到dest
     * 3. 找到一个tile，直接移动tile1到dest
     * 4. 没有tile了，直接去下个行或列
     *
     * 因为是扫描，所以巧妙的符合了要求的不能连续合并的要求
     * */
    public boolean tilt(Side side) {
        boolean changed,modified = false;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        checkGameOver();

        if(!this.gameOver){
            int width = this.board.size();
            switch (side){
                case NORTH://这里是按列来合并，即destX代表列，destY代表行，坐标原点就是2048的左下角
                    for(int destX = 0;destX<width;destX++){
                        int destY = width;
                        //开始确定每一格的最终tile
                        while (destY-- != 0){
                            //找tile1和tile2
                            int index1 = -1,index2 = -1;
                            for(int i=destY;i>-1;i--) if(this.board.tile(destX,i) != null) {index1 = i;break;}
                            for(int i=index1-1;i>-1;i--) if(this.board.tile(destX,i) != null) {index2 = i;break;}

                            if(index1 == -1) break;//0个tile的情况
                            //1-2tile情况
                            int score1 = this.board.tile(destX,index1).value(),score2 = index2 == -1?-1:this.board.tile(destX,index2).value();
                            this.board.move(destX, destY, this.board.tile(destX, index1));
                            if(score1 == score2){  //同分情况的合并
                                this.score += score1 + score2;
                                this.board.move(destX, destY, this.board.tile(destX, index2));
                            }
                            changed = ((destY != index1)||(score1 == score2))?true:changed;//对changed的定义，只有board改变时changed才改变
                        }
                    }
                    break;
                case SOUTH:
                    for(int destX = 0;destX<width;destX++){
                        int destY = -1;
                        while (destY++ != width-1){
                            int index1 = -1,index2 = -1;
                            for(int i=destY;i<width;i++) if(this.board.tile(destX,i) != null) {index1 = i;break;}
                            for(int i=index1+1;i<width;i++) if(this.board.tile(destX,i) != null) {index2 = i;break;}
                            if(index1 == -1) break;
                            if(index1 == -1) break;
                            int score1 = this.board.tile(destX,index1).value(),score2 = index2 == -1?-1:this.board.tile(destX,index2).value();
                            this.board.move(destX, destY, this.board.tile(destX, index1));
                            if(score1 == score2){
                                this.score += score1 + score2;
                                this.board.move(destX, destY, this.board.tile(destX, index2));
                            }
                            changed = ((destY != index1)||(score1 == score2))?true:changed;
                        }
                    }
                    break;
                case WEST:
                    for(int destY = 0;destY<width;destY++){
                        int destX = -1;
                        while (destX++ != width-1){
                            int index1 = -1,index2 = -1;
                            for(int i=destX;i<width;i++) if(this.board.tile(i,destY) != null) {index1 = i;break;}
                            for(int i=index1+1;i<width;i++) if(this.board.tile(i,destY) != null) {index2 = i;break;}

                            if(index1 == -1) break;
                            int score1 = this.board.tile(index1,destY).value(),score2 = index2 == -1?-1:board.tile(index2,destY).value();
                            this.board.move(destX, destY, this.board.tile(index1, destY));
                            if(score1 == score2){
                                this.score += score1 + score2;
                                this.board.move(destX, destY, this.board.tile(index2, destY));
                            }
                            changed = ((destX != index1)||(score1 == score2))?true:changed;
                        }
                    }
                    break;
                case EAST:
                    for(int destY = 0;destY<width;destY++){
                        int destX = width;
                        while (destX-- != 0){
                            int index1 = -1,index2 = -1;
                            for(int i=destX;i>-1;i--) if(this.board.tile(i,destY) != null) {index1 = i;break;}
                            for(int i=index1-1;i>-1;i--) if(this.board.tile(i,destY) != null) {index2 = i;break;}

                            if(index1 == -1) break;
                            int score1 = this.board.tile(index1,destY).value(),score2 = index2 == -1?-1:board.tile(index2,destY).value();
                            this.board.move(destX, destY, this.board.tile(index1, destY));
                            if(score1 == score2){
                                this.score += score1 + score2;
                                this.board.move(destX, destY, this.board.tile(index2, destY));
                            }
                            changed = ((destX != index1)||(score1 == score2))?true:changed;
                        }
                    }
                    break;
            }
        }

        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        if (b.size() == 0) return true;
        int len = b.size();
        for(int i = 0;i<len;i++){
            for (int j = 0;j<len;j++){
                if (b.tile(i,j) == null) return true;
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        for (int i = 0,len = b.size();i<len;i++){
            for (int j = 0;j<len;j++){
                if (b.tile(i,j) != null && b.tile(i,j).value() == MAX_PIECE) return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        if(emptySpaceExists(b)) return true;
        for (int i = 0,len = b.size();i < len;i++){
            for (int j = 0;j < len;j++){
                if((j+1 < len && b.tile(i,j).value() == b.tile(i,j+1).value()) || (i+1 < len && b.tile(i,j).value() == b.tile(i+1,j).value())){
                    return true;
                }
            }
        }
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
