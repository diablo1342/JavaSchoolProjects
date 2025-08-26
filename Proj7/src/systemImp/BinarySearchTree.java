package systemImp;


import java.util.Stack;
import java.util.LinkedList;  //only use this if you assign to a Queue variable
import java.util.Queue;

/**
 * A simple generic Binary Search Tree (BST) implementation
 * that supports basic insertion, traversal operations, etc.
 * This version models a Set: nodes store keys only.
 *
 * @param <K> the key type, which must be Comparable
 */
public class BinarySearchTree<K extends Comparable<K>> {
    
    /**
     * Inner class representing a node in the BST.
     */
    private class Node {
        private K key;
        private Node left, right;

        private Node(K key) {
            this.key = key;
        }
    }

    private Node root;
    
    public boolean add(K key) {
    	//check edge cases
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        if (root == null) {
            root = new Node(key);
            return true;
        }
        
        
        return add(root, key);
    }

    private boolean add(Node node, K key) {
    	//find the difference between our pointer and the key
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
        	//if key is less than node, and node.left is null, insert key at node.left
            if (node.left == null) {
                node.left = new Node(key);
                
                return true;
            } else {
                return add(node.left, key);
            }
        } else if (cmp > 0) {
        	//do the opposite if key is greater
            if (node.right == null) {
                node.right = new Node(key);
                
                return true;
            } else {
                return add(node.right, key);
            }
        }
        
        //if it is equal, return false
        return false; 
    }

    public String toString() {
        // Check edge cases
        if (root == null) {
            return "Empty Tree";
        }
        
        
        String result = "";
        //make a queue to traverse the tree in level order
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        //make variable to keep track of the level and to see when a level is only null
        int level = 0;
        boolean levelHasNodes = true;
        
        //if levelHasNodes is false or queue is empty, stop the loop
        while (!queue.isEmpty() && levelHasNodes) {
        	
            levelHasNodes = false;
            int levelSize = queue.size();
            result += "Level " + level + "   ";
            
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                //if current is not equal to null, add left and right to the queue while updating toString
                if (current != null) {
                    result += current.key + " ";
                    queue.add(current.left);
                    queue.add(current.right);
                    //if there is a child, level will have nodes
                    if (current.left != null || current.right != null) {
                        levelHasNodes = true;
                    }
                } else {
                	//if it is null, add null to queue instead of stopping because its sibling could not be null
                    result += "null ";
                    queue.add(null);
                    queue.add(null);
                }
            }
            
            //only iterate level if levelHasNOdes is ture
            if (levelHasNodes) {
                result += "\n";
                level++;
            }
        }
        
        return result;
    }

    public String inOrder() {
        return inOrder(root);
    }

    private String inOrder(Node node) {
    	//check edge cases
        if (node == null) return "";
        
        //return in inOrder order (left, root, right)
        return inOrder(node.left) + node.key + " " + inOrder(node.right);
    }

    public String inorderNonRecursive() {
    	//check edge cases
        if (root == null) return "";
        
        //create a stack as this is depth first traversal
        String result = "";
        Stack<Node> stack = new Stack<>();
        Node current = root;
        
        while (current != null || !stack.isEmpty()) {
        	//first, add all the left side elements
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            
            current = stack.pop();
            //then, visit the node
            result += current.key + " ";
            //then, add right
            current = current.right;
        }
        return result;
    }

    public String preOrder() {
        return preOrder(root);
    }

    private String preOrder(Node node) {
        if (node == null) return "";
        //return in preOrder order (root, left, right)
        return node.key + " " + preOrder(node.left) + preOrder(node.right);
    }

    public String preorderNonRecursive() {
        if (root == null) return "";
        
      //create a stack as this is depth first traversal
        String result = "";
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            
            //visit the node
            result += current.key + " ";
            
            //push current.right first because stack is LIFO, so if you pop left will come out since its added last
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
        }
        return result;
    }

    public String postOrder() {
        return postOrder(root);
    }

    private String postOrder(Node node) {
        if (node == null) return "";
        //return in postOrder order (left, right, root)
        return postOrder(node.left) + postOrder(node.right) + node.key + " ";
    }

    public String postorderNonRecursive() {
        if (root == null) return "";
        
        String result = "";
        //create 2 stacks because we need to first convert it into pre order, and then use the 2nd stack to reverse it
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        
        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);
            //add left first this time because we are going to reverse it with 2nd stack
            if (current.left != null) stack1.push(current.left);
            if (current.right != null) stack1.push(current.right);
        }
        
        while (!stack2.isEmpty()) {
        	
            result += stack2.pop().key + " ";
        }
        return result;
    }


    public K min() {
    	//check edge cases
        if (root == null) return null;
        
        //min is leftmost, so just keep going left until null
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.key;
    }

    public K max() {
    	//check edge cases
        if (root == null) return null;
        Node current = root;
        
        //max is rightmost, so just keep going right until null
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    public boolean remove(K key, boolean preferLeft) {
    	//check edge cases
        if (key == null || root == null) return false;
        
        
        Node parent = null;
        Node current = root;
        boolean isLeftChild = false;
        
        //find the node to remove and its parent
        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
        }
        
        //if there is no node to remove, return false
        if (current == null) return false; 
        
        //if node has no children, just remove its parents link to it
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        
        //if node has 1 child, remove its link to the child and make its parent link to the child
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        
        //if node has 2 children
        else {
            Node replacement;
            Node replacementParent = current;
            
            if (preferLeft) {
                //since we preferLeft, find max in left subtree
                replacement = current.left;
                while (replacement.right != null) {
                    replacementParent = replacement;
                    replacement = replacement.right;
                }
                
                //if the max is not the first node in the left subtree, make replacementParent.right link to replacement.left
                if (replacementParent != current) {
                    replacementParent.right = replacement.left;
                } else { //otherwise, do the opposite
                    replacementParent.left = replacement.left;
                }
            } else {
                //if we preferRight, do the same as left but adjust accordingly
                replacement = current.right;
                while (replacement.left != null) {
                    replacementParent = replacement;
                    replacement = replacement.left;
                }
                
                if (replacementParent != current) {
                    replacementParent.left = replacement.right;
                } else {
                    replacementParent.right = replacement.right;
                }
            }
            
            //update the key
            current.key = replacement.key;
        }
        
        return true;
    }

    


    public boolean isPerfect() {
    	//check edge cases
        if (root == null) return true;
        int depth = -1;
        Node node = root;
        //find depth of a leaf node to check if level = depth for all nodes
        while (node != null) {
            depth++;
            node = node.left;
        }
        return isPerfect(root, 0, depth);
    }

    private boolean isPerfect(Node node, int level, int depth) {
    	
    	//if node is null, then return true if level = depth, false if it doesnt
        if (node == null) {
        	return level == depth;
        }
        
        //same if node.left equal null and node.right equals null
        if (node.left == null && node.right == null) {
        	return level == depth;
        }
        
        //if only one of them is null, then return false because it would mean only one child
        if (node.left == null || node.right == null) {
        	return false;
        }
        
        //return twice to account for both left and right subTrees
        return isPerfect(node.left, level + 1, depth) && 
               isPerfect(node.right, level + 1, depth);
    }
    
    
    
    public boolean isComplete() {
    	//check edge cases
        if (root == null) return true;
        
        //make a queue so we can traverse level order
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean end = false;
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == null) {
                end = true;
            } else {
            	//if end is true, that means we have seen a null node before. 
            	//if we see a null node and then a non null node, then that means the tree is not complete, so return false
                if (end) return false;
                
                //otherwise, add to queue
                queue.add(current.left);
                queue.add(current.right);
            }
        }
        return true;
    }

    public boolean isFull() {
        return isFull(root);
    }

    private boolean isFull(Node node) {
        if (node == null) {
        	return true;
        }
        if (node.left == null && node.right == null) {
        	return true;
        }
        //keep going through the tree until we find a node that is null or with null children
        //if a node only has one null child, it is not full, so we return false
        if (node.left != null && node.right != null) {
            return isFull(node.left) && isFull(node.right);
        }
        return false;
    }
}
    


