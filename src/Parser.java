import java.io.*;
import java.util.ArrayList;

public class Parser
{
    public static void main(String[] args) throws Exception
    {
        // Create an input and output files and all the data structures needed

        BufferedReader fastaReader = new BufferedReader(new FileReader(new File("/Users/danielwright/JavaProjects/FastaParser/src/SampleSequence.txt")));
        BufferedWriter fastaWriter = new BufferedWriter(new FileWriter(new File("fastaOutput.txt")));

        ArrayList<String> seqNames = new ArrayList<>();
        ArrayList<String> seqArray = new ArrayList<>();

        String line;
        StringBuilder build = new StringBuilder();

        // Loop through the File and store values in String Array Lists
        while((line = fastaReader.readLine()) != null)
        {
            if(line.startsWith(">"))
            {
                seqNames.add(line.replace(">","").trim());

                if(build.length() > 0)
                {
                    seqArray.add(build.toString());
                    build.delete(0,build.length());
                }
            }
            else
                build.append(line);
        }

        // Nested loop to write everything to local file
        seqArray.add(build.toString());
        fastaWriter.write("sequenceiD \t numA \t numC \t numG \t numT \t sequence \n");
        for(int i=0;i < seqNames.size();i++)
        {
            fastaWriter.write(seqNames.get(i) + "\t");

            int a =0, c=0, t=0, g=0;
            String s = seqArray.get(i);
            String[] array = s.split("");

            for(int j=0;j < array.length; j++)
            {
                if(array[j].equals("A"))
                    a++;
                if(array[j].equals("C"))
                    c++;
                if(array[j].equals("G"))
                    g++;
                if(array[j].equals("T"))
                    t++;
            }
            fastaWriter.write("\t" + a);
            fastaWriter.write("\t" + c);
            fastaWriter.write("\t" + g);
            fastaWriter.write("\t" + t + "\t");
            fastaWriter.write(seqArray.get(i));
            fastaWriter.write("\n");
        }

        fastaWriter.close();
    }
}
