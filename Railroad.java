/* Mina Georgoudiou
 * Dr. Steinberg
 * COP3503 Fall 2024
 * Programming Assignment 5
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Railroad 
{
    int trackNum;
    int stationNum;
    Edge[] tracks;
    ArrayList<String> stations;

    public Railroad(int n, String fName)
    {
        this.trackNum = n;
        this.tracks = new Edge[this.trackNum];
        this.stationNum = 0;
        this.stations = new ArrayList<String>();

        try
        {
            File inFile = new File(fName);

            Scanner fScan = new Scanner(inFile);

            for (int x = 0; x < this.trackNum; x++)
            {
                this.tracks[x] = new Edge(fScan.next(), fScan.next(), Integer.parseInt(fScan.next()));

                if (!stations.contains(this.tracks[x].start))
                {
                    stations.add(this.tracks[x].start);
                    stationNum++;
                }
                if (!stations.contains(this.tracks[x].end))
                {
                    stations.add(this.tracks[x].end);
                    stationNum++;
                }
            }

            Arrays.sort(this.tracks);

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
        Edge[] mst = new Edge[trackNum - 1];

        DisjointSetImproved disj = new DisjointSetImproved(stationNum);

        String output = "";

        int x, y;

        int mstCount = 0;

        int totalWeight = 0;

        for (int c = 0; c < trackNum; c++)
        {
            x = stations.indexOf(tracks[c].start);
            y = stations.indexOf(tracks[c].end);
            if (disj.find(x) != disj.find(y))
            {
                disj.union(x, y);
                mst[mstCount] = tracks[c];
                mstCount++;
                totalWeight += tracks[c].weight;
                output += tracks[c].toString();
            }
        }

        output += "The cost of the railroad is $" + Integer.toString(totalWeight) + ".";

        return output;
    }

    int getIndex(String station)
    {
        return stations.indexOf(station);
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
        
        return start + "---" + end + "\t$" + Integer.toString(weight) + "\n";
    }
}