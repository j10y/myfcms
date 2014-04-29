package org.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobConf;
import org.apache.nutch.indexer.DeleteDuplicates;
import org.apache.nutch.indexer.IndexMerger;
import org.apache.nutch.indexer.Indexer;
import org.apache.nutch.util.HadoopFSUtil;
import org.apache.nutch.util.NutchConfiguration;
import org.apache.nutch.util.NutchJob;

public class IndexTest {

	
	private static String getDate() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf = NutchConfiguration.createCrawlConfiguration();
		JobConf job = new NutchJob(conf);
		Path dir = new Path("crawl");		
		
		Path crawlDb = new Path(dir + "/crawldb");
		Path linkDb = new Path(dir + "/linkdb");
		Path segments = new Path(dir + "/segments");
		Path indexes = new Path(dir + "/indexes");
		Path index = new Path(dir + "/index");
		
		Path tmpDir;
		try {
			tmpDir = job.getLocalPath("crawl" + Path.SEPARATOR + getDate());
			
			FileSystem fs = FileSystem.get(job);
			FileStatus[] fstats = fs.listStatus(segments, HadoopFSUtil.getPassDirectoriesFilter(fs));
			
			DeleteDuplicates dedup = new DeleteDuplicates(conf);
			if (indexes != null) {
				// Delete old indexes
				if (fs.exists(indexes)) {
					System.out.println("Deleting old indexes: " + indexes);
					fs.delete(indexes, true);
				}
				
				// Delete old index
				if (fs.exists(index)) {
					System.out.println("Deleting old merged index: " + index);
					fs.delete(index, true);
				}
			}

			Indexer indexer = new Indexer(conf);
			indexer.index(indexes, crawlDb, linkDb, Arrays.asList(HadoopFSUtil.getPaths(fstats)));

			IndexMerger merger = new IndexMerger(conf);
			if (indexes != null) {
				dedup.dedup(new Path[] { indexes });
				fstats = fs.listStatus(indexes, HadoopFSUtil.getPassDirectoriesFilter(fs));
				merger.merge(HadoopFSUtil.getPaths(fstats), index, tmpDir);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
