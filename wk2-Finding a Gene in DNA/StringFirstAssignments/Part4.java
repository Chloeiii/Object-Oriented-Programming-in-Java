import edu.duke.*;

public class Part4{
    public void printURL(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for(String line: ur.lines()){
            String lineLower = line.toLowerCase();
            int indexOfYoutube = lineLower.indexOf("youtube");
            if (indexOfYoutube >= 0){
                String strbeforeYoutube = lineLower.substring(0, indexOfYoutube);
                int firstQuotationIndex = strbeforeYoutube.lastIndexOf("\"");
                int secondQuatationindex = lineLower.indexOf("\"", indexOfYoutube);
                System.out.println(line.substring(firstQuotationIndex+1, secondQuatationindex));
            }
        }
        System.out.println("completed");
    }
}