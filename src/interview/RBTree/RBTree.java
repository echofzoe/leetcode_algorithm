package interview.rbtree;


/**
 * classname: 红黑树
 *
 * <p>------操作如下------</p>
 * <p>① 创建RBTree，定义颜色</p>
 * <p>② 创建RBNode</p>
 * <p>③ 辅助方法定义：parentOf(node), isRed(node), isBlack(node), setRed(node), setBlack(node), inOrderPrint()</p>
 * <p>④ 左旋方法定义：leftRotate(node)</p>
 * <p>⑤ 右旋方法定义：rightRotate(node)</p>
 * <p>⑥ 公开插入接口方法定义：insert(K key, V value)</p>
 * <p>⑦ 内部插入接口方法定义：insert(RBNode node)</p>
 * <p>⑧ 修正插入导致失衡的方法定义：insertFixUp(RBNode node)</p>
 * <p>⑨ 测试红黑树正确性</p>
 * <p>-------------------</p>
 *
 * @param <K> 键
 * @param <V> 值
 * @author echofzoe
 * @date 2021.3.16 21.31
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    /**
     * 树根的引用
     */
    private RBNode root;
    public RBNode getRoot() {
        return root;
    }

    /**
     * 获取当前节点的父节点
     */
    private RBNode parentOf(RBNode node) {
        return node == null ? null : node.parent;
    }

    /**
     * 节点是否为红色
     */
    private boolean isRed(RBNode node) {
        return node != null && node.color == RED;
    }

    /**
     * 节点是否为黑色
     */
    private boolean isBlack(RBNode node) {
        return node != null && node.color == BLACK;
    }

    /**
     * 设置节点为红色
     */
    private void setRed(RBNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 设置节点为黑色
     */
    private void setBlack(RBNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 中序打印
     */
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    private void inOrderPrint(RBNode node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key: " + node.key + ", value: " + node.value);
            inOrderPrint(node.right);
        }
    }

    /**
     * 左旋
     *      p                  p
     *      |                  |
     *      x                  y
     *     / \      --->      / \
     *    lx  y              x  ry
     *       / \            / \
     *      ly ry          lx ly
     */
    private void leftRotate(RBNode x) {
        RBNode y = x.right;

        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }

        if (x.parent != null) {
            RBNode p = x.parent;

            y.parent = p;

            if (p.left == x) {
                p.left = y;
            } else {
                p.right = y;
            }
        } else {
            this.root = y;
            y.parent = null;
        }

        x.parent = y;
        y.left = x;
    }

    /**
     * 右旋
     *      p                  p
     *      |                  |
     *      x                  y
     *     / \      --->      / \
     *    y  rx              ly  x
     *   / \                    / \
     *  ly ry                  ry rx
     */
    private void rightRotate(RBNode x) {
        RBNode y = x.left;

        x.left = y.right;
        if (x.left != null) {
            x.left.parent = y;
        }

        if (x.parent != null) {
            RBNode p = x.parent;

            y.parent = p;

            if (p.left == x) {
                p.left = y;
            } else {
                p.right = y;
            }
        } else {
            this.root = y;
            y.parent = null;
        }

        x.parent = y;
        y.right = x;
    }

    /**
     * 公开插入接口方法
     * @param key 键
     * @param value 值
     */
    public void insert(K key, V value) {
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);

        // 新节点一定是红色
        node.setColor(RED);

        insert(node);
    }

    private void insert(RBNode node) {
        // 1.查找当前node的父节点
        RBNode parent = null;
        RBNode x = this.root;

        while (x != null) {
            parent = x;

            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                x.setValue(node.getValue());
                return;
            }
        }

        node.parent = parent;

        // 2.判断node在parent的左右位置，注意要判空，因为初始化时root为空会导致parent为空
        if (parent != null) {
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {
                parent.right = node;
            } else {
                parent.left = node;
            }
        } else {
            this.root = node;
        }

        // 自平衡
        insertFixUp(node);
    }

    /**
     * 插入后修复红黑树平衡的方法
     * <p>|---情景1：红黑树为空树 - 将根节点染黑</p>
     * <p>|---情景2：插入节点的key已经存在 - 在insert(node)方法中已做替换，无需处理</p>
     * <p>|---情景3：插入节点的父节点为黑色 - 插入节点必红，黑高不变，无需处理</p>
     * <p>|---情景4：插入节点的父节点为红色</p>
     * <p style="margin-left:20px;">|---情景4.1：叔叔节点存在，且为红色（叔父双红） - 将叔父节点染黑，将爷节点染红，并以爷节点为当前节点，进行下一轮处理</p>
     * <p style="margin-left:20px;">|---情景4.2：叔叔节点不存在，或为黑色，父节点为爷节点的左子节点</p>
     * <p style="margin-left:40px;">|---情景4.2.1：插入节点为其父节点的左子节点（LL） - 将父节点染黑，将爷节点染红，并将爷节点右旋</p>
     * <p style="margin-left:40px;">|---情景4.2.2：插入节点为其父节点的右子节点（LR） - 将父节点左旋，再按LL双红处理</p>
     * <p style="margin-left:20px;">|---情景4.3：叔叔节点不存在，或为黑色，父节点为爷节点的右子节点</p>
     * <p style="margin-left:40px;">|---情景4.3.1：插入节点为其父节点的右子节点（RR） - 将父节点染黑，将爷节点染红，并将爷节点左旋</p>
     * <p style="margin-left:40px;">|---情景4.3.2：插入节点为其父节点的左子节点（RL） - 将父节点右旋，再按RR双红处理</p>
     */
    private void insertFixUp(RBNode node) {
        RBNode parent = parentOf(node); // 父节点
        RBNode gparent = parentOf(parent); // 爷节点

        // 1
        this.root.setColor(BLACK);

        // 4
        if (isRed(parent)) {
            // 如果父节点是红色，那么一定存在爷节点，因为根节点非红
            RBNode uncle = null;

            // 父节点为爷节点的左子树
            if (parent == gparent.left) {
                uncle = gparent.right;

                // 4.1 叔父双红
                if (isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);

                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }

                // 4,2
                if (uncle == null || isBlack(uncle)) {
                    if (node == parent.left) {
                        // 4.2.1 LL双红
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    }

                    if (node == parent.right) {
                        // 4.2.2 LR双红
                        leftRotate(parent);
                        insertFixUp(parent);
                        return;
                    }
                }
            }
            // 父节点为爷节点的右子树
            else {
                uncle = gparent.left;

                // 4.1 叔父双红
                if (isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);

                    setRed(gparent);
                    insertFixUp(gparent);
                    return;
                }

                // 4.3
                if (uncle == null || isBlack(uncle)) {
                    if (node == parent.right) {
                        // 4.3.1 RR双红
                        setBlack(parent);
                        setRed(gparent);
                        leftRotate(gparent);
                        return;
                    }

                    if (node == parent.left) {
                        // 4.3.2 RL双红
                        rightRotate(parent);
                        insertFixUp(parent);
                        return;
                    }
                }
            }
        }
    }

    /**
     * classname: 红黑树节点
     * @param <K> key
     * @param <V> value
     */
    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;

        private boolean color;

        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }
    }

}