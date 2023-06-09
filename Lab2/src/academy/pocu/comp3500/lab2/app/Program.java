package academy.pocu.comp3500.lab2.app;

import academy.pocu.comp3500.lab2.LinkedList;
import academy.pocu.comp3500.lab2.Queue;
import academy.pocu.comp3500.lab2.Stack;
import academy.pocu.comp3500.lab2.datastructure.Node;

public class Program {

    public static void main(String[] args) {
        officialTest();
        myTest();
        reverseTest();
        interleaveTest();

        System.out.println("No prob lab 2");
    }

    private static void officialTest() {
        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);

            assert (root.getData() == 10);

            Node next = root.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 12);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.prepend(root, 11);

            assert (root.getData() == 11);

            root = LinkedList.prepend(root, 12);

            assert (root.getData() == 12);

            Node next = root.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.insertAt(root, 0, 11);

            assert (root.getData() == 11);

            root = LinkedList.insertAt(root, 1, 12);

            assert (root.getData() == 11);

            Node next = root.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);

            root = LinkedList.removeAt(root, 0);

            assert (root.getData() == 11);

            root = LinkedList.removeAt(root, 1);

            assert (root.getData() == 11);

            Node next = root.getNextOrNull();

            assert (next.getData() == 13);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            int index = LinkedList.getIndexOf(root, 10);

            assert (index == 0);

            index = LinkedList.getIndexOf(root, 11);

            assert (index == 1);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);

            Node node = LinkedList.getOrNull(root, 0);

            assert (node.getData() == 10);

            node = LinkedList.getOrNull(root, 1);

            assert (node.getData() == 11);
        }

        {
            Node root1 = LinkedList.append(null, 10);

            root1 = LinkedList.append(root1, 11);
            root1 = LinkedList.append(root1, 12);

            Node root2 = LinkedList.append(null, 13);

            root2 = LinkedList.append(root2, 14);
            root2 = LinkedList.append(root2, 15);

            Node newRoot = LinkedList.interleaveOrNull(root1, root2); // newRoot: 10, list: 10 -> 13 -> 11 -> 14 -> 12 -> 15

            assert (newRoot.getData() == 10);

            Node next = newRoot.getNextOrNull();

            assert (next.getData() == 13);

            next = next.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 14);

            next = next.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 15);
        }

        {
            Node root = LinkedList.append(null, 10);

            root = LinkedList.append(root, 11);
            root = LinkedList.append(root, 12);
            root = LinkedList.append(root, 13);
            root = LinkedList.append(root, 14);

            root = LinkedList.reverse(root); // root: 14, list: 14 -> 13 -> 12 -> 11 -> 10

            assert (root.getData() == 14);

            Node next = root.getNextOrNull();

            assert (next.getData() == 13);

            next = next.getNextOrNull();

            assert (next.getData() == 12);

            next = next.getNextOrNull();

            assert (next.getData() == 11);

            next = next.getNextOrNull();

            assert (next.getData() == 10);
        }

        {
            Stack stack = new Stack();

            stack.push(20);
            stack.push(21); // stack: 21
            //        20

            int data = stack.pop();

            assert (data == 21);

            data = stack.pop();

            assert (data == 20);
        }

        {
            Stack stack = new Stack();

            stack.push(20); // stack: 20

            assert (stack.peek() == 20);

            stack.push(21); // stack: 21
            //        20

            assert (stack.peek() == 21);
        }

        {
            Stack stack  = new Stack();

            stack.push(20);
            stack.push(21);

            assert (stack.getSize() == 2);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);

            assert (queue.peek() == 20);

            queue.enqueue(21);

            assert (queue.peek() == 20);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);
            queue.enqueue(21);

            int data = queue.dequeue();

            assert (data == 20);

            data = queue.dequeue();

            assert (data == 21);
        }

        {
            Queue queue = new Queue();

            queue.enqueue(20);
            queue.enqueue(21);

            assert (queue.getSize() == 2);
        }
    }

    private static void myTest() {
        Node root = LinkedList.append(null, 12);

        root = LinkedList.prepend(root, 11);
        root = LinkedList.prepend(root, 10);

        root = LinkedList.insertAt(root, 10, 20);
        root = LinkedList.insertAt(root, 0, 8);
        root = LinkedList.insertAt(root, 1, 9);
        root = LinkedList.insertAt(root, 4, 13);
        // 8 -> 9 -> 10 -> 11 -> 13 -> 12

        Node node = LinkedList.getOrNull(root, 0);
        assert(node.getData() == 8);
        node = LinkedList.getOrNull(root, 1);
        assert(node.getData() == 9);
        node = LinkedList.getOrNull(root, 2);
        assert(node.getData() == 10);
        node = LinkedList.getOrNull(root, 3);
        assert(node.getData() == 11);
        node = LinkedList.getOrNull(root, 4);
        assert(node.getData() == 13);
        node = LinkedList.getOrNull(root, 5);
        assert(node.getData() == 12);
        node = LinkedList.getOrNull(root, 6);
        assert(node == null);

        root = LinkedList.reverse(root);
        assert(root.getData() == 12);

        node = root.getNextOrNull();
        assert(node.getData() == 13);

        node = node.getNextOrNull();
        assert(node.getData() == 11);

        node = node.getNextOrNull();
        assert(node.getData() == 10);

        node = node.getNextOrNull();
        assert(node.getData() == 9);

        node = node.getNextOrNull();
        assert(node.getData() == 8);

        node = node.getNextOrNull();
        assert(node == null);

        root = LinkedList.reverse(root);

        root = LinkedList.removeAt(root, 0);
        // 9 -> 10 -> 11 -> 13 -> 12
        root = LinkedList.removeAt(root, 1);
        // 9 -> 11 -> 13 -> 12
        root = LinkedList.removeAt(root, 2);
        // 9 -> 11  -> 12
        root = LinkedList.removeAt(root, 2);
        // 9 -> 11
        root = LinkedList.removeAt(root, 2);
        // 9 -> 11
    }

    private static void reverseTest() {
            {
                Node root = LinkedList.append(null, 10);

                root = LinkedList.append(root, 11);
                root = LinkedList.append(root, 12);
                root = LinkedList.append(root, 13);
                root = LinkedList.append(root, 14);
                root = LinkedList.append(root, 15);

                Node reverse = LinkedList.reverse(root);
            }

            {
                Node oneNode = new Node(10);

                Node reverse = LinkedList.reverse(oneNode);
                assert (reverse.getData() == 10);
                assert (reverse.getNextOrNull() == null);
            }

            {
                Node root = new Node(10);
                root = LinkedList.append(root, 11);

                Node reverse = LinkedList.reverse(root);

                assert (reverse.getData() == 11);
                Node next = reverse.getNextOrNull();
                assert (next.getData() == 10);
                next = next.getNextOrNull();
                assert (next == null);

                root = LinkedList.reverse(reverse);

                assert (root.getData() == 10);
                next = root.getNextOrNull();
                assert (next.getData() == 11);
                next = next.getNextOrNull();
                assert (next == null);
            }
    }

    private static void interleaveTest() {
        {
            Node root1 = LinkedList.append(null, 10);
            root1 = LinkedList.append(root1, 12);
            root1 = LinkedList.append(root1, 14);

            Node root2 = LinkedList.append(null, 11);
            root2 = LinkedList.append(root2, 13);
            root2 = LinkedList.append(root2, 15);

            Node interleave = LinkedList.interleaveOrNull(root1, root2);

            assert (interleave.getData() == 10);
            Node next = interleave.getNextOrNull();
            assert (next.getData() == 11);
            next = next.getNextOrNull();
            assert (next.getData() == 12);
            next = next.getNextOrNull();
            assert (next.getData() == 13);
            next = next.getNextOrNull();
            assert (next.getData() == 14);
            next = next.getNextOrNull();
            assert (next.getData() == 15);
        }

        {
            Node root1 = LinkedList.append(null, 10);
            root1 = LinkedList.append(root1, 12);
            root1 = LinkedList.append(root1, 14);

            Node root2 = LinkedList.append(null, 11);

            Node interleave = LinkedList.interleaveOrNull(root1, root2);

            assert (interleave.getData() == 10);
            Node next = interleave.getNextOrNull();
            assert (next.getData() == 11);
            next = next.getNextOrNull();
            assert (next.getData() == 12);
            next = next.getNextOrNull();
            assert (next.getData() == 14);
        }

        {
            Node root1 = LinkedList.append(null, 10);

            Node root2 = LinkedList.append(null, 11);
            root2 = LinkedList.append(root2, 13);
            root2 = LinkedList.append(root2, 15);

            Node interleave = LinkedList.interleaveOrNull(root1, root2);

            assert (interleave.getData() == 10);
            Node next = interleave.getNextOrNull();
            assert (next.getData() == 11);
            next = next.getNextOrNull();
            assert (next.getData() == 13);
            next = next.getNextOrNull();
            assert (next.getData() == 15);
        }

        {
            Node root1 = LinkedList.append(null, 10);
            root1 = LinkedList.append(root1, 12);
            root1 = LinkedList.append(root1, 14);

            Node interleave = LinkedList.interleaveOrNull(root1, null);

            assert (interleave.getData() == 10);
            Node next = interleave.getNextOrNull();
            assert (next.getData() == 12);
            next = next.getNextOrNull();
            assert (next.getData() == 14);
        }

        {
            Node root2 = LinkedList.append(null, 11);
            root2 = LinkedList.append(root2, 13);
            root2 = LinkedList.append(root2, 15);

            Node interleave = LinkedList.interleaveOrNull(null, root2);

            assert (interleave.getData() == 11);
            Node next = interleave.getNextOrNull();
            assert (next.getData() == 13);
            next = next.getNextOrNull();
            assert (next.getData() == 15);
        }

        {
            Node interleave = LinkedList.interleaveOrNull(null, null);
            assert (interleave == null);
        }
    }
}
