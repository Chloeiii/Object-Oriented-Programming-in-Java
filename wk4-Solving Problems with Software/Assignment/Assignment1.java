
/**
 * Java Programming: Solving Problems with Software -> Week4 -> MiniProject Expercise Guide.
 * 
 * @YA 
 * @2021-01-10
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class Assignment1 {
    public File getFile(int year){
        File file = new File("./us_babynames/us_babynames_by_year/yob"+year+".csv");
        /*
        if(file.exists()) { 
            System.out.println("File Exists"); 
        }
        */
        return file;
    }
    //print the number of girls names , the number of boys names and the total names in the file.
    public void totalBirths(FileResource fr){
        //int totalBirths = 0;
        //int totalBoys = 0;
        //int totalGirls = 0;
        int totalNames = 0;
        int totalGirlNames = 0;
        int totalBoyNames = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals("F")){
                totalGirlNames += 1;
            }else{
                totalBoyNames += 1;
            }
            totalNames += 1;
        }
        System.out.println("the total names = " + totalNames);
        System.out.println("the total girls names = " + totalGirlNames);
        System.out.println("the total boys names = " + totalBoyNames);
    }
    
    public void testTotalBirths (){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }   
    //returns the rank of the name in the file for the given gender,  where rank 1 is the name with the largest number of births. If the name is not in the file, then -1 is returned.
    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource(getFile(year));
        for(CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                rank += 1;
            }
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                return rank;
            }
        }        
        return -1;
    }
    
    public void testGetRank(){
        System.out.println("The rank is " + getRank(1971, "Frank", "M"));
    }
    
    //returns the name of the person in the file at this rank, for the given gender, where rank 1 is the name with the largest number of births. If the rank does not exist in the file, then “NO NAME”  is returned.
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource(getFile(year));
        for(CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                rank -= 1;
            }
            if(rank == 0){
                return rec.get(0);
            }
        }        
        return "NO NAME";
    }
    
    public void testGetName(){
        System.out.println("The name is " + getName(1982, 450, "M"));
    }
    
    //This method determines what name would have been named if they were born in a different year, based on the same popularity. 
    public void whatIsNameInYear(String name, int year, int newYear, String gender){      
        int rank = getRank(year, name, gender);
        String namenew = getName(newYear, rank, gender);      
        System.out.println(name + " born in " + year + " would be " + namenew + " if she was born in " + newYear);
    }
    
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    //this method selects a range of files to process and returns an integer, the year with the highest rank for the name and gender.  
    public int yearOfHighestRank(String name, String gender){
        int highestRank = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currank = 0;
            for(CSVRecord rec : fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    currank += 1;
                }
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                    break;
                }
            } 
            
            if(currank > 0){
                if(highestRank == -1){
                    highestRank = currank;
                }else{
                    if(currank < highestRank){
                        highestRank = currank;
                        System.out.println(f.getName());
                    }
                }
            }
        }
        return highestRank;
    }
    
    
    public void testyearOfHighestRank(){
        System.out.println("highest rank is " + yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender){
        int totalRank = 0;
        int numofFiles = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int currank = 0;
            for(CSVRecord rec : fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    currank += 1;
                }
                if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                    break;
                }
            } 
            
            if(currank == 0){
                return -1.0;
            }
            
            totalRank += currank;
            numofFiles += 1;
        }
        return (double)totalRank/numofFiles;        
    }
    
    public void testgetAverageRank(){
        System.out.println("the average rank of selected files is " + getAverageRank("Robert", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalBirths = 0;
        FileResource fr = new FileResource(getFile(year));
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)){
                break;
            }
            if (rec.get(1).equals(gender)){
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }
    
    public void testgetTotalBirthsRankedHigher(){
        System.out.println("total births whose rank is higher is " + getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }
}
