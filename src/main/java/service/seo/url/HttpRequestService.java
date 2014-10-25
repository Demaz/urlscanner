package service.seo.url;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Transformer;

import seo.scanner.dataService.ProjetService;
import seo.scanner.domain.Event;
import seo.scanner.domain.UrlToCheck;
import seo.scanner.dto.TUrl;
import seo.scanner.dto.Urlset;
import service.seo.dao.UrlCheckDaoService;
import domaine.seo.url.Turltocheck;

public class HttpRequestService {

	private Integer clientUID;

	@Autowired
	private ProjetService projetService;

	Transformer tUrllistTransformer = new Transformer() {

		public Object transform(Object obj) {
			return new Turltocheck(((TUrl) obj).getLoc().toLowerCase());
		}
	};

	Transformer turlToCheckTransformer = new Transformer() {

		public Object transform(Object obj) {
			return ((Turltocheck) obj).getUrl().toLowerCase();
		}
	};

	@Autowired
	private UrlCheckDaoService urlCheckDaoService;

	public void analyzeUrl() throws JAXBException, IOException, InterruptedException {

		// MethodInvokingTimerTaskFactoryBean test = new
		// MethodInvokingTimerTaskFactoryBean();
		// UrlCheckDaoService url = new UrlCheckDaoService();
		// test.setTargetObject(url);

		Integer urlListInteger = 12;

		System.out.print("Recuperation de la derniere version du sitemap");
		Urlset sitemap = getLastVersionOfSitemap();
		System.out.print("Mise à jour des stats BDD");
		List<Turltocheck> urlsToCheck = mergeBddUrlfromSitemapForStats(sitemap, urlListInteger);
		System.out.print("Analyse des urls");
		// checkUrlsList(urlsToCheck,3000);
		System.out.print("Number checks urls : " + sitemap.getUrl().size());

	}

	public void checkUrlsList(List<UrlToCheck> urlsToCheck, int timeToWiteBetweenHttpCall) throws InterruptedException,
			IOException {

		for (UrlToCheck urlToCheck : urlsToCheck) {

			System.out.print("Check url : " + urlToCheck);
			HashMap<String, String> responseDetails = doFullRequest(urlToCheck.getUrl());

			urlToCheck.setRedirectionUrl1(responseDetails.get("DESTINATION_URL"));
			urlToCheck.setRedirectionUrlCode1(responseDetails.get("RESPONSE_CODE"));

			projetService.addCheckResult(urlToCheck);

			Thread.sleep(timeToWiteBetweenHttpCall);
		}
	}

	public void doEvent() throws InterruptedException, IOException {

		List<Event> events = projetService.getAllEventsToDo();
		for (Event event : events) {
			List<UrlToCheck> urlsToCheck = projetService.getUrlToCheck(event.getProjetListUrlUid());
			projetService.flagEventStart(event);
			checkUrlsList(urlsToCheck, 1500);
			projetService.flagEventEnd(event);
		}

	}

	@SuppressWarnings("static-access")
	public HashMap<String, String> doFullRequest(String url) throws IOException {

		Map<String, String> responseDetails = new HashMap<String, String>();
		URL urlObj = new URL(url);
		URLConnection urlConection = urlObj.openConnection();
		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection connection = (HttpURLConnection) urlConection;
		connection.setFollowRedirects(false);
		connection.connect();
		responseDetails.put("RESPONSE_CODE", String.valueOf(connection.getResponseCode()));
		if (connection.getHeaderField("Location") != null) {
			responseDetails.put("DESTINATION_URL", connection.getHeaderField("Location"));
		} else {
			responseDetails.put("DESTINATION_URL", url);
		}

		return (HashMap<String, String>) responseDetails;
	}

	public Integer getClientUID() {
		return clientUID;
	}

	public Urlset getLastVersionOfSitemap() throws JAXBException, MalformedURLException {
		JAXBContext jc = JAXBContext.newInstance("seo.scanner.dto");
		URL sitemap = new URL("http://www.kiabi.com/sitemap/sitemap-category-fr-FR.xml");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		Urlset urlset = (Urlset) unmarshaller.unmarshal(sitemap);
		return urlset;
	}

	@SuppressWarnings("unchecked")
	public List<Turltocheck> mergeBddUrlfromSitemapForStats(Urlset urlSet, Integer urlListUid) {

		List<Turltocheck> currentUrlsList = urlCheckDaoService.getCurrentUrlToCheck(urlListUid);
		List<Turltocheck> urlsFromSitemap = CollectionUtils.transform(urlSet.getUrl(), tUrllistTransformer);
		List<Turltocheck> urlsToDelete = ListUtils.subtract(currentUrlsList, urlsFromSitemap);
		List<Turltocheck> urlsToAdd = ListUtils.subtract(urlsFromSitemap, currentUrlsList);

		for (Turltocheck urlToAdd : urlsToAdd) {
			Integer urlUid = urlCheckDaoService.addUrlToCheck(urlToAdd, urlListUid);
			urlToAdd.setUid(urlUid);
			currentUrlsList.add(urlToAdd);
		}

		for (Turltocheck urlToDelete : urlsToDelete) {
			urlCheckDaoService.flagUrlIsDelete(urlToDelete, urlListUid);
			currentUrlsList.remove(urlToDelete);
		}

		return currentUrlsList;
	}

	public void setClientUID(Integer clientUID) {
		this.clientUID = clientUID;
	}

}
