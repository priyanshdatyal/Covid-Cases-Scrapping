import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ScrapIt {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String url = "https://www.worldometers.info/coronavirus/";
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0";
        Document doc = getDocument(url,userAgent);


        System.out.print("Enter a for all countries & s for specific : ");
        String c = sc.nextLine();
        if(c.equals("a")){
            allCountriesData(doc);
        }
        else{
            printInstructions(doc);
            String cName="";
            System.out.print("Enter country name : ");
            cName += sc.nextLine();
            countryData(doc,cName);
        }

    }


    private static void printInstructions(Document doc) {
        System.out.println("\n\n--------------------------------------------------------");
        System.out.println("Kindly choose country from list only");
        System.out.println("Country List \n");
        listCountries(doc);
        System.out.println("--------------------------------------------------------\n\n");
    }

    private static void listCountries(Document doc) {
        int i = 1;
        Elements row;
        do {
        row = doc.select("#main_table_countries_today > tbody:nth-child(2) > tr:nth-child(" + i + ")");
        for (Element el : row) {
            Elements cols = el.select("td");
            System.out.print(cols.get(1).text()+", ");
            i++;
        }
    } while (i <=235);
    }

    private static Document getDocument(String url,String userAgent) {
        System.out.println("Loading ......");
        try {
            System.out.println("Loading Completed \n\n");
            return Jsoup.connect(url).userAgent(userAgent).get();
        }
        catch(Exception e) {
            System.out.println("Error occures ! "+e.getMessage());
        }
        System.out.println("Error while Loading !\n\n");
        return null;
    }

    private static int countryData(Document doc,String countryName){
        System.out.println("here");
        int i = 1;
        Elements row;do {
            row = doc.select("#main_table_countries_today > tbody:nth-child(2) > tr:nth-child(" + i + ")");
            for (Element el : row) {
                Elements cols = el.select("td");
                if((cols.get(1).text().toString().toLowerCase()).equals(countryName.toLowerCase())) {
                    printInformation(cols);
                    return 0;
                }
                i++;
            }
        } while (i <236);
        return -1;
    }
    private static void allCountriesData(Document doc){   int i = 1;
        Elements row;do {
            row = doc.select("#main_table_countries_today > tbody:nth-child(2) > tr:nth-child(" + i + ")");
            for (Element el : row) {
                Elements cols = el.select("td");
                printInformation(cols);
                i++;
            }
        } while (row != null);
    }
    private static void printInformation(Elements cols) {
        System.out.println("----------------------------------"+cols.get(1).text()+"-----------------------------------");

        System.out.println("Country  			: "+cols.get(1).text());
        System.out.println("Total Case  		: "+cols.get(2).text());
        System.out.println("New Case  			: "+cols.get(3).text());
        System.out.println("Total Death  		: "+cols.get(4).text());
        System.out.println("New Death			: "+cols.get(6).text());
        System.out.println("Total reovered		: "+cols.get(7).text());
        System.out.println("New Recoevered  	: "+cols.get(8).text());
        System.out.println("Active Cases  		: "+cols.get(9).text());
        System.out.println("Serious Cases		: "+cols.get(10).text());
        System.out.println("Cases Per Million  	: "+cols.get(11).text());
        System.out.println("Deaths Per Million  : "+cols.get(12).text());
        System.out.println("Total Tests 		: "+cols.get(13).text());
        System.out.println("Population  		: "+cols.get(14).text());
        System.out.println("---------------------------------------------------------------------------------------------\n\n");

    }
}
