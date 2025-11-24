package com.example.demo.controller.ch04_structured_output;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Hotel;
import com.example.demo.dto.ReviewClassification;
import com.example.demo.service.ch04_structured_output.AiServiceBeanOutputConverter;
import com.example.demo.service.ch04_structured_output.AiServiceListOutputConverter;
import com.example.demo.service.ch04_structured_output.AiServiceMapOutputConverter;
import com.example.demo.service.ch04_structured_output.AiServiceParameterizedTypeReference;
import com.example.demo.service.ch04_structured_output.AiServiceSystemMessage;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ai/structured-output")
@Slf4j
public class StructuredOutputController {
	@Autowired
	private AiServiceBeanOutputConverter aiServiceBean;

	@Autowired
	private AiServiceListOutputConverter aiServiceList;

	@Autowired
	private AiServiceMapOutputConverter aiServiceMap;

	@Autowired
	private AiServiceParameterizedTypeReference aiServiceGeneric;

	@Autowired
	private AiServiceSystemMessage aiServiceSystemMessage;

	@PostMapping(
		value = "/bean-output-converter",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE   
	)
	public Hotel beanOutputConverter(@RequestParam("city") String city) {
		Hotel hotel = aiServiceBean.beanOutputConverterHighLevel(city);
		return hotel;
	}

	@PostMapping(
		value = "/list-output-converter",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE   
	)
	public List<String> listOutputConverter(@RequestParam("city") String city) {
		List<String> hotelList = aiServiceList.listOutputConverterLowLevel(city);
		return hotelList;
	}

	@PostMapping(
		value = "/map-output-converter",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE 
	)
	public Map<String, Object> mapOutputConverter(@RequestParam("hotel") String hotel) {
		Map<String, Object> hotelInfo = aiServiceMap.mapOutputConverterLowLevel(hotel);
		return hotelInfo;
	}

	@PostMapping(
		value = "/generic-bean-output-converter",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE  
	)
	public List<Hotel> genericBeanOutputConverter(@RequestParam("cities") String cities) {
		List<Hotel> hotelList = aiServiceGeneric.genericBeanOutputConverterHighLevel(cities);
		return hotelList;
	}

	@PostMapping(
		value = "/system-message",
		consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE   
	)
	public ReviewClassification classifyReview(@RequestParam("review") String review) {
		ReviewClassification reviewClassification = aiServiceSystemMessage.classifyReview(review);
		return reviewClassification;
	}
}
