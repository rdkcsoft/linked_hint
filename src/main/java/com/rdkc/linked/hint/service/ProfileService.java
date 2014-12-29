package com.rdkc.linked.hint.service;

import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
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

	
	private String accountApiKey;
	
	
	private String accountSecretKey;
	
	
	private String token;
	
	
	private String tokenSecret;
	
	
	//@Autowired
	//HttpClient httpClient; // TODO use
	
	// TODO from DB
	private static final String PROTECTED_RESOURCE_URL = "http://api.linkedin.com/v1/people/~";
	  

	public void setAccountApiKey(String accountApiKey) {
		this.accountApiKey = accountApiKey;
	}

	public void setAccountSecretKey(String accountSecretKey) {
		this.accountSecretKey = accountSecretKey;
	}


	public void setToken(String token) {
		this.token = token;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	public void getPublicProfile(String publicUrl) {
		
		/*HttpClientContext context = HttpClientContext.create();
		
		HttpGet httpget = new HttpGet(uriString);
		HttpResponse response = httpClient.execute(httpget, context);*/
		
		OAuthService service = new ServiceBuilder()
	        .provider(LinkedInApi.class)
	        .apiKey(accountApiKey)
	        .apiSecret(accountSecretKey)
	        .build();
		
		// if token expires, uncomment and run with the user logged out from linkedin
		/*Scanner in = new Scanner(System.in);
	    
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

	    // Trade the Request Token and Verfier for the Access Token
	    System.out.println("Trading the Request Token for an Access Token...");
	    Token accessToken = service.getAccessToken(requestToken, verifier);
	    System.out.println("Got the Access Token!");
	    System.out.println("(if your curious it looks like this: " + accessToken + " )");
	    System.out.println(); */
	    
		// token, tokenSecret expire in 60 days
		Token accessToken = new Token(token, tokenSecret);

	    // Now let's go and ask for a protected resource!
	    System.out.println("Now we're going to access a protected resource...");
	    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
	    service.signRequest(accessToken, request);
	    Response response = request.send();
	    System.out.println("Got it! Lets see what we found...");
	    System.out.println();
	    System.out.println(response.getBody());
		
	}
}
