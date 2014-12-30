package com.rdkc.linked.hint.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.util.EntityUtils;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class ProfileService {

	private static Log log = LogFactory.getLog(ProfileService.class);
	
	private static final String PROFILE_URL = "https://api.linkedin.com/v1/people/url={url}:(id,educations,positions:(title,company:(name)))";
	
	private String accountApiKey;
	private String accountSecretKey;
	
	@Autowired
	HttpClient httpClient;
	  

	public void setAccountApiKey(String accountApiKey) {
		this.accountApiKey = accountApiKey;
	}

	public void setAccountSecretKey(String accountSecretKey) {
		this.accountSecretKey = accountSecretKey;
	}


	public void getProfile(String profileUrl, String token) throws ClientProtocolException, IOException {
		String url = PROFILE_URL.replace("{url}", URLEncoder.encode(profileUrl,"UTF-8"));
		
		/*OAuthService service = new ServiceBuilder()
	        .provider(LinkedInApi.class)
	        .apiKey(accountApiKey)
	        .apiSecret(accountSecretKey)
	        .build();
		
		// if token expires, uncomment and run with the user logged out from linkedin
		Scanner in = new Scanner(System.in);
	    
	    System.out.println("=== LinkedIn's OAuth Workflow ===");
	    System.out.println();

	    // Obtain the Request Token
	    System.out.println("Fetching the Request Token...");
	    Token requestToken = service.getRequestToken();
	    System.out.println("Got the Request Token!");
	    System.out.println();

	    System.out.println("Now go and authorize Scribe here:");
	    System.out.println(service.getAuthorizationUrl(requestToken));
	    System.out.println("And paste the verifier here");
	    System.out.print(">>");
	    Verifier verifier = new Verifier(in.nextLine());
	    System.out.println();

	    // Trade the Request Token and Verifier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = service.getAccessToken(requestToken, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println(); */
	    
		// token, tokenSecret expire in 60 days
		/*Token accessToken = new Token(token, tokenSecret);

	    OAuthRequest request = new OAuthRequest(Verb.GET, url);
	    service.signRequest(accessToken, request);
	    System.out.println(request.getCompleteUrl());
	    System.out.println(request.getHeaders());
	    Response response = request.send();
	    System.out.println(response.getBody());*/
		
		HttpClientContext context = HttpClientContext.create();
	
	    url += "?oauth2_access_token=" +  token;
	    log.debug(url);
	    
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpGet, context);
		
	    log.debug(EntityUtils.toString(response.getEntity(), "UTF-8"));
	}
}
