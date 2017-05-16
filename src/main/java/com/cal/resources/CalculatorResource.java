package com.cal.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cal.response.CalculatorResponse;
import com.cal.services.FXCalculatorService;
import com.cal.services.FXCalculatorServiceImpl;
import com.cal.services.RPNCalculatorService;
import com.cal.services.RPNCalculatorServiceImpl;

@Path(value="/")
public class CalculatorResource {
	
	@GET
	@Path("/rpn/{inputList:.+}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sendRPNCalculatorValues(@PathParam("inputList") String inputList) {

		RPNCalculatorService rpnCalculatorService = new RPNCalculatorServiceImpl();
		CalculatorResponse calculatorResult = rpnCalculatorService.calculateRPN(inputList);

		return Response.status(200)
		.entity("Status : " + calculatorResult.getStatus() + "\n" +
				"Message : " + calculatorResult.getMessage() + "\n" + 
				"Result : " + calculatorResult.getResult())
		.build();

	}
	
	@GET
	@Path("/fx/{inputList:.+}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sendFXCalculatorValues(@PathParam("inputList") String inputList) {

		FXCalculatorService fxCalculatorService = new FXCalculatorServiceImpl();
		CalculatorResponse calculatorResult = fxCalculatorService.calculateFX(inputList);

		return Response.status(200)
		.entity("Status : " + calculatorResult.getStatus() + "\n" +
				"Message : " + calculatorResult.getMessage() + "\n" + 
				"Result : " + calculatorResult.getResult())
		.build();

	}

}
