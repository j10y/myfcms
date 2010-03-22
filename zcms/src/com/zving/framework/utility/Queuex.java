 package com.zving.framework.utility;
 
 import java.io.Serializable;
 import java.util.Arrays;
 import java.util.Comparator;
 
 public class Queuex
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private Object[] arr;
   private int max;
   private int pos;
   private int size;
   private ExitEventListener listener;
 
   public Queuex(int max)
   {
     this.max = max;
     this.arr = new Object[max];
   }
 
   public synchronized void sort(Comparator comparator) {
     Arrays.sort(this.arr, comparator);
     this.pos = 0;
   }
 
   public synchronized Object get(int index) {
     if (this.size <= index) {
       throw new RuntimeException("超出队列索引长度：" + index);
     }
     return this.arr[((this.pos + index) % this.max)];
   }
 
   public synchronized Object push(Object o) {
     if (this.size == this.max) {
       Object r = this.arr[this.pos];
       this.arr[this.pos] = o;
       this.pos = ((this.pos + 1) % this.max);
       if (this.listener != null) {
         this.listener.onExit(null, r);
       }
       return r;
     }
     this.arr[((this.pos + this.size) % this.max)] = o;
     this.size += 1;
     return null;
   }
 
   public synchronized boolean contains(Object v)
   {
     for (int i = 0; i < this.arr.length; ++i) {
       if (this.arr[i] == v) {
         return true;
       }
     }
     return false;
   }
 
   public synchronized Object remove(Object v) {
     for (int i = 0; i < this.size; ++i) {
       if (get(i) == v) {
         return remove(i);
       }
     }
     return null;
   }
 
   public synchronized Object remove(int index) {
     if (this.size <= index) {
       throw new RuntimeException("超出队列索引长度：" + index);
     }
     Object r = get(index);
     index = (index + this.pos) % this.max;
     Object[] newarr = new Object[this.max];
     if (this.pos == 0) {
       System.arraycopy(this.arr, 0, newarr, 0, index);
       System.arraycopy(this.arr, index + 1, newarr, index, this.max - index - 1);
     } else {
       if (index >= this.pos) {
         System.arraycopy(this.arr, this.pos, newarr, 0, index - this.pos);
         System.arraycopy(this.arr, index + 1, newarr, index - this.pos, this.max - index - 1);
         System.arraycopy(this.arr, 0, newarr, this.max - this.pos - 1, this.pos);
       } else {
         System.arraycopy(this.arr, this.pos, newarr, 0, this.max - this.pos);
         System.arraycopy(this.arr, 0, newarr, this.max - this.pos, index);
         System.arraycopy(this.arr, index + 1, newarr, this.max - this.pos + index, this.pos - index);
       }
       this.pos = 0;
     }
     this.arr = newarr;
     this.size -= 1;
     return r;
   }
 
   public synchronized void clear() {
     this.arr = new Object[this.max];
     this.size = 0;
   }
 
   public int size() {
     return this.size;
   }
 
   public void setExitEventListener(ExitEventListener listener)
   {
     this.listener = listener;
   }
 
   public String toString() {
     StringBuffer sb = new StringBuffer();
     sb.append("{");
     for (int i = 0; (i < 20) && (i < this.size); ++i) {
       if (i != 0) {
         sb.append(" , ");
       }
       sb.append(get(i));
     }
     sb.append("}");
     return sb.toString();
   }
 
   public int getMax() {
     return this.max;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.Queuex
 * JD-Core Version:    0.5.3
 */