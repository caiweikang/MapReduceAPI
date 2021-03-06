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
		
		// 1.设置Job工作的运行类
		job.setJarByClass(WordCountDriver.class);
		// 设置Mapper组件运行的类
		job.setMapperClass(WordCountMapper.class);
		// 设置Reducer组件运行类
		job.setReducerClass(WordCountReducer.class);
		
		// 2.设置Mapper组件输出key的类型
		job.setMapOutputKeyClass(Text.class);
		// 设置Mapper组件输出value类型
		job.setMapOutputValueClass(IntWritable.class);
		// 设置Reducer组件输出key的类型
		job.setOutputKeyClass(Text.class);
		// 设置Reducer组件输出value类型
		job.setOutputValueClass(IntWritable.class);
		
		// 3.设置job待处理文件所在的HDFS路径
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.154.129:9000/wordCount/words.txt"));
		// 设置结果文件路径，也必须是存储HDFS上
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.154.129:9000/wordCount/result"));
		
		// 4.提交任务
		job.waitForCompletion(true);
	}

}
