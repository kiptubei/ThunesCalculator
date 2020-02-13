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
public class Calculator {

	@GET
	@Path("/add/{value1}/{value2}")
	@Produces(MediaType.APPLICATION_XML)
	public Result add(@PathParam("value1") String a, @PathParam("value2") String b) {
		Result result = new Result("add");
		Double ans;

		String regex = "\\d+";

		if (!a.matches(regex) || !b.matches(regex)) {
			result.setOutput("values used in caclculation should be numbers e.g <add/2/3> ");
		} else if (a == null || b == null) {
			result.setOutput("Please supplly a values to be calculated e.g add/2/3");
		} else {
			result.setInputOne(a);
			result.setInputTwo(b);
			ans = Double.valueOf(a) + Double.valueOf(b);
			result.setOutput(ans.toString());
		}

		return result;

	}

	@GET
	@Path("/subtract/{value1}/{value2}")
	@Produces(MediaType.APPLICATION_XML)
	public Result subtract(@PathParam("value1") String a, @PathParam("value2") String b) {
		Double ans;
		Result result = new Result("subtract");
		result.setInputOne(a.toString());
		result.setInputTwo(b.toString());
		if (a != null && b != null) {
			ans = Math.abs(Double.valueOf(a)- Double.valueOf(b));
			result.setOutput(ans.toString());
		}

		return result;
	}

	@GET
	@Path("/divide/{value1}/{value2}")
	@Produces(MediaType.APPLICATION_XML)
	public Result divide(@PathParam("value1") Double a, @PathParam("value2") Double b) {
		Double ans;
		Result result = new Result("divide");
		result.setInputOne(a.toString());
		result.setInputTwo(b.toString());
		if (b == 0) {
			result.setOutput("Denominator cannot be zero");
			return result;
		}
		if (a != null && b != null) {
			ans = a / b;
			result.setOutput(ans.toString());
		}

		return result;
	}

	@GET
	@Path("/multiply/{value1}/{value2}")
	@Produces(MediaType.APPLICATION_XML)
	public Result multiply(@PathParam("value1") Double a, @PathParam("value2") Double b) {

		Double ans;
		Result result = new Result("multiply");
		result.setInputOne(a.toString());
		result.setInputTwo(b.toString());
		if (a != null && b != null) {
			ans = a * b;
			result.setOutput(ans.toString());
		}

		return result;

	}

	@GET
	@Path("/factorial/{value1}")
	@Produces(MediaType.APPLICATION_XML)
	public Result factorial(@PathParam("value1") Double a) {
		Double ans;
		Result result = new Result("Factorial");
		result.setInputOne(a.toString());

		// factorialStreams is more efficient but cannot handle factorials of numbers
		// greater than 21
		if (a <= 1) {
			result.setOutput("1");
		}
		if (a < 20) {
			ans = factorialStreams(a.longValue());
			result.setOutput(ans.toString());
		} else {
			result.setOutput(getFactorial(a.intValue()));
		}

		return result;

	}

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
