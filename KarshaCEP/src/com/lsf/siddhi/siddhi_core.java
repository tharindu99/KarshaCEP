package com.lsf.siddhi;

import org.wso2.siddhi.core.ExecutionPlanRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;

public class siddhi_core {

	public void siddhi_test() {
		String definition = "@config(async = 'true') define stream cseEventStream (symbol string, price float, volume long);";
		
		String query = "@info(name = 'query1') from cseEventStream#window.timeBatch(1)  select symbol, sum(price) as price, sum(volume) as volume group by symbol insert into outputStream ;";
		
		SiddhiManager siddhiManager = new SiddhiManager();
		  
		ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(definition + query);
		
		executionPlanRuntime.addCallback("query1", new QueryCallback() {
		      @Override
		      public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
		       EventPrinter.print(timeStamp, inEvents, removeEvents);
		      }
		});
		InputHandler inputHandler = executionPlanRuntime.getInputHandler("cseEventStream");
		executionPlanRuntime.start();
		  
		try {
			inputHandler.send(new Object[]{"ABC", 700f, 100l});
			inputHandler.send(new Object[]{"WSO2", 60.5f, 200l});
			inputHandler.send(new Object[]{"DEF", 700f, 100l});
			inputHandler.send(new Object[]{"ABC", 700f, 100l});
			inputHandler.send(new Object[]{"WSO2", 60.5f, 200l});
			inputHandler.send(new Object[]{"DEF", 700f, 100l});
			inputHandler.send(new Object[]{"ABC", 700f, 100l});
			inputHandler.send(new Object[]{"WSO2", 60.5f, 200l});
			inputHandler.send(new Object[]{"DEF", 700f, 100l});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		executionPlanRuntime.shutdown();
	}
}
