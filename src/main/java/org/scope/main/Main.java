package org.scope.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.scope.jackson.mapper.CapitalizedStrategy;
import org.scope.json.model.Availability;
import org.scope.json.model.Sites;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class Main {


	
	public static void test() {

		System.out.println(getSiteIds("1", "1"));
		
//		List<Availability> ss = getAvailability("2018-03-01","2018-07-01","28");

		
//		for(Availability avail : ss) {
//			System.out.println(avail.getIsAvailable());
//		}
	}

	private static List<Availability> getAvailability(String fromDate, String toDate, String siteId) {
		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(generateAvailabilityFormData(fromDate, toDate, siteId),generateHeaders());
		final ResponseEntity<Availability[]> response = instantiateRestTemplate().postForEntity("https://www.passport.gov.ph/appointment/timeslot/available", request, Availability[].class);

		if(response.getStatusCode().equals(HttpStatus.OK) && response.getBody().length>0) {
			return Arrays.asList(response.getBody()).stream().filter(availability -> availability.getIsAvailable()).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
	
	private static Sites getSiteIds(String regionId, String countryId) {
		
		final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(generateSitesFormData(regionId,countryId),generateHeaders());
		final ResponseEntity<Sites> response = instantiateRestTemplate().postForEntity("https://www.passport.gov.ph/sites", request, Sites.class);
		
//		final ResponseEntity<String> response = instantiateRestTemplate().postForEntity("https://www.passport.gov.ph/sites", request, String.class);
//		System.out.println(response.getBody());
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			return response.getBody();
		}
		return null;
	}

	private static MultiValueMap<String, String> generateSitesFormData(String regionId, String countryId) {
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("regionId", regionId);
		map.add("countryId", countryId);
		return map;
	}
	
	private static MultiValueMap<String, String> generateAvailabilityFormData(String fromDate, String toDate, String siteId) {
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("fromDate", fromDate);
		map.add("toDate", toDate);
		map.add("siteId", siteId);
		map.add("requestedSlots", "1");
		return map;
	}

	private static HttpHeaders generateHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.add("Accept-Encoding", "gzip, deflate, br");
		headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
		return headers;
	}

	private static RestTemplate instantiateRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().stream()
				.filter(converter -> (converter instanceof MappingJackson2HttpMessageConverter))
				.forEach(conv -> {
					MappingJackson2HttpMessageConverter c = (MappingJackson2HttpMessageConverter) conv;
					c.getObjectMapper().setPropertyNamingStrategy(new CapitalizedStrategy());
				});
		return restTemplate;
	}
	
	public static void main(String[] args) {
		test();
//		test2();
	}
	
	public static void test2() {
		String str = "{\"IsAvailable\":false,\"AppointmentDate\":1517443200000}";
		Availability av = jasonToObject(str, Availability.class);
		System.out.println(av.getIsAvailable());
	}
	
	public static <T> T jasonToObject(String json, Class<?> type) {
		final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setPropertyNamingStrategy(new CapitalizedStrategy());
		try {
			return (T) mapper.readValue(json, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (T) new Object();
	}

}
