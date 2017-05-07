package com.rain.app.server.redux;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import com.rain.app.service.riot.api.RiotApi;

public class FutureTask<V> implements Callable<V> {
	RiotApi reference;
	Object[] args;
	Method futureRequest;
	
	public FutureTask(RiotApi api, Method futureRequest, Object... args) {
		this.reference = api;
		this.futureRequest = futureRequest;
		this.args = args;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V call() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		System.out.println("FutureTask: Invoked: " + futureRequest.toGenericString());
		return (V) futureRequest.invoke(reference, args);
	}
}
