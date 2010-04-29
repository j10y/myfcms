package cn.org.rapid_framework.generator.annotation;   
  
import java.lang.annotation.Documented;   
import java.lang.annotation.ElementType;   
import java.lang.annotation.Retention;   
import java.lang.annotation.RetentionPolicy;   
import java.lang.annotation.Target;   
  
@Target({ElementType.TYPE,ElementType.FIELD}) 
@Retention(RetentionPolicy.RUNTIME)   
@Documented  
public @interface Description {   
    String value();   
} 
