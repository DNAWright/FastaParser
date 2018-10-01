import java.io.*;
import java.util.ArrayList;

public class Parser
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader fastaReader = new BufferedReader(new FileReader(new File("/Users/danielwright/JavaProjects/FastaParser/src/SampleSequence.txt")));
        BufferedWriter fastaWriter = new BufferedWriter(new FileWriter(new File("fastaOutput.txt")));
        String line;
        fastaWriter.write("sequenceiD \t numA \t numC \t numG \t numT \t sequence \n");
        StringBuilder build = new StringBuilder();

        while((line = fastaReader.readLine()) != null)
        {
            if(line.startsWith(">"))
            {
                if(build.length() > 0)
                {
                    int a =0, c=0, t=0, g=0;
                    String s = String.valueOf(build);
                    String[] array = s.split("");
                    for(int i=0;i < array.length; i++)
                    {
                        if(array[i].equals("A"))
                            a++;
                        if(array[i].equals("C"))
                            c++;
                        if(array[i].equals("G"))
                            g++;
                        if(array[i].equals("T"))
                            t++;

                    }
                    fastaWriter.write("\t" + a);
                    fastaWriter.write("\t" + c);
                    fastaWriter.write("\t" + g);
                    fastaWriter.write("\t" + t + "\t");
                    fastaWriter.write(build.toString());
                    fastaWriter.write("\n");
                    build.delete(0,build.length());
                }
                fastaWriter.write(line.replace(">", ""));
            }
            else
            {
                build.append(line);
            }
        }
        fastaWriter.close();
    }
}
