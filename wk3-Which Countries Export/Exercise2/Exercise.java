
/**
 * Week 3 > Programming Exercise : Parsing Weather Data
 * 
 * @YA 
 * @2020-12
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class Exercise {
    public CSVRecord compareColdest(CSVRecord current, CSVRecord coldest){
        if (coldest == null){
            coldest = current;
        }else{   
            double curTemp = Double.parseDouble(current.get("TemperatureF"));
            double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
            if((int)curTemp != -9999){
                if((int)coldestTemp == -9999){
                    coldest = current;
                }else{
                    if(curTemp < coldestTemp){
                        coldest = current;
                    }
                }
            }  
        }
        return coldest;
    }
    //returnes the CSVRecord with the coldest termperature in the file
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest = null;
        for (CSVRecord current : parser){
            coldest = compareColdest(current, coldest);
        }
        return coldest;
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("The coldest temperature is: " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));     
    }

    //return a string that is the name of the file from selected files that has the coldest temperature
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        File fColdest = null;
        for (File fCurrent : dr.selectedFiles()){            
            if(fColdest == null){
                fColdest = fCurrent;
            }else{
                FileResource frColdest = new FileResource(fColdest);
                CSVParser parserColdest = frColdest.getCSVParser();
                CSVRecord recordColdest = coldestHourInFile(parserColdest);

                FileResource frCurrent = new FileResource(fCurrent);
                CSVParser parserCurrent = frCurrent.getCSVParser();
                CSVRecord recordCurrent = coldestHourInFile(parserCurrent);

                CSVRecord record = compareColdest(recordCurrent, recordColdest);

                if(record == recordCurrent){
                    fColdest = fCurrent;
                }
            }

        }

        //print out the info
        FileResource frColdest = new FileResource(fColdest);
        CSVParser parserColdest = frColdest.getCSVParser();
        CSVRecord recordColdest = coldestHourInFile(parserColdest); 
        System.out.println("Coldest day was in file "+ fColdest.getName());
        System.out.println("Coldest temperature on that day was "+ recordColdest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        parserColdest = frColdest.getCSVParser();
        for(CSVRecord record : parserColdest){
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));   
        }

        return fColdest.getName();
    }

    public void testFileWithColdestTemperature(){
        String FileNameWithColdestTemp = fileWithColdestTemperature();
    }

    public CSVRecord compareLowestHum(CSVRecord current, CSVRecord lowestHum){
        //first record
        /*
        if (lowestHum == null){
        lowestHum = current;
        }else if(lowestHum.get("Humidity") == "N/A"){
        lowestHum = current;
        }else{ 
        // set humidity to inifinity if it's N/A
        if(current.get("Humidity") != "N/A"){
        double curH = Double.parseDouble(current.get("Humidity"));
        double lowestH = Double.parseDouble(lowestHum.get("Humidity"));
        if(curH < lowestH){
        lowestHum = current;
        }
        }           
        }
         */

        if(current != null){
            if(current.get("Humidity").equals("N/A") == false){
                if (lowestHum == null){
                    lowestHum = current;
                }else if (lowestHum.get("Humidity").equals("N/A")){
                    lowestHum = current;
                }else{
                    double curH = Double.parseDouble(current.get("Humidity"));
                    double lowestH = Double.parseDouble(lowestHum.get("Humidity"));
                    if(curH < lowestH){
                        lowestHum = current;
                    }

                }
            }
        } 
        return lowestHum;
    }
    //returns the CSVRecord that has the lowest humidity.
    //If there is a tie, then return the first such record that was found.
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHum = null;
        for (CSVRecord current : parser){
            lowestHum = compareLowestHum(current, lowestHum);
        }
        return lowestHum;
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHum = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + lowestHum.get("Humidity") + " at " + lowestHum.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestRecord = null;
        DirectoryResource dr = new DirectoryResource();
        //File fColdest = null;
        for (File fCurrent : dr.selectedFiles()){   
            FileResource frCurrent = new FileResource(fCurrent);
            CSVParser parserCurrent = frCurrent.getCSVParser();
            CSVRecord currentRecord = lowestHumidityInFile(parserCurrent);

            if(lowestRecord == null){
                lowestRecord = currentRecord;
            }else{
                lowestRecord = compareLowestHum(currentRecord, lowestRecord);
            }

        }
        return lowestRecord;
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord lowestHum = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHum.get("Humidity") + " at " + lowestHum.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser){
        double totaltemp = 0.0;
        int num = 0;
        for (CSVRecord record : parser){
            totaltemp += Double.parseDouble(record.get("TemperatureF"));
            num += 1;
        }        

        if(num == 0){
            return totaltemp;
        }
        return totaltemp/num;
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avg);
    }

    //eturns a double that represents the average temperature of only those temperatures when the humidity was greater than or equal to value
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double totaltemp = 0.0;
        int num = 0;
        for (CSVRecord record : parser){
            double curTemp = Double.parseDouble(record.get("TemperatureF"));
            String curHumidity = record.get("Humidity");
            if(curHumidity != "N/A"){
                double curH = Double.parseDouble(curHumidity);
                if(curH >= value){
                    totaltemp += curTemp;
                    num += 1;
                }
            } 
        }        

        if(num == 0){
            return totaltemp;
        }
        return totaltemp/num;
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avg == 0.0){
            System.out.println("No temperatures with that humidity");
        }else{
            System.out.println("Average Temp when high Humidity is " + avg);
        }
    }

}
