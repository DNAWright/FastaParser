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
        this.setHeader(name);
        this.setSequence(seq);
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
        String sequence;
        String seqName;
        float ratio;

        ArrayList<FastaSequence> fastaList = new ArrayList<>();
        BufferedReader fastaReader = new BufferedReader(new FileReader(new File(filePath)));

        String line;
        StringBuilder build = new StringBuilder();

        while((line = fastaReader.readLine()) != null)
        {

                seqName = line.replace(">", "").trim();
                while((line = fastaReader.readLine()) != null && !line.startsWith(">"))
                {
                    build.append(line);
                }
                sequence = build.toString();
                fastaList.add(new FastaSequence(seqName,sequence));

            build.delete(0,build.length());
            break;
        }
            return fastaList;
    }
}
