
/**
 * Programming Exercise: Parsing Export Data
 * 
 * @YA
 * @2020-12
 */
import edu.duke.*;
import org.apache.commons.csv.*; 
public class Exercise {
    //returns a string of information about the country or returns “NOT FOUND” if there is no information about the country
    public String countryInfo(CSVParser parser, String country){
        String result = "NOT FOUND";
        for(CSVRecord record: parser){
            //if we find the country, update the result
            if(record.get("Country").equals(country)){
                result = country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return result;
    }
    
    //prints the names of all the countries that have both exportItem1 and exportItem2 as export items
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){  
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }  
    }
    
    //returns the number of countries that export exportItem
    public int numberOfExports(CSVParser parser, String exportItem){
        int result = 0;
        for(CSVRecord record: parser){  
            String exports = record.get("Exports");
            if(exports.contains(exportItem)){
                result += 1;
            }
        }          
        return result;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record: parser){  
            String value = record.get("Value (dollars)");
            if(value.length() > amount.length()){
                System.out.println(record.get("Country") + " " + value);
            }
        }         
    
    }
    
    public void test(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        
        //Each time you want to use the parser with another method, you will need to reset the parser by calling fr.getCSVParser() again to get a new parser.
        //parser = fr.getCSVParser();
        
        //parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "gold", "diamonds");
        
        //parser = fr.getCSVParser();
        //System.out.println(numberOfExports(parser, "sugar"));
        
        //parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
    }
}
