import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

    public void run()throws IOException{
        int port=8010;
        ServerSocket socket=new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true){
            try{
                System.out.println("Server is Listening on port "+port);
                Socket acceptedConnection=socket.accept();
                System.out.println("Connection accepted from Client "+acceptedConnection);

                PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient=new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                toClient.println("Hello from the Server!!");

                acceptedConnection.close();
                toClient.close();
                fromClient.close();
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Server server=new Server();
        try{
            server.run();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
