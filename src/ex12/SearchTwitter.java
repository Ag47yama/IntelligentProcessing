//package ex12;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//
//import twitter4j.Query;
//import twitter4j.QueryResult;
//import twitter4j.Status;
//import twitter4j.Twitter;
//import twitter4j.TwitterException;
//import twitter4j.TwitterFactory;
//
//public class SearchTwitter{
//	private ArrayList<Status> tweets;
////	private ArrayList<Integer> weights;
//	final private int tweetNum = 1;
//	private Query query;
//	Twitter twitter;
//	QueryResult result;
//
//	public SearchTwitter()throws TwitterException {
//		Calendar cal = Calendar.getInstance();
//		twitter = new TwitterFactory().getInstance();
//		tweets = new ArrayList<Status>();
////		weights = new ArrayList<Integer>();
//		query = new Query();
//		query.setCount(tweetNum);
//		String today = "";
//		today += cal.get(Calendar.YEAR);
//		today += "-";
//		today += cal.get(Calendar.MONTH + 1);
//		today += "-";
//		today += cal.get(Calendar.DAY_OF_MONTH);
//
////        SimpleDateFormat dat = new SimpleDateFormat("yyyy-MM-dd");
////        SimpleDateFormat past = new SimpleDateFormat("yyyy-MM-dd");
//		query.until(today);
//		System.out.println("今日の日付" + today);
//        cal.add(Calendar.DAY_OF_MONTH, -7);
//        String past = "";
//		past += cal.get(Calendar.YEAR);
//		past += "-";
//		int tmp = cal.get(Calendar.MONTH) + 1;
//		past += tmp;
//		past += "-";
//		past += cal.get(Calendar.DAY_OF_MONTH);
//		System.out.println("past:" + past);
//		query.since(past);
//	}
//	public ArrayList<Status> getTweets(){
//		return tweets;
//	}
//	public int searchTweets(String eachId) throws TwitterException {
//		int weight = 0;
//		// 検索ワードをセット
//		query.setQuery(eachId);
//		//query.setQuery(eachId + "exclude:retweets");
//
//		// 検索実行
//		result = twitter.search(query);
//		System.out.println("ヒット数 : " + result.getTweets().size());
//		weight += result.getTweets().size() * 2;
//
//		for (Status tweet : result.getTweets()) {
//			tweets.add(tweet);
//			System.out.println(tweet);
////			System.out.println("動画ID:" + eachId);
//			// 本文
//			System.out.println("本文:" + tweet.getText());
//			// 発言したユーザ
//			System.out.println("ユーザ:" + tweet.getUser().getScreenName());
//			//fav
//			System.out.println("ふぁぼ:" + tweet.getFavoriteCount());
//			weight += tweet.getFavoriteCount() * 1;
//			//rt
//			System.out.println("RT:" + tweet.getRetweetCount());
//			weight += tweet.getRetweetCount() * 2;
//			// 発言した日時
//			System.out.println("日時:" + tweet.getCreatedAt() + "\n");
//		}
//		return weight;
//	}
//}




package ex12;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SearchTwitter{
    private String movID;
    public static void main(String[] args)throws TwitterException {
        Twitter twitter = new TwitterFactory().getInstance();
        Query query = new Query();
        query.setCount(5);
        query.since("2019-6-11");
        query.until("2019-6-12");

        // 検索ワードをセット（試しに寿司を検索）
        query.setQuery("テスト");
        // exclude:retweets

        // 検索実行
        QueryResult result = twitter.search(query);

        System.out.println("ヒット数 : " + result.getTweets().size());

        // 検索結果を見てみる
        for (Status tweet : result.getTweets()) {
            // 本文
            System.out.println(tweet.getText());
            // 発言したユーザ
            System.out.println(tweet.getUser().getScreenName());
            //fav
            System.out.println(tweet.getFavoriteCount());
            //rt
            System.out.println(tweet.getRetweetCount());
            // 発言した日時
            System.out.println(tweet.getCreatedAt());
            // 他、取れる値はJavaDoc参照
            // http://twitter4j.org/ja/javadoc/twitter4j/Tweet.html
        }
    }

    public SearchTwitter(String id) {
        movID = id;
    }
}
