package inet.parsing;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> resumeURLlist = new ArrayList<String>();
        ArrayList<Integer> resumeNumberList = new ArrayList<Integer>();
        for(int i = 1; i < 3; i++) {
            String addres = "http://rabota.e1.ru/resume?page=" + i + "&search_key=00iul4s&limit=100&order_by[]=orderby_date&order_dir[]=desc";
            String filename = "resume" + i + ".html";

            URL url = new URL(addres);
            InputStream inStream = url.openStream();

            Scanner in = new Scanner(inStream);

            while (in.hasNextLine()) {
                list.add(in.nextLine().trim());
            }
            /*System.out.println(list.get(810));
            if (!list.get(810).equals("<h3>Резюме не доступно</h3>")) {
                File f = new File(filename);
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
                for(String s : list)
                    out.println(s);
            }*/

            for (i = 0; i < list.size(); i++) {

                if(list.get(i).equals("<span class=\"ra-toolbar__link-dotted\">Ссылка</span>")) {
                    String s = list.get(i + 3);
                    System.out.println(s.substring(56, s.length() - 11));
                    resumeURLlist.add(s.substring(56, s.length() - 11));
                }
                //list.get(i + 3).substring(30, 40);
            }
        }

        for(String resumeURL : resumeURLlist) {
            String addres = resumeURL;
            String filename = "resume" + resumeURL.substring(32) + ".html";
            //System.out.println(filename);

            URL url = new URL(addres);
            InputStream inStream = url.openStream();

            Scanner in = new Scanner(inStream);

            /*while (in.hasNextLine()) {
                list.add(in.nextLine().trim());
            }
            System.out.println(list.get(810));*/
            /*if (!list.get(810).equals("<h3>Резюме не доступно</h3>")) {
                File f = new File(filename);
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
                for(String s : list)
                    out.println(s);
            }*/
            File f = new File(filename);
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
            while(in.hasNextLine()) {
                out.println(in.nextLine());
                out.flush();
            }
        }

        /*String addres = "http://rabota.e1.ru/resume/p?id=47185";
        String filename = "resume1.html";
        URL url = new URL(addres);
        InputStream inStream = url.openStream();
        File f = new File(filename);
        Scanner in = new Scanner(inStream);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f)));
        while (in.hasNextLine()) {
            out.println(in.nextLine());
            out.flush();
            //System.out.println(in.nextLine());
        }*/
    }
}
