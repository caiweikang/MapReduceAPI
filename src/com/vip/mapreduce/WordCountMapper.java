package com.vip.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	// key为每行行首的偏移量，固定为长整型
	// value指的是每行的内容，固定为Text
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split(" ");
		for(String word : data) {
			context.write(new Text(word), new IntWritable(1));
		}
	}
}
