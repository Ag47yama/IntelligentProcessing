package exFin;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TheMovieDataBase {
    static Properties props = new Properties();
    static {
        try{
            props.load(new FileInputStream("TMDb.properties"));
        }catch(IOException e){
            System.out.println(e);
        }
    }
    String key = props.getProperty("key");

    String url = "https://api.themoviedb.org/3/movie/550?api_key=" + key;
}
