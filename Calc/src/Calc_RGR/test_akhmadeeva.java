package Calc_RGR;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class test_akhmadeeva {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(timeout = 150)
	public final void test1() {
		int h = 1024;
		int w = 768;
		int fps = 10;
		double deep = 10;
		double form = 0.37;
		double razv = 0.5;
		int cam = 3;
		int days = 5;
		int hours = 8;
		int delta = 0;
		double expect = 6285164544000d;
		double actual_answer = Frame1.end_calc(h,w,deep,fps,form,razv,cam,hours,days);
		assertEquals(actual_answer,expect,delta);
	}
	
	@Test(timeout = 10)
	public final void test2() {
		int h = 2048;
		int w = 1152;
		int fps = 30;
		double deep = 36;
		double form = 0.133;
		double razv = 1;
		int cam = 1;
		int days = 1;
		int hours = 24;
		int delta = 0;
		double expect = 29280033570816d;
		double actual_answer = Frame1.end_calc(h,w,deep,fps,form,razv,cam,hours,days);
		assertEquals(actual_answer,expect,delta);
	}

	@Test(timeout = 10)
	public final void test3() {
		double x = 29280033570816d;
		int y = 8;
		double expect = 3660004196352d;
		int delta = 0;
		double actual_answer = Frame1.convert_to_higher(x,y);
		assertEquals(actual_answer,expect,delta);
	}
	
	@Test(timeout = 10)
	public final void test4() {
		double x = 3771098726400d;
		int y = 1024;
		double expect = 3682713600d;
		int delta = 0;
		double actual_answer = Frame1.convert_to_higher(x,y);
		assertEquals(actual_answer,expect,delta);
	}
}
