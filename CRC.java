import java.awt.*;
import java.awt.event.*;

public class CRC extends Frame{
    /*
    * 主函数
    * */
    public static void main(String[] args) {
        Frame f = new Frame("CRC校验");

        // 关闭窗体
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setLocation(455,207);    // 设置窗体出现坐标
        f.setSize(420,230); // 设置窗体的尺寸
        f.setBackground(Color.white);     // 设置背景颜色
        f.setResizable(false);           // 禁止窗体改变尺寸

        // 添加面板
        Panel p = new Panel(null);
        p.setBounds(455,207,420,230);
        f.add(p);

        // 待校验码
        Label pendingCode = new Label("校验:");
        pendingCode.setLocation(75, 50);
        pendingCode.setSize(50, 25);
        pendingCode.setFont(new Font("微软雅黑",Font.BOLD,16));
        p.add(pendingCode);

        // 结果标签
        Label result = new Label("结果:");
        result.setLocation(75, 100);
        result.setSize(50, 25);
        result.setFont(new Font("微软雅黑",Font.BOLD,16));
        p.add(result);

        // 输入待校验码的文本框
        TextField t1 = new TextField();
        t1.setLocation(130, 50);
        t1.setSize(220,25);
        t1.setFont(new Font("微软雅黑",Font.PLAIN,16));
        p.add(t1);

        // 结果文本框
        TextField t2 = new TextField();
        t2.setEditable(false);
        t2.setLocation(130, 100);
        t2.setSize(220,25);
        t2.setFont(new Font("微软雅黑",Font.PLAIN,16));
        p.add(t2);


        // 计算按钮
        Button caculate = new Button("计算");
        //触发事件
        caculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = t1.getText();                     // 获取待校验的内容
                String array[] = new String[code.length()];     // 将字符串转化为字符串数组
                for (int i = 0; i < array.length; i++) {
                    array[i] = String.valueOf(code.charAt(i));  // 赋值
                }

                int crci=0x00;                                  // 起始字节0x00
                for (int j = 0; j < array.length; j=j+2)        // 两位两位进行读取
                {                                               // 转化为10进制数
                    int initial = Integer.parseInt(array[j]+array[j+1],16);
                    crci ^= initial;                            // 异或运算
                }

                t2.setText(intToHex(crci));
            }
        });
        caculate.setLocation(130, 160);    //按钮在窗体中的坐标
        caculate.setSize(220, 30);  //设计按钮的尺寸
        p.add(caculate);                         //把按钮元素添加到窗体中
        f.setVisible(true);                      //设置窗体的可见性

    }


    /*
    * 此函数用于10进制数转化为16进制数
    * */
    private static String intToHex(int crci) {
        int i = 0;                          // 字符数组索引
        char[] S = new char[100];           // 开辟存储空间

        // 进行计算，如果为0，直接返回，否则执行10进制转16进制的运算
        if(crci == 0){
            return "00";
        } else{
            while(crci!=0) {
                int t=crci%16;
                if(t >=0 && t<10) {
                    S[i] = (char)(t+'0');
                    i++;
                } else {
                    S[i] = (char)(t+'A'-10);
                    i++;
                }
                crci=crci/16;
            }
        }

        // 将字符数组转化为字符串
        String s = new String(S,0,2);
        String s1 = "";
        for(int j=s.length()-1; j>=0; j--){
            s1 = s1+s.charAt(j);
        }
        return s1;
    }
}
