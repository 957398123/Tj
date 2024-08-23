
class AStarTree {
    // 起点

    private int curCol;
    private int curRow;
    // 终点
    private int aimCol;
    private int aimRow;
    public static final int ID_COL = 0;
    public static final int ID_ROW = 1;
    public static final int ID_G = 2;
    public static final int ID_H = 3;
    public static final int ID_FID = 4;
    public static final int ID_F = 5;
    // 开放列表 [col, row, g(n), h(n), fid, f(n)]
    int[][] open;
    // 关闭列表
    int[][] closed;
    // 路径
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

    /**
     * 查找路径
     *
     * @param curCol
     * @param curRow
     * @param aimCol
     * @param aimRow
     * @return
     */
    public int[][] findPath(int curCol, int curRow, int aimCol, int aimRow) {
        this.curCol = curCol;
        this.curRow = curRow;
        this.aimCol = aimCol;
        this.aimRow = aimRow;
        return startFind();
    }

    /**
     * 开始查找路径
     *
     * @return
     */
    private int[][] startFind() {
        // 初始化路径
        path = null;
        // 初始化openlist
        open = new int[2][];
        // 获取起点g-cost，启发式估计
        int AG = getH(curCol, curRow);
        open[0] = new int[0];
        open[1] = new int[]{curCol, curRow, 0, AG, 0, AG};
        // 初始化关闭列表
        closed = new int[0][];
        while (open.length > 0) {
            // 找成本最低的格子从开放列表中删除，加入关闭列表，并且重新排序开放列表
            openAddClosed();
            // 这里以关闭列表索引作为标识，因为关闭列表是不会再排序的
            int fatherId = closed.length - 1;
            // 如果找到了终点
            if (closed[fatherId][0] == aimCol && closed[fatherId][1] == aimRow) {
                getPath();
                break;
            }
            // 处理当前格子
            makeStar(fatherId);
        }
        open = null;
        closed = null;
        return path;
    }

    private void getPath() {
        path = new int[0][];
        path = resizeArray(path, 0, 1);

        for (path[path.length - 1] = closed[closed.length - 1]; path[0][2] != 0; path[0] = closed[path[1][4]]) {
            path = resizeArray(path, 0, 1);
        }
        curCol = aimCol;
        curRow = aimRow;
    }

    void makeStar(int id) {
        // 获取当前位置
        int col = closed[id][0];
        int row = closed[id][1];
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;
        // 检查斜右上方是否可走
        int dCol = col;
        int dRow = row - 1;
        if (dRow >= 0 && checkMap(dCol, dRow)) {
            up = true;
            if (checkOpen(dCol, dRow, id) && checkClosed(dCol, dRow)) {
                addPoint(dCol, dRow, id);
            }
        }
        // 检查斜左下方是否可走
        dCol = col;
        dRow = row + 1;
        if (dRow < Map.currentTotalRow && checkMap(dCol, dRow)) {
            down = true;
            if (checkOpen(dCol, dRow, id) && checkClosed(dCol, dRow)) {
                addPoint(dCol, dRow, id);
            }
        }
        // 检查斜左上方是否可走
        dCol = col - 1;
        dRow = row;
        if (dCol >= 0 && this.checkMap(dCol, dRow)) {
            left = true;
            if (checkOpen(dCol, dRow, id) && checkClosed(dCol, dRow)) {
                addPoint(dCol, dRow, id);
            }
        }
        // 检查斜右下方是否可走
        dCol = col + 1;
        dRow = row;
        if (dCol < Map.currentTotalColumn && checkMap(dCol, dRow)) {
            right = true;
            if (checkOpen(dCol, dRow, id) && checkClosed(dCol, dRow)) {
                addPoint(dCol, dRow, id);
            }
        }

        // 检测正上方是否可以行走
        dCol = col - 1;
        dRow = row - 1;
        if (up && left && checkMap(dCol, dRow) && checkOpen(dCol, dRow, id) && checkClosed(dCol, dRow)) {
            addPoint(dCol, dRow, id);
        }
        // 检测正右方是否可以行走
        dCol = col + 1;
        dRow = row - 1;
        if (up && right && checkMap(dCol, dRow) && checkOpen(dCol, dRow, id) && checkClosed(dCol, dRow)) {
            addPoint(dCol, dRow, id);
        }

        // 检测正下方是否可以行走
        dCol = col + 1;
        dRow = row + 1;
        if (down && right && checkMap(dCol, dRow) && checkOpen(dCol, dRow, id) && checkClosed(dCol, dRow)) {
            addPoint(dCol, dRow, id);
        }
        // 检测正左方是否可以行走
        dCol = col - 1;
        dRow = row + 1;
        if (down && left && checkMap(dCol, dRow) && checkOpen(dCol, dRow, id) && checkClosed(dCol, dRow)) {
            addPoint(dCol, dRow, id);
        }
    }

    /**
     * 检测是否要更新open
     * @param col
     * @param row
     * @param fid
     * @return 
     */
    boolean checkOpen(int col, int row, int fid) {
        for (int i = open.length - 1; i > 0; --i) {
            if (open[i][0] == col && open[i][1] == row) {
                int tmpG = getG(col, row, fid);
                // 如果当前成本更低，直接更新
                if (tmpG < open[i][2]) {
                    open[i][2] = tmpG;
                    open[i][4] = fid;
                    open[i][5] = open[i][2] + open[i][3];
                    resetPointResize(i);
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 检测是否在关闭列表
     * @param col
     * @param row
     * @return 
     */
    boolean checkClosed(int col, int row) {
        for (int i = closed.length - 1; i >= 0; --i) {
            if (closed[i][0] == col && closed[i][1] == row) {
                return false;
            }
        }
        return true;
    }

    boolean checkMap(int col, int row) {
        return Map.getInstance().isFloor(col, row);
    }

    /**
     * 将当前加入开放列表，更新成本和排序
     * @param col
     * @param row
     * @param fId 
     */
    void addPoint(int col, int row, int fId) {
        this.open = resizeArray(this.open, this.open.length, 1);
        int g = this.getG(col, row, fId);
        int h = this.getH(col, row);
        this.open[this.open.length - 1] = new int[]{col, row, g, h, fId, g + h};
        this.addPointResize();
    }

    private void addPointResize() {
        this.resetPointResize(this.open.length - 1);
    }

    /**
     * 最小堆删除操作
     */
    private void removePointResize() {
        int last = open.length - 1;
        open[1] = open[last];
        open = resizeArray(open, last, -1);
        last = open.length - 1;
        int childMin;
        for (int head = 1; (head << 1) + 1 <= last; head = childMin) {
            int child1 = head << 1;
            int child2 = child1 + 1;
            childMin = open[child1][5] < open[child2][5] ? child1 : child2;
            if (open[head][5] <= open[childMin][5]) {
                break;
            }
            int[] tmp = open[head];
            open[head] = open[childMin];
            open[childMin] = tmp;
        }
    }

    private void resetPointResize(int i) {
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

    /**
     * g(n)
     * @param col
     * @param row
     * @param fId
     * @return 
     */
    private int getG(int col, int row, int fId) {
        int fx = closed[fId][0];
        int fy = closed[fId][1];
        int fg = closed[fId][2];
        return fx - col != 0 && fy - row != 0 ? fg + 14 : fg + 10;
    }

    /**
     * 获取启发式预估代价，这里是曼哈顿距离
     *
     * @param col
     * @param row
     * @return
     */
    private int getH(int col, int row) {
        return Math.abs(this.aimCol - col) * 10 + Math.abs(this.aimRow - row) * 10;
    }

    /**
     * 重置数组大小
     *
     * @param array
     * @param index
     * @param var
     * @return
     */
    public static int[][] resizeArray(int[][] array, int index, int var) {
        int len = array.length;
        int[][] tmp = array;
        array = new int[len + var][];
        System.arraycopy(tmp, 0, array, 0, index);
        if (var > 0) {
            System.arraycopy(tmp, index, array, index + var, len - index);
        } else {
            System.arraycopy(tmp, index - var, array, index, len + var - index);
        }
        tmp = null;
        return array;
    }

    private void openAddClosed() {
        // 扩容关闭列表1空间
        closed = resizeArray(closed, closed.length, 1);
        // 将当前遍历的格子（成本最低）加入关闭列表
        closed[closed.length - 1] = open[1];
        // 从开放列表中删除当前格子，并且排序
        removePointResize();
    }
}
