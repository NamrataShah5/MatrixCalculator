import java.util.*;
public class List{
        private class ListNode {
                ListNode next;
                ListNode prev;
                Object data;
                public ListNode(Object data) {
                        this.data=data;
                        prev=null;
                        next=null;
                }
                public boolean equals(Object x) {
                        boolean equal = false;
                        ListNode t;
                        if(x instanceof ListNode) {
                                t=(ListNode)x;
                                equal=(this.data.equals(t.data));
                        }
                        return equal;
                }
        }
        private ListNode back=null;
        private ListNode front=null;
        private ListNode cursor=null;
        private int length=0;
        int index = -1;
        int length() {
                return length;
        }
        int index() {
                if(cursor==null) { return -1; }
                return index;
        }
        Object front() {
                if(length()>0) {
                        return front.data;
                }
                return null;
        }
        Object back() {
                if(length()>0) {
                        return back.data;
                }
                return null;
        }
        Object get() {  // returns cursor element
                if(index()>=0 && length()>0) {
                        return cursor.data;
                }
                return null;
        }
        public boolean equals(Object x) {
            boolean equal=false;
            List L;
            ListNode A, B;
            if(x instanceof List) {
                    L=(List) x;
                    A=this.front;
                    B=L.front;
                    equal=(this.length==L.length);
                    while(equal && A!=null) {
                            equal=A.equals(B);
                            A=A.next;
                            B=B.next;
                    }

            }
            return equal;
    }

    void clear() {
            front=back=null;
            length=0;
            index=-1;
    }
    void movePrev() {
            if(cursor!=null) {
                    if(cursor.prev!=null) {
                            cursor=cursor.prev;
                            index--;
                    }
                    else {
                            cursor=null;
                    }
            }
    }
    void moveNext() {
            if(cursor!=null) {
                    if(cursor.next!=null) {
                            cursor=cursor.next;
                            index=index()+1;
                    }
                    else {
                            cursor=null;
                            index = -1;
                    }
            }
    }
    void moveFront() {
        if(front!=null) {
                cursor=front;
                index=0;
        }
}
void moveBack() {
        if(back!=null) {
                cursor=back;
                index=length()-1;
        }
}
void prepend(Object entry) {
        ListNode node = new ListNode(entry);
        if(front!=null) {
                node.next=front;
                node.prev=null;
                front.prev=node;
        }
        length++;
        if(cursor!=null) {
                index++;
        }
        if(length == 1){ back=node; }
        if(length>1) {
                while(back.next!=null) {
                        back=back.next;
                }
        }
        front=node;
}
void append(Object entry) {
        ListNode node = new ListNode(entry);
        if(back!=null) {
                back.next=node;
                node.prev=back;
                node.next=null;
        }
        back=node;
        length++;
        if (length == 1) front=node;
}
void insertBefore(Object entry) {
    ListNode node = new ListNode(entry);
    if(length()>0 && index>=0) {
            if(cursor!=null) {
                    node.next=cursor;
                    if(cursor.prev!=null) {
                            node.prev=cursor.prev;
                            cursor.prev.next=node;
                            cursor.prev=node;
                    }
                    else {
                            node.prev=null;
                            cursor.prev=node;
                            front = node;
                    }
                    index++;
                    length++;
            }
    }
}
void insertAfter(Object entry) {
    ListNode node = new ListNode(entry);
    if(length()>0 && index>=0) {
            if(cursor!=null) {
                    node.prev=cursor;
                    if(cursor.next!=null) {
                            node.next=cursor.next;
                            cursor.next=node;
                            cursor.next.prev=node;
                    }
                    else {
                            cursor.next=node;
                            node.next=null;
                            back=node;
                    }
                    length++;
                    if(length==1) { front=node; }
            }
    }
}
void deleteFront() {
    if(length()>0) {
            if(front!=null && cursor!=null) {
                    if(cursor==front) {
                            cursor=null;
                            index=-1;
                    }
                    else { index--; }
            }
            if(front!=null) {
                    if(front.next!=null) {
                            front=front.next;
                            front.prev=null;
                    }
                    else { front=null; }
            }
            length--;
            if(length==1) { front = back; }
            if(length<0) { length=0; }
    }
}
void deleteBack() {
    if(length()>0) {
            if(cursor!=null && back!=null) {
                    if(cursor==back) { cursor=null; index=-1; }
                    back=back.prev;
                    back.next=null;

            }
    }
    length--;
    if(length<0) { length=0; }
    if(length==1) { back = front; }
}
void delete() {
    if(cursor==null) { return; }
    if(index()>=0) {
            if (length() == 1) {clear(); return;}
            if (cursor==front) { front=front.next; front.prev=null;}
            else if(cursor==back) { back=back.prev; back.next=null; }
            else {
                    cursor.prev.next=cursor.next;
                    cursor.next.prev=cursor.prev;
            }
            cursor=null;
            length--;
            index=-1;
    }
}
public String toString() {
    String p = new String();
    ListNode t=front;
    while(t!=null) {
            p=p+String.valueOf(t.data);
            if(t.next!=null) { p=p+" "; }
            t=t.next;
    }
    return p;
}


}
