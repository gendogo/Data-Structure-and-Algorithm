package datastructure;

/**
 * @Description:
 * @Author: whj
 * @Date: 2023-12-12 11:17
 */
public class Trie {

    class TrieNode{
        public int pass;
        public int end;
        private TrieNode[] next;

        public TrieNode() {
            this.pass = 0;
            this.end = 0;
            this.next = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        node.pass++;
        for (int i = 0,path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (node.next[path] == null) {
                node.next[path] = new TrieNode();
            }
            node = node.next[path];
            node.pass++;
        }
        node.end++;
    }

    // 如果之前word插入过前缀树，那么此时删掉一次
    // 如果之前word没有插入过前缀树，那么什么也不做
    public void erase(String word) {
        if (countWordsEqualTo(word) > 0) {
            TrieNode node = root;
            node.pass--;
            for (int i = 0,path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (--node.next[path].pass == 0) {
                    node.next[path] = null;
                    return;
                }
                node = node.next[path];
            }
            node.end--;
        }
    }

    // 查询前缀树里，word单词出现了几次
    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (int i = 0,path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (node.next[path] == null) {
                return 0;
            }
            node = node.next[path];
        }
        return node.end;
    }

    // 查询前缀树里，有多少单词以pre做前缀
    public int countWordsStartingWith(String pre) {
        TrieNode node = root;
        for (int i = 0, path; i < pre.length(); i++) {
            path = pre.charAt(i) - 'a';
            if (node.next[path] == null) {
                return 0;
            }
            node = node.next[path];
        }
        return node.pass;
    }


}
