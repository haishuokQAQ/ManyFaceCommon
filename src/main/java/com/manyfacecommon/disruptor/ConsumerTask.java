package com.manyfacecommon.disruptor;

public interface ConsumerTask {
	public String getTaskName();
	public ConsumerTask copy();
	public void copyFrom(ConsumerTask task);
}
