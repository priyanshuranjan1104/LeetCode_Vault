class AllOne {
    public static class Node {
        int count;
        Set<String> keys = new HashSet<>();
        Node prev;
        Node next;

        Node(int count) {
            this.count = count;
        }

        Node insertAfter(Node newNode) {
            newNode.prev = this;
            newNode.next = this.next;
            this.next.prev = newNode;
            this.next = newNode;
            return newNode;
        }

        void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

    public final Node head;                    
    public final Node tail;                   
    public final Map<String, Node> keyToNode; 

    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        keyToNode = new HashMap<>();
    }

    public void inc(String key) {
        Node curr = keyToNode.get(key);

        if (curr == null) {
            Node target = head.next;
            if (target == tail || target.count > 1) {
                target = head.insertAfter(new Node(1));
            }
            target.keys.add(key);
            keyToNode.put(key, target);
        } else {
            int nextCount = curr.count + 1;
            Node target = curr.next;
            if (target == tail || target.count != nextCount) {
                target = curr.insertAfter(new Node(nextCount));
            }
            target.keys.add(key);
            keyToNode.put(key, target);

            curr.keys.remove(key);
            if (curr.keys.isEmpty()) {
                curr.remove();
            }
        }
    }

    public void dec(String key) {
        Node curr = keyToNode.get(key);
        if (curr == null) return;

        curr.keys.remove(key);

        if (curr.count == 1) {
            keyToNode.remove(key);
        } else {
            int prevCount = curr.count - 1;
            Node target = curr.prev;
            if (target == head || target.count != prevCount) {
                target = target.insertAfter(new Node(prevCount));
            }
            target.keys.add(key);
            keyToNode.put(key, target);
        }

        if (curr.keys.isEmpty()) {
            curr.remove();
        }
    }

    public String getMaxKey() {
        return tail.prev == head ? "" : tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        return head.next == tail ? "" : head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */