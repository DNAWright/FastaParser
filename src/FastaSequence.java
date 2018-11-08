import java.io.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;


public class FastaSequence
{
    private String header;
    private String sequence;
    private float gcRatio;


    public float getGCRatio()
    {
        return gcRatio;
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
            char aminoAcids[] = fs1.sequence.toCharArray();
            float counter = 0;
            for(char s : aminoAcids)
            {
                if (s == 'G' || s == 'C')
                    counter++;
            }

            fs1.gcRatio = counter/aminoAcids.length;
            sequenceList.add(fs1);

        }

        fReader.close();
        return sequenceList;
    }

    public static void writeUnique(File inFile, File outFile ) throws Exception
    {
        BufferedReader fReader = new BufferedReader(new FileReader(inFile));
        BufferedWriter fastaWriter = new BufferedWriter(new FileWriter(outFile));
        Map<String,Integer> seqMap = new LinkedHashMap<>();
        String line = fReader.readLine();

        while(line != null)
        {
            StringBuilder build = new StringBuilder();
            if(line.startsWith(">"))
            {
                line = fReader.readLine();
            }

            while(line != null && ! line.startsWith(">"))
            {
                build.append(line);
                line = fReader.readLine();
            }

            String seqKey = build.toString().trim();
            if(!seqMap.containsKey(seqKey))
            {
                seqMap.put(seqKey, 1);
            }
            else
                seqMap.replace(seqKey, seqMap.get(seqKey)+1);

        }

        seqMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x,y)-> {throw new AssertionError();},
                        LinkedHashMap::new
                ));


        for(Map.Entry<String, Integer> entry : seqMap.entrySet()) {
        System.out.println(entry.getKey()+" : "+entry.getValue());
    }

    }

}
