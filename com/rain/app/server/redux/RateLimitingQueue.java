package com.rain.app.server.redux;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class RateLimitingQueue extends LinkedBlockingQueue<Runnable>{

	private static final long serialVersionUID = 5760705082033662223L;
	private Queue<Runnable> q = new LinkedList<Runnable>();
    private int limit;
    private int dequeueLimit;
    private long dequeueTime;
    private long lastDequeueTime;
    private long timeStamp;
    private int dequeueCount = 0;
    private boolean firstDQ;

    public RateLimitingQueue(int limit, int dequeueLimit, long dequeueTime, int numberOfThreads) {
        this.limit = limit;
        this.dequeueLimit = dequeueLimit;
        this.dequeueTime = dequeueTime;
        this.timeStamp = System.currentTimeMillis();
        this.lastDequeueTime = System.currentTimeMillis();
        this.dequeueCount = numberOfThreads;
        this.firstDQ = true;
        
        System.err.println("Queue Started: ");
    }
    
    @Override
	public synchronized void put (Runnable t) throws InterruptedException {
    	//System.out.println("Queue put " + size());
    	while (isFull()) {
            wait();
        }
        boolean e = isEmpty();
        q.add(t);
        if (e)
        	notifyAll();
    }
    
    @Override
    public synchronized boolean offer(Runnable t){
    	System.err.println("Qp added from " + Thread.currentThread().getName() + ": " +  q.size());
    	while (isFull()) {
            try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    	boolean e = isEmpty();
    	if(e)
    		notifyAll();
        return q.add(t);
    }

    @Override
	public synchronized Runnable take () throws InterruptedException {
    	System.err.println("Qt " + Thread.currentThread().getName() + " " + isOverRateLimit() + " " + dequeueCount+"/"+dequeueLimit + " " + timeTill());
    	while (isEmpty()) {
            wait();
        }
    	
    	while(!isOverSecondGap() || isOverRateLimit()){
    		
    	}
    	if(firstDQ){
    		timeStamp = System.currentTimeMillis();
    		firstDQ = false;
    	}
    		
        dequeueCount++;
        lastDequeueTime = System.currentTimeMillis();
    	boolean f = isFull();
        Runnable t = q.poll();
        
        if (f)
            notifyAll();
        return t;
    }
    
    public String timeTill(){
    	return String.format("%,.0f/%,.0f Sec", (((double)System.currentTimeMillis() - (double)timeStamp)/1000), (dequeueTime/1000.0));
    }
    
    public boolean isOverRateLimit(){
    	if(System.currentTimeMillis() - timeStamp > dequeueTime ){
    		dequeueCount = 0;
    		timeStamp = System.currentTimeMillis();
    	} return (dequeueCount>=dequeueLimit);
    }
    
    public boolean isOverSecondGap(){
    	 return (System.currentTimeMillis() - lastDequeueTime > 1000L );
    }

    @Override
	public boolean isEmpty() {
        return q.size() == 0;
    }
    private boolean isFull() {
        return q.size() == limit;
    }
}
