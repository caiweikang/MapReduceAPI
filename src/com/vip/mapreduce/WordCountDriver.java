package com.vip.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);
		
		// 1.����Job������������
		job.setJarByClass(WordCountDriver.class);
		// ����Mapper������е���
		job.setMapperClass(WordCountMapper.class);
		// ����Reducer���������
		job.setReducerClass(WordCountReducer.class);
		
		// 2.����Mapper������key������
		job.setMapOutputKeyClass(Text.class);
		// ����Mapper������value����
		job.setMapOutputValueClass(IntWritable.class);
		// ����Reducer������key������
		job.setOutputKeyClass(Text.class);
		// ����Reducer������value����
		job.setOutputValueClass(IntWritable.class);
		
		// 3.����job�������ļ����ڵ�HDFS·��
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.154.129:9000/wordCount/words.txt"));
		// ���ý���ļ�·����Ҳ�����Ǵ洢HDFS��
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.154.129:9000/wordCount/result"));
		
		// 4.�ύ����
		job.waitForCompletion(true);
	}

}
