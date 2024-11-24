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

// main class
public class Railroad 
{
    int trackNum;
    int stationNum;
    Edge[] tracks; // all edges
    ArrayList<String> stations; // all vertices

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

            // populate tracks and stations
            for (int x = 0; x < this.trackNum; x++)
            {
                // always add to tracks list
                this.tracks[x] = new Edge(fScan.next(), fScan.next(), Integer.parseInt(fScan.next()));

                // add to stations if any new
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
            
            // now tracks can be sorted
            Arrays.sort(this.tracks);

            fScan.close();
        }

        // input invalid case
        catch (FileNotFoundException e)
        {
            System.err.println("Input file not found!");
            e.printStackTrace();
        }        
    }

    // kruskal implementation, edges already sorted
    public String buildRailroad()
    {
        // minimum spanning tree edge list
        Edge[] mst = new Edge[trackNum - 1];

        // disjoint set with stationnum entries
        DisjointSetImproved disj = new DisjointSetImproved(stationNum);

        String output = "";

        // index ints
        int x, y, mstCount = 0;

        int totalWeight = 0;

        // kruskal loop
        for (int c = 0; c < trackNum; c++)
        {
            x = stations.indexOf(tracks[c].start);
            y = stations.indexOf(tracks[c].end);
            // check for a connection
            if (disj.find(x) != disj.find(y))
            {
                // if not, connect them
                disj.union(x, y);
                mst[mstCount] = tracks[c];
                mstCount++; // and increment

                // for output
                totalWeight += tracks[c].weight;
                output += tracks[c].toString();
            }
        }

        // last part of output
        output += "The cost of the railroad is $" + Integer.toString(totalWeight) + ".";

        return output;
    }
}

// for sorting and string output
final class Edge implements Comparable<Edge>
{
    String start;
    String end;
    int weight;

    // constructor
    public Edge(String start, String end, int weight)
    {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    // sorting interface, based on weight
    @Override public int compareTo(Edge o) 
    {
        if (this.weight - o.weight != 0)
            return this.weight - o.weight;
        else if (this.start.compareTo(o.start) != 0)
            return this.start.compareTo(o.start);
        else
            return this.end.compareTo(o.end);
    }

    // string output
    @Override
    public String toString() {
        // formatted lexically
        if (start.compareTo(end) <= 0)
            return start + "---" + end + "\t$" + Integer.toString(weight) + "\n";
        else
            return end + "---" + start + "\t$" + Integer.toString(weight) + "\n";
    }
}