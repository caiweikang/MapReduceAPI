package com.vip.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	// keyΪÿ�����׵�ƫ�������̶�Ϊ������
	// valueָ����ÿ�е����ݣ��̶�ΪText
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
