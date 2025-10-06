///////////////////////////////////////////////////////////////////////
//
//  Class Name :        ChatServerx
//  Description :       Server program to accept client connection,
//                      exchange chat messages, and store them in file.
//
//  Author :            Nikhil Kailas Gambhir
//  Date :              10/08/2025
//
///////////////////////////////////////////////////////////////////////


import java.io.*;
import java.net.*;

class ChatServerx
{
    ////////////////////////////////////////////////////////////////////////
    //
    //  Function Name :     main
    //  Description :       Waits for client connection, communicates 
    //                      with client, and logs chat to file.
    //  Parameters :        String Arg[] - Command line arguments
    //  Return Value :      None
    //
    ///////////////////////////////////////////////////////////////////////
    
    public static void main(String Arg[]) throws Exception
    {
        ServerSocket ssobj = new ServerSocket(5100);
        System.out.println("Marvellous server is waiting at port number 5100");
        
        Socket sobj = ssobj.accept();
        System.out.println("Marvellous Server suucesfully connected with the client");

        PrintStream pobj = new PrintStream(sobj.getOutputStream());

        BufferedReader bobj1 = new BufferedReader(new InputStreamReader(sobj.getInputStream()));

        BufferedReader bobj2 = new BufferedReader(new InputStreamReader(System.in));

        FileWriter fobj = new FileWriter("Serverxx.txt", true);  // file add
        BufferedWriter cobj = new BufferedWriter(fobj);

        System.out.println("----------------------------------------------------");
        System.out.println("Marvellous Chat messanger is ready to use");
        System.out.println("----------------------------------------------------");
   
        String str1 = null, str2 = null;

        ///////////////////////////////////////////////////////////////////////
        //
        //  Logic : Continuously reads message from client, displays it, 
        //          sends reply, and writes both messages to log file.
        //
        ///////////////////////////////////////////////////////////////////////

        while((str1 = bobj1.readLine()) != null)
        {
            System.out.println("Client says : "+str1);
            System.out.println("Enter the message for client : ");
            str2 = bobj2.readLine();
            
            // Send reply to client
            pobj.println(str2);
            pobj.flush();

            // Write chat log to file
            cobj.flush();
            cobj.write("Client :"+str1);
            cobj.newLine();
            cobj.write("Server :"+str2);
            cobj.newLine();
        }

        // Close file and socket connections

        cobj.close();
        fobj.close();
        sobj.close();

    }
}