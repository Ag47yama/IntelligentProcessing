package ex12;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class Sample1 {
    public static void main(String[] args) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        User user = twitter.verifyCredentials();
//        System.out.println(user.getName());
//        System.out.println(user.getScreenName());
//        System.out.println(user.getFriendsCount());
//        System.out.println(user.getFollowersCount());

        Status status = twitter.updateStatus("Twitter4Jからのツイート！ #twitter4j");
    }
}