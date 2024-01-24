import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class tt {
    @Test
    public void t1(){
        String regex = "^(?<!999.*)$";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入要匹配的内容：");
            scanner.nextLine();
            String s = scanner.nextLine();
            boolean b = s.matches(regex);
            System.out.println("匹配结果: "+b+"\\n\\n");
        }
    }
}
