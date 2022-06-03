package me.rages.tiktokmap;


import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * @author : Michael
 * @since : 5/24/2022, Tuesday
 **/
public class MainTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Testing TikTok Client");
        try {
            Socket socket = IO.socket("https://tiktok-chat-reader.zerody.one/");
            socket.open();
            socket.emit("setUniqueId", "tarynthings");
            socket.on("like", args12 -> {
                try {
                    JSONObject json = new JSONObject(String.valueOf(args12[0]));
                    System.out.println(String.format("%s sent likes to the host", json.getString("nickname")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }).on("gift", args1 -> {
                try {
                    JSONObject json = new JSONObject(String.valueOf(args1[0]));
                    System.out.println(String.format("%s has gifted a %s", json.getString("uniqueId"), json.getString("giftName")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
