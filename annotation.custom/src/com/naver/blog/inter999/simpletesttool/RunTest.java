package com.naver.blog.inter999.simpletesttool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import com.naver.blog.inter999.simpletesttool.annotation.DataProvider;
import com.naver.blog.inter999.simpletesttool.annotation.StartUp;
import com.naver.blog.inter999.simpletesttool.annotation.TearDown;
import com.naver.blog.inter999.simpletesttool.annotation.Test;
import com.naver.blog.inter999.simpletesttool.annotation.Test.ActiveType;

public class RunTest {
	public static void getStartUp(Object targetObject)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		for (Method m : targetObject.getClass().getMethods()) {
			if (m.isAnnotationPresent(StartUp.class)) {
				m.invoke(targetObject);
			}
		}
	}

	public static void getTearDown(Object targetObject)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		for (Method m : targetObject.getClass().getMethods()) {
			if (m.isAnnotationPresent(TearDown.class)) {
				m.invoke(targetObject);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Object[]> getProvider(Object targetObject,
			String providerName) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		List<Object[]> providerData = null;
		for (Method m : targetObject.getClass().getMethods()) {
			if (m.isAnnotationPresent(DataProvider.class)) {
				if (((DataProvider) m.getAnnotation(DataProvider.class)).name().equals(providerName)) {
					providerData = (List<Object[]>) m.invoke(targetObject);
				}
			}
		}
		return providerData;
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("USE : RunTest [targetClass] [TestGroup] or null");
			System.err.println("EX : RunTest com.naver.blog.inter999.math.test.TestCalculator min");
			System.exit(0);
			// 수정 commit (push) 테스트
		}
		String testGroup = "";
		if (args.length == 2) {
			testGroup = args[1];
		}
		Class<?> targetClass = Class.forName(args[0]);
		Object targetObject = targetClass.newInstance();
		getStartUp(targetObject);
		for (Method m : targetClass.getMethods()) {
			if (m.isAnnotationPresent(Test.class)) {
				Test t = (Test) m.getAnnotation(Test.class);
				if (t.IsActive().equals(ActiveType.ACTIVE)) {
					if (t.TestGroup().equals(testGroup) || testGroup.equals("")) {
						System.out.print("Method : ");
						System.out.println(m.getName());
						String providerName = t.DataProvider();
						List<Object[]> dataProvider = getProvider(targetObject,
								providerName);
						for (Object[] obj : dataProvider) {
							m.invoke(targetObject, obj);
						}
					}
				}
			}
		}
		getTearDown(targetObject);
	}
}