 package com.zving.search;
 
 import org.apache.lucene.search.Similarity;
 
 public class ZvingSimilarity extends Similarity
 {
   private static final long serialVersionUID = 1L;
 
   public float coord(int overlap, int maxOverlap)
   {
     float overlap2 = (float)Math.pow(2.0D, overlap);
     float maxOverlap2 = (float)Math.pow(2.0D, maxOverlap);
     return (overlap2 / maxOverlap2);
   }
 
   public float idf(int docFreq, int numDocs) {
     return (float)(Math.log(numDocs / (docFreq + 1)) + 1.0D);
   }
 
   public float lengthNorm(String fieldName, int numTokens)
   {
     return 1.0F;
   }
 
   public float queryNorm(float sumOfSquaredWeights) {
     return (float)(1.0D / Math.sqrt(sumOfSquaredWeights));
   }
 
   public float sloppyFreq(int distance) {
     return (1.0F / (distance + 1));
   }
 
   public float tf(float freq) {
     return (float)Math.sqrt(freq);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.ZvingSimilarity
 * JD-Core Version:    0.5.3
 */