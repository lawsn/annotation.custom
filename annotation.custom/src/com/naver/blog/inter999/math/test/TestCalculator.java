package com.naver.blog.inter999.math.test;

import java.util.ArrayList;
import java.util.List;
import com.naver.blog.inter999.math.Calculator;
import com.naver.blog.inter999.simpletesttool.annotation.DataProvider;
import com.naver.blog.inter999.simpletesttool.annotation.StartUp;
import com.naver.blog.inter999.simpletesttool.annotation.TearDown;
import com.naver.blog.inter999.simpletesttool.annotation.Test;
import com.naver.blog.inter999.simpletesttool.annotation.Test.ActiveType;

public class TestCalculator {
	Calculator calculator = null;

	@StartUp
	// 1
	public void StartUp() {
		calculator = new Calculator();
		System.out.println("Start Up");
	}

	@TearDown
	// 2
	public void TearDown() {
		calculator = null;
		System.out.println("Tear Down");
	}

	@DataProvider(name = "testplus")
	// 3
	public List<Object[]> PlusDataProvider() {
		List<Object[]> provider = new ArrayList<Object[]>();
		Double x = new Double(10);
		Double y = new Double(20);
		Object[] obj = { x, y };
		Double x2 = new Double(15);
		Double y2 = new Double(25);
		Object[] obj2 = { x2, y2 };
		provider.add(obj);
		provider.add(obj2);
		return provider;
	}

	@DataProvider(name = "testminus")
	// 4
	public List<Object[]> MinusDataProvider() {
		List<Object[]> provider = new ArrayList<Object[]>();
		Double x = new Double(100);
		Double y = new Double(20);
		Object[] obj = { x, y };
		provider.add(obj);
		return provider;
	}

	@DataProvider(name = "testmultiplied")
	public List<Object[]> MultipliedDataProvider() {
		List<Object[]> provider = new ArrayList<Object[]>();
		Double x = new Double(100);
		Double y = new Double(20);
		Object[] obj = { x, y };
		provider.add(obj);
		return provider;
	}

	@DataProvider(name = "testdivided")
	public List<Object[]> DividedDataProvider() {
		List<Object[]> provider = new ArrayList<Object[]>();
		Double x = new Double(15);
		Double y = new Double(3);
		Object[] obj = { x, y };
		provider.add(obj);
		return provider;
	}

	@Test(DataProvider = "testplus", TestGroup = "min")
	// 5
	public void TestPlus(Double x, Double y) {
		Double result = calculator.Plus(x, y);
		System.out.println(result);
	}

	@Test(DataProvider = "testminus", TestGroup = "min")
	public void TestMinus(Double x, Double y) {
		Double result = calculator.Minus(x, y);
		System.out.println(result);
	}

	@Test(DataProvider = "testmultiplied", IsActive = ActiveType.INACTIVE)
	// 6
	public void TestMultiplied(Double x, Double y) {
		Double result = calculator.Multiplied(x, y);
		System.out.println(result);
	}

	@Test(DataProvider = "testdivided")
	public void TestDivided(Double x, Double y) {
		Double result = calculator.Divided(x, y);
		System.out.println(result);
	}
}