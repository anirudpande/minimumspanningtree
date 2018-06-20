package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MSTMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Enter 1 to use hardcoded values. Enter 2 to accept input from the user.");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		int input=Integer.parseInt(s);
		//graph[][] is a graph that I have hard coded
		int graph[][]={{0,1,7,10,0,0,0,0,0,0},{1,0,5,0,8,0,0,0,0,0},{7,5,0,11,9,0,0,0,0,0},{10,0,11,0,3,0,0,0,0,0},{0,8,9,3,0,0,0,0,0,0},{0,0,0,0,0,0,4,2,1,5},{0,0,0,0,0,4,0,6,3,0},{0,0,0,0,0,2,6,0,0,6},{0,0,0,0,0,1,3,0,0,2},{0,0,0,0,0,5,0,2,2,0}};
		if(input==2)//If the user enters 2 then we accept input from the user instead of using the hardcoded graph[][]
		{
			//All code written inside this if condition is used to accept input from the user
			int ei,ej,wt;
			System.out.println("Enter number of nodes:");
			br=new BufferedReader(new InputStreamReader(System.in));
			s=br.readLine();
			int length=Integer.parseInt(s);
			int graph1[][]=new int[length][length];
			graph=graph1;
			for(int i=0;i<length;i++)
			{
				for(int j=0;j<length;j++)
					graph[i][j]=0;
			}
		
			while(true)
			{
				System.out.println("Enter edges in the format node1,node2(weight)");
				System.out.println("For eg: 0,1(10) indicates that there is an edge between nodes 0 and 1(and viceversa) and weight=10");
				System.out.println("Enter \"no\" if you don't want to insert any more edges");
				br=new BufferedReader(new InputStreamReader(System.in));
				s=br.readLine();
				if(s.equalsIgnoreCase("no"))
					break;
				s=s.replaceAll(" ", "");
				Scanner fi = new Scanner(s);
				fi.useDelimiter("[^\\p{Alnum}\\-]"); 
				ei=fi.nextInt();
				ej=fi.nextInt();
				if(ei>=length||ej>=length)
				{
					System.out.println("ERROR:Please enter nodes less than "+length);
					continue;
				}
				wt=fi.nextInt();
				graph[ei][ej]=wt;
				graph[ej][ei]=wt;
			}	
		}
		//First the adjacency matrix of the input graph is displayed
		System.out.println("Adjacency matrix of graph is: ");
		for(int i=0;i<graph.length;i++)
		{
			for(int j=0;j<graph.length;j++)
			{
				System.out.print(graph[i][j]+"  ");
				
			}
			System.out.println();
		}
		int mst[][]=new int[graph.length][graph.length];//This matrix will store the adjaceny matrix of the MST
		int edgesLeft[][]=new int[graph.length][graph.length];//This matrix stores the edges which haven't been visited yet
		boolean stop=false;//stop is a boolean value which becomes true when all the vertices have been visited
		int[] cluster=new int[graph.length];//cluster variable stores the cluster id of each node
		for(int i=0;i<graph.length;i++)
			cluster[i]=i;//Initially each node is considered as a separate cluster
		int max=graph[0][0],min;
		int x=-1,y=-1;
		int sum=0;
		for(int i=0;i<graph.length;i++)
		{
			
			for(int j=0;j<graph.length;j++)
			{
				mst[i][j]=0;
				edgesLeft[i][j]=graph[i][j];
				if(graph[i][j]>max)
				{
					max=graph[i][j];
				}
			}
		}
		
		min=max+1;
		System.out.print("The edges of MST(with weights) are:");
		int d=0;
		while(!stop)
		{
			min=max+1;
			for(int i=0;i<graph.length;i++)
			{
				for(int j=0;j<graph.length;j++)
				{
					//The below if condition is used to find the minimum amongst edges which are not already present in the MST
					if(edgesLeft[i][j]<min&&edgesLeft[i][j]!=0&&cluster[i]!=cluster[j])
					{
						min=edgesLeft[i][j];
						x=i;
						y=j;
						
					}
				}
			}
			
			if(min==max+1)
			{
				stop=true;
				break;
			}
			sum+=min;
			if(d==0)
			System.out.print(x+","+y+"("+min+")");
			else
				System.out.print("  "+x+","+y+"("+min+")");
			int k=cluster[y];
			for(int i=0;i<graph.length;i++)
			{
				if(cluster[i]==k)
				{
					cluster[i]=cluster[x];
					
				}
			}
			cluster[y]=cluster[x];
			
			
			for(int i=0;i<graph.length;i++)
			{
				for(int j=0;j<graph.length;j++)
				{
					if((i==x&&j==y)||(i==y&&j==x))
					{
						mst[i][j]=graph[i][j];
						edgesLeft[i][j]=0;
						
					}
				}
			}
			d++;
			
		}
		System.out.println("\nSum of weights of edges of MST="+sum);
		System.out.println("Adjacency matrix of MST is:");
		for(int i=0;i<graph.length;i++)
		{
			for(int j=0;j<graph.length;j++)
			{
				System.out.print(mst[i][j]+"  ");
			}
			System.out.println();
		}	
	}
}
