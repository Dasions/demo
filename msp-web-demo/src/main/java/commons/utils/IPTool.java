package commons.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IPTool {

	public static synchronized List<String> getIps(int ipNums, String markUrl) {
		int defaultPages = 1;
		List<String> ips = new CopyOnWriteArrayList();
		try {
			while (ips.size() < ipNums && defaultPages < 20) {
				ips.addAll(getIpXICI(defaultPages, defaultPages + 1));
				testIp(ips, markUrl);
				defaultPages++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ips;
	}

	public static void testIp(List<String> ips, String markUrl) {
		HttpclientTool tool = new HttpclientTool();
		tool.createDefaultConfigClientByHttpClients();
		tool.setDefaultCookieStore();
		tool.setDefaultHeader();
		tool.setUrl(markUrl);
		List<String> ipsUnUsed = new ArrayList<>();
		ips.forEach(ip -> {
			if(!ip.contains(":")){
				ips.remove(ip);
				try {
					TimeUnit.MILLISECONDS.sleep(new Random().nextInt(10));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				tool.setRequestConfig(tool.getProxyRequestConfig(ip.split(":")[0], Integer.parseInt(ip.split(":")[1])));
			}

			try {
				tool.getContentByGet();
			} catch (Exception e) {
				ipsUnUsed.add(ip);
			}
		});

		ips.removeAll(ipsUnUsed);
	}

	public static HashMap<String, String> getIpKUAI() throws IOException {
		HttpclientTool tool = new HttpclientTool();
		tool.createDefaultConfigClientByHttpClients();
		tool.setDefaultCookieStore();
		tool.setDefaultHeader();
		tool.setUrl("http://www.kuaidaili.com/free/inha/1/");
		String ipHtml = tool.getContentByGet();
		HashMap<String, String> ips = new HashMap<String, String>();
		Document docs = Jsoup.parse(ipHtml);
		Element list = docs.getElementById("list");
		Elements trs = list.getElementsByTag("tr");
		for (Element tr : trs) {
			Elements tds = tr.getElementsByTag("td");
			if (tds.size() > 0) {
				ips.put(tds.get(0).html().trim(), tds.get(1).html().trim());
			}
		}
		return ips;
	}

	public static List<String> getIpXICI(int startPage, int endPages) throws IOException {
		HttpclientTool tool = new HttpclientTool();
		tool.createDefaultConfigClientByHttpClients();
		tool.setDefaultCookieStore();
		tool.setDefaultHeader();
		List<String> ips = new ArrayList();
		for (int i = startPage; i < endPages; i++) {
			tool.setUrl("http://www.xicidaili.com/nt/" + i);
			String ipHtml = tool.getContentByGet();
			Document docs = Jsoup.parse(ipHtml);
			Element list = docs.getElementById("ip_list");
			Elements trs = list.getElementsByTag("tr");
			for (Element tr : trs) {
				Elements tds = tr.getElementsByTag("td");
				if (tds.size() > 0) {
					String sudu = tds.get(6).getElementsByTag("div").get(0).attr("title");
					if (sudu.contains("秒")) {
						sudu = sudu.replace("秒", "");
					}

					String sudu2 = tds.get(7).getElementsByTag("div").get(0).attr("title");
					if (sudu2.contains("秒")) {
						sudu2 = sudu2.replace("秒", "");
					}

					if (Double.parseDouble(sudu) < 0.6 && Double.parseDouble(sudu2) < 0.1) {
						ips.add(tds.get(1).html().trim() + ":" + tds.get(2).html().trim());
					}

				}
			}
		}
		return ips;
	}

}
