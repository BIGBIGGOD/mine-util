package com.mine.utils.algorithmutil.balancetree;

import lombok.Data;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/9/8 0008 19:57
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Data
public class AVLNode {

    public AVLNode parent;
    public AVLNode leftChild, rightChild;
    public int data;

    public AVLNode(AVLNode parent, AVLNode leftChild, AVLNode rightChild, int data) {
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.data = data;
    }

    public AVLNode(int data) {
        this(null, null, null, data);
    }

    public AVLNode(AVLNode parent, int data) {
        this.parent = parent;
        this.data = data;
    }

}

