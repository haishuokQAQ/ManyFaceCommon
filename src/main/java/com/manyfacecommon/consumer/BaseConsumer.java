package com.manyfacecommon.consumer;

import java.util.Queue;

import com.manyfacecommon.disruptor.ConsumerTask;

/**
 * Base model of consumer
 * @author haishuokQAQ
 *
 */
public abstract class BaseConsumer {
	private Queue<ConsumerTask> executeQueue;
	
	private boolean isShutdown = false;
	
	protected abstract boolean consume(ConsumerTask task);
	
	protected void compeleteTasks() {
		
	}
	
	public boolean addTask(ConsumerTask task) {
		return executeQueue.offer(task);
	}
	
	public void shutdown() {
		isShutdown = true;
	}
	
	class ConsumeThread extends Thread{
		@Override
		public void run() {
			while(true) {
				//TODO empty for justStrategy?
				ConsumerTask task = executeQueue.remove();
				consume(task);
				
				if (isShutdown = true) {
					compeleteTasks();
					break;
				}
			}
		}
	}
	
}
