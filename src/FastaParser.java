import java.util.ArrayList;

public class FastaParser
{
    public static void main(String[] args) throws Exception
    {
        ArrayList<FastaSequence> fastaList = FastaSequence.readFastaFile("/Users/danielwright/JavaProjects/FastaParser/src/SampleSequence.txt");

        for(FastaSequence fs : fastaList)
        {
            System.out.println(fs.getHeader());
            System.out.println(fs.getSequence());
        }
    }
}
