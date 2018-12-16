package logic.api.service;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import logic.api.Configuration;
import logic.api.service.LogicService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Configuration.class)
public class LogicServiceTest {

	@Autowired
	private LogicService logicService;
	
	@Test
	public void testValidFizzBuzzSequence() {
		Map<String, String> inputDataPair = new HashMap<>();
		inputDataPair.put("number","15");
		
		Map<String, List<String>> sequence = logicService.generateFizzBuzzSequences(inputDataPair);
		Assert.assertThat(sequence, is(notNullValue()));
		Assert.assertThat(sequence.get("number"), is(notNullValue()));
		
		this.assertSequence(sequence.get("number"),inputDataPair.get("number"));
		
	}
	
	@Test
	public void testValidFizzBuzzSequenceWithTwoInputs() {
		Map<String, String> inputDataPair = new HashMap<>();
		inputDataPair.put("A","15");
		inputDataPair.put("B","30");
		
		Map<String, List<String>> sequence = logicService.generateFizzBuzzSequences(inputDataPair);
		Assert.assertThat(sequence, is(notNullValue()));
		Assert.assertThat(sequence.get("A"), is(notNullValue()));
		Assert.assertThat(sequence.get("B"), is(notNullValue()));
		
		for(String key : inputDataPair.keySet()) {
			this.assertSequence(sequence.get(key),inputDataPair.get(key));
		}
		
	}
	
	@Test
	public void testInvalidInputs() {
		Map<String, String> inputDataPair = new HashMap<>();
		inputDataPair.put("A","1.5");
		inputDataPair.put("B","!");
		inputDataPair.put("C","C");
		inputDataPair.put("D","-1");
		inputDataPair.put("D","ä");
		inputDataPair.put("E", null);
		
		Map<String, List<String>> sequence = logicService.generateFizzBuzzSequences(inputDataPair);
		
		for(String key : inputDataPair.keySet()) {
			Assert.assertThat(sequence.get(key).get(0), is(LogicService.INVALID_INPUT));
		}
		
	}
	
	@Test
	public void testCollectionSerialization() {
		
		Map<String, String> inputDataPair = new HashMap<>();
		inputDataPair.put("A","15");
		
		Map<String, List<String>> sequence = logicService.generateFizzBuzzSequences(inputDataPair);
		String json = logicService.generateJsonFromCollection(sequence);
		
		Assert.assertThat(deserializeSequenceFromJson(json),is(equalTo(sequence)));
	}

	private void assertSequence(List<String> sequences, String sequenceGeneratedFor) {
		for(int i=1;i<=Integer.parseInt(sequenceGeneratedFor);i++) {
			if(i % 3 == 0 && i % 5 == 0) {
				Assert.assertThat(sequences.get(i - 1),is(String.format("%s %s", LogicService.FIZZ, LogicService.BUZZ)));
			} else if(i % 3 == 0) {
				Assert.assertThat(sequences.get(i - 1),is(LogicService.FIZZ));
			} else if(i % 5 == 0) {
				Assert.assertThat(sequences.get(i - 1),is(LogicService.BUZZ));
			} else {
				Assert.assertThat(sequences.get(i - 1),is(Integer.toString(i)));
			}
		}
		
	}
	
	public static Map<String, List<String>> deserializeSequenceFromJson(String json) {
		TypeReference<Map<String, List<String>>> typeReference = new TypeReference<Map<String,List<String>>>() {};
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(json, typeReference);
		} catch (IOException e) {
			Assert.fail("Should not fail ..");
		}
		return null;
	}
	
	
	 
	
}
