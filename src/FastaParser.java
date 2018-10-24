import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class FastaParser
{

    public static void main(String[] args) throws Exception
    {
        List<FastaSequence> fastaList = FastaSequence.readFastaFile("/Users/danielwright/JavaProjects/FastaParser/src/SampleSequence.txt");

        for(FastaSequence fs : fastaList)
        {
            System.out.println(fs.getHeader());
            System.out.println(fs.getSequence());
        }

    }

}
