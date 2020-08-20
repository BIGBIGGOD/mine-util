package com.mine.utils.algorithmutil.btree;

import lombok.Data;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 红黑树节点
 * @DATE 2020/7/14 0014 14:19
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Data
public class TreeNode {

    private final boolean RED = false;
    private final boolean BLACK = true;
    private int key;
    private boolean color;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    public TreeNode(int key) {
        this.key = key;
        this.color = RED;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "key=" + key +
                ", color=" + color +
                '}';
    }
}
