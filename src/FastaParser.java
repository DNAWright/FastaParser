import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class FastaParser
{

    public static void main(String[] args) throws Exception
    {

        File try2 = new File("/Users/danielwright/JavaProjects/FastaParser/src/SampleSequence2.txt");
        File out2 = new File("TrialOutput.txt");
        // List<FastaSequence> fastaList = FastaSequence.readFastaFile("/Users/danielwright/JavaProjects/FastaParser/src/SampleSequence2.txt");

        FastaSequence hi2 = new FastaSequence();

        hi2.writeUnique(try2, out2);

        /** for(FastaSequence fs : fastaList)
        {
            System.out.println(fs.getHeader());
            System.out.println(fs.getSequence());
            System.out.println(fs.getGCRatio());
        } **/

    }

}
