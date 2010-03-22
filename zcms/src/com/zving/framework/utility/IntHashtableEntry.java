 package com.zving.framework.utility;
 
 class IntHashtableEntry
 {
   int hash;
   int key;
   Object value;
   IntHashtableEntry next;
 
   protected Object clone()
   {
     IntHashtableEntry entry = new IntHashtableEntry();
     entry.hash = this.hash;
     entry.key = this.key;
     entry.value = this.value;
     entry.next = ((this.next != null) ? (IntHashtableEntry)this.next.clone() : null);
     return entry;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.IntHashtableEntry
 * JD-Core Version:    0.5.3
 */