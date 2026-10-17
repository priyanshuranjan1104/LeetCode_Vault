// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
  public PeekingIterator(Iterator<Integer> iterator) {
    this.iterator = iterator;
    buffer = iterator.hasNext() ? iterator.next() : null;
  }
  public Integer peek() {
    return buffer;
  }
  public Integer next() {
    Integer next = buffer;
    buffer = iterator.hasNext() ? iterator.next() : null;
    return next;
  }
  public boolean hasNext() {
    return buffer != null;
  }
  public Iterator<Integer> iterator;
  public Integer buffer;
}