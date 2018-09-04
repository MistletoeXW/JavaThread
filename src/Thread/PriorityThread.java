package Thread;

import javax.swing.*;
import java.awt.*;

/*
 * @program: Thread
 * @description: 线程优先级实例
 * @author: xw
 * @create: 2018-09-04 22:37
 **/
public class PriorityThread extends JFrame {
    private Thread threadA; //创建四个进程
    private Thread threadB;
    private Thread threadC;
    private Thread threadD;

    final JProgressBar progressBar1 = new JProgressBar(); //创建四个进度条
    final JProgressBar progressBar2 = new JProgressBar();
    final JProgressBar progressBar3 = new JProgressBar();
    final JProgressBar progressBar4 = new JProgressBar();
    int count = 0;

    //定义一个实现Runnable接口的类
    private final class MyThread implements Runnable{
        private final JProgressBar bar;
        int count = 0;
        private MyThread(JProgressBar bar){
            this.bar = bar;
        }
        public void run(){
            while(true){
                bar.setValue(count+=5);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.print("当前线程被中断");
                }
            }
        }
    }

    //定义设置线程的名称、优先级方法
    public static void setPriority(String threadName, int priority,Thread thread){
        thread.setPriority(priority);
        thread.setName(threadName);
        thread.start();
    }

    public PriorityThread() {
        super();
        getContentPane().add(progressBar1, BorderLayout.NORTH); //将第一个进度条设置到窗体最北边
        getContentPane().add(progressBar2,BorderLayout.BEFORE_FIRST_LINE); //将第二个进度条设置到窗体最南边
        getContentPane().add(progressBar3,BorderLayout.AFTER_LINE_ENDS);
        getContentPane().add(progressBar4, BorderLayout.SOUTH);
        progressBar1.setStringPainted(true);  //设置显示进度条数字字符
        progressBar2.setStringPainted(true);
        progressBar3.setStringPainted(true);
        progressBar4.setStringPainted(true);

        threadA = new Thread(new MyThread(progressBar1)); //分别实例化4个线程
        threadB = new Thread(new MyThread(progressBar2));
        threadC = new Thread(new MyThread(progressBar3));
        threadD = new Thread(new MyThread(progressBar4));

        setPriority("threadA",5,threadA);
        setPriority("threadB",5,threadB);
        setPriority("threadC",4,threadC);
        setPriority("threadD",3,threadD);
    }

    //设置窗体的各种属性的方法
    public static void init(JFrame frame, int width, int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        init(new PriorityThread(),200,200);
    }

}
