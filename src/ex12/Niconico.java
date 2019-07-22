package ex12;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

public class Niconico {
	private static ArrayList<item> itemList;
	private final int length = 10;
	public Niconico() {
		System.out.println("a");
		try {
			URL url = new URL("https://www.nicovideo.jp/ranking/fav/daily/g_tech?rss=2.0&lang=ja-jp");
			//total genre
			//https://www.nicovideo.jp/ranking/fav/daily/all?rss=2.0&lang=ja-jp
			//music genre
			//https://www.nicovideo.jp/ranking/fav/daily/g_ent2?rss=2.0&lang=ja-jp
			//technology genre
			//https://www.nicovideo.jp/ranking/fav/daily/g_tech?rss=2.0&lang=ja-jp
			URLConnection connection = url.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			//ArrayList<item> itemList = new ArrayList<item>();
			itemList = new ArrayList<item>();

			//----------------------------------------------------------------------------------------
			//DOMツリーの構築
			Document document = Niconico.buildDocument(inputStream, "UTF-8");
			//viewers.showTree(document.setDocumentElement());
			//XPathの表現を担うXPathオブジェクトを生成
			XPath xPath = XPathFactory.newInstance().newXPath();
			//XPathでitem要素を全て取ってきたい
			NodeList itemNodeList = (NodeList)xPath.evaluate("/rss/channel/item", document, XPathConstants.NODESET);//RSS 2.0

			//各item要素について
			int rank = 0;
			for(int i = 0; i< length; i++){
				Node current = itemNodeList.item(i);
				String description = ((NodeList)xPath.evaluate("description", current, XPathConstants.NODESET)).item(0).getTextContent();
				String title = ((NodeList)xPath.evaluate("title", current, XPathConstants.NODESET)).item(0).getTextContent();
				URL link = new URL(((NodeList)xPath.evaluate("link", current, XPathConstants.NODESET)).item(0).getTextContent());
				itemList.add(new item(title, description, link));
			}
			for(item i : itemList) {
				System.out.println(++rank);
				System.out.println(i.getLink());
				i.handleData();
				System.out.println(i.getId());
			}
		}
		catch (IOException e) {
			System.out.println(e);
		} catch (XPathExpressionException e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e);
		}
	}
	public int getLength() {
		return length;
	}
	public ArrayList<item> getItemList() {
		return itemList;
	}
	/** DOM Tree の構築 */
	public static Document buildDocument(InputStream inputStream, String encoding) {
		Document document = null;
		try {

			// DOM実装(implementation)の用意 (Load and Save用)
			DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			DOMImplementationLS implementation = (DOMImplementationLS)registry.getDOMImplementation("XML 1.0");
			// 読み込み対象の用意
			LSInput input = implementation.createLSInput();
			input.setByteStream(inputStream);
			input.setEncoding(encoding);
			// 構文解析器(parser)の用意
			LSParser parser = implementation.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
			parser.getDomConfig().setParameter("namespaces", false);
			// DOMの構築
			document = parser.parse(input);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
	static class item{
		private String title;
		private String description;
		private URL link;
		private String id;
		item(String title, String description, URL link){
			this.title = title;
			this.description = description;
			this.link = link;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public URL getLink() {
			return link;
		}
		public void setLink(URL link) {
			this.link = link;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public void handleData() {
			URLConnection connection;
			try {
				connection = link.openConnection();
				connection.connect();
				Pattern pattern = Pattern.compile("watch/(.+)");
				String line = getLink().toString();
					Matcher matcher = pattern.matcher(line);

					if(matcher.find()) {
						setId(matcher.group(1));

					}
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}
//	public static void main(String[] args) {
//	Niconico viewer = new Niconico();
//	try {
//		URL url = new URL("https://www.nicovideo.jp/ranking/fav/daily/all?rss=2.0&lang=ja-jp");
//		URLConnection connection = url.openConnection();
//		connection.connect();
//		InputStream inputStream = connection.getInputStream();
//		//ArrayList<item> itemList = new ArrayList<item>();
//		itemList = new ArrayList<item>();
//
//		//----------------------------------------------------------------------------------------
//		//DOMツリーの構築
//		Document document = viewer.buildDocument(inputStream, "UTF-8");
//		//viewers.showTree(document.setDocumentElement());
//		//XPathの表現を担うXPathオブジェクトを生成
//		XPath xPath = XPathFactory.newInstance().newXPath();
//		//XPathでitem要素を全て取ってきたい
//		NodeList itemNodeList = (NodeList)xPath.evaluate("/rss/channel/item", document, XPathConstants.NODESET);//RSS 2.0
//
//		//各item要素について
//		int rank = 0;
//		for(int i = 0; i< itemNodeList.getLength(); i++){
//			Node current = itemNodeList.item(i);
//			String description = ((NodeList)xPath.evaluate("description", current, XPathConstants.NODESET)).item(0).getTextContent();
//			String title = ((NodeList)xPath.evaluate("title", current, XPathConstants.NODESET)).item(0).getTextContent();
//			URL link = new URL(((NodeList)xPath.evaluate("link", current, XPathConstants.NODESET)).item(0).getTextContent());
//			itemList.add(new item(title, description, link));
//		}
//		for(item i : itemList) {
//			System.out.println(++rank);
//			System.out.println(i.getLink());
//			i.handleData();
//			System.out.println(i.getId());
//		}
//	}
//	catch (IOException e) {
//		System.out.println(e);
//	} catch (XPathExpressionException e) {
//		// TODO 自動生成された catch ブロック
//		System.out.println(e);
//	}
//}
}