package cn.net.sexygirls.controller.lombok.domian;

import lombok.*;

/**
 * @author zule
 * @description
 * @date 2019/5/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lombok {
    private String username;
    private int age;

    public void print(@NonNull String username){
        System.out.println(username);
    }
}
