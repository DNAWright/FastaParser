import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FastaSequence
{
    private static String header;
    private static String sequence;
    private static float GCRatio;

    public FastaSequence(String name, String seq)
    {
        this.header = name;
        this.sequence = seq;
    }

    public static float getGCRatio()
    {
        return GCRatio;
    }

    public static void setGCRatio(float GCRatio)
    {
        FastaSequence.GCRatio = GCRatio;
    }

    public static String getSequence()
    {
        return sequence;
    }

    public static void setSequence(String sequence)
    {
        FastaSequence.sequence = sequence;
    }

    public static String getHeader()
    {

        return header;
    }

    public static void setHeader(String header)
    {
        FastaSequence.header = header;
    }

    public static ArrayList<FastaSequence> readFastaFile(String filePath) throws Exception
    {
        ArrayList<FastaSequence> sequenceList = new ArrayList<>();
        String sequence;
        String seqName;
        float ratio;

        BufferedReader fReader = new BufferedReader(new FileReader(new File(filePath)));

        String line;
        StringBuilder build = new StringBuilder();

        while((line = fReader.readLine()) != null)
        {
            seqName = line.replace(">", "").trim();

            while ((line = fReader.readLine()) != null && !line.startsWith(">"))
            {
                build.append(line);
            }
            sequence = build.toString();
            sequenceList.add(new FastaSequence(seqName,sequence));
            build.delete(0, build.length());
        }
        return sequenceList;
    }
}
