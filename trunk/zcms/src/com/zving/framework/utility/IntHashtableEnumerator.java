 package com.zving.framework.utility;
 
 import java.util.Enumeration;
 import java.util.NoSuchElementException;
 
 class IntHashtableEnumerator
   implements Enumeration
 {
   boolean keys;
   int index;
   IntHashtableEntry[] table;
   IntHashtableEntry entry;
 
   IntHashtableEnumerator(IntHashtableEntry[] table, boolean keys)
   {
     this.table = table;
     this.keys = keys;
     this.index = table.length;
   }
 
   public boolean hasMoreElements()
   {
     // Byte code:
     //   0: aload_0
     //   1: getfield 33	com/zving/framework/utility/IntHashtableEnumerator:entry	Lcom/zving/framework/utility/IntHashtableEntry;
     //   4: ifnull +24 -> 28
     //   7: iconst_1
     //   8: ireturn
     //   9: aload_0
     //   10: aload_0
     //   11: getfield 21	com/zving/framework/utility/IntHashtableEnumerator:table	[Lcom/zving/framework/utility/IntHashtableEntry;
     //   14: aload_0
     //   15: getfield 25	com/zving/framework/utility/IntHashtableEnumerator:index	I
     //   18: aaload
     //   19: dup_x1
     //   20: putfield 33	com/zving/framework/utility/IntHashtableEnumerator:entry	Lcom/zving/framework/utility/IntHashtableEntry;
     //   23: ifnull +5 -> 28
     //   26: iconst_1
     //   27: ireturn
     //   28: aload_0
     //   29: dup
     //   30: getfield 25	com/zving/framework/utility/IntHashtableEnumerator:index	I
     //   33: dup_x1
     //   34: iconst_1
     //   35: isub
     //   36: putfield 25	com/zving/framework/utility/IntHashtableEnumerator:index	I
     //   39: ifgt -30 -> 9
     //   42: iconst_0
     //   43: ireturn
   }
 
   public Object nextElement()
   {
     // Byte code:
     //   0: aload_0
     //   1: getfield 33	com/zving/framework/utility/IntHashtableEnumerator:entry	Lcom/zving/framework/utility/IntHashtableEntry;
     //   4: ifnonnull +34 -> 38
     //   7: aload_0
     //   8: dup
     //   9: getfield 25	com/zving/framework/utility/IntHashtableEnumerator:index	I
     //   12: dup_x1
     //   13: iconst_1
     //   14: isub
     //   15: putfield 25	com/zving/framework/utility/IntHashtableEnumerator:index	I
     //   18: ifle +20 -> 38
     //   21: aload_0
     //   22: aload_0
     //   23: getfield 21	com/zving/framework/utility/IntHashtableEnumerator:table	[Lcom/zving/framework/utility/IntHashtableEntry;
     //   26: aload_0
     //   27: getfield 25	com/zving/framework/utility/IntHashtableEnumerator:index	I
     //   30: aaload
     //   31: dup_x1
     //   32: putfield 33	com/zving/framework/utility/IntHashtableEnumerator:entry	Lcom/zving/framework/utility/IntHashtableEntry;
     //   35: ifnull -28 -> 7
     //   38: aload_0
     //   39: getfield 33	com/zving/framework/utility/IntHashtableEnumerator:entry	Lcom/zving/framework/utility/IntHashtableEntry;
     //   42: ifnull +42 -> 84
     //   45: aload_0
     //   46: getfield 33	com/zving/framework/utility/IntHashtableEnumerator:entry	Lcom/zving/framework/utility/IntHashtableEntry;
     //   49: astore_1
     //   50: aload_0
     //   51: aload_1
     //   52: getfield 37	com/zving/framework/utility/IntHashtableEntry:next	Lcom/zving/framework/utility/IntHashtableEntry;
     //   55: putfield 33	com/zving/framework/utility/IntHashtableEnumerator:entry	Lcom/zving/framework/utility/IntHashtableEntry;
     //   58: aload_0
     //   59: getfield 23	com/zving/framework/utility/IntHashtableEnumerator:keys	Z
     //   62: ifeq +17 -> 79
     //   65: new 42	java/lang/Integer
     //   68: dup
     //   69: aload_1
     //   70: getfield 44	com/zving/framework/utility/IntHashtableEntry:key	I
     //   73: invokespecial 47	java/lang/Integer:<init>	(I)V
     //   76: goto +7 -> 83
     //   79: aload_1
     //   80: getfield 50	com/zving/framework/utility/IntHashtableEntry:value	Ljava/lang/Object;
     //   83: areturn
     //   84: new 54	java/util/NoSuchElementException
     //   87: dup
     //   88: ldc 56
     //   90: invokespecial 58	java/util/NoSuchElementException:<init>	(Ljava/lang/String;)V
     //   93: athrow
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.IntHashtableEnumerator
 * JD-Core Version:    0.5.3
 */