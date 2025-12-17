package LRU;

import java.util.HashMap;
import java.util.Map;

public class lru {
    class LinkNode{
        int key;
        int value;
        LinkNode pre;
        LinkNode next;
        public LinkNode(){};
        public LinkNode(int key,int value){}
    }
    public int size;
    public int capacity;
    public LinkNode head,tail;
    public Map<Integer,LinkNode> cache;

    //初始化lru缓存
    public void LRUCache(int capacity) {
        //cache哈希表
        cache = new HashMap<>();
        //伪头节点尾节点
        head = new LinkNode();
        tail = new LinkNode();
        head.next = tail;
        tail.pre = head;
        //参数初始化
        this.capacity = capacity;
        size = 0;
    }

    public int get(int key) {
        if(!cache.containsKey(key)){
            return -1;
        }else{
            LinkNode node =  cache.get(key);
            refresh(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        if(size >= capacity){
            LinkNode del = tail.pre;
            cache.remove(del.key);
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
            cache.put(key, del);
            LinkNode node = head.next;
            LinkNode n = new  LinkNode(key,value);
            head.next = n;
            n.pre = head;
            n.next = node;
            node.pre = n;
        }else{
            LinkNode node = head.next;
            LinkNode n = new LinkNode(key, value);
            head.next = n;
            n.pre = head;
            n.next = node;
            node.pre = n;
            cache.put(key, n);
            size++;
        }
    }

    //刷新缓存，将对应节点移动至头部
    public void refresh(LinkNode node){
        remove(node);
        LinkNode p = head.next;
        head.next = node;
        node.pre = head;
        node.next = p;
        p.pre = node;
    }

    //删除该节点
    public void remove(LinkNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}
