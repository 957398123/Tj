
class AStarTree {

    private int curCol;
    private int curRow;
    private int aimCol;
    private int aimRow;
    public static final int ID_COL = 0;
    public static final int ID_ROW = 1;
    public static final int ID_G = 2;
    public static final int ID_H = 3;
    public static final int ID_FID = 4;
    public static final int ID_F = 5;
    int[][] open;
    int[][] closed;
    int[][] path;
    private static AStarTree instance = null;

    public static AStarTree getInstance() {
        if (instance == null) {
            instance = new AStarTree();
        }

        return instance;
    }

    public AStarTree() {
    }

    public final int[][] findPath(int curCol, int curRow, int aimCol, int aimRow) {
        this.curCol = curCol;
        this.curRow = curRow;
        this.aimCol = aimCol;
        this.aimRow = aimRow;
        return startFind();
    }

    private final int[][] startFind() {
        this.path = (int[][]) null;
        this.open = new int[2][];
        int AG = this.getH(this.curCol, this.curRow);
        this.open[0] = new int[0];
        this.open[1] = new int[]{this.curCol, this.curRow, 0, AG, 0, AG};
        this.closed = new int[0][];

        while (this.open.length > 0) {
            this.openAddClosed();
            int fatherId = this.closed.length - 1;
            if (this.closed[fatherId][0] == this.aimCol && this.closed[fatherId][1] == this.aimRow) {
                this.getPath();
                break;
            }

            this.makeStar(fatherId);
        }

        this.open = (int[][]) null;
        this.closed = (int[][]) null;
        return this.path;
    }

    private void getPath() {
        this.path = new int[0][];
        this.path = resizeArray(this.path, 0, 1);

        for (this.path[this.path.length - 1] = this.closed[this.closed.length - 1]; this.path[0][2] != 0; this.path[0] = this.closed[this.path[1][4]]) {
            this.path = resizeArray(this.path, 0, 1);
        }

        this.curCol = this.aimCol;
        this.curRow = this.aimRow;
    }

    void makeStar(int id) {
        int col = this.closed[id][0];
        int row = this.closed[id][1];
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        int dCol = col;
        int dRow = row - 1;
        if (dRow >= 0 && this.checkMap(dCol, dRow)) {
            up = true;
            if (this.checkOpen(dCol, dRow, id) && this.checkClosed(dCol, dRow)) {
                this.addPoint(dCol, dRow, id);
            }
        }

        dCol = col;
        dRow = row + 1;
        if (dRow < Map.currentTotalRow && this.checkMap(dCol, dRow)) {
            down = true;
            if (this.checkOpen(dCol, dRow, id) && this.checkClosed(dCol, dRow)) {
                this.addPoint(dCol, dRow, id);
            }
        }

        dCol = col - 1;
        dRow = row;
        if (dCol >= 0 && this.checkMap(dCol, dRow)) {
            left = true;
            if (this.checkOpen(dCol, dRow, id) && this.checkClosed(dCol, dRow)) {
                this.addPoint(dCol, dRow, id);
            }
        }

        dCol = col + 1;
        dRow = row;
        if (dCol < Map.currentTotalColumn && this.checkMap(dCol, dRow)) {
            right = true;
            if (this.checkOpen(dCol, dRow, id) && this.checkClosed(dCol, dRow)) {
                this.addPoint(dCol, dRow, id);
            }
        }

        dCol = col - 1;
        dRow = row - 1;
        if (up && left && this.checkMap(dCol, dRow) && this.checkOpen(dCol, dRow, id) && this.checkClosed(dCol, dRow)) {
            this.addPoint(dCol, dRow, id);
        }

        dCol = col + 1;
        dRow = row - 1;
        if (up && right && this.checkMap(dCol, dRow) && this.checkOpen(dCol, dRow, id) && this.checkClosed(dCol, dRow)) {
            this.addPoint(dCol, dRow, id);
        }

        dCol = col + 1;
        dRow = row + 1;
        if (down && right && this.checkMap(dCol, dRow) && this.checkOpen(dCol, dRow, id) && this.checkClosed(dCol, dRow)) {
            this.addPoint(dCol, dRow, id);
        }

        dCol = col - 1;
        dRow = row + 1;
        if (down && left && this.checkMap(dCol, dRow) && this.checkOpen(dCol, dRow, id) && this.checkClosed(dCol, dRow)) {
            this.addPoint(dCol, dRow, id);
        }

    }

    boolean checkOpen(int col, int row, int fid) {
        for (int i = this.open.length - 1; i > 0; --i) {
            if (this.open[i][0] == col && this.open[i][1] == row) {
                int tmpG = this.getG(col, row, fid);
                if (tmpG < this.open[i][2]) {
                    this.open[i][2] = tmpG;
                    this.open[i][4] = fid;
                    this.open[i][5] = this.open[i][2] + this.open[i][3];
                    this.resetPointResize(i);
                }

                return false;
            }
        }

        return true;
    }

    boolean checkClosed(int col, int row) {
        for (int i = this.closed.length - 1; i >= 0; --i) {
            if (this.closed[i][0] == col && this.closed[i][1] == row) {
                return false;
            }
        }

        return true;
    }

    boolean checkMap(int col, int row) {
        return Map.getInstance().isFloor(col, row);
    }

    void addPoint(int col, int row, int fId) {
        this.open = resizeArray(this.open, this.open.length, 1);
        int g = this.getG(col, row, fId);
        int h = this.getH(col, row);
        this.open[this.open.length - 1] = new int[]{col, row, g, h, fId, g + h};
        this.addPointResize();
    }

    private final void addPointResize() {
        this.resetPointResize(this.open.length - 1);
    }

    private final void removePointResize() {
        int last = this.open.length - 1;
        this.open[1] = this.open[last];
        this.open = resizeArray(this.open, last, -1);
        last = this.open.length - 1;

        int childMin;
        for (int head = 1; (head << 1) + 1 <= last; head = childMin) {
            int child1 = head << 1;
            int child2 = child1 + 1;
            childMin = this.open[child1][5] < this.open[child2][5] ? child1 : child2;
            if (this.open[head][5] <= this.open[childMin][5]) {
                break;
            }

            int[] tmp = this.open[head];
            this.open[head] = this.open[childMin];
            this.open[childMin] = tmp;
        }

    }

    private final void resetPointResize(int i) {
        for (int last = i; last > 1; last >>= 1) {
            int half = last >> 1;
            if (this.open[last][5] >= this.open[half][5]) {
                break;
            }

            int[] tmp = this.open[last];
            this.open[last] = this.open[half];
            this.open[half] = tmp;
        }

    }

    private final int getG(int col, int row, int fId) {
        int fx = this.closed[fId][0];
        int fy = this.closed[fId][1];
        int fg = this.closed[fId][2];
        return fx - col != 0 && fy - row != 0 ? fg + 14 : fg + 10;
    }

    private final int getH(int col, int row) {
        return Math.abs(this.aimCol - col) * 10 + Math.abs(this.aimRow - row) * 10;
    }

    public static final int[][] resizeArray(int[][] array, int index, int var) {
        int len = array.length;
        int[][] tmp = array;
        array = new int[len + var][];
        System.arraycopy(tmp, 0, array, 0, index);
        if (var > 0) {
            System.arraycopy(tmp, index, array, index + var, len - index);
        } else {
            System.arraycopy(tmp, index - var, array, index, len + var - index);
        }

        tmp = (int[][]) null;
        return array;
    }

    private void openAddClosed() {
        this.closed = resizeArray(this.closed, this.closed.length, 1);
        this.closed[this.closed.length - 1] = this.open[1];
        this.removePointResize();
    }
}
