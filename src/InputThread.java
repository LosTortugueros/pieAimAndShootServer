import java.io.*;
import java.net.Socket;

/**
 * Created by root on 05/12/14.
 */
public class InputThread extends Thread {
        // reader to read keyboard input
        private CoucouPieServer server;
        private BufferedReader reader;
        private InputStreamReader streamReader;
    private boolean alive;

    // sock must be opened already and connection established
        public InputThread(CoucouPieServer server)
        {
            this.server =server ;
            alive = true;
            streamReader = new InputStreamReader(System.in);
            reader = new BufferedReader(streamReader);



        }

        public void run()
        {
            while(!alive)
            {
                //attempt to read from kb, then send
                // the output straight along the socket
                try {
                    server.print(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
