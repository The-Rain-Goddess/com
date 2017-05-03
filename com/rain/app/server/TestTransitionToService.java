package com.rain.app.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.RiotApi;
import com.rain.app.service.riot.api.RiotApiException;
import com.rain.app.service.riot.constant.Platform;

public class TestTransitionToService {

	public TestTransitionToService() {

	}
	
	public static void main(String[] args) throws RiotApiException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		RiotApi riotApi = new RiotApi(new ApiConfig().setKey(""));
		System.out.println(riotApi.getSummonerByName(Platform.NA, "theraingoddess").getSummonerLevel());
	}	
	
	public void testReflect() throws NoSuchMethodException, SecurityException, ReflectiveOperationException, Exception, InvocationTargetException{
		List<Method> methods = new LinkedList<Method>();
		Class<TestTransitionToService> aClass = TestTransitionToService.class;
		methods.add(aClass.getMethod("foo", double.class));
		Method method = methods.get(0);
		System.out.println(method.invoke(new TestTransitionToService(), 5.0));
	}
	
	public int foo(double bar){
		return 1;
	}

}
