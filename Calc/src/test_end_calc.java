package Calc_RGR;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class test_end_calc {
	//инициируем переменные
	int h,w,fps,hours,cam,days;
	double deep,form,razv,expected_answer;
	
	public test_end_calc(int h, int w, double deep, int fps, double form, double razv, int cam, int hours, int days, double expected_answer) {
		this.h = h;
		this.w = w;
		this.deep = deep;
		this.fps = fps;
		this.form = form;
		this.razv = razv;
		this.cam = cam;
		this.hours = hours;
		this.days = days;
		this.expected_answer = expected_answer;
	}
	//инируем параметры
	@Parameterized.Parameters
	public static Collection<Object[]> numbers(){
		return Arrays.asList(new Object[][]{
				{1,1,1,1,1,1,1,1,1,3600},
				{900,1600,24,25,0.065,0.5,4,24,7,67931136000000D},
				{320,240,8,6,0.043,1,1,6,7,23967498240D}});
	}
	
	//проверяем соответствие полученных и ожидаемых результатов
	@Test
	public final void testEnd_calc() {
		double delta = 0.0001;
		double actual_answer = Frame1.end_calc(h,w,deep,fps,form,razv,cam,hours,days);
		assertEquals(actual_answer,expected_answer,delta);
	}
}