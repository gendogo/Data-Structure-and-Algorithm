package crawler;

import org.jsoup.nodes.Node;

import java.util.*;

/**
 * @Description: An Iterable class for traversing a DOM tree.
 * @Author: whj
 * @Date: 2024-02-03 13:13
 */
public class WikiNodeIterable implements Iterable<Node>{
    private Node root;

    public WikiNodeIterable(Node root) {
        this.root = root;
    }

    @Override
    public Iterator<Node> iterator() {
        return new WikiNodeIterator(root);
    }

    private class WikiNodeIterator implements Iterator<Node> {
        Deque<Node> stack;

        public WikiNodeIterator(Node node) {
            stack = new ArrayDeque<>();
            stack.push(node);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node node = stack.pop();
            List<Node> nodes = new ArrayList<>(node.childNodes());
            Collections.reverse(nodes);
            for (Node childNode : nodes) {
                stack.push(childNode);
            }
            return node;
        }
    }
}
