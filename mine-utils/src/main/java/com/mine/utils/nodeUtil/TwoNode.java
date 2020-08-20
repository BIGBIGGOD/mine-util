package com.mine.utils.nodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树
 * Created by jiangqd on 2019/3/17.
 */
public class TwoNode {
    public TwoNode leftNode;
    public TwoNode rightNode;
    public Object value;

    /**
     *  二叉树插入
     */

    public void add(int value) {
        if (this.value == null){
            this.value = value;
        }else {
            if (((int)value - (int)this.value) <= 0){
                if (leftNode == null){
                    leftNode = new TwoNode();
                }
                leftNode.add(value);
            }else {
                if (rightNode == null) {
                    rightNode = new TwoNode();
                }
                rightNode.add(value);
            }
        }
    }

    /**
     * 二叉树遍历
     */
    public List<Object> ergodic() {
        List<Object> list = new ArrayList<>();
        //左节点
        if (leftNode != null) {
            list.addAll(leftNode.ergodic());
        }
        //中间节点
        list.add(value);
        //右节点
        list.addAll(rightNode.ergodic());
        return list;
    }
}
