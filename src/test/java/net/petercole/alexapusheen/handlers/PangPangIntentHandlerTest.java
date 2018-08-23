package net.petercole.alexapusheen.handlers;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;
import com.amazon.ask.response.ResponseBuilder;
 

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = {"com.amazon.ask.model.Slot","com.amazon.ask.model.Intent",
		"com.amazon.ask.model.IntentRequest","com.amazon.ask.model.RequestEnvelope", "com.amazon.ask.response.ResponseBuilder", 
		"java.util.function.Predicate", "com.amazon.ask.request.Predicates"})

public class PangPangIntentHandlerTest {

	// Remember: number of pangs = 1 + number of pangs in Slot
	public static final String PANG_TEST_SINGLE = null;
	public static final String PANG_TEST_SINGLE_EXPECTED = "meow";
	public static final String PANG_TEST_DOUBLE = "Pang";
	public static final String PANG_TEST_DOUBLE_EXPECTED = "meow meow";
	public static final String PANG_TEST_TRIPLE = "Pang pang";
	public static final String PANG_TEST_TRIPLE_EXPECTED = "meow meow meow";
	public static final String PANG_TEST_QUADRUPLE = "Pang pang pang";
	public static final String PANG_TEST_QUADRUPLE_EXPECTED = "meow meow meow meow";
	public static final String PANG_TEST_QUINTIPLE = "Pang pang pang pang";
	public static final String PANG_TEST_QUINTIPLE_EXPECTED = "Stamina";
	public static final String PANG_TEST_SEXTUPLE = "Pang pang pang pang pang";
	public static final String PANG_TEST_SEXTUPLE_EXPECTED = "Stamina";
	public static final String PANG_INTENT_NAME = "PangPangIntent";


	PangPangIntentHandler testPangPangIntentHandler = new PangPangIntentHandler();
	//for all tests
	HandlerInput mockHandlerInput = mock(HandlerInput.class);
	
	//for handle() test
	RequestEnvelope mockRequestEnvelope = mock(RequestEnvelope.class);
	IntentRequest mockIntentRequest = mock(IntentRequest.class);
	Intent mockIntent = mock(Intent.class);
	Map<String, Slot> mockMap = mock(Map.class);
	Slot mockSlot = mock(Slot.class);
	ResponseBuilder mockResponseBuilder = mock(ResponseBuilder.class);
	Optional<Response> response = Optional.empty();
	Predicate<HandlerInput> trueMockPredicate = mock(Predicate.class);
	
	
	@Before
	public void setUp() {
		//for handle() test
		when(mockHandlerInput.getRequestEnvelope()).thenReturn(mockRequestEnvelope);
		when(mockRequestEnvelope.getRequest()).thenReturn(mockIntentRequest);
		when(mockIntentRequest.getIntent()).thenReturn(mockIntent);
		when(mockIntent.getSlots()).thenReturn(mockMap);
		when(mockMap.get(any())).thenReturn(mockSlot);
		when(mockHandlerInput.getResponseBuilder()).thenReturn(mockResponseBuilder);
		when(mockResponseBuilder.withSimpleCard(any(),any())).thenReturn(mockResponseBuilder);
		when(mockResponseBuilder.withSpeech(any())).thenReturn(mockResponseBuilder);
		when(mockResponseBuilder.withShouldEndSession(any())).thenReturn(mockResponseBuilder);
		when(mockResponseBuilder.build()).thenReturn(response);
		

	}
	
	@Test
	public void testIntentName() {
		PowerMockito.mockStatic(Predicates.class);
		when(Predicates.intentName(PANG_INTENT_NAME)).thenReturn(trueMockPredicate);
		when(mockHandlerInput.matches(trueMockPredicate)).thenReturn(true);
		
		//test that intentName has been called with the correct argument and so returned predicate resolving to true
		boolean matches = testPangPangIntentHandler.canHandle(mockHandlerInput);
		assertTrue("Testing if canHandle returns true", matches);
		
		//test that the intentName method is called with the correct argument
		PowerMockito.verifyStatic(Predicates.class);
		Predicates.intentName(PANG_INTENT_NAME);
		
	}
	
	@Test
	public void testResponseOnePang() {
		when(mockSlot.getValue()).thenReturn(PANG_TEST_SINGLE);	
		testPangPangIntentHandler.handle(mockHandlerInput);
		verify(mockSlot, atLeastOnce()).getValue();
		verify(mockResponseBuilder).withSpeech(PANG_TEST_SINGLE_EXPECTED);
	}
	
	@Test	
	public void testResponseTwoPangs() {
		when(mockSlot.getValue()).thenReturn(PANG_TEST_DOUBLE);	
		testPangPangIntentHandler.handle(mockHandlerInput);
		verify(mockSlot, atLeastOnce()).getValue();
		verify(mockResponseBuilder).withSpeech(PANG_TEST_DOUBLE_EXPECTED);
	}
	
	@Test
	public void testResponseThreePangs() {
		when(mockSlot.getValue()).thenReturn(PANG_TEST_TRIPLE);	
		testPangPangIntentHandler.handle(mockHandlerInput);
		verify(mockSlot, atLeastOnce()).getValue();
		verify(mockResponseBuilder).withSpeech(PANG_TEST_TRIPLE_EXPECTED);
	}
	
	@Test
	public void testResponseFourPangs() {
		when(mockSlot.getValue()).thenReturn(PANG_TEST_QUADRUPLE);	
		testPangPangIntentHandler.handle(mockHandlerInput);
		verify(mockSlot, atLeastOnce()).getValue();
		verify(mockResponseBuilder).withSpeech(PANG_TEST_QUADRUPLE_EXPECTED);
	}
	
	@Test
	public void testResponseFivePangs() {
		when(mockSlot.getValue()).thenReturn(PANG_TEST_QUINTIPLE);	
		testPangPangIntentHandler.handle(mockHandlerInput);
		verify(mockSlot, atLeastOnce()).getValue();
		verify(mockResponseBuilder).withSpeech(PANG_TEST_QUINTIPLE_EXPECTED);
	}
	
	@Test
	public void testResponseSixPangs() {
		when(mockSlot.getValue()).thenReturn(PANG_TEST_SEXTUPLE);	
		testPangPangIntentHandler.handle(mockHandlerInput);
		verify(mockSlot, atLeastOnce()).getValue();
		verify(mockResponseBuilder).withSpeech(PANG_TEST_SEXTUPLE_EXPECTED);
	}
}
