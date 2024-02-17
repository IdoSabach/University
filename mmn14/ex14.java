package mmn14;

public class ex14 {

    // q1
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

    // q2
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

    // q1

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
}
