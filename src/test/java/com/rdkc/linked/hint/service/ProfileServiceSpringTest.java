package com.rdkc.linked.hint.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationTestContext.xml"})
public class ProfileServiceSpringTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	public ProfileService profileService;
	
	@Value( "${linkedIn.token}" )
	private String token;
	
	@Test
	public void testGetProfile() throws ClientProtocolException, IOException {
		profileService.getProfile("https://www.linkedin.com/in/danielaenyedi", token);
	}
}
