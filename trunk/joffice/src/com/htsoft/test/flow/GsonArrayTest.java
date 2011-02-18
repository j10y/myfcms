 package com.htsoft.test.flow;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import java.io.PrintStream;
 
 public class GsonArrayTest
 {
   private String name;
   private int age;
 
   public String getName()
   {
     return this.name;
   }
 
   public void setName(String name)
   {
     this.name = name;
   }
 
   public int getAge()
   {
     return this.age;
   }
 
   public void setAge(int age)
   {
     this.age = age;
   }
 
   public String toString() {
     return "name:" + this.name + " age:" + this.age;
   }
 
   public static void main(String[] args)
   {
     Gson gson = new GsonBuilder().serializeNulls().create();
 
     String obj2 = "[{name:'ding',age:''},{name:'king',age:'1'}]";
 
     GsonArrayTest[] test2 = (GsonArrayTest[])gson.fromJson(obj2, [Lcom.htsoft.test.flow.GsonArrayTest.class);
     for (GsonArrayTest a : test2)
       System.out.println("a:" + a.toString());
   }
 }


 
 
 