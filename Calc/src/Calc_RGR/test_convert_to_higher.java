package Calc_RGR;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class test_convert_to_higher {
	//инициируем переменные
	double x, expected_answer;
	int y;
	
	public test_convert_to_higher(double x, int y, double expected_answer){
		this.x = x;
		this.y = y;
		this.expected_answer = expected_answer;
	}
	//инируем параметры
	@Parameterized.Parameters
	public static Collection<Object[]> numbers(){
		return Arrays.asList(new Object[][]{
				{4,2,2D},
				{4,1,4D},
				{781353216000D,8,97669152000D}}
		);
	}
	
	//проверяем соответствие полученных и ожидаемых результатов
	@Test
	public final void testconvert_to_higher() {
		double delta = 0.00001;
		double actual_answer = Frame1.convert_to_higher(x,y);
		assertEquals(actual_answer,expected_answer,delta);
	}
}