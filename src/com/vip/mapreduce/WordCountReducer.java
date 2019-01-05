package com.vip.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// 对于MapReduce，Mapper组件可以单独工作，但Reducer组件必须依赖Mapper组件
// Reducer会把Mapper相同key的value进行合并迭代
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		int result = 0;
		for(IntWritable value : values) {
			result += value.get();
		}
		context.write(key, new IntWritable(result));
	}
}
