package com.example.ssh_client;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class MainActivity extends AppCompatActivity {
//public class JSchExampleSSHConnection {

    private TextView textview1, textview2, textview3;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //find id
        this.textview1 = (TextView) this.findViewById(R.id.textView);
        this.textview2 = (TextView) this.findViewById(R.id.textView2);
        this.textview3 = (TextView) this.findViewById(R.id.textView3);

//        SCPUpload.main(new String[]{"dsa", "dsa"});


        this.textview2.setText("start");

        String host="43.3.177.221";
        String user="thanh";
        String password="thanh123";
        String command1="ls -ltr";
        try{
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");


            JSch jsch = new JSch();


            this.textview2.setText("before privateKey add");

            Session session_ = jsch.getSession(user, host, 22);
            session_.setConfig("StrictHostKeyChecking", "no");

            String passPhrase= "thanh123";

            String privateKey= "-----BEGIN RSA PRIVATE KEY-----\n" +
                    "Proc-Type: 4,ENCRYPTED\n" +
                    "DEK-Info: AES-128-CBC,01A942FA1C0B8E32E7230AD4321B51DE\n" +
                    "\n" +
                    "SIXNFAwTxQS9mpnWtM+hv2AcGfxsws5s9OzuqeyCuhR3sf9fVLB+ybW2n9rKhlIA\n" +
                    "TPK/zsRmYbFn0J0XlSVbR+i+5k6KfCwY7FLxD1VsUz0yxSeMxNyoDkwfO3kXAkp8\n" +
                    "U26XnFJfsCGt9vGLe3kPr9JakQyeli7mzGdo32x8v2UAQX5QuoQoFs4lDu4m3b5d\n" +
                    "pnVcsoNM8NEK/v1YUOgesECyCGjFMAsmHZ/pSRz+XCcHwcLoCg/Mm3lGTfwJlbHt\n" +
                    "cU1kZ/125A5lNF3kR+5Ew8vCIIyhU+WC5diTgaDm80h7f9c1kpzAn/GtYWbe2rap\n" +
                    "UuBRsjIUDqTzHew6EkcG9zGMAL49fcL2aEKopvy2SIbA4DuFFbjZEc60FStGfyjR\n" +
                    "R48TojFo1R9EXwtSQYNgxNQpx+MJhYX+UrzjVTw8IpWuLu8gIRP5ov/XVaYg5kN6\n" +
                    "M3YoKHe8Oh9bYhCZGaborrrGF8c+zhhhd9qyJS8RqHHJTo9/gBn6fH37/PwFH9Ip\n" +
                    "jbYsW9WCH0NnZgrvmZGQ6E+TbPkpO4nvVNh1Efjw7Xn2gcpgSchIR/1oQcAIpxBJ\n" +
                    "4AeyrdUGw8c4pgpEkPo4K+V58Xm+BIe3BCptLIyItNQ2U9AkPWUwkPXNNOo5wmJK\n" +
                    "vUcCIM7kRVSQfMwqy4PzFERKJPE7NDrUxxlluy7je6bIZxMuUzszOTaydk/ZQSAd\n" +
                    "CnJ12ptczDCD0ea/CPMhQ0o4E+wJBWL1D9dN4HtojlpEML4Yk+CeuOHDCAsxHAe+\n" +
                    "V0s3oHQBbVmnan4uFhvFH89GHgiC12Ic+gFF5QhB3S+J3KHiSDh+w5b58beQsHEj\n" +
                    "TEkJCYixzSqHenuy3/zY3QWYP87v0O+hfbVbL+SHoVnS6bYYwXVRJ/O5lMk1fM/N\n" +
                    "vt6MfC2jGXRvMjnDp3iz+Dr4F6+fraV4K/bfU8Hc0/7l9WSM1+nZgGtdiLqtiBg6\n" +
                    "xTSRILXEEc1K/5zg4Zuk7yOklNTvGFiGNXAN3O3Adku+AsXw6VO+gpF1o/UTBxjk\n" +
                    "UZGY3A/V940wXuMvmCGwI5YAZqpB/8CGthKqqoTtp+VyLEzeTnnWtUUG9BXILh7I\n" +
                    "sPtPuxCPDtH4z9RKpWnXlohqgzEKQmh5HVMBDdr3wgiJ/67E3u88P9O5b7tB2cg8\n" +
                    "OWRNFPHrlLMcaTf4sWJ1vgn2gLLTQ1w/6yTIS/8BtyZnNTtS79a9krZ8oDb5fzhd\n" +
                    "9EaCm5fSVRVG3bfAnWmS0aUihrWq036Sjs/ooZLw3sLmEiIBiAg0lRcmjRF2nefL\n" +
                    "GsNkh2ZpD9EaoJDHuzaMjI9Czvbrtc5e+YT5KLAnLe8FEkP8pxpUB8fxm1KQDcIy\n" +
                    "rj5POTakKhmZ0QcI7XQCvA7vVZGbE5doWaJcPnmAqZ3R/s/J0Mm18quLOLZcEZ/W\n" +
                    "J3mIr3btv2q2Mv984xvj8ygqZPqCn6WAJJcjwDNVEVN90IQZdTqXbFNVHG/+YYGg\n" +
                    "zF7yQ4o/lBxDiYG4QOHVGgulD1zEgabiOLduPqTNW9qj0Cqit5aL6zmRpm4ErrOv\n" +
                    "PTsHoifK8ei9rSyj2UT5bPLRlxMM9YRwhYuRfv67XHAfw47m07HZ7XLx4arljtCh\n" +
                    "-----END RSA PRIVATE KEY-----\n";

            String publicKey= "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDD98aPHZ00ByAnNnVy4PJj66h7DByDOOjmeH/1w1EOW6xxBWkxbghbXIn7gSPYO9f92H23He7tB/muRgG4jixCjVA2wAOBinUfrPvVZrkSH6aMFNTwbPBvo/e63sPXO/ObQG660UoJDtJ6kVD/mMWOUVeOFuXp2VJrENP13I/HEhzjvrMm6tSIwf93EZm++Ci9CqgwywRzGyemtGxq2rzi+RUrZljRk4uNPGiQ+Aha/Kxqybdw9TTJa06opAhi/ebG5/veAu+5A/PwGawtXNsGJ+AeCUjJut9w7IdmsaUAca/+r0PciDwRbaUSsRYwiOVieNs5OekIv5/XAZNJsQYL Duong.Thanh@sony.com";
            jsch.addIdentity(
                    "Test Conn", privateKey.getBytes(StandardCharsets.US_ASCII),
                    publicKey.getBytes(StandardCharsets.US_ASCII), passPhrase.getBytes());


            this.textview2.setText("privateKey add");

            session_.connect();
            this.textview2.setText("privateKey conenct");






//            String privateKey = "/home/thanh/.ssh/id_rsa";
            String privateKey1 = ".raw/id_rsa";
            jsch.addIdentity(privateKey);

            this.textview2.setText("privateKey add");

            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            this.textview2.setText("start connect 2");
            session.connect();
            System.out.println("Connected");
            this.textview2.setText("Connected");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command1);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        }catch(Exception e){
            e.printStackTrace();
        }




    }
}