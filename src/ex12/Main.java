///*
//x * niconico動画の動画IDをtwitterで検索する。
// * 検索結果のツイート数が2、ふぁぼが1、RTが2として重みづけ。
// * 重みの合計の逆数と、ニコ動でのランキングの相関係数を求める。
// * グラフに表すと更に良し。
// * SearchTwitterのStatusとNiconicoのitemを持つクラスを設けて、メインはそれのリストを持つようにすべき？
// */
//
//
//package ex12;
//import java.util.ArrayList;
//
//import twitter4j.TwitterException;
//
//public class Main{
//    private ArrayList<Double> tweetWeights;
//    private static SearchTwitter search;
//
//    public static void main(String []args) {
//        Main launch = new Main();
//        double n = (double)launch.tweetWeights.size();
//        //x:rank, y:weight
//        double Sxy = 0.0, Sxx = 0.0, Syy = 0.0;
//        double Sx = 0.0, Sy = 0.0;
//        int index = 0;
//        double covariation = 0.0;
//        double sigX = 0.0, sigY = 0.0;
//        double correlation = 0.0;
//        for(double i:launch.tweetWeights) {
//            index++;
//            Sx += index;
//            Sy += i;
//            Sxx += index * index;
//            Syy += i * i;
//            Sxy += index * i;
//        }
//        //1位から順に重みの計算結果を表示
//        //desplay the results from 1
//        System.out.println(launch.tweetWeights);
//        System.out.println("size:" + launch.tweetWeights.size());
//        System.out.println("Sx:" + Sx + ", Sy:" + Sy + ", Sxy" + Sxy);
//        System.out.println("Sxx:" + Sxx + ", Syy:" + Syy);
//        covariation = Sxy /n - Sx / n * Sy / n;
//        sigX = Math.sqrt(Sxx / n - Sx / n * Sx / n);
//        sigY = Math.sqrt(Syy / n - Sy / n * Sy / n);
//        correlation = -covariation / sigX /sigY;
//        System.out.println("Correlation is " + correlation);
//    }
//
//    public Main() {
//        //1位から順にニコニコランキングのIDのリスト。
//        //
//        Niconico nico = new Niconico();
//        //twitterの重みのリスト。1位から順に。
////		ArrayList<String> movies = new ArrayList<String>();
//
//        tweetWeights = new ArrayList<Double>();
//        try {
//            search = new SearchTwitter();
//        } catch (TwitterException e) {
//            // TODO 自動生成された catch ブロック
//            e.printStackTrace();
//        }
//        for(Niconico.item mov: nico.getItemList()) {
////			movies.add(mov.getId());
//            System.out.println("動画ID:" + mov.getId());
//            try {
//                tweetWeights.add((double)search.searchTweets(mov.getId()));
//            } catch (TwitterException e) {
//                // TODO 自動生成された catch ブロック
//                e.printStackTrace();
//            }
//        }
//    }
//}