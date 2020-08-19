package com.mine.util.algorithmutil.btree;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 红黑树算法
 * @DATE 2020/7/14 0014 14:22
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class Tree {

    TreeNode root;
    private final boolean RED = false;
    private final boolean BLACK = true;

    /**
     * 迭代查找，类似二分
     * @param key
     * @return
     */
    public TreeNode query(int key) {
        TreeNode tmp = root;
        while (tmp != null) {
            if (tmp.getKey() == key) {
                return tmp;
            } else if (tmp.getKey() > key) {
                tmp = tmp.getLeft();
            } else {
                tmp = tmp.getRight();
            }
        }
        return null;
    }

    /**
     * 插入
     * @param key
     */
    public void insert(int key) {
        TreeNode node = new TreeNode(key);
        //根节点为空直接赋值且返回
        if (root == null) {
            root = node;
            node.setColor(BLACK);
            return;
        }

        TreeNode parent = root;
        TreeNode son = null;
        if (key <= parent.getKey()) {
            son = parent.getLeft();
        } else {
            son = parent.getRight();
        }
        //查找节点数据插入位置，
        while (son != null) {
            parent = son;
            if (key <= parent.getKey()) {
                son = parent.getLeft();
            } else {
                son = parent.getRight();
            }
        }
        if (key <= parent.getKey()) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        node.setParent(parent);

        //修复红黑树数据结构
        insertFix(node);
    }

    /**
     * 修复红黑树数据结构
     * @param node
     */
    private void insertFix(TreeNode node) {
        TreeNode father, grandFather;
        //当父节点不为空且父节点为红的时候
        while ((father = node.getParent()) != null && father.isColor() == RED) {
            grandFather = father.getParent();
            if (grandFather.getLeft() == father) {
                //当父节点为祖父节点的左支时
                TreeNode uncle = grandFather.getRight();
                //当叔叔节点不为空且为红色的时候，将父节点和叔叔节点设为黑色，将祖父节点设为红色，将当前节点设置为祖父节点，之后跳过此次循环以新的节点迭代向上更新
                if (uncle != null && uncle.isColor() == RED) {
                    setBlack(father);
                    setBlack(uncle);
                    setRed(grandFather);
                    node = grandFather;
                    continue;
                }
                //当插入节点为父节点的右节点时，左旋并将插入节点和父节点交换
                if (node == father.getRight()) {
                    leftRotate(father);
                    TreeNode tmp = node;
                    node = father;
                    father = tmp;
                }
                setBlack(father);
                setRed(grandFather);
                rightRotate(grandFather);
            } else {
                //当父节点为祖父节点的右支时
                TreeNode uncle = grandFather.getLeft();
                if (uncle != null && uncle.isColor() == RED) {
                    setBlack(father);
                    setBlack(uncle);
                    setRed(grandFather);
                    node = grandFather;
                    continue;
                }
                if (node == father.getLeft()) {
                    rightRotate(father);
                    TreeNode tmp = node;
                    node = father;
                    father = tmp;
                }
                setBlack(father);
                setRed(grandFather);
                leftRotate(grandFather);
            }
        }
        setBlack(root);
    }

    /**
     * 删除对应key
     * @param key
     */
    public void delete(int key) {
        delete(query(key));
    }

    /**
     * 删除对应节点
     * @param node
     */
    private void delete(TreeNode node) {
        if (node == null) {
            return;
        }
        //左、右节点不为空
        if (node.getLeft() != null && node.getRight() != null) {
            TreeNode replaceNode = node;
            TreeNode tmp = node.getRight();
            //循环查询出当前节点右边分支的最小节点，用replaceNode接收
            while (tmp != null) {
                replaceNode = tmp;
                tmp = tmp.getLeft();
            }
            //将当前节点的key与前面查找到的最小节点replaceNode的key值交换，之后对该replaceNode进行迭代
            int t = replaceNode.getKey();
            replaceNode.setKey(node.getKey());
            node.setKey(t);
            delete(replaceNode);
            return;
        }
        TreeNode replaceNode = null;
        //用replaceNode接收当前节点的左节点或者右节点
        if (node.getLeft() != null) {
            replaceNode = node.getLeft();
        } else {
            replaceNode = node.getRight();
        }

        TreeNode parent = node.getParent();
        if (parent == null) {
            root = replaceNode;
            if (replaceNode != null) {
                replaceNode.setParent(null);
            }
        } else {
            if (replaceNode != null) {
                replaceNode.setParent(parent);
            }
            if (parent.getLeft() == node) {
                parent.setLeft(replaceNode);
            } else {
                parent.setRight(replaceNode);
            }
        }
        if (node.isColor() == BLACK) {
            removeFix(parent, replaceNode);
        }

    }

    /**
     * 多余的颜色在node里
     * @param father
     * @param node
     */
    private void removeFix(TreeNode father, TreeNode node) {
        while ((node == null || node.isColor() == BLACK) && node != root) {
            if (father.getLeft() == node) {
                //S为P的左儿子的情况，如之前的分析
                TreeNode brother = father.getRight();
                if (brother != null && brother.isColor() == RED) {
                    setRed(father);
                    setBlack(brother);
                    leftRotate(father);
                    brother = father.getRight();
                }
                if (brother == null || (isBlack(brother.getLeft()) && isBlack(brother.getRight()))) {
                    setRed(brother);
                    node = father;
                    father = node.getParent();
                    continue;
                }
                if (isRed(brother.getLeft())) {
                    setBlack(brother.getLeft());
                    setRed(brother);
                    rightRotate(brother);
                    brother = brother.getParent();
                }

                brother.setColor(father.isColor());
                setBlack(father);
                setBlack(brother.getRight());
                leftRotate(father);
                node = root;//跳出循环
            } else {                         //S为P的右儿子的情况，对称操作
                TreeNode brother = father.getLeft();
                if (brother != null && brother.isColor() == RED) {
                    setRed(father);
                    setBlack(brother);
                    rightRotate(father);
                    brother = father.getLeft();
                }
                if (brother == null || (isBlack(brother.getLeft()) && isBlack(brother.getRight()))) {
                    setRed(brother);
                    node = father;
                    father = node.getParent();
                    continue;
                }
                if (isRed(brother.getRight())) {
                    setBlack(brother.getRight());
                    setRed(brother);
                    leftRotate(brother);
                    brother = brother.getParent();
                }

                brother.setColor(father.isColor());
                setBlack(father);
                setBlack(brother.getLeft());
                rightRotate(father);
                node = root;//跳出循环
            }
        }

        if (node != null) {
            node.setColor(BLACK);
        }
    }

    private boolean isBlack(TreeNode node) {
        if (node == null) {
            return true;
        }
        return node.isColor() == BLACK;
    }

    private boolean isRed(TreeNode node) {
        if (node == null) {
            return false;
        }
        return node.isColor() == RED;
    }

    private void leftRotate(TreeNode node) {
        //得到被左旋节点的右节点和父节点
        TreeNode right = node.getRight();
        TreeNode parent = node.getParent();
        //如果父节点为空，将右子节点设为根节点，并将该根节点的父节点设为null
        if (parent == null) {
            root = right;
            right.setParent(null);
        } else {
            //如果父节点的左节点不为空且该左子节点为当前节点，则将当前节点的右子节点设为父节点的左节点
            if (parent.getLeft() != null && parent.getLeft() == node) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            right.setParent(parent);
        }
        node.setParent(right);
        node.setRight(right.getLeft());
        if (right.getLeft() != null) {
            right.getLeft().setParent(node);
        }
        right.setLeft(node);
    }

    private void rightRotate(TreeNode node) {
        //得到被右旋节点的左子节点和父节点
        TreeNode left = node.getLeft();
        TreeNode parent = node.getParent();
        //如果父节点为空，则以左子节点为根节点，并将当前节点作为左子节点的右节点
        if (parent == null) {
            root = left;
            left.setParent(null);
        } else {
            //如果父节点的左节点不为空且当前节点等于父节点的左节点，将当前节点的左子节点设置为父节点的左节点
            if (parent.getLeft() != null && parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                //将当前节点的左子节点设置为父节点的右节点
                parent.setRight(left);
            }
            left.setParent(parent);
        }
        //将当前节点的父节点设置为之前的左子节店
        node.setParent(left);
        node.setLeft(left.getRight());
        if (left.getRight() != null) {
            left.getRight().setParent(node);
        }
        left.setRight(node);
    }

    private void setBlack(TreeNode node) {
        node.setColor(BLACK);
    }

    private void setRed(TreeNode node) {
        node.setColor(RED);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.println(node);
        inOrder(node.getRight());
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(6);
        tree.insert(4);
        tree.insert(8);
        tree.insert(2);
        tree.insert(3);
        tree.delete(3);
        System.out.println(tree);
    }
}
