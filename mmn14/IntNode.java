package mmn14;


public class IntNode {
  private int _value;
  private IntNode _next;

  public IntNode(int val, IntNode n) {
    _value = val;
    _next = n;
  }

  public IntNode(int val) {
    _value = val;
    _next = null;
  }

  public int getValue() {
    return _value;
  }

  public IntNode getNext() {
    return _next;
  }

  public void setValue(int v) {
    _value = v;
  }

  public void setNext(IntNode node) {
    _next = node;
  }

}

public class IntList {
  private IntNode _head;

  public IntList() {
    _head = null;
  }

  public void addToEnd(int num) {
    // adds num at the end of the list
    IntNode node = new IntNode(num);
    if (_head == null)
      _head = node;
    else {
      IntNode ptr = _head;
      while (ptr.getNext() != null)
        ptr = ptr.getNext();
      ptr.setNext(node);
    }
  }
}

public String toString() {
  String s = "";
  IntNode temp = _head;
  while (temp != null) {
    s = s + temp.getValue() + " --> ";
    temp = temp.getNext();
  }
  s += " null";
  return s;
}

public boolean subListSum(int num) {
  int sum = 0;
  IntNode subListHead = _head;
  IntNode subListTail = _head;

  if (subListHead == null) {
    return false;
  }

  while (sum != num && subListTail != null) {
    sum += subListTail.getValue();
    if (sum == num) {
      return true;
    } else if (sum > num) {
      sum -= subListHead.getValue();
      subListHead = subListHead.getNext();
    }
    subListTail = subListTail.getNext();
  }
  if (sum == num)
    return true;
  return false;
}



public IntNode averageNode() {
  if (_head == null || _head.getNext() == null) {
      return null;

  }
  double avg1 = _head.getValue();

  int length1 = 1;
  int length2 = 0;
  double sum1 = _head.getValue();
  double sum2 = 0;
  double max = 0;
  IntNode current = _head.getNext();
  IntNode divnode = _head;

  while (current != null) {
      sum2 += current.getValue();
      length2 += 1;
      current = current.getNext();

  }
  double avg2 = sum2 / length2;
  current = _head;
  if (Math.abs(avg1 - avg2) >= max) {
      max = Math.abs(avg1 - avg2);

  }
  while (current.getNext().getNext() != null) {
      current = current.getNext();
      sum1 += current.getValue();
      sum2 -= current.getValue();
      length1++;
      length2--;
      avg1 = sum1 / length1;
      avg2 = sum2 / length2;
  }
  if (Math.abs(avg1 - avg2) >= max) {
      max = Math.abs(avg1 - avg2);

  }
  return current;
}