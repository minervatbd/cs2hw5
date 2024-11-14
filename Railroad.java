/* Mina Georgoudiou
 * Dr. Steinberg
 * COP3503 Fall 2024
 * Programming Assignment 5
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class Railroad 
{
    int trackNum;
    Edge[] tracks;

    public Railroad(int n, String fName)
    {
        this.trackNum = n;
        this.tracks = new Edge[this.trackNum];

        try
        {
            File inFile = new File(fName);

            Scanner fScan = new Scanner(inFile);

            for (int x = 0; x < this.trackNum; x++)
            {
                this.tracks[x] = new Edge(fScan.next(), fScan.next(), Integer.parseInt(fScan.next()));
                System.out.println(this.tracks[x]);
            }

            fScan.close();
        }

        catch (FileNotFoundException e)
        {
            System.err.println("Input file not found!");
            e.printStackTrace();
        }        
    }

    public String buildRailroad()
    {
        String output = "";

        return output;
    }
}

final class Edge implements Comparable<Edge>
{
    String start;
    String end;
    int weight;

    public Edge(String start, String end, int weight)
    {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override public int compareTo(Edge o) 
    {
        if (this.weight - o.weight != 0)
            return this.weight - o.weight;
        else if (this.start.compareTo(o.start) != 0)
            return this.start.compareTo(o.start);
        else
            return this.end.compareTo(o.end);
    }

    @Override
    public String toString() {
        
        return start + " " + end + " " + Integer.toString(weight);
    }
}