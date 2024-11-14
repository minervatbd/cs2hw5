/* Mina Georgoudiou
 * Dr. Steinberg
 * COP3503 Fall 2024
 * Programming Assignment 5
 */

public class Railroad 
{
    int tracks;
    String file;

    public Railroad(int tracks, String file)
    {
        this.tracks = tracks;
        this.file = file;
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
}