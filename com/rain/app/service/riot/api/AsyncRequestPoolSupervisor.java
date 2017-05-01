package com.rain.app.service.riot.api;

import java.util.logging.Level;

public class AsyncRequestPoolSupervisor extends Thread {

	private static final long IDLE_TIME_RESIGN = 5000;

	private final AsyncRequestPool pool;
	private long lastJob = 0;
	private boolean running = true;

	AsyncRequestPoolSupervisor(AsyncRequestPool pool) {
		super("Riot Api - Async Request Pool Supervisor Thread");
		this.pool = pool;
	}

	private void clearPool() {
		if (pool.getPoolSize() > 0) {
			pool.clearPool();
		}
	}

	private void pollQueue() {
		if (pool.getPoolSize() == pool.getMaxAsyncThreads()) {
			return;
		}
		for (int i = pool.getPoolSize(); i < pool.getMaxAsyncThreads(); i++) {
			if (pool.pollQueue()) {
				lastJob = System.currentTimeMillis();
			} else {
				break;
			}
		}
	}

	@Override
	public void run() {
		while (running) {
			clearPool();
			pollQueue();
			// Resign, if idle for a while
			if (lastJob + IDLE_TIME_RESIGN < System.currentTimeMillis()) {
				pool.resignSupervisor();
			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				RiotApi.log.log(Level.WARNING, "Supervisor Sleep Interrupted", e);
			}
		}
	}

	public void shutdown() {
		running = false;
	}

}
