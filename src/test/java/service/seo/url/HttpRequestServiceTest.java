package service.seo.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.seo.dao.UrlCheckDaoService;

import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class HttpRequestServiceTest {
	
	//@Mock
	//UrlCheckDaoService urlCheckDaoServiceMock;
	
	HttpRequestService service;
		
	@Before
	public void configTest() {
//		DataSource source = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/seoscanner","pgmaster","seo");
//		UrlCheckDaoService urlCheckDaoService = new UrlCheckDaoService();
//		urlCheckDaoService.
//		service.setUrlCheckDaoService(urlCheckDaoService);
	}
	
	@Ignore
	@Test
	public void doFullRequestTest() throws IOException {
	HashMap<String,String>	responseDetails = service.doFullRequest("http://www.kiabi.com/chaussettes-petit-garcon_202405");
	responseDetails.get("DESTINATION_URL");
	}
	
	@Ignore
	@Test
	public void mergeBddUrlfromSitemapForStatsTest() throws MalformedURLException, JAXBException {
		 seo.scanner.dto.Urlset urlSet = service.getLastVersionOfSitemap();
		 
		 Map<String,Integer> currentUrlMock = new HashMap<String,Integer>();
		 currentUrlMock.put("http://www.kiabi.com", 1);
		 //Mockito.when(urlCheckDaoServiceMock.getCurrentUrlToCheck(12)).thenReturn(currentUrlMock);
		service.mergeBddUrlfromSitemapForStats(urlSet, 12);
	}
	
	@Test
	public void analyzeUrlTest() throws JAXBException, IOException, InterruptedException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("urlscannerContext.xml");
		HttpRequestService serviceInt = (HttpRequestService) context.getBean("httpRequestService");
		serviceInt.doEvent();
	}
	
	

}
