package Thread;

import javax.swing.*;
import java.awt.*;

/*
 * @program: Thread
 * @description: 线程加入实例
 * @author: xw
 * @create: 2018-09-04 19:26
 **/
public class JoinThread extends JFrame {
    private Thread threadA; //创建两个线程
    private Thread threadB;

    final JProgressBar progressBar = new JProgressBar(); //创建两个进度条
    final JProgressBar progressBar2 = new JProgressBar();
    int count = 0;
    public JoinThread(){
        super();
        getContentPane().add(progressBar, BorderLayout.NORTH); //将第一个进度条设置到窗体最北边
        getContentPane().add(progressBar2,BorderLayout.SOUTH); //将第二个进度条设置到窗体最南边
        progressBar.setStringPainted(true);  //设置显示进度条数字字符
        progressBar2.setStringPainted(true);
        threadA = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                while(true){
                    progressBar.setValue(++count);   //设置进度条当前值
                    try{
                        Thread.sleep(100);   //使线程A休眠100毫米
                        threadB.join();             //使线程B调用join()方法
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        threadA.start();
        threadB = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                    while(true){
                        progressBar2.setValue(++count);
                        try{
                            Thread.sleep(100);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if(count==100) break;
                    }
            }
        });
        threadB.start();
    }

    //设置窗体的各种属性的方法
    public static void init(JFrame frame, int width, int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        init(new JoinThread(),100,100);
    }
}
