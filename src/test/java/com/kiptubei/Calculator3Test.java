package com.kiptubei;
/**
* Calculator3Test
* Junit test suite for automation of functional testing for Calculator3 class.
* Methods affected are process for
* Addition,Subtraction,Multiplication,Division and Factorial
* 
*  Tests for the following:
*  Correctness of calculations,
*  Negative numbers
*  Wrong parameter options
*  0 as an input
*  Very large factorial
* 
* @author  Mark James Kiptubei
* @version 1.0
* @since   2019-02-13
*/

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;


class Calculator3Test {
	Calculator3 calc = new  Calculator3();
	
	
	/** tests for addition,subtraction,multiplication and factorial*/
	@Test
	void testProcessStringStringString() {
	assertAll("Testing add,sub,mul,div",()->assertEquals("1.0",calc.process("add","1", "0").getOutput()),
		()->assertEquals("2.0",calc.process("sub","4", "2").getOutput()),
		()->assertEquals("-7.0",calc.process("sub","-5", "2").getOutput()),
		()->assertEquals("4.0",calc.process("sub","2", "-2").getOutput()),
		()->assertEquals("-5.0",calc.process("div","25", "-5").getOutput()),
		()->assertEquals("Denominator cannot be zero",calc.process("div","20", "0").getOutput()),
		()->assertEquals("100.0",calc.process("mul","20", "5").getOutput()),
		()->assertEquals("Please supply a valid operation types add,sub,mul,div",calc.process("gibbbs","20", "-5").getOutput())
		);
	}
	
	@Test
	void testProcessStringString() {
		assertAll("Testing factorial",
				()->assertEquals("120.0",calc.process("fac","5").getOutput()),
				()->assertEquals("1124000727777607680000",calc.process("fac","22").getOutput())
				);
		
		
		
	}

}
