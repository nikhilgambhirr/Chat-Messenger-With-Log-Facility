//////////////////////////////////////////////////////////////////////
//
//  Class Name :        ChatClientx
//  Description :       Client program to connect with server, 
//                      exchange messages and store chat in file.
//
//  Author :            Nikhil Kailas Gambhir
//  Date :              00/00/2025
//
///////////////////////////////////////////////////////////////////////

import java.io.*;
import java.net.*;

class ChatClientx
{
    ///////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     main
    //  Description :       Connects to server, handles chat communication 
    //                      and saves conversation in a log file.
    //  Parameters :        String Arg[] - Command line arguments
    //  Return Value :      None
    //
    ////////////////////////////////////////////////////////////////////////
    public static void main(String Arg[]) throws Exception
    {
        System.out.println(" Client is ready to coonect with server");

        Socket sobj = new Socket("localhost",5100);
        System.out.println(" Client is succesfully connect with server");


        PrintStream pobj = new PrintStream(sobj.getOutputStream());

        BufferedReader bobj1 = new BufferedReader(new InputStreamReader(sobj.getInputStream()));

        BufferedReader bobj2 = new BufferedReader(new InputStreamReader(System.in));

        FileWriter fobj = new FileWriter("Clientxx.txt", true);
        BufferedWriter cobj = new BufferedWriter(fobj);

        System.out.println("----------------------------------------------------");
        System.out.println(" Chat messanger is ready to use");
        System.out.println("----------------------------------------------------");
    
        String str1 = null, str2 = null;

        ///////////////////////////////////////////////////////////////////////
        //
        //  Logic : Reads user message, sends to server, receives reply,
        //           displays it and writes both to file until "end".
        //
        ///////////////////////////////////////////////////////////////////////

        while(!(str1 = bobj2.readLine()).equals("end"))
        {
            pobj.println(str1);
            str2 = bobj1.readLine();
            System.out.println("Server says : "+str2);
            System.out.println("Enter message for server : ");
             pobj.println(str2);
            pobj.flush();
            cobj.flush();
            cobj.write("Client :"+str1);
            cobj.newLine();
            cobj.write("Server :"+str2);
            cobj.newLine();
        }
        cobj.close();
        fobj.close();
        sobj.close();
    }

}
