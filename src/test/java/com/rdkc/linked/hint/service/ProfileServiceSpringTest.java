package com.rdkc.linked.hint.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-config.xml"})
public class ProfileServiceSpringTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	public ProfileService profileService;
	
	@Test
	public void testGetPublicProfile() {
		profileService.getPublicProfile("");
	}
}
