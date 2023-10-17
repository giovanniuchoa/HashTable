import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    public T search(T value) {
        return searchRec(root, value);
    }

    public T delete(T value) {
        DeleteResult result = deleteRec(root, value);
        root = result.node;
        return result.value;
    }

    public List<T> inOrderTraversal() {
        List<T> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    private Node<T> insertRec(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }

        if (value.compareTo(root.value) < 0) {
            root.left = insertRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    private T searchRec(Node<T> root, T value) {
        if (root == null) {
            return null;
        }

        if (value.compareTo(root.value) == 0) {
            return root.value;
        } else if (value.compareTo(root.value) < 0) {
            return searchRec(root.left, value);
        } else {
            return searchRec(root.right, value);
        }
    }

    private DeleteResult deleteRec(Node<T> root, T value) {
        if (root == null) {
            return new DeleteResult(null, null);
        }

        if (value.compareTo(root.value) < 0) {
            DeleteResult result = deleteRec(root.left, value);
            root.left = result.node;
            return new DeleteResult(root, result.value);
        } else if (value.compareTo(root.value) > 0) {
            DeleteResult result = deleteRec(root.right, value);
            root.right = result.node;
            return new DeleteResult(root, result.value);
        } else {
            if (root.left == null) {
                return new DeleteResult(root.right, root.value);
            } else if (root.right == null) {
                return new DeleteResult(root.left, root.value);
            }

            T minValue = minValue(root.right);
            root.value = minValue;
            DeleteResult result = deleteRec(root.right, minValue);
            root.right = result.node;
            return new DeleteResult(root, result.value);
        }
    }

    private T minValue(Node<T> root) {
        T minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }

    private void inOrderTraversal(Node<T> root, List<T> result) {
        if (root != null) {
            inOrderTraversal(root.left, result);
            result.add(root.value);
            inOrderTraversal(root.right, result);
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private class DeleteResult {
        private Node node;
        private T value;

        public DeleteResult(Node node, T value) {
            this.node = node;
            this.value = value;
        }
    }
}
