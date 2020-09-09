package com.mine.utils.algorithmutil.balancetree;

import com.alibaba.fastjson.JSON;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/9/9 0009 11:07
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class AVLTree {

    private AVLNode root;
    private int MAX_LEFT = 2;
    private int MAX_RIGHT = -2;
    private int LEFT = 1;
    private int RIGHT = 2;

    /**
     * 右旋,称为左子节点的右节点
     *
     * @param node
     * @return
     */
    public AVLNode rightRotation(AVLNode node) {
        if (node != null) {
            AVLNode leftChild = node.leftChild;
            node.leftChild = leftChild.rightChild;
            // 如果leftChild的右节点存在，则需将该右节点的父节点指给node节点
            if (leftChild.rightChild != null) {
                leftChild.rightChild.parent = node;
            }
            leftChild.parent = node.parent;
            if (node.parent == null) {
                this.root = leftChild;
            } else if (node.parent.rightChild == node) {  // 即node节点在它原父节点的右子树中
                node.parent.rightChild = leftChild;
            } else if (node.parent.leftChild == node) {
                node.parent.leftChild = leftChild;
            }

            leftChild.rightChild = node;
            node.parent = leftChild;
            return leftChild;
        }

        return null;
    }

    /**
     * 左旋，称为右子节点的左节点
     *
     * @param node
     * @return
     */
    public AVLNode leftRotation(AVLNode node) {
        if (node != null) {
            AVLNode rightChild = node.rightChild;
            node.rightChild = rightChild.leftChild;
            if (rightChild.leftChild != null) {
                rightChild.leftChild.parent = node;
            }
            rightChild.parent = node.parent;
            if (node.parent == null) {
                this.root = rightChild;
            } else if (node.parent.rightChild == node) {
                node.parent.rightChild = rightChild;
            } else if (node.parent.leftChild == node) {
                node.parent.leftChild = rightChild;
            }
            rightChild.leftChild = node;
            node.parent = rightChild;
            return rightChild;
        }

        return null;
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.put(100);
        avlTree.put(99);
        avlTree.put(88);
        avlTree.put(77);
        avlTree.put(66);
        avlTree.put(9);
        avlTree.put(6);
        avlTree.put(2);
        avlTree.put(1);
        avlTree.put(0);
        System.out.println(avlTree.root);
    }
    /**
     * 插入节点
     *
     * @param data
     */
    public void put(int data) {
        putData(root, data);
    }

    private boolean putData(AVLNode node, int data) {
        if (node == null) {
            node = new AVLNode(data);
            root = node;
            return true;
        }
        int t;
        AVLNode p;
        AVLNode temp = node;
        do {
            p = temp;
            t = temp.data - data;
            if (t < 0) {
                temp = temp.rightChild;
            } else if (t > 0) {
                temp = temp.leftChild;
            } else {
                return false;
            }
        } while (temp != null);

        if (t < 0) {
            p.rightChild = new AVLNode(p, data);
        } else if (t > 0) {
            p.leftChild = new AVLNode(p, data);
        }

        rebuild(p); //平衡二叉树的方法
        return true;
    }

    /**
     * 平衡二叉树的方法
     *
     * @param node
     */
    public void rebuild(AVLNode node) {
        while (node != null) {
            if (calcNodeBalanceValue(node) == MAX_LEFT) {
                //左子树高
                fixAfterInsertion(node, LEFT);
            } else if (calcNodeBalanceValue(node) == MAX_RIGHT) {
                //右子树高
                fixAfterInsertion(node, RIGHT);
            }
            node = node.parent;
        }
    }

    /**
     * 计算node节点的BF值
     *
     * @param node
     * @return
     */
    public int calcNodeBalanceValue(AVLNode node) {
        if (node != null) {
            return getHeightByNode(node);
        }
        return 0;
    }

    private int getHeightByNode(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getChildDepth(node.leftChild) - getChildDepth(node.rightChild);
    }

    private int getChildDepth(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getChildDepth(node.leftChild), getChildDepth(node.rightChild));
    }

    /**
     * 调整树的结构
     *
     * @param node
     * @param type
     */
    public void fixAfterInsertion(AVLNode node, int type) {
        if (type == LEFT) {
            AVLNode leftChild = node.leftChild;
            if (leftChild.leftChild != null) {
                //右旋
                rightRotation(node);
            } else if (leftChild.rightChild != null) {
                //左右旋
                leftRotation(leftChild);
                rightRotation(node);
            }
        } else if (type == RIGHT) {
            AVLNode rightChild = node.rightChild;
            if (rightChild.rightChild != null) {
                //左旋
                leftRotation(node);
            } else if (rightChild.leftChild != null) {
                //右左旋
                rightRotation(rightChild);
                leftRotation(node);
            }
        }
    }

}
