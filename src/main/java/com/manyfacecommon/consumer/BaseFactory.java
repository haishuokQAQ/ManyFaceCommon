package com.manyfacecommon.consumer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manyfacecommon.disruptor.ConsumerTask;

public abstract class BaseFactory {
	private List<BaseConsumer> consumerList;
	
	private int nextConsumer = 0;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected void postTask(ConsumerTask task) {
		while(true) {
			BaseConsumer consumer = consumerList.get(nextConsumer++);
			if (nextConsumer == consumerList.size()) {
				nextConsumer = 0;
			}
			if (!consumer.addTask(task)&&!tryOtherConsumer(task)) {
				//TODO waitStartegy
				break;
			}
			else 
				break;
		}
	}
	private boolean tryOtherConsumer(ConsumerTask task) {
		Boolean b = false;
		for(int i = 1;i < 4;i++) {
			BaseConsumer consumer = consumerList.get((nextConsumer + i) % consumerList.size());
			if (consumer.addTask(task)) {
				b = true;
				break;
			}
		}
		return b;
	}
}
