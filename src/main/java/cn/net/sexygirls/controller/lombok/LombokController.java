package cn.net.sexygirls.controller.lombok;

import cn.net.sexygirls.controller.lombok.domian.Lombok;
import lombok.Cleanup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @Description:RestFull Interface
 * @Author: zule
 * @Date: 2019/5/5
 */

@RestController
public class LombokController {
    @GetMapping("/lombok")
    Lombok lombok(){
        Lombok lombok = new Lombok();
        lombok.setUsername("tom");

        lombok = new Lombok("lucy1",13);

        //@NonNull
        //lombok.print(null);

        System.out.println(lombok.toString());

        return lombok;
    }

    /**
     * @description how to use @Cleanup
     * @param from
     * @param to
     * @return void
     * @author grandhappy
     * @date 2019/5/30
     */
    private void cleanUp(String from,String to) throws IOException {
        @Cleanup InputStream in = new FileInputStream(from);
        @Cleanup OutputStream out = new FileOutputStream(to);
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1){
                break;
            }
            out.write(b, 0, r);
        }
    }

    public void noCleanUp(String from,String to) throws IOException {
        InputStream in = new FileInputStream(from);
        try {
            OutputStream out = new FileOutputStream(to);
            try {
                byte[] b = new byte[10000];
                while (true) {
                    int r = in.read(b);
                    if (r == -1) break;
                    out.write(b, 0, r);
                }
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
