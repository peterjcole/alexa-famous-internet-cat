package net.petercole.alexapusheen.handlers;

import static org.junit.Assert.*;

//import java.util.function.Predicate;
//
//import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.function.Predicate;

import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
//import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;


public class HelloWorldIntentHandlerTest {

	@Mock
	private HandlerInput mockInput;
	
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 

	@Test 
	public void testIntentIsChecked() {
		HelloWorldIntentHandler handler = new HelloWorldIntentHandler();
		Mockito.when(mockInput.matches(any())).thenReturn(true);
		handler.canHandle(mockInput);
		verify(mockInput).matches(any());
	}
	

}
