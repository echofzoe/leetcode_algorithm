package algorithm.leetcode.tree.TrieTree;

/**
 * 实现 Trie (前缀树)
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 *
 * @author echofzoe
 * @since 2021.4.14
 */
public class Lc_208_实现Trie {

    public static void main(String[] args) {
        Lc_208_实现Trie lc = new Lc_208_实现Trie();

        System.out.println("初始化字典树");
        Trie208 trie = new Trie208();
        System.out.println("插入\"apple\"");
        trie.insert("apple");
        System.out.println("字典树中有\"apple\"吗? " + trie.search("apple"));  // 返回 True
        System.out.println("字典树中有\"app\"吗? " + trie.search("app"));  // 返回 False
        System.out.println("字典树中有\"app\"前缀吗? " + trie.startsWith("app"));  // 返回 True
        System.out.println("插入\"app\"");
        trie.insert("app");
        System.out.println("字典树中有\"app\"吗? " + trie.search("app"));  // 返回 True
    }

}

class Trie208 {

    private Trie208[] children;
    private boolean isWord;

    /**
     * Initialize your data structure here.
     */
    public Trie208() {
        this.children = new Trie208[26];
        this.isWord = false;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie208 node = this;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) node.children[idx] = new Trie208();

            node = node.children[idx];
        }
        node.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie208 node = searchPrefix(word);
        return node != null && node.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie208 searchPrefix(String prefix) {
        Trie208 node = this;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (node.children[idx] == null) return null;

            node = node.children[idx];
        }
        return node;
    }

}