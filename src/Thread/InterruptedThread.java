package Thread;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

/*
 * @program: Thread
 * @description: 线程中断实例
 * @author: xw
 * @create: 2018-09-04 20:14
 **/
public class InterruptedThread extends JFrame {
    Thread thread;
    public InterruptedThread(){
        super();
        final JProgressBar jProgressBar = new JProgressBar();  //创建进度条
        getContentPane().add(jProgressBar, BorderLayout.NORTH);
        jProgressBar.setStringPainted(true); //设置进度条显示数字
        thread = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                while(true){
                    jProgressBar.setValue(++count);
                    try{
                        thread.sleep(1000); //使线程休眠1000毫秒
                    }catch (Exception e){
                        System.out.print("当前线程程序被中断");
                        break;
                    }
            }
        }
    });
        thread.start();    //启动线程
        thread.interrupt();   //中断线程
    }
    //设置窗体的各种属性的方法
    public static void init(JFrame frame, int width, int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        init(new InterruptedThread(),100,100);
    }
}
