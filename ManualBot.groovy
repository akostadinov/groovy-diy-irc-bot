import org.jibble.pircbot.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManualBot extends PircBot {

    public static void main(String[] args) throws Exception {
        def channels = new HashMap<String, String>();
        channels.put("#chan1", null);
        channels.put("#chan2", null);
        String channel="#chan1";
        def curLine;
        Matcher matcher;

        // Now start our bot up.
        MyBot bot = new MyBot(args[0], channels);

        // Enable debugging output.
        bot.setVerbose(true);

        // Connect to the IRC server.
        bot.connect("irc.example.com");

        // Join the #pircbot channel.
        // bot.joinChannel(channel);

        // Read messages from file
        try {
           InputStreamReader converter = new InputStreamReader(System.in);
           BufferedReader stdin = new BufferedReader(converter);
           while ( curLine = stdin.readLine() ) {
              if ( ( matcher = curLine =~ "^/op[\\s]([\\S]+)[\\s](.*)\$" ) ) {
                 bot.op(matcher[0][1], matcher[0][2]);
                 //println "1:" + matcher[0][1];
                 //println "2:" +  matcher[0][2];
              } else {
                 // send message to channel
                 curLine = curLine.split("[\\s]",2);
                 bot.sendMessage(curLine[0], curLine[1]);
              }
              // println curLine;
           }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}

