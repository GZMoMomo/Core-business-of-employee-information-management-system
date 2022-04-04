package crud.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
public class ServerManager {
	@Autowired
	private static Collection<BitCoinServer> servers = Collections.synchronizedCollection(new ArrayList<BitCoinServer>());
    
    public static void broadCast(String msg){
        for (BitCoinServer bitCoinServer : servers) {
            try {
                bitCoinServer.sendMessage(msg);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
     
    public static int getTotal(){
        return servers.size();
    }
    public static void add(BitCoinServer server){
        System.out.println("有新连接加入！ 当前总连接数是："+ servers.size());
        servers.add(server);
    }
    public static void remove(BitCoinServer server){
        System.out.println("有连接退出！ 当前总连接数是："+ servers.size());
        servers.remove(server);
    }
    
    
}















