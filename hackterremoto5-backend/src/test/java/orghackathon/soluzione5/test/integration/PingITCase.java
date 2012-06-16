package orghackathon.soluzione5.test.integration;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Classe di esempio di test di integrazione
 * Il plugin failsafe configurato per Maven mette in esecuzione automaticamente tutte le
 * classi di test che terminano per ITCase
 * 
 * @author ste
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"/META-INF/spring/applicationContext-client.xml"})
public class PingITCase extends Base{
	

	@Autowired
	private RestTemplate restTemplate;
	
	String restServer = "http://localhost:9000/servizioAlloggi";
	
	static
	{
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "debug");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "debug");
	}
	
	@BeforeClass
	public static void before() {
		setHttpsSupport();
	}
	
	@Before
	public void setupTests(){
		((CommonsClientHttpRequestFactory) restTemplate.getRequestFactory())
		.getHttpClient()
		.getState()
		.setCredentials(
				AuthScope.ANY,
				new UsernamePasswordCredentials(
						"test",
						"prova"));
	}
	
	@Test
	public void testPingMethod(){
		String response = restTemplate.getForObject(restServer+"/echo/whoami",String.class);
		Assert.assertTrue( response != null);
		Assert.assertEquals( response, "whoami");
		
	}

}
