package crud.controller;

import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import crud.service.ServerManager;
@Controller
@RequiresRoles("admin")
@RequestMapping("/")
@ResponseBody
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class) 
public class BitCoinDataCenter  implements Runnable{
	 
	@Autowired
	ServerManager serverManager;
	
	
    public BitCoinDataCenter(){
        startup();
    }
    
    public  void startup(){
        new Thread(this).start();
    }
    
    public void run() {
        int bitPrice = 100000;
        while(true){
             
            //每隔1-3秒就产生一个新价格
            int duration = 1000+new Random().nextInt(2000);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //新价格围绕100000左右50%波动
            float random = 1+(float) (Math.random()-0.5);
            int newPrice = (int) (bitPrice*random);
             
            //查看的人越多，价格越高
            int total = serverManager.getTotal();
            newPrice = newPrice*total;
             
            String messageFormat = "{\"price\":\"%d\",\"total\":%d}";
            String message = String.format(messageFormat, newPrice,total);
            //广播出去
            
            serverManager.broadCast(message);
        }
    }
}




