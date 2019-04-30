package com.tomato.framework.plugin.rmi.transport;

import com.tomato.framework.plugin.rmi.RMIRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Transport {
    
    private String host;
    
    private int port;
    
    public Object send(RMIRequest request) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket socket = null;
        try {
            socket = new Socket(this.host, this.port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(request);
            ois = new ObjectInputStream(socket.getInputStream());
            Object obj = ois.readObject();
            return obj;
        } catch (IOException e) {
        
        } catch (ClassNotFoundException e) {
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    public Object accept() {
        return null;
    }
}
