import org.jibble.pircbot.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyBot extends PircBot {

    private Map<String, String> permChannels;

    public MyBot(String nick, Map<String, String> channels) {
       this.permChannels = channels;
       this.setName(nick);
    }
    public MyBot(String nick) {
       this(nick, new HashMap<String, String>());
    }

    @Override
    protected void onDisconnect() {
       // reconnect on disconnect
       while (!isConnected()) {
          try {
             reconnect();
          }
          catch (Exception e) {
             println "Crap, can't connect: " + e.toString();
             Thread.sleep(2000);
          }
       }
    }

    @Override
    protected void onConnect() {
       permChannels.each {
          if (it.getValue() == null) {
             joinChannel(it.getKey());
          } else {
             joinChannel(it.getKey(),it.getValue());
          }
       }
    }

    @Override
    protected void onJoin(String channel,
                      String sender,
                      String login,
                      String hostname) {
       def masters = [ "akostadino.*", 
                   "myfrien.*",
                 ];
       // give op to user if it is not us
       // must do the same onNickChange
       masters.each() {
          if ( sender ==~ "${it}" ) {
             if (!sender.equals(getNick()))
                op(channel, sender);
             //sendMessage(channel, "Greetings " + sender);
          }
       }
    }
}

