package com.zetyun.driver.socket;

import com.zetyun.driver.log.LogWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Created by Bing
 */
public class SocketClient {

    public static String readInfo(String ip, int port) {
        StringBuffer stringBuffer = null;

        Socket socket = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        BufferedReader inputBufferedReader = null;
        try {

            // Create socket client.
            socket = new Socket(ip, port);

            // Get output stream, send info to server.
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            printWriter = new PrintWriter(socket.getOutputStream());
            String line = bufferedReader.readLine();
            while (!line.equalsIgnoreCase("end")) {
                printWriter.println(line);
                line = bufferedReader.readLine();
            }
            printWriter.flush();

            // Get input stream, read info from server.
            inputBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stringBuffer = new StringBuffer();
            String tmp;
            int index;
            while ((tmp = inputBufferedReader.readLine()) != null) {
                if ((index = tmp.indexOf("eof")) != -1) {
                    stringBuffer.append(tmp.substring(0, index));
                    break;
                }
                stringBuffer.append(tmp);
            }
        } catch (Exception e) {
            LogWriter.error(SocketClient.class, "Read message error, exception: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                if (inputBufferedReader != null) {
                    inputBufferedReader.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                LogWriter.error(SocketClient.class, "Close IO session error, exception: " + e.getMessage());
            }
        }

        return stringBuffer.toString();
    }

}
