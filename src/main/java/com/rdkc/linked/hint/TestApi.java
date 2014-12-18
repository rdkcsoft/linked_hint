package com.rdkc.linked.hint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class TestApi {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody String test() {
		return "hello world";
	}

}
