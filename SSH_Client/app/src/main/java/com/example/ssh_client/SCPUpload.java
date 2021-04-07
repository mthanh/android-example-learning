package com.example.ssh_client;
//
//import net.schmizz.sshj.SSHClient;
//import net.schmizz.sshj.xfer.FileSystemFile;
//
//import java.io.File;
//import java.io.IOException;
//
///** This example demonstrates uploading of a file over SCP to the SSH server. */
//public class SCPUpload {
//
//    public static void main()
//            throws IOException, ClassNotFoundException {
//        SSHClient ssh = new SSHClient();
//        ssh.loadKnownHosts();
//        ssh.connect("43.3.177.221");
//        try {
//            ssh.authPublickey(System.getProperty("thanh"));
//
//            // Present here to demo algorithm renegotiation - could have just put this before connect()
//            // Make sure JZlib is in classpath for this to work
//            ssh.useCompression();
//
//            final String src = System.getProperty("user.home") + File.separator + "test_file";
//            ssh.newSCPFileTransfer().upload(new FileSystemFile(src), "/tmp/");
//        } finally {
//            ssh.disconnect();
//        }
//    }
//
//}



import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class SCPUpload {

    /**
     * JSch Example Tutorial
     * Java SSH Connection Program
     */
    public static void main(String[] args) {
        String host="ssh.journaldev.com";
        String user="sshuser";
        String password="sshpwd";
        String command1="ls -ltr";
        try{

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

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