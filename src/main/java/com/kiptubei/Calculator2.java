package com.kiptubei;

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
public class Calculator2 {

	/**
	 * Method to calculate add ,sub,div,multiply
	 */
	@GET
	@Path("/{action}/{value1}/{value2}")
	@Produces(MediaType.APPLICATION_XML)
	public Result process(@PathParam("action") String action, @PathParam("value1") String a,
			@PathParam("value2") String b) {

		
		Double ans;
		Result result = new Result(" ..start ..");
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
				System.out.println(b + " -< value for b-- ");
				ans = Double.valueOf(a) + Double.valueOf(b);
				System.out.println(ans + " -< value for ans-- ");
				result.setOutput(ans.toString());
				break; // optional

			case "sub":
				ans = Double.valueOf(a) - Double.valueOf(b);
				result.setOutput(ans.toString());
				break; // optional

			case "mul":
				ans = Double.valueOf(a) * Double.valueOf(b);
				result.setOutput(ans.toString());
				break;

			case "div":
				if (Double.valueOf(b) == 0) {
					result.setOutput("Denominator cannot be zero");
					return result;
				}
				if (a != null && b != null) {
					ans = Double.valueOf(a) / Double.valueOf(b);
					result.setOutput(ans.toString());
				}

				break;

			default: // Optional
				result.setOutput("Please supply valid values and operation types add,sub,mul,div");
			}

		}

		return result;

	}

	/**
	 * Overload method process to handle factorial
	 */
	@GET
	@Path("/{action}/{value1}")
	@Produces(MediaType.APPLICATION_XML)
	public Result process(@PathParam("action") String action, @PathParam("value1") String a) {
		Result result = new Result(action);
		Double ans;
		/*regular expression for digits only*/
		String regex = "\\d+";

		if (!a.matches(regex)) {
			result.setOutput("values used in factorial should be numbers e.g <fac/2> ");
		} else {
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
	 * cannot handle factorial of numbers greater than 21
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
