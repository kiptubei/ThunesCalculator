package com.kiptubei;

/**
* Calculator3
* Programs to perform simple arithmetic on numbers.
* Generates a web api to provide access to its method are returns the results 
* as an XML to browser or CLI
* 
* Addition,Subtraction,Multiplication,Division and Factorial
*
* @author  Mark James Kiptubei
* @version 1.0
* @since   2019-02-13
*/
import java.math.BigInteger;

import java.util.stream.LongStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kiptubei.Result;

@Path("/calculate")
@Consumes(MediaType.APPLICATION_XML)
public class Calculator3 {

	/**
   *  Method: process
    *
    *  Purpose: This is where the calculation of Addition,Subtraction,
	*	Division and Multiplication is done given the action to be
    *	performed and the inputs to be worked on.
	*	Interaction is through GET method via http    
    *
    *  Parameters (3):
	*	@param action specifies type of calculation. String either "add","div,"sub","mul"
	*	@param a and b specifies values to be calculated must be numerals,either +ve or -ve
    *
    *  Returns:  
	*	@returns result Object containing description of action inputs and output
    **********************************************/
	@GET
	@Path("/{action}/{value1}/{value2}")
	@Produces(MediaType.APPLICATION_XML)
	public Result process(@PathParam("action") String action, @PathParam("value1") String a,
			@PathParam("value2") String b) {
		
		Double ans;
		Result result = new Result();
		/*regular expression for digits only*/
		String regex = "-?[1-9]\\d*|0";

		if (!a.matches(regex) || !b.matches(regex)) {
			
			result.setOutput("values used in caclculation should be numbers e.g <add/2/3> ");
		} else if (a == null || b == null) {
			result.setOutput("Please supply a values to be calculated e.g add/2/3");
		} else {
			result.setInputOne(a);
			result.setInputTwo(b);

			switch (action) {
			case "add":
				
				ans = Double.valueOf(a) + Double.valueOf(b);
				result.setDescription("add");
				result.setOutput(ans.toString());
				break; // optional

			case "sub":
				
				ans = Double.valueOf(a) - Double.valueOf(b);
				result.setDescription("sub");
				result.setOutput(ans.toString());
				break; // optional

			case "mul":
				ans = Double.valueOf(a) * Double.valueOf(b);
				result.setDescription("mul");
				result.setOutput(ans.toString());
				break;

			case "div":
				if (Double.valueOf(b) == 0) {
					result.setDescription("div");
					result.setOutput("Denominator cannot be zero");
					return result;
				}
				if (a != null && b != null) {
					ans = Double.valueOf(a) / Double.valueOf(b);
					result.setDescription("div");
					result.setOutput(ans.toString());
				}

				break;

			default: // Optional
				result.setDescription("err");
				result.setOutput("Please supply valid values and operation types add,sub,mul,div");
			}

		}

		return result;

	}

	/**
    |  Method: process
    |
    |  Purpose: Overloaded method to handle factorial calculations.
	|	Interaction is through GET method via http    
    |
    |  Parameters:
	|	@param action specifies type of calculation. String either "fac"
	|	@param a specifies values to be calculated must be numerals,either +ve or -ve
    |
    |  Returns:  
	|	@returns result Object containing description of action inputs and output
    *************************************************/
	@GET
	@Path("/{action}/{value1}")
	@Produces(MediaType.APPLICATION_XML)
	public Result process(@PathParam("action") String action, @PathParam("value1") String a) {
		Result result = new Result(action);
		Double ans;
		/*regular expression for digits only*/
		String regex = "-?[1-9]\\d*|0";

		if (!a.matches(regex)) {
			result.setOutput("values used in factorial should be numbers e.g <fac/2> ");
		} else {
			result.setDescription("fac");
			result.setInputOne(a);
			switch (action) {
			case "fac":
				if (Double.valueOf(a) <= 1) {
					result.setOutput("1");
				}
				if (Double.valueOf(a) < 20) {
					ans = factorialStreams(Double.valueOf(a).longValue());
					result.setOutput(ans.toString());
				} else {
					result.setOutput(getFactorial(Double.valueOf(a).intValue()));
				}

				break;
			default: // Optional
				result.setOutput("Please supply a valid operation types add,sub,mul,div,fac");
			}
		}

		return result;
	}

	/**
	 * Functions for factorial calculation factorialStreams is more efficient but
	 * cannot handle factorial of numbers greater than 21 for those we use BigIntegers function
	 * getFactorial
	 */
	public static Double factorialStreams(long n) {
		Double ans;
		long ansLong;
		ansLong = LongStream.rangeClosed(1, n).reduce(1, (long a, long b) -> a * b);
		ans = (double) ansLong;
		return ans;
	}

	public static String getFactorial(int num) {
		BigInteger result = BigInteger.ONE;
		String ans;
		for (int i = 1; i <= num; i++)
			result = result.multiply(BigInteger.valueOf(i));

		ans = result.toString();
		return ans;
	}

}
