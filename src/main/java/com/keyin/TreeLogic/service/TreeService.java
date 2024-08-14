package com.keyin.TreeLogic.service;

import com.keyin.TreeLogic.model.TreeRecord;
import com.keyin.TreeLogic.util.BinarySearchTree;
import org.springframework.stereotype.Service;

@Service
public class TreeService {

    /**
     * Creates a binary search tree from the given array of numbers.
     * @param numbers The array of numbers to be inserted into the BST.
     * @return A TreeRecord object containing the input numbers and JSON representation of the tree.
     */
    public TreeRecord createBinarySearchTree(int[] numbers) {
        BinarySearchTree bst = new BinarySearchTree();

        for (int num : numbers) {
            bst.insert(num);
        }

        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setInputNumbers(numbers);
        treeRecord.setTreeJson(bst.toJson());

        return treeRecord;
    }
}
