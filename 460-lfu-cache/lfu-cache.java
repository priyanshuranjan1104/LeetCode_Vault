class LFUCache {
  public LFUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    if (!keyToVal.containsKey(key))
      return -1;

    final int freq = keyToFreq.get(key);
    freqToLRUKeys.get(freq).remove(key);
    if (freq == minFreq && freqToLRUKeys.get(freq).isEmpty()) {
      freqToLRUKeys.remove(freq);
      ++minFreq;
    }
    putFreq(key, freq + 1);
    return keyToVal.get(key);
  }

  public void put(int key, int value) {
    if (capacity == 0)
      return;
    if (keyToVal.containsKey(key)) {
      keyToVal.put(key, value);
      get(key); 
      return;
    }

    if (keyToVal.size() == capacity) {
      final int keyToEvict = freqToLRUKeys.get(minFreq).iterator().next();
      freqToLRUKeys.get(minFreq).remove(keyToEvict);
      keyToVal.remove(keyToEvict);
    }

    minFreq = 1;
    putFreq(key, minFreq);    
    keyToVal.put(key, value); 
  }

  public int capacity;
  public int minFreq = 0;
  public Map<Integer, Integer> keyToVal = new HashMap<>();
  public Map<Integer, Integer> keyToFreq = new HashMap<>();
  public Map<Integer, LinkedHashSet<Integer>> freqToLRUKeys = new HashMap<>();

  public void putFreq(int key, int freq) {
    keyToFreq.put(key, freq);
    freqToLRUKeys.putIfAbsent(freq, new LinkedHashSet<>());
    freqToLRUKeys.get(freq).add(key);
  }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */