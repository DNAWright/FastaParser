import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class FastaSequence
{
    private String header;
    private String sequence;
    private float GCRatio;


    public float getGCRatio()
    {
        return GCRatio;
    }

    public String getSequence()
    {
        return sequence;
    }

    public String getHeader()
    {

        return header;
    }

    public static List<FastaSequence> readFastaFile(String filePath) throws Exception
    {
        List<FastaSequence> sequenceList = new ArrayList<>();
        BufferedReader fReader = new BufferedReader(new FileReader(new File(filePath)));
        String line = fReader.readLine();

        while(line != null)
        {
            StringBuilder build = new StringBuilder();
            FastaSequence fs1 = new FastaSequence();
            if(line.startsWith(">"))
            {
                fs1.header = (line.replace(">", "").trim());
                line = fReader.readLine();
            }

            while(line != null && ! line.startsWith(">"))
            {
                build.append(line);
                line = fReader.readLine();
            }

            fs1.sequence = build.toString().trim();
            sequenceList.add(fs1);

        }

        fReader.close();
        return sequenceList;
    }
}
